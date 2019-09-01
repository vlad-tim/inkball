package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Circle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.Inkball.pool.SegmentPool;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class MultiLine {

    public static final float defaultRadius = (Wall.defaultSize / 10);
    public static final int MAX_SEGMENTS = 100;

    private ArrayList<Segment> segments;
    private int firstSegmentPointer = 0;
    private Vector2 lastAddedPoint;

    private SpartialHashGridForSegments spartialHashGrid;

    public MultiLine(SpartialHashGridForSegments spartialHashGrid) {
        segments = new ArrayList<Segment>(MAX_SEGMENTS);
        this.spartialHashGrid = spartialHashGrid;
    }

    //Creates new segment
    public void addPoint(Vector2 point) {

        Vector2 firstPoint = Vector2Pool.acquire();
        Vector2 secondPoint = Vector2Pool.acquire().set(point);

        if (lastAddedPoint == null) {
            lastAddedPoint = new Vector2(point);
        }

        if (segments.size() >= MAX_SEGMENTS) {

            int firstSegmentIndex = getFirstSegmentIndex();
            Segment segmentToRemove = segments.get(firstSegmentIndex);
            spartialHashGrid.removeSegment(segmentToRemove);
            SegmentPool.release(segmentToRemove);

            firstPoint.set(lastAddedPoint);
            Segment segmentToAdd = SegmentPool.acquire(firstPoint, secondPoint);
            segmentToAdd.setMultiLine(this);
            segments.set(firstSegmentIndex, segmentToAdd);
            spartialHashGrid.insertSegment(segmentToAdd);
            lastAddedPoint.set(point);

            incrementFirstSegmentIndex();
        }
        
        else {

            firstPoint.set(lastAddedPoint);
            Segment segmentToAdd = SegmentPool.acquire(firstPoint, secondPoint);
            segmentToAdd.setMultiLine(this);
            segments.add(segmentToAdd);
            spartialHashGrid.insertSegment(segmentToAdd);
            lastAddedPoint.set(point);

        }

    }

    public int getFirstSegmentIndex() {
        return firstSegmentPointer;
    }

    public int getLastSegmentIndex() {
        
        if (segments.size() < MAX_SEGMENTS) {
            return segments.size() - 1;
        }
        else {
            return (firstSegmentPointer - 1 + MAX_SEGMENTS) % MAX_SEGMENTS;
        }
    }

    private void incrementFirstSegmentIndex() {
        
        firstSegmentPointer = (firstSegmentPointer + 1);
        
        //Loop
        if (firstSegmentPointer == MAX_SEGMENTS) {
            firstSegmentPointer = 0;
        }
    }

    public void dispose() {
        
        Segment segment;
        
        for (int i = 0; i < segments.size(); ++i) {

            segment = segments.get(i);
            spartialHashGrid.removeSegment(segment);
            segment.setMultiLine(null);
            SegmentPool.release(segment);
        }
        
        segments.clear();
        lastAddedPoint = null;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public Segment getLastSegment() {
        return segments.get(getLastSegmentIndex());
    }

}
