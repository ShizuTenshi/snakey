package Snakey;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, STOP;

    public boolean isOpposite(Direction other) {
        return this == UP && other == DOWN ||
                this == DOWN && other == UP ||
                this == LEFT && other == RIGHT ||
                this == RIGHT && other == LEFT;
    }
}
