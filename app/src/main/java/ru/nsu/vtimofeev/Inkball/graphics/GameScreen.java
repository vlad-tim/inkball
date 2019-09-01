package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.SpriteBatcher;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.TextureRegion;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;
import ru.nsu.vtimofeev.Inkball.model.*;
import ru.nsu.vtimofeev.Inkball.resources.Assets;
import ru.nsu.vtimofeev.Inkball.resources.Rating;
import ru.nsu.vtimofeev.Inkball.resources.Settings;
import ru.nsu.vtimofeev.Inkball.utils.FPSCounter;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/27/12
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen extends AbstractScreen{

    private final int maxObjectsToRender = World.MAX_MULTILINES * MultiLine.MAX_SEGMENTS +
            + (World.WALLS_IN_COLUMN + 1)* (World.WALLS_IN_ROW + 1);
    //Magnification of ball when affected by Accelerator or Hole
    private final float ballAffectCoef = 0.9f;

    private GameScreenInfo gameScreenInfo;
    private SpriteBatcher batcher;
    private MultilineRenderer multilineRenderer;
    private HintRenderer hintRenderer;
    private World world;
    private FPSCounter fpsCounter;

    private boolean backgroundEnabled;
    private boolean hintsEnabled;

    public GameScreen(GameScreenInfo gameScreenInfo, World world) {

        super(gameScreenInfo);

        this.gameScreenInfo = gameScreenInfo;
        this.batcher = new SpriteBatcher(graphics, maxObjectsToRender);
        this.multilineRenderer = new MultilineRenderer(graphics, gameScreenInfo,
                (MultiLine.MAX_SEGMENTS + 2) * 6 * World.MAX_MULTILINES);
        this.hintRenderer = new HintRenderer(graphics, gameScreenInfo);
        this.world = world;
        this.fpsCounter = new FPSCounter();

        init();
    }

    @Override
    public void init() {
        camera.setViewportAndMatrices();
        GL10 gl = graphics.getGL();

        gl.glClearColor(183/255f, 226/255f, 247/255f, 1f);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        backgroundEnabled = Settings.isBackgroundEnabled();
        hintsEnabled = Settings.isHintsEnabled();
    }

    public void present() {
        GL10 gl = graphics.getGL();

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        render();
        fpsCounter.logFrame();
    }

    private void render() {

        if (backgroundEnabled) {
            batcher.beginBatch(Assets.background);
            renderBackground();
            batcher.endBatch();
        }

        batcher.beginBatch(Assets.holes);
        renderHoles();
        batcher.endBatch();

        batcher.beginBatch(Assets.objects);
        renderGenerator();
        renderWalls();
        renderAccels();
        renderUnstableWalls();
        batcher.endBatch();

        batcher.beginBatch(Assets.bars);
        renderBars();
        batcher.endBatch();

        batcher.beginBatch(Assets.puzzles);
        renderPuzzles();
        batcher.endBatch();

        renderMultiline();

        batcher.beginBatch(Assets.balls);
        renderBalls();
        renderMultilineCircles();
        batcher.endBatch();

        batcher.beginBatch(Assets.score);
        renderDigits();
        batcher.endBatch();
        gameScreenInfo.getGraphics().getGL().glColor4f(1f,1f,1f,1f);

        renderGameMenu();
        renderWinLost();
        renderRating();

        renderHints();
    }

    private void renderHints() {
        hintRenderer.render();
    }

    private void renderRating() {

        if (!gameScreenInfo.isRatingVisible()) {
            return;
        }

        batcher.beginBatch(Assets.levelups);

        int rating = gameScreenInfo.getRating();

        if (rating == Rating.NOVICE) {
            batcher.drawSprite(gameScreenInfo.levelUpX, gameScreenInfo.levelUpY,
                    gameScreenInfo.levelUpWidth, gameScreenInfo.levelUpHeight,
                    Assets.noviceReg);
        }

        else if (rating == Rating.INTERMEDIATE) {
            batcher.drawSprite(gameScreenInfo.levelUpX, gameScreenInfo.levelUpY,
                    gameScreenInfo.levelUpWidth, gameScreenInfo.levelUpHeight,
                    Assets.intermediateReg);
        }

        else if (rating == Rating.ADVANCED) {
            batcher.drawSprite(gameScreenInfo.levelUpX, gameScreenInfo.levelUpY,
                    gameScreenInfo.levelUpWidth, gameScreenInfo.levelUpHeight,
                    Assets.advancedReg);
        }

        else if (rating == Rating.EXPERT) {
            batcher.drawSprite(gameScreenInfo.levelUpX, gameScreenInfo.levelUpY,
                    gameScreenInfo.levelUpWidth, gameScreenInfo.levelUpHeight,
                    Assets.expertReg);
        }

        else if (rating == Rating.MASTER) {
            batcher.drawSprite(gameScreenInfo.levelUpX, gameScreenInfo.levelUpY,
                    gameScreenInfo.levelUpWidth, gameScreenInfo.levelUpHeight,
                    Assets.masterReg);
        }

        batcher.endBatch();
    }

    private void renderGameMenu() {

        //No need if game is already over
        if (world.isLost() || world.isWin()) {
            return;
        }

        batcher.beginBatch(Assets.gameMenu);

        if (gameScreenInfo.isMenuVisible()) {


            batcher.drawSprite(gameScreenInfo.menuX, gameScreenInfo.menuY,
                    gameScreenInfo.menuWidth, gameScreenInfo.menuHeight, Assets.gameMenuReg);
        }
        else {
            batcher.drawSprite(gameScreenInfo.menuIconX, gameScreenInfo.menuIconY,
                    gameScreenInfo.menuIconWidth, gameScreenInfo.menuIconHeight,
                    Assets.gameIconReg);
        }

        if (gameScreenInfo.isMenuItemVisible()) {

            GameMenuItem item = gameScreenInfo.getVisibleItem();

            if (item == GameMenuItem.RESUME) {
                drawRectangle(gameScreenInfo.resumeArea, Assets.resumeReg);
            }
            else if (item == GameMenuItem.RESTART) {
                drawRectangle(gameScreenInfo.restartArea, Assets.restartReg);
            }
            else if (item == GameMenuItem.ACCELERATE) {
                drawRectangle(gameScreenInfo.accelerateArea, Assets.accelerateReg);
            }
            else if (item == GameMenuItem.CLEAR) {
                drawRectangle(gameScreenInfo.clearArea, Assets.clearScreenReg);
            }
            else if (item == GameMenuItem.EXIT) {
                drawRectangle(gameScreenInfo.exitArea, Assets.exitReg);
            }
        }

        batcher.endBatch();
    }

    private void drawRectangle(Rectangle area, TextureRegion region) {

        batcher.drawSprite(area.lowerLeft.x + area.width/2f, area.lowerLeft.y + area.height/2f,
                area.width, area.height, region);
    }

    private void renderWinLost() {

        //If Game is not over
        if (!(world.isWin() || world.isLost())) {
            return;
        }

        batcher.beginBatch(Assets.winlost);


//        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
//        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        if (world.isWin()) {
            batcher.drawSprite(gameScreenInfo.levelCompleteX, gameScreenInfo.levelCompleteY,
                    gameScreenInfo.levelCompleteWidth, gameScreenInfo.levelCompleteHeigth,
                    Assets.levelComplete);

            if (gameScreenInfo.isShouldDrawNextLevelButton()) {

                batcher.drawSprite(gameScreenInfo.winLostNextLevelX, gameScreenInfo.winLostNextLevelY,
                        gameScreenInfo.winLostNextLevelWidth, gameScreenInfo.winLostNextLevelHeigth,
                        Assets.winLostNextLevel);
            }

            else {
                batcher.drawSprite(gameScreenInfo.wellDoneX, gameScreenInfo.wellDoneY,
                        gameScreenInfo.wellDoneWidth, gameScreenInfo.wellDoneHeight,
                        Assets.wellDoneReg);
            }

        }
        //if (world.isLost())
        else {
            batcher.drawSprite(gameScreenInfo.levelFailedX, gameScreenInfo.levelFailedY,
                    gameScreenInfo.levelFailedWidth, gameScreenInfo.levelFailedHeigth,
                    Assets.levelFailed);

            batcher.drawSprite(gameScreenInfo.winLostRestartX, gameScreenInfo.winLostRestartY,
                    gameScreenInfo.winLostRestartWidth, gameScreenInfo.winLostRestartHeigth,
                    Assets.winLostRestart);


            if (world.isTimeIsUp()) {
                batcher.drawSprite(gameScreenInfo.timeIsUpX, gameScreenInfo.timeIsUpY,
                        gameScreenInfo.timeIsUpWidth, gameScreenInfo.timeIsUpHeigth,
                        Assets.timeIsUp);
            }

            else if (world.isBallInWrongHole()) {
                batcher.drawSprite(gameScreenInfo.wrongColorX, gameScreenInfo.wrongColorY,
                        gameScreenInfo.wrongColorWidth, gameScreenInfo.wrongColorHeigth,
                        Assets.wrongColor);
            }
        }

        batcher.endBatch();

//        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
//        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
    }

    private void renderBackground() {
        float realX = gameScreenInfo.toFrustrumPositionX(World.WIDTH / 2);
        float realY = gameScreenInfo.toFrustrumPositionY(World.HEIGHT / 2);

        float defWidth = gameScreenInfo.frustumWidth;
        float defHeight = gameScreenInfo.frustumHeight;

        batcher.drawSprite(realX, realY, defWidth, defHeight, Assets.backgroundReg);
    }

    private void renderGenerator() {
        drawObject(world.getBallGenerator(), Assets.generator);
    }

    private void renderWalls() {
        List<Wall> walls = world.getWalls();
        for (int i = 0; i < walls.size(); ++i) {

            Wall wall = walls.get(i);
            renderWall(wall);
        }
    }

    private void drawObject(GameObject object, TextureRegion textureRegion) {
        float realX = gameScreenInfo.toFrustrumPositionX(object.position.x);
        float realY = gameScreenInfo.toFrustrumPositionY(object.position.y);

        float width = gameScreenInfo.translateWidthToFrustum(object.getWidth());
        float height = gameScreenInfo.translateHeightToFrustum(object.getHeight());

        batcher.drawSprite(realX, realY, width, height, textureRegion);
    }

    private void drawObject(GameObject object, TextureRegion textureRegion, float angle) {
        float realX = gameScreenInfo.toFrustrumPositionX(object.position.x);
        float realY = gameScreenInfo.toFrustrumPositionY(object.position.y);

        float width = gameScreenInfo.translateWidthToFrustum(object.getWidth());
        float height = gameScreenInfo.translateHeightToFrustum(object.getHeight());

        batcher.drawSprite(realX, realY, width, height, angle, textureRegion);
    }

    private void renderWall(Wall wall) {

        TextureRegion wallRegion = getRegionForWall(wall);
        drawObject(wall, wallRegion);
    }

    private TextureRegion getRegionForWall(Wall wall) {

        if (!wall.isCracked()) {
            return getRegionForUncrackedWall(wall);
        }
        else {
            return getRegionForCrackedWall(wall);
        }
    }

    private TextureRegion getRegionForUncrackedWall(Wall wall) {

        TextureRegion wallRegion;
        Color wallColor = wall.getColor();

        switch (wallColor) {
            case GREEN: {
                wallRegion = Assets.greenWall;
                break;
            }
            case YELLOW: {
                wallRegion = Assets.yellowWall;
                break;
            }
            case BLUE: {
                wallRegion = Assets.blueWall;
                break;
            }
            case ORANGE: {
                wallRegion = Assets.redWall;
                break;
            }
            default: {
                wallRegion = Assets.uncoloredWall;
            }
        }

        return wallRegion;
    }

    private TextureRegion getRegionForCrackedWall(Wall wall) {
        TextureRegion wallRegion;
        Color wallColor = wall.getColor();

        switch (wallColor) {
            case GREEN: {
                wallRegion = Assets.crackedGreenWall;
                break;
            }
            case YELLOW: {
                wallRegion = Assets.crackedYellowWall;
                break;
            }
            case BLUE: {
                wallRegion = Assets.crackedBlueWall;
                break;
            }
            case ORANGE: {
                wallRegion = Assets.crackedRedWall;
                break;
            }
            default: {
                wallRegion = Assets.crackedUncoloredWall;
            }
        }

        return wallRegion;
    }

    private void renderAccels() {
        List<Accelerator> accels = world.getAccels();

        for (int i = 0; i < accels.size(); ++i) {

            Accelerator accel = accels.get(i);
            float angle = getAngleByDirection(accel.direction);
            drawObject(accel, Assets.accelerator, angle);

        }

    }

    private float getAngleByDirection(Direction direction) {
        if (direction == Direction.UP) {
            return 0.0f;
        }
        else if (direction == Direction.LEFT) {
            return 90.0f;
        }
        else if (direction == Direction.DOWN) {
            return 180.0f;
        }
        // if (direction == Direction.RIGHT)
        else  {
            return 270.0f;
        }
    }

    private void renderUnstableWalls() {
        List<TimerWall> timerWalls = world.getTimerWalls();

        for (int i = 0; i < timerWalls.size(); ++i) {

            TimerWall timerWall = timerWalls.get(i);
            TextureRegion unstableWallRegion = getUnstableWallRegion(timerWall);
            drawObject(timerWall, unstableWallRegion);
        }
    }

    public TextureRegion getUnstableWallRegion(TimerWall timerWall) {

        if (timerWall.getState() == TimerWallState.OPAQUE) {
            return Assets.unstableWallOpaque;
        }
        else {
            return Assets.unstableWallTransparent;
        }
    }

    private void renderBars() {
        List<Bar> bars = world.getBars();

        for (int i = 0; i < bars.size(); ++i) {

            Bar bar = bars.get(i);
            TextureRegion barRegion = Utils.getBarRegion(bar);
            drawObject(bar, barRegion);
        }
    }

    private void renderPuzzles() {
        List<Puzzle> puzzles = world.getPuzzles();

        for (int i = 0; i < puzzles.size(); ++i) {

            Puzzle puzzle = puzzles.get(i);
            TextureRegion puzzleRegion = Utils.getPuzzleRegion(puzzle);
            drawObject(puzzle, puzzleRegion);
        }
    }

    private void renderHoles() {
        List<Hole> holes = world.getHoles();

        for (int i = 0; i < holes.size(); ++i) {

            Hole hole = holes.get(i);
            TextureRegion holeRegion = getHoleRegion(hole);
            drawObject(hole, holeRegion);
        }
    }

    private TextureRegion getHoleRegion(Hole hole) {

        Color holeColor = hole.getColor();
        if (holeColor == Color.NONE) {
            return Assets.uncolored_hole;
        }
        else if (holeColor == Color.BLUE) {
            return Assets.blue_hole;
        }
        else if (holeColor == Color.ORANGE) {
            return Assets.orange_hole;
        }
        else if (holeColor == Color.GREEN) {
            return Assets.green_hole;
        }
        else if (holeColor == Color.YELLOW) {
            return Assets.yellow_hole;
        }
        else {
            throw new IllegalArgumentException("Illegal hole color");
        }
    }

    private void renderBalls() {

        List<Ball> balls = world.getBalls();
        for (int i = 0; i < balls.size(); ++i) {

            Ball ball = balls.get(i);
            boolean isAffected = ball.isAffected();

            float realX = gameScreenInfo.toFrustrumPositionX(ball.position.x);
            float realY = gameScreenInfo.toFrustrumPositionY(ball.position.y);

            float width = (!isAffected) ? gameScreenInfo.translateBallWidthFrustum()
                    : gameScreenInfo.translateBallWidthFrustum()*ballAffectCoef;
            float height = (!isAffected) ? gameScreenInfo.translateBallHeightFrustum()
                    : gameScreenInfo.translateBallHeightFrustum()*ballAffectCoef;

            Color ballColor = ball.getColor();

            if (ballColor == Color.BLUE) {
                batcher.drawSprite(realX, realY, width, height, Assets.blue_ball);
                batcher.drawSprite(realX, realY, width, height, Assets.shader);
            } else if (ballColor == Color.ORANGE) {
                batcher.drawSprite(realX, realY, width, height, Assets.red_ball);
                batcher.drawSprite(realX, realY, width, height, Assets.shader);
            } else if (ballColor == Color.GREEN) {
                batcher.drawSprite(realX, realY, width, height, Assets.green_ball);
                batcher.drawSprite(realX, realY, width, height, Assets.shader);
            } else if (ballColor == Color.YELLOW) {
                batcher.drawSprite(realX, realY, width, height, Assets.yellow_ball);
                batcher.drawSprite(realX, realY, width, height, Assets.shader);
            }
            else {
                batcher.drawSprite(realX, realY, width, height, Assets.shader);
            }
        }
    }

    private void renderMultiline() {

        ArrayList<Segment> segments = world.getAllSegments();

        multilineRenderer.startRendering();

        for (int i = 0; i < segments.size(); ++i) {
            Segment segment = segments.get(i);
            multilineRenderer.renderSegment(segment);
        }
        multilineRenderer.endRendering();
    }

    private void renderMultilineCircles() {

        ArrayList<Segment> segments = world.getAllSegments();

        for (int i = 0; i < segments.size(); ++i) {
            Segment segment = segments.get(i);
            float realX = gameScreenInfo.toFrustrumPositionX(segment.v1.x);
            float realY = gameScreenInfo.toFrustrumPositionY(segment.v1.y);

            float width = gameScreenInfo.translateMultilineWidthFrustum();
            float height = gameScreenInfo.translateMultilineHeightFrustum();

            batcher.drawSprite(realX, realY, 2 * width, 2 * height, Assets.multiline);
        }
    }

    private void renderDigits() {

        float fpsNumberX = gameScreenInfo.fpsX;
        float fpsNumberY = gameScreenInfo.fpsY;

        float timeX = gameScreenInfo.timeX;
        float timeY = gameScreenInfo.timeY;

        float digitWidth = gameScreenInfo.digitWidth;
        float digitHeight = gameScreenInfo.digitHeight;

        int fps = fpsCounter.getFPS();
        float time = world.getTime();
        int timeInt = (int)time;

//        drawDigits(fps, fpsNumberX, fpsNumberY, digitWidth, digitHeight);


        if (time > World.lowTime) {
            drawDigits(timeInt, timeX, timeY, digitWidth, digitHeight);
        }
        else if ((time - (timeInt)) > 0.5f) {
            gameScreenInfo.getGraphics().getGL().glColor4f(1f,0.4f,0.4f,1f);
            drawDigits(timeInt, timeX, timeY, digitWidth, digitHeight);
        }


    }

    private void drawDigits(int score, float scoreNumberX, float scoreNumberY,
                            float scoreNumberWidth, float scoreNumberHeight) {

        for (int i = 100; i > 1; i /= 10) {
            int index = (score % i) / (i / 10);
            batcher.drawSprite(scoreNumberX, scoreNumberY, scoreNumberWidth, scoreNumberHeight, Assets.scores[index]);
            scoreNumberX += scoreNumberWidth;
        }
    }

    public void resume() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void pause() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void dispose() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
