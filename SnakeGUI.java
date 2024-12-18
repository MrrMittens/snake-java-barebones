import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class SnakeGUI {
    private SnakeGame game;
    private JFrame frame;
    private Container c;
    private JPanel gameGrid;
    private JLabel snakeLength;
    private JLabel[][] gameCells;

    private ImageIcon emptyCell, foodCell, player1Snake;
    private int updateDelay;
    private ActionListener updateProcess;
    private Timer updating;


// constructor
    public SnakeGUI() {
        game = new SnakeGame();
        frame = new JFrame();
        c = frame.getContentPane();
        gameGrid = new JPanel();
        snakeLength = new JLabel("Player 1: 3");
        gameCells = new JLabel[15][15];
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                gameCells[row][col] = new JLabel();
            }
        }

        emptyCell = new ImageIcon("empty.png");
        foodCell = new ImageIcon("food.png");
        player1Snake = new ImageIcon("1b.png");
        createUpdate();
    }
    private void createUpdate() {
        updateDelay = 120;
        updateProcess = new ActionListener() {
            public void actionPerformed(ActionEvent u) {
                if (game.hasLoser()) {
                    updating.stop();
                    return;
                }
                game.update();
                updateGUI();
                gameGrid.repaint();
            }
        };
        updating = new Timer(updateDelay, updateProcess);
    }


// GUI setup
    public void setUpGUI() {
        c.setLayout(null);
        
        setUpComponents();
        updateGUI();
        updating.start();

        frame.setSize(660, 710);
        frame.setTitle("Snake 15x15");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void setUpComponents() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                gameGrid.add(gameCells[row][col]);
                gameCells[row][col].setBounds(0 + 40*row, 0 + 40*col, 40, 40);
            }
        }
        gameGrid.setBounds(25, 50, 600, 600);
        gameGrid.setLayout(null);
        gameGrid.setFocusable(true);
        c.add(gameGrid);

        snakeLength.setBounds(25, 25, 400, 25);
        c.add(snakeLength);
    }


// ButtonListener setup
    public void setUpKeyListener() {
        gameGrid.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent p) {
                switch(p.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        game.getSnake().changeDirection("up");
                        break;    
                    }
                    case KeyEvent.VK_DOWN: {
                        game.getSnake().changeDirection("down");
                        break;    
                    }
                    case KeyEvent.VK_LEFT: {
                        game.getSnake().changeDirection("left");
                        break;    
                    }
                    case KeyEvent.VK_RIGHT: {
                        game.getSnake().changeDirection("right");
                        break;    
                    }
                    default: {
                        break;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent r) {}
            @Override
            public void keyTyped(KeyEvent t) {}
        });
    }


// update
    private void updateGUI() {
        updateGameComponents();
        updateSnakeLengths();
    }
    private void updateGameComponents() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                if (game.getGrid()[row + 1][col + 1].isEmpty()) {
                    gameCells[row][col].setIcon(emptyCell);
                } else if (game.getGrid()[row + 1][col + 1].isSnake()) {
                    gameCells[row][col].setIcon(player1Snake);
                } else if (game.getGrid()[row + 1][col + 1].isFood()) {
                    gameCells[row][col].setIcon(foodCell);
                }
            }
        }
    }
    private void updateSnakeLengths() {
        snakeLength.setText("Player 1: " + game.getSnake().getBodyCoords().size());
    }
}