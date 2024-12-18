public class Cell {
    private boolean empty, snake, border, food;


// constructor
    public Cell() {
        empty = true;
        snake = false;
        border = false;
        food = false;
    }


// accessors
    public boolean isEmpty() {
        return empty;
    }
    public boolean isSnake() {
        return snake;
    }
    public boolean isBorder() {
        return border;
    }
    public boolean isFood() {
        return food;
    }


// mutators
    public void toggleTo(String state) {
        offSwitch();
        switch(state) {
            case "empty": {
                empty = true;
                break;
            }
            case "snake": {
                snake = true;
                break;
            }
            case "border": {
                border = true;
                break;
            }
            case "food": {
                food = true;
            }
        }
    } 
    private void offSwitch() {
        empty = false;
        snake = false;
        border = false;
        food = false;
    }
}