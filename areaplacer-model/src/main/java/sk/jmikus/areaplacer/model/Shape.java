package sk.jmikus.areaplacer.model;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private List<Point> points;

    public Shape(List<Point> points) {
        this.points = points;
    }

    public void moveLeft() {
        for (Point point : points) {
            point.setX(point.getX() + 1);
        }
    }

    public void moveUp() {
        for (Point point : points) {
            point.setY(point.getY() + 1);
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Shape{points=" + points + '}';
    }

    public static ShapeBuilder builder() {
        return new ShapeBuilder();
    }

    public static class ShapeBuilder {

        private List<Point> points = new ArrayList<>();

        public ShapeBuilder points(List<Point> points) {
            this.points = points;
            return this;
        }

        public Shape build() {
            return new Shape(points);
        }
    }

}
