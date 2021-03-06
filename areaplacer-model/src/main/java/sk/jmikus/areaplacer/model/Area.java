package sk.jmikus.areaplacer.model;

import java.util.*;

public class Area {

    private final List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public int getRightBoundary() {
        return points.stream().mapToInt(Point::getX).max().orElse(0);
    }

    public int getTopBoundary() {
        return points.stream().mapToInt(Point::getY).max().orElse(0);
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Area{points=" + points + '}';
    }

    public static AreaBuilder builder() {
        return new AreaBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Area area = (Area) o;
        return Objects.equals(getPoints(), area.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }

    public static class AreaBuilder {
        private List<Point> points = new ArrayList<>();
        public AreaBuilder points(List<Point> points) {
            this.points = points;
            return this;
        }
        public Area build() {
            Area area = new Area();
            points.forEach(area::addPoint);
            return area;
        }
    }

}
