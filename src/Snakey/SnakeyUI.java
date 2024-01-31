package Snakey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SnakeyUI extends JFrame {
        private Snake snake;

        public SnakeyUI() {
            snake = new Snake();
            snake.setDirection(Direction.RIGHT);

            JButton moveButton = new JButton("Move Snake");
            moveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    snake.move();
                    repaint();
                }
            });

            setLayout(new BorderLayout());

            JPanel gamePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    // Draw the snake
                    for (BodyPart bodyPart : snake.getBody()) {
                        g.fillRect(bodyPart.getX() * 20, bodyPart.getY() * 20, 20, 20);
                    }
                }
            };

            add(moveButton, BorderLayout.SOUTH);
            add(gamePanel, BorderLayout.CENTER);

            setSize(300, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SnakeyUI();
                }
            });
        }

}
