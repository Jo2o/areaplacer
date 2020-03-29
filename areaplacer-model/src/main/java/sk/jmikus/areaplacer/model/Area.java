package sk.jmikus.areaplacer.model;

import java.util.ArrayList;
import java.util.List;

public class Area {

    private final List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public int getWidth() {
        return points.stream().mapToInt(p -> p.getX() + 1).max().orElse(0);
    }

    public int getHeight() {
        return points.stream().mapToInt(p -> p.getY() + 1).max().orElse(0);
    }

    @Override
    public String toString() {
        return "Area{points=" + points + '}';
    }

}
