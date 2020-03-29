package sk.jmikus.areaplacer.service;

import static java.util.stream.Collectors.toList;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.*;

@Service
public class PlacementService {

    private final AreaService areaService;
    private final ShapeService shapeService;

    @Autowired
    public PlacementService(AreaService areaService, ShapeService shapeService) {
        this.areaService = areaService;
        this.shapeService = shapeService;
    }

    public List<List<Shape>> calculatePlacementCombinations() {
        List<List<Shape>> results = new ArrayList<>();
        List<Shape> shapes = shapeService.loadShapes();
        Area area = areaService.loadArea();

        List<Shape> allPlacements = getAllPlacements(shapes.get(0), area);


        return results;
    }

    private List<Shape> getAllPlacements(Shape shape, Area area) {
        List<Shape> placements = new ArrayList<>();
        int moveRightCounter = 0;
        while (shape.getTopBoundary() <= area.getTopBoundary()) {
            while (shape.getRightBoundary() <= area.getRightBoundary()) {
                if (fitsInArea(shape, area)) {
                    placements.add(shape);
                }
                shape.moveRightByOne();
                moveRightCounter++;
            }
            shape.moveUpByOne();
            shape.moveLeft(moveRightCounter);
            moveRightCounter = 0;
        }
        return placements;
    }



    private boolean fitsInArea(Shape shape, Area area) {
        return area.getPoints().containsAll(shape.getPoints());
    }

    private Area getModifiedArea(Shape shape, Area area) {
        List<Point> originalAreaPoints = area.getPoints();
        List<Point> modifiedAreaPoints = originalAreaPoints.stream()
                .filter(point -> !shape.getPoints().contains(point))
                .collect(toList());
        return Area.builder().points(modifiedAreaPoints).build();
    }


}
