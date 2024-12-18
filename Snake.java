import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Snake {
    private ArrayList<int[]> bodyCoordinates, bodyDirections;
    private int[] currentDirection;
    private int[] lastDirection;
    private Map<String, int[]> directionMap;


// constructor
    public Snake() {
        bodyCoordinates = new ArrayList<>();
        bodyDirections = new ArrayList<>();
        directionMap = new HashMap<>();

        createDirectionMap();
        initializeSnakeBody();
    } 
    private void createDirectionMap() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        directionMap.put("left", directions[0]);
        directionMap.put("right", directions[1]);
        directionMap.put("up", directions[2]);
        directionMap.put("down", directions[3]);
    } 
    private void initializeSnakeBody() {
        for (int x = 0; x < 3; x++) {
            int[] cellCoordinate = {4 - x, 2};
            int[] cellDirection = directionMap.get("right");
            currentDirection = directionMap.get("right");
            lastDirection = directionMap.get("right");
            bodyCoordinates.add(cellCoordinate);
            bodyDirections.add(cellDirection);
        }
    } 


// accessors
    public ArrayList<int[]> getBodyCoords() {
        return bodyCoordinates;
    }
    public ArrayList<int[]> getBodyDirects() {
        return bodyDirections;
    }
    public int[] getCurrentDirection() {
        return currentDirection;
    }


// mutators
    public void changeDirection(String direction) {
        int[] temp = directionMap.get(direction);
        if (lastDirection[0] != -temp[0] && lastDirection[1] != -temp[1]) {
            currentDirection = directionMap.get(direction);
        }
    }
    public void snakeMoves(boolean toFood) {
        if (toFood) {
            bodyAdd(currentDirection);
        } else {
            bodyShift(currentDirection);
        }
        lastDirection = currentDirection;
    } 
    private void bodyShift(int[] shift) {
        bodyDirections.remove(bodyDirections.size()-1);
        bodyDirections.add(0, shift);
        for (int i = 0; i < bodyCoordinates.size(); i++) {
            bodyCoordinates.get(i)[0] += bodyDirections.get(i)[0];
            bodyCoordinates.get(i)[1] += bodyDirections.get(i)[1];
        }
    } 
    private void bodyAdd(int[] shift) {
        int[] bodyGrowth = {bodyCoordinates.get(0)[0] + shift[0], bodyCoordinates.get(0)[1] + shift[1]};
        bodyDirections.add(0, shift);
        bodyCoordinates.add(0, bodyGrowth);
    }
}