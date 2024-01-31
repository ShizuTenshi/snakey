package Snakey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class SnakeyUI extends JFrame {
    private Snake snake;
    private Direction intendedDirection; // Intended direction based on the most recent key pressed
    private Set<Integer> pressedKeys; // Set to keep track of currently pressed keys
    private boolean isKeyPressed; // Flag to check if any arrow key is pressed

    public SnakeyUI() {
        snake = new Snake();
        intendedDirection = Direction.RIGHT; // Initial intended direction
        pressedKeys = new HashSet<>();
        isKeyPressed = false;

        // Add a KeyListener to capture arrow key events
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used in this example
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                updateIntendedDirection();
                isKeyPressed = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
                updateIntendedDirection();
                isKeyPressed = !pressedKeys.isEmpty(); // Check if any arrow key is still pressed
            }
        });

        setFocusable(true); // Enable keyboard focus to capture key events

        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the snake
                for (int i = 0; i < snake.getLength(); i++) {
                    BodyPart bodyPart = snake.getBody().get(i);
                    g.setColor(Color.BLACK);

                    // Draw tongue for the snake's head
                    if (i == 0) {
                        g.fillRect(bodyPart.getX() * 20, bodyPart.getY() * 20, 20, 20);
                        g.setColor(Color.RED);
                        g.fillRect(bodyPart.getX() * 20 + 14, bodyPart.getY() * 20, 6, 6);
                    } else {
                        // Draw the body with a finer tail
                        if (i == snake.getLength() - 1) {
                            g.fillRect(bodyPart.getX() * 20, bodyPart.getY() * 20, 16, 16);
                        } else {
                            g.fillRect(bodyPart.getX() * 20, bodyPart.getY() * 20, 20, 20);
                        }
                    }
                }
            }
        };

        add(gamePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // Start a timer to continuously move the snake
        Timer timer = new Timer(100, e -> {
            if (isKeyPressed) {
                snake.setDirection(intendedDirection);
                snake.move();
                repaint();
            }
        });
        timer.start();
    }

    private void updateIntendedDirection() {
        Direction currentDirection = snake.getDirection();

        // If no arrow keys are pressed, stop the movement
        if (pressedKeys.isEmpty()) {
            intendedDirection = Direction.STOP;
            return;
        }

        // Update the intended direction based on the most recent key pressed
        if (pressedKeys.contains(KeyEvent.VK_UP) && currentDirection != Direction.DOWN) {
            intendedDirection = Direction.UP;
        } else if (pressedKeys.contains(KeyEvent.VK_DOWN) && currentDirection != Direction.UP) {
            intendedDirection = Direction.DOWN;
        } else if (pressedKeys.contains(KeyEvent.VK_LEFT) && currentDirection != Direction.RIGHT) {
            intendedDirection = Direction.LEFT;
        } else if (pressedKeys.contains(KeyEvent.VK_RIGHT) && currentDirection != Direction.LEFT) {
            intendedDirection = Direction.RIGHT;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeyUI());
    }
}
