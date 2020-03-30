package sk.jmikus.areaplacer.model;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private String name;
    private List<Point> points;

    public Shape(String name, List<Point> points) {
        this.name = name;
        this.points = points;
    }

    public void moveRightByOne() {
        points.forEach(point -> point.setX(point.getX() + 1));
    }

    public void moveUpByOne() {
        points.forEach(point -> point.setY(point.getY() + 1));
    }

    public void moveLeft(int num) {
        points.forEach(point -> point.setX(point.getX() - num));
    }

    public int getTopBoundary() {
        return points.stream().mapToInt(Point::getY).max().orElse(0);
    }

    public int getBottomBoundary() {
        return points.stream().mapToInt(Point::getY).min().orElse(0);
    }

    public int getRightBoundary() {
        return points.stream().mapToInt(Point::getX).max().orElse(0);
    }

    public int getLeftBoundary() {
        return points.stream().mapToInt(Point::getX).min().orElse(0);
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return makeDeepCopyOfPoints();
    }

    @Override
    public String toString() {
        return "Shape{name=" + name + ", points=" + points + '}';
    }

    public static ShapeBuilder builder() {
        return new ShapeBuilder();
    }

    public static class ShapeBuilder {
        private String name;
        private List<Point> points = new ArrayList<>();
        public ShapeBuilder name(String name) {
            this.name = name;
            return this;
        }
        public ShapeBuilder points(List<Point> points) {
            this.points = points;
            return this;
        }
        public Shape build() {
            return new Shape(name, points);
        }
    }

    private List<Point> makeDeepCopyOfPoints() {
        List<Point> result = new ArrayList<>();
        for (Point point : points) {
            result.add(Point.builder().x(point.getX()).y(point.getY()).build());
        }
        return result;
    }

}
