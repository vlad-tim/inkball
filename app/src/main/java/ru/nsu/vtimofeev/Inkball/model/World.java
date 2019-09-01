package ru.nsu.vtimofeev.Inkball.model;

import android.os.Handler;
import android.util.Log;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.*;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;
import ru.nsu.vtimofeev.Inkball.activities.HandlerCodes;
import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class World implements WorldInitialization{

    public static final float WIDTH = 22;
    public static final float HEIGHT = 13;

    public static final int MAX_MULTILINES = 10;

    public static final int WALLS_IN_ROW = (int) (WIDTH / Wall.defaultSize);
    public static final int WALLS_IN_COLUMN = (int) (HEIGHT / Wall.defaultSize);

    private WorldCollisionProcessor collisionProcessor = new WorldCollisionProcessor();

    private BallGenerator ballGenerator;

    private List<Ball> balls = new ArrayList<Ball>();
    private List<Wall> walls = new ArrayList<Wall>();
    private List<Hole> holes = new ArrayList<Hole>();
    private List<Accelerator> accels = new ArrayList<Accelerator>();
    private List<TimerWall> timerWalls = new ArrayList<TimerWall>();
    private List<Bar> bars = new ArrayList<Bar>();
    private List<Puzzle> puzzles = new ArrayList<Puzzle>();

    private float criticalDistanceSquared =
            (Ball.radius + MultiLine.defaultRadius) *
                    (Ball.radius + MultiLine.defaultRadius);

    private SpartialHashGridForSegments spartialHashGrid = new SpartialHashGridForSegments(WIDTH,
            HEIGHT, 8, 4,
            MultiLine.defaultRadius);
    private SpartialHashGridForWalls spartialHashGridWalls = new SpartialHashGridForWalls(
            WIDTH + 2*Wall.defaultSize,
            HEIGHT + 2*Wall.defaultSize,
            3, 3);

    private LinkedList<MultiLine> multiLines = new LinkedList<MultiLine>();
    private ArrayList<Segment> segments = new ArrayList<Segment>(MultiLine.MAX_SEGMENTS);

    private boolean timeIsUp = false;
    private boolean ballInWrongHole = false;
    private boolean blockedForDrawing = false;

    public static final float lowTime = 10f;
    private float time = 99f;

    private Handler handler;

    private boolean lost = false;
    private boolean win = false;

    public boolean isWin() {
        return win;
    }

    public boolean isLost() {
        return lost;
    }

    public World(LevelInitializer levelInitializer, Handler handler) {

        initialize(levelInitializer);
        this.handler = handler;
    }

    public void initialize(LevelInitializer levelInitializer) {
        levelInitializer.initialize(this);
        fillHashGridForWalls();
    }

    private void fillHashGridForWalls() {

        spartialHashGridWalls.clear();

        for (int i = 0; i < walls.size(); ++i) {
            spartialHashGridWalls.insertGameObject(walls.get(i));
        }

        for (int i = 0; i < timerWalls.size(); ++i) {
            spartialHashGridWalls.insertGameObject(timerWalls.get(i));
        }

        for (int i = 0; i < bars.size(); ++i) {
            spartialHashGridWalls.insertGameObject(bars.get(i));
        }

        for (int i = 0; i < puzzles.size(); ++i) {
            spartialHashGridWalls.insertGameObject(puzzles.get(i));
        }
    }

    public void update(float deltaTime) {

        if (win || lost) {
            return;
        }

        ballGenerator.update(deltaTime);

        updateTimerWalls(deltaTime);
        updatePuzzles();
        updateBallPositions(deltaTime);
        processCollisionWithSquareObjects();
        processCollisionWithMultilines();
        processCollisionBetweenBalls();

        updateTime(deltaTime);
        checkWinLostState();
    }

    private void updateTimerWalls(float deltaTime) {
        for (int i = 0; i < timerWalls.size(); ++i) {
            TimerWall timerWall = timerWalls.get(i);
            setUpdateState(timerWall);
            timerWall.update(deltaTime);
        }
    }

    private void setUpdateState(TimerWall timerWall) {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (overlapBallAndSquareObject(ball, timerWall)) {
                timerWall.setCanChangeState(false);
                return;
            }
        }
        timerWall.setCanChangeState(true);
    }

    private void updatePuzzles() {
        for (int i = 0; i < puzzles.size(); ++i) {
            Puzzle puzzle = puzzles.get(i);
            setUpdateState(puzzle);
            puzzle.update();
        }
    }

    private void setUpdateState(Puzzle puzzle) {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (overlapBallAndSquareObject(ball, puzzle)) {
                puzzle.setCanChangeState(false);
                return;
            }
        }
        puzzle.setCanChangeState(true);
    }

    private void updateBallPositions(float deltaTime) {

        resetAffections();
        processAffectionsByAccels();
        processAffectionsByHoles();

        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            ball.update(deltaTime);
        }
    }

    private void resetAffections() {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            ball.setAffected(false);
        }
    }



    private void processAffectionsByAccels() {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);

            for (int j = 0; j < accels.size(); ++j) {
                Accelerator accel = accels.get(j);
                if (AccelAffectsBall(accel, ball)) {
                    accel.affectBall(ball);
                    //Break: assume that only one accel affects a ball at a time
                    break;
                }
            }
        }
    }

    private void processAffectionsByHoles() {

        //Affections By Holes
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);

            //TODO Nonoptimal code
            for (int j = 0; j < holes.size(); ++j) {
                Hole hole = holes.get(j);
                if (holeAffectsBall(hole, ball)) {
                    hole.affectBall(ball);
                    //Break: assume that only one hole affects a ball at a time
                    break;
                }
            }

            for (int j = 0; j < holes.size(); ++j) {
                Hole hole = holes.get(j);
                if (holeConsumesBall(hole, ball)) {
                    balls.remove(ball);
                    if (hole.getColor()!= Color.NONE && ball.getColor() != Color.NONE
                            && hole.getColor() != ball.getColor()) {
                        ballInWrongHole = true;
                    }

                    changePuzzles(hole.getColor());
                }
            }
        }
    }

    private boolean holeAffectsBall(Hole hole, Ball ball) {
        float criticalDistanceSquared = (Ball.radius + Hole.defaultSize/3) *
                (Ball.radius + Hole.defaultSize/3);
        return (hole.position.distSquared(ball.position) < criticalDistanceSquared);
    }

    private boolean AccelAffectsBall(Accelerator accel, Ball ball) {
        float criticalDistSquared = Accelerator.defaultSize/2 * Accelerator.defaultSize/2;
        return (accel.position.distSquared(ball.position) < criticalDistSquared);
    }

    private boolean holeConsumesBall(Hole hole, Ball ball) {
        float criticalDistanceSquared = (Ball.radius /2f)*(Ball.radius /2f);
        return (hole.position.distSquared(ball.position) < criticalDistanceSquared);
    }

    private void changePuzzles(Color color) {
        for (int i = 0; i < puzzles.size(); ++i) {
            Puzzle puzzle = puzzles.get(i);
            if (puzzle.getColor() == color) {
                puzzle.changeState();
            }
        }
    }

    private void processCollisionWithSquareObjects() {

        for (int i = 0; i < balls.size(); i++) {

            Ball ball = balls.get(i);
            GameObject objectToCollide = null;
            float distanceToWallToCollide = Float.MAX_VALUE;

            //Walls
            List<GameObject> wallsCollide = spartialHashGridWalls.getPotentialColliders(ball, Wall.class);
            for (int j = 0; j < wallsCollide.size(); j++) {
                Wall wall = (Wall) wallsCollide.get(j);
                if (overlapBallAndSquareObject(ball, wall) &&
                        (collisionProcessor.getCollisionDistance(ball, wall)
                                < distanceToWallToCollide)) {

                    objectToCollide = wall;
                    distanceToWallToCollide = collisionProcessor.getCollisionDistance(ball, wall);
                }
            }

            //Timer walls
            List<GameObject> timerWalls = spartialHashGridWalls.getPotentialColliders(ball,
                    TimerWall.class);
            for (int j = 0; j < timerWalls.size(); j++) {
                TimerWall timerWall = (TimerWall) timerWalls.get(j);
                if (overlapBallAndSquareObject(ball, timerWall) &&
                        timerWall.getState() == TimerWallState.OPAQUE &&
                        (collisionProcessor.getCollisionDistance(ball, timerWall)
                                < distanceToWallToCollide)) {
                    objectToCollide = timerWall;
                    distanceToWallToCollide = collisionProcessor.getCollisionDistance(ball, timerWall);
                }
            }

            //Bars
            List<GameObject> bars = spartialHashGridWalls.getPotentialColliders(ball,
                    Bar.class);
            for (int j = 0; j < bars.size(); j++) {
                Bar bar = (Bar) bars.get(j);
                if (overlapBallAndSquareObject(ball, bar) &&
                        bar.shouldInteractWithBall(ball) &&
                        (collisionProcessor.getCollisionDistance(ball, bar)
                                < distanceToWallToCollide)) {

                    objectToCollide = bar;
                    distanceToWallToCollide = collisionProcessor.getCollisionDistance(ball, bar);
                }
            }

            //Puzzles
            List<GameObject> puzzles = spartialHashGridWalls.getPotentialColliders(ball,
                    Puzzle.class);
            for (int j = 0; j < puzzles.size(); j++) {
                Puzzle puzzle = (Puzzle) puzzles.get(j);
                if (puzzle.getState() == PuzzleState.LOCKED &&
                        overlapBallAndSquareObject(ball, puzzle) &&
                        (collisionProcessor.getCollisionDistance(ball, puzzle)
                                < distanceToWallToCollide)) {
                    objectToCollide = puzzle;
                    distanceToWallToCollide = collisionProcessor.getCollisionDistance(ball, puzzle);
                }
            }

            //If we are going to collide
            if (objectToCollide != null) {
                collisionProcessor.processCollisionWithSquareGameObject(ball, objectToCollide);

                if (objectToCollide instanceof Wall){

                    Wall wall = (Wall)objectToCollide;

                    if (!wall.isCracked()) {
                        changeColorIfAbleTO(wall, ball);
                    }
                    else {
                        destroyWallIfAbleTo(wall, ball);
                    }
                }
            }
        }
    }

    private void changeColorIfAbleTO(Wall wall, Ball ball) {
        Color wallColor = wall.getColor();
        if (wallColor != Color.NONE) {
            ball.setColor(wallColor);
        }
    }

    private void destroyWallIfAbleTo(Wall wall, Ball ball) {
        Color wallColor = wall.getColor();
        Color ballColor = ball.getColor();
        if (wallColor == Color.NONE || wallColor == ballColor) {
            walls.remove(wall);
            spartialHashGridWalls.removeGameObject(wall);
        }
    }

    private void processCollisionWithMultilines() {
        for (int i = 0; i < balls.size(); i++) {

            Ball ball = balls.get(i);

            MultiLine multiLineToRemove = null;
            List<Segment> segments = spartialHashGrid.getPotentialColliders(ball);

            for (int j = 0; j < segments.size(); j++) {

                Segment segment = segments.get(j);

                if (overlapSegmentBall(segment, ball)) {
                    MultiLine multiLine = segment.getMultiLine();
                    ball.reflectFromSegment(segment);
                    multiLineToRemove = multiLine;
                    break;
                }
            }

            segments.clear();

            if (multiLineToRemove != null) {

                if (multiLineToRemove.equals(multiLines.getLast())) {
                    //Can't draw until finger up and down again
                    blockedForDrawing = true;
                }
                removeMultiline(multiLineToRemove);
            }
        }
    }

    private void removeMultiline(MultiLine multiLineToRemove) {
        multiLineToRemove.dispose();
        multiLines.remove(multiLineToRemove);
    }

    private boolean overlapSegmentBall(Segment segment, Ball ball) {

        Circle circle = (Circle) ball.bounds;
        return segment.distanceToVectorSquared(circle.center) < criticalDistanceSquared;

    }

    private void processCollisionBetweenBalls() {

        for (int i = 0; i < balls.size(); i++) {
            Ball firstBall = balls.get(i);
            for (int j = 0; j < balls.size(); j++) {
                Ball secondBall = balls.get(j);
                if (firstBall.overlapsWithAnotherBall(secondBall)) {
                    collisionProcessor.processCollision(firstBall, secondBall);
                }
            }
        }
    }

    private boolean overlapBallAndSquareObject(Ball ball, GameObject squareObject) {
        return OverlapTester.overlapCircleRectangle((Circle) ball.bounds,
                (Rectangle) squareObject.bounds);
    }

    public void addPointToNewMultiline(Vector2 point) {

        blockedForDrawing = false;

        MultiLine newMultiline = null;
        if (multiLines.size() == MAX_MULTILINES) {
            newMultiline = multiLines.poll();
            newMultiline.dispose();
        } else {
            newMultiline = new MultiLine(spartialHashGrid);
        }
        multiLines.add(newMultiline);
        newMultiline.addPoint(point);
    }

    public void addPointToLastMultiline(Vector2 point) {

        if (blockedForDrawing) {
            return;
        }
        if (!multiLines.isEmpty()) {
            MultiLine lastMultiLine = multiLines.getLast();
            lastMultiLine.addPoint(point);

            Segment lastSegment = lastMultiLine.getLastSegment();
            processImmediateStrikeWithSegment(lastSegment);
        }
    }

    //If there is a ball that overlaps the segment, segment pushes the ball
    private void processImmediateStrikeWithSegment(Segment lastSegment) {
        for (int i = 0; i < balls.size(); ++i) {
            Ball ball = balls.get(i);

            if (ball.overlapsSegment(lastSegment)) {

                ball.reflectFromSegmentDirect(lastSegment);

                removeMultiline(multiLines.getLast());
                blockedForDrawing = true;

                return;
            }
        }
    }

    public ArrayList<Segment> getAllSegments() {
        segments.clear();

        for (int i = 0; i < multiLines.size(); ++i) {
            MultiLine multiLine = multiLines.get(i);
            ArrayList<Segment> particularSegments = multiLine.getSegments();
            for (int j = 0; j < particularSegments.size(); ++j) {
                segments.add(particularSegments.get(j));
            }
        }
        return segments;
    }

    public List<Wall> getWalls() {
        return walls;
    }


    public List<Hole> getHoles() {
        return holes;
    }

    public List<Accelerator> getAccels() {
        return accels;
    }

    public List<TimerWall> getTimerWalls() {
        return timerWalls;
    }

    private void updateTime(float deltaTime) {
        if (!timeIsUp) {
            time -= deltaTime;

            if (time < 0f) {
                time = 0f;
                timeIsUp = true;
                lost = true;
            }
        }
    }

    public float getTime() {
        return time;
    }

    @Override
    public void setBallGenerator(BallGenerator ballGenerator) {
        this.ballGenerator = ballGenerator;
    }

    @Override
    public void addWall(Wall wall) {
        walls.add(wall);
    }

    @Override
    public void addTimerWall(TimerWall timerWall) {
        timerWalls.add(timerWall);
    }

    @Override
    public void addAccel(Accelerator accelerator) {
        accels.add(accelerator);
    }

    @Override
    public void addHole(Hole hole) {
        holes.add(hole);
    }

    @Override
    public void addBar(Bar bar) {
        bars.add(bar);
    }

    @Override
    public void addPuzzle(Puzzle puzzle) {
        puzzles.add(puzzle);
    }

    @Override
    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public List<Ball> getBalls() {
        return balls;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public BallGenerator getBallGenerator() {
        return ballGenerator;
    }

    public void reset() {

        balls = new ArrayList<Ball>();
        walls = new ArrayList<Wall>();
        holes = new ArrayList<Hole>();
        accels = new ArrayList<Accelerator>();
        timerWalls = new ArrayList<TimerWall>();
        bars = new ArrayList<Bar>();
        puzzles = new ArrayList<Puzzle>();

        spartialHashGrid = new SpartialHashGridForSegments(WIDTH, HEIGHT, 8, 4,
                MultiLine.defaultRadius);

        for (int i = 0; i < multiLines.size(); ++i) {
            multiLines.get(i).dispose();
        }

        multiLines = new LinkedList<MultiLine>();

        blockedForDrawing = false;

        timeIsUp = false;
        ballInWrongHole = false;
        lost = false;
        win = false;

        time = 99f;
    }

    private void checkWinLostState() {

        if (ballInWrongHole) {
            lost = true;
        }

        if (balls.size() == 0 && ballGenerator.isEmpty()
                && !lost) {
            win = true;
            handler.obtainMessage(HandlerCodes.COMPLETE_LEVEL).sendToTarget();
            removeMultilines();
        }
    }

    public void accelerateBalls() {
        for (int i = 0; i < balls.size(); ++i) {
            balls.get(i).accelerate();
        }
    }

    public void removeMultilines() {
        for (int i = 0; i < multiLines.size(); ++i) {
            multiLines.get(i).dispose();
        }

        multiLines.clear();
    }

    public boolean isTimeIsUp() {
        return timeIsUp;
    }

    public boolean isBallInWrongHole() {
        return ballInWrongHole;
    }

    public void reset(LevelInitializer next) {
        reset();
        initialize(next);
    }

    public void removeLastMultiline() {
        if (!multiLines.isEmpty()) {
            multiLines.getLast().dispose();
        }
    }

    public void immediateWin() {
        balls.clear();
        ballGenerator.clear();
    }
}
