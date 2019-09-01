package ru.nsu.vtimofeev.Inkball.model;

import android.util.FloatMath;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Circle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.OverlapTester;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/9/12
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpartialHashGridForSegments {

    private static int initialCapacity = 100;
    private static int initialCollidersCapacity = 1000;
    private static int initialMapCapacity = 1000;
    
    private List<Segment>[] cells;
    private List<Segment> potentialColliders;
    private Rectangle[] cellBounds;

    private final int cellNumber;

    private final float segmentWidth;
    
    //for internal needs
    private int[] cellIds;
    private Rectangle rect = new Rectangle(0,0,0,0);

    @SuppressWarnings("unchecked")
    public SpartialHashGridForSegments(float worldWidth, float worldHeigth,
                                       int cellsPerRow, int cellsPerCol, float segmentWidth) {

        this.cellNumber = cellsPerCol*cellsPerRow;
        
        float cellWidth = worldWidth/cellsPerRow;
        float cellHeigth = worldHeigth/cellsPerCol;
        
        this.segmentWidth = segmentWidth;

        cells = new List[cellNumber];
        cellBounds = new Rectangle[cellNumber];
        cellIds = new int[cellNumber];

        for(int i = 0; i < cellNumber; i++) {
            cells[i] = new ArrayList<Segment>(initialCapacity);
            cellBounds[i] = new Rectangle((i % cellsPerRow) * cellWidth,
                    (i/cellsPerRow) * cellHeigth, cellWidth, cellHeigth);
        }
        
        potentialColliders = new ArrayList<Segment>(initialCollidersCapacity);
    }
    
    private void setRectFromSegment(Segment segment) {
        float maxX = Math.max(segment.v0.x, segment.v1.x) + segmentWidth;
        float minX = Math.min(segment.v0.x, segment.v1.x) - segmentWidth;

        float maxY = Math.max(segment.v0.y, segment.v1.y) + segmentWidth;
        float minY = Math.min(segment.v0.y, segment.v1.y) - segmentWidth;

        rect.lowerLeft.set(minX, minY);
        rect.width = maxX - minX;
        rect.height = maxY - minY;        
    }
    
    public void insertSegment(Segment segment) {

        setRectFromSegment(segment);
        
        int[] cellIds = getCellIdsOverlap(rect);
        
        for (int i = 0; i < cellNumber; ++i) {
            int id = cellIds[i];
            
            if (id == -1) {
                break;
            }
            
            cells[id].add(segment);
        }
    }
    
    public void removeSegment(Segment segment) {
        
        setRectFromSegment(segment);

        int[] cellIds = getCellIdsOverlap(rect);

        for (int i = 0; i < cellNumber; ++i) {
            int id = cellIds[i];

            if (id == -1) {
                break;
            }

            cells[id].remove(segment);
        }
    }

    public List<Segment> getPotentialColliders(Ball ball) {
        
        setRectFromCircle((Circle)ball.bounds);
        potentialColliders.clear();

        int[] cellIds = getCellIdsOverlap(rect);

        for (int i = 0; i < cellNumber; ++i) {
            int id = cellIds[i];

            if (id == -1) {
                break;
            }

            List<Segment> segments = cells[id];
            for (int j = 0; j < segments.size(); ++j) {
                potentialColliders.add(segments.get(j));
            }
        }

        return potentialColliders;
    }

    private void setRectFromCircle(Circle bounds) {
        rect.lowerLeft.set(bounds.center);
        rect.lowerLeft.sub(bounds.radius, bounds.radius);
        
        rect.height = 2 * bounds.radius;
        rect.width = 2 * bounds.radius;
    }

    private int[] getCellIdsOverlap(Rectangle rectangle) {
        int index = 0;
        for (int i = 0; i < cellNumber; ++i) {
            if (OverlapTester.overlapRectangles(rectangle, cellBounds[i])) {
                cellIds[index++] = i;
            }
        }
        //Mark the end
        if (index < cellNumber) {
            cellIds[index] = -1;
        }
        
        return cellIds;
    }

    public void clear() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
