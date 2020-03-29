package sk.jmikus.areaplacer.model;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public String toString() {
        return "Room{points=" + points + '}';
    }

}
