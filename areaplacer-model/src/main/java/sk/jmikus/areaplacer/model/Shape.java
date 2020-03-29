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

    public void moveLeftByOne() {
        points.forEach(point -> point.setX(point.getX() + 1));
    }

    public void moveUpByOne() {
        points.forEach(point -> point.setY(point.getY() + 1));
    }

    public List<Point> getPoints() {
        return points;
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

}
