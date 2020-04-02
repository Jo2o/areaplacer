package sk.jmikus.areaplacer.model;

import java.util.Objects;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        return (getX() == point.getX()) && (getY() == point.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + '}';
    }

    public static PointBuilder builder() {
        return new PointBuilder();
    }

    public static class PointBuilder {
        private int x;
        private int y;
        public PointBuilder x(int x) {
            this.x = x;
            return this;
        }
        public PointBuilder y(int y) {
            this.y= y;
            return this;
        }
      public Point build() {
            return new Point(x, y);
        }
    }

}
