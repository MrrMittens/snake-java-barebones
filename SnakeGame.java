import java.util.Random;

public class SnakeGame {
    private Cell[][] grid;
    private Snake snake;
    private boolean loser;


// constructor
    public SnakeGame() {
        grid = new Cell[17][17];
        gridGeneration();
        snake = new Snake();
        update();
        loser = false;
    } 
    private void gridGeneration() {
        for (int row = 0; row < 17; row++) {
            for (int col = 0; col < 17; col++) {
                grid[row][col] = new Cell();
                addBorders(row, col);
            }
        }
    } 
        private void addBorders(int row, int col) {
            boolean gridEdge = (row == 0 || row == 16 || col == 0 || col == 16);
            if (gridEdge) {
                grid[row][col].toggleTo("border");
            }
        }


// accessors
    public Cell[][] getGrid() {
        return grid;
    }
    public Snake getSnake() {
        return snake;
    }
    public boolean hasLoser() {
        return loser;
    }


// mutators
    public void update() {
        moveSnake();
        updateSnakeCells();
        addFood();
    }
    private void moveSnake() {
        int[] foodCheck = {snake.getBodyCoords().get(0)[0] + snake.getCurrentDirection()[0], snake.getBodyCoords().get(0)[1] + snake.getCurrentDirection()[1]};
        if (grid[foodCheck[0] + 1][foodCheck[1] + 1].isFood()) {
            snake.snakeMoves(true);
        } else if (grid[foodCheck[0] + 1][foodCheck[1] + 1].isEmpty()) {
            snake.snakeMoves(false);
        } else {
            loser = true;
        }
    }
    private void updateSnakeCells() {
        for (int row = 1; row < 16; row++) {
            for (int col = 1; col < 16; col++) {
                snakeCellCheck(row, col);
            }
        }
    } 
    private void addFood() {
        int maxCounter = 2;
        int foodCounter = 0;
        for (int row = 1; row < 16; row++) {
            for (int col = 1; col < 16; col++) {
                if (grid[row][col].isFood()) {
                    foodCounter++;
                }
            }
        }
        while (foodCounter < maxCounter) {
            Random r = new Random();
            int row = r.nextInt(1,16);
            int col = r.nextInt(1,16);
            if (grid[row][col].isEmpty()) {
                grid[row][col].toggleTo("food");
                foodCounter++;
            }
        }
    }
    private void snakeCellCheck(int row, int col) {
        int[] currentCoordinates = {row - 1, col - 1};
        for (int cell = 0; cell < snake.getBodyCoords().size(); cell++) {
            boolean match = snake.getBodyCoords().get(cell)[0] == currentCoordinates[0] && snake.getBodyCoords().get(cell)[1] == currentCoordinates[1];
            if (match) {
                grid[row][col].toggleTo("snake");
                return;
            }
        }
        if (!grid[row][col].isFood()) {
            grid[row][col].toggleTo("empty");
        }
    }
}