package Snakey;
import java.util.LinkedList;
public class Snake {
    private static final int SNAKE_SPEED = 1;
    private LinkedList<BodyPart> body;
    private Direction direction;
    private int length;
    private int currency;

        // Constructor
      //  public Snake() {
            // Initialize the snake
       // }

        // Move the snake
    //    public void move() {
            // Update the snake's position based on the current direction
    //    }

        // Grow the snake when it eats a dot
     //   public void grow() {
            // Increase the length of the snake and update the body
    //    }

        // Check for collisions
      //  public boolean checkCollision() {
       //     return false;
            // Check if the snake collided with itself or obstacles
    //    }

        // Handle user input to change the snake's direction
    //    public void setDirection(Direction newDirection) {
            // Set the new direction, preventing immediate reversal
     //   }

 //   public BodyPart[] getBody() {
        //    return null;

   // }

    // Additional methods for handling power-ups, scoring, etc.


        public Snake() {
            this.body = new LinkedList<>();
            this.direction = Direction.RIGHT; // Initial direction
            initializeSnake(); // Initialize the snake with a few body parts
        }

        private void initializeSnake() {
            // Add initial body parts to the snake
            for (int i = 0; i < 3; i++) {
                body.add(new BodyPart(5 - i, 5));
            }
        }

    public void move() {
        for (int i = 0; i < SNAKE_SPEED; i++) {
            BodyPart newHead = createNewHead();
            body.addFirst(newHead);
            body.removeLast();  // Remove the tail to keep the same size
        }
    }

        private BodyPart createNewHead() {
            // Get the current head of the snake
            BodyPart currentHead = getHead();

            // Calculate the new head's position based on the current direction
            int newHeadX = currentHead.getX();
            int newHeadY = currentHead.getY();

            switch (direction) {
                case UP:
                    newHeadY--;
                    break;
                case DOWN:
                    newHeadY++;
                    break;
                case LEFT:
                    newHeadX--;
                    break;
                case RIGHT:
                    newHeadX++;
                    break;
            }

            return new BodyPart(newHeadX, newHeadY);
        }

        public void grow() {
            // Create a new head without removing the tail (snake grows)
            BodyPart newHead = createNewHead();
            body.addFirst(newHead);
        }

        public boolean checkCollision() {
            // Check if the head collides with the body or goes out of bounds
            BodyPart head = getHead();
            int headX = head.getX();
            int headY = head.getY();

            // Check collision with body
            for (BodyPart bodyPart : body.subList(1, body.size())) {
                if (headX == bodyPart.getX() && headY == bodyPart.getY()) {
                    return true; // Collision with body
                }
            }

            // Check collision with walls (assuming a grid size of 10x10)
            return headX < 0 || headX >= 10 || headY < 0 || headY >= 10;
        }

        public LinkedList<BodyPart> getBody() {
            return new LinkedList<>(body); // Return a copy to avoid external modification
        }

        public int getLength() {
            return body.size();
        }

        public BodyPart getHead() {
            return body.getFirst();
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction newDirection) {
            // Prevent immediate reversal of direction
            if (!newDirection.isOpposite(direction)) {
                this.direction = newDirection;
            }
        }
    }


