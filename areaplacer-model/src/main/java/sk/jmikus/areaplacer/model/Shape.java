package sk.jmikus.areaplacer.model;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.*;

public class Shape {

    private final String name;
    private final List<Point> points;

    public Shape(String name, List<Point> points) {
        this.name = name;
        this.points = points;
    }

    public Shape moveRightByOne() {
        return builder().name(name)
                .points(makeDeepCopyOfPoints().stream()
                        .map(point -> { point.setX(point.getX() + 1); return point; })
                        .collect(toList()))
                .build();
    }

    public Shape moveUpByOne() {
        return builder().name(name)
                .points(makeDeepCopyOfPoints().stream()
                        .map(point -> { point.setY(point.getY() + 1); return point; })
                        .collect(toList()))
                .build();
    }

    public Shape moveLeft(int num) {
        return builder().name(name)
                .points(makeDeepCopyOfPoints().stream()
                        .map(point -> { point.setX(point.getX() - num); return point; })
                        .collect(toList()))
                .build();
    }

    public Shape moveDown(int num) {
        return builder().name(name)
                .points(makeDeepCopyOfPoints().stream()
                        .map(point -> { point.setY(point.getY() - num); return point; })
                        .collect(toList()))
                .build();
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

    public Point getBottomLeftPoint() {
        int bottom = getBottomBoundary();
        return points.stream()
                .filter(point -> point.getY() == bottom)
                .min(comparing(Point::getX))
                .orElse(Point.builder().x(0).y(0).build());
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
