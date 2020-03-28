package sk.jmikus.areaplacer.model;

public class Point {

    private final int x;
    private final int y;
    private final Filled filled;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.filled = Filled.EMPTY;
    }

    public Point(int x, int y, Filled filled) {
        this.x = x;
        this.y = y;
        this.filled = filled;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Filled getFilled() {
        return filled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        if (y != point.y) {
            return false;
        }
        return filled == point.filled;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = (31 * result) + y;
        result = (31 * result) + filled.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + ", filled=" + filled + '}';
    }

    public static PointBuilder builder() {
        return new PointBuilder();
    }

    public static class PointBuilder {
        private int x;
        private int y;
        private Filled filled;

        public PointBuilder x(int x) {
            this.x = x;
            return this;
        }

        public PointBuilder y(int y) {
            this.y= y;
            return this;
        }

        public PointBuilder filled(Filled filled) {
            this.filled = filled;
            return this;
        }

        public Point build() {
            return new Point(x, y, filled);
        }
    }

}
