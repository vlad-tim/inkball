package ru.nsu.vtimofeev.Inkball.model;


//WE SUPPOSE THAT GAMEOBJECTS ARE ALWAYS SQUARE

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Circle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.OverlapTester;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

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
public class SpartialHashGridForWalls {

    private static int initialCapacity = 30;
    private static int initialCollidersCapacity = 200;

    private List<GameObject>[] cells;
    private ArrayList<GameObject> potentialColliders;
    private Rectangle[] cellBounds;

    private final int cellNumber;

    //for internal needs
    private int[] cellIds;
    private Rectangle rect = new Rectangle(0,0,0,0);

    @SuppressWarnings("unchecked")
    public SpartialHashGridForWalls(float worldWidth, float worldHeigth,
                                    int cellsPerRow, int cellsPerCol) {

        this.cellNumber = cellsPerCol*cellsPerRow;
        
        float cellWidth = worldWidth/cellsPerRow;
        float cellHeigth = worldHeigth/cellsPerCol;


        cells = new List[cellNumber];
        cellBounds = new Rectangle[cellNumber];
        cellIds = new int[cellNumber];

        for(int i = 0; i < cellNumber; i++) {
            cells[i] = new ArrayList<GameObject>(initialCapacity);
            cellBounds[i] = new Rectangle(-Wall.defaultSize + (i % cellsPerRow) * cellWidth,
                    -Wall.defaultSize + (i/cellsPerRow) * cellHeigth, cellWidth, cellHeigth);
        }
        
        potentialColliders = new ArrayList<GameObject>(initialCollidersCapacity);
    }

    public void insertGameObject(GameObject gameObject) {

        rect.set((Rectangle) gameObject.bounds);

        int[] cellIds = getCellIdsOverlap(rect);

        for (int i = 0; i < cellNumber; ++i) {
            int id = cellIds[i];

            if (id == -1) {
                break;
            }

            cells[id].add(gameObject);
        }
    }
    
    public void removeGameObject(GameObject gameObject) {

        rect.set((Rectangle) gameObject.bounds);

        int[] cellIds = getCellIdsOverlap(rect);

        for (int i = 0; i < cellNumber; ++i) {
            int id = cellIds[i];

            if (id == -1) {
                break;
            }

            cells[id].remove(gameObject);
        }
    }


    public List<GameObject> getPotentialColliders(Ball ball, Class objectType) {

        setRectFromCircle((Circle) ball.bounds);
        potentialColliders.clear();

        int[] cellIds = getCellIdsOverlap(rect);

        for (int i = 0; i < cellNumber; ++i) {
            int id = cellIds[i];

            if (id == -1) {
                break;
            }

            List<GameObject> gameObjects = cells[id];

            for (int j = 0; j < gameObjects.size(); ++j) {

                GameObject gameObject = gameObjects.get(j);
                if (objectType.isInstance(gameObject)) {
                    potentialColliders.add(gameObject);
                }
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

        for (int i = 0; i < cellNumber; ++i) {

            cells[i].clear();
        }

        potentialColliders.clear();
    }
}
