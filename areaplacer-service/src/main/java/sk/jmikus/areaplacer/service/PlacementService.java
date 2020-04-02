package sk.jmikus.areaplacer.service;

import static java.util.stream.Collectors.toList;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sk.jmikus.areaplacer.exception.ValidationException;
import sk.jmikus.areaplacer.model.*;

@Service
public class PlacementService {

    private static final int AREA_POINTS_LIMIT = 1600;
    private static final int SHAPES_LIMIT = 26;

    private final AreaService areaService;
    private final ShapeService shapeService;

    private List<List<Shape>> results = new ArrayList<>();
    private Deque<Shape> currentResult = new ArrayDeque<>();

    public PlacementService(AreaService areaService, ShapeService shapeService) {
        this.areaService = areaService;
        this.shapeService = shapeService;
    }

    public List<List<Shape>> calculatePlacementCombinations(String shapesPath, String areaPath) {
        List<Shape> shapes = shapeService.loadShapes(shapesPath);
        Area area = areaService.loadArea(areaPath);
        validateLimits(shapes.size(), area.getPoints().size());
        gatherResultsRecursively(ListWithPointer.builder().pointer(0).shapes(shapes).build(), area);
        return results;
    }

    private void validateLimits(int shapeTypesCount, int areaPointsCount) {
        if (shapeTypesCount > SHAPES_LIMIT) {
            throw new ValidationException("Program does not support more than " + SHAPES_LIMIT + " different shapes count.");
        }
        if (areaPointsCount > AREA_POINTS_LIMIT) {
            throw new ValidationException("Program does not support area having more than " + AREA_POINTS_LIMIT + " points.");
        }
    }

    private void gatherResultsRecursively(ListWithPointer shapes, Area area) {
        if (shapes.getShapes().isEmpty()) {
            return;
        }
        Shape currentShape = shapes.getShapes().get(shapes.getPointer());
        List<Shape> allShapePlacements = getAllPlacements(currentShape, area);
        if (CollectionUtils.isEmpty(allShapePlacements)) {      // the shape could not be placed = go back to try others
            if (!currentResult.isEmpty()) {
                currentResult.pop();
            }
            return;
        }
        if (shapes.getPointer() == (shapes.getShapes().size() - 1)) {
            for (Shape lastResultShape : allShapePlacements) {  // found fitting result combination when the last shape placed successfully
                currentResult.push(lastResultShape);
                results.add(new ArrayList<>(currentResult));
                currentResult.pop();
            }
            return;
        }
        for (Shape shape : allShapePlacements) {
            int pointer = shapes.getPointer();
            pointer++;
            currentResult.push(shape);
            gatherResultsRecursively(
                    ListWithPointer.builder()
                            .pointer(pointer)
                            .shapes(shapes.getShapes())
                            .build(),
                    getModifiedArea(shape, area));
            if (!currentResult.isEmpty()) {
                currentResult.pop();
            }
        }
    }

    private List<Shape> getAllPlacements(Shape shape, Area area) {
        List<Shape> placements = new ArrayList<>();
        int moveRightCounter = 0;
        while (shape.getTopBoundary() <= area.getTopBoundary()) {
            while (shape.getRightBoundary() <= area.getRightBoundary()) {
                if (fitsInArea(shape, area)) {
                    placements.add(Shape.builder()
                            .name(shape.getName())
                            .points(shape.getPoints())
                            .build());
                }
                shape = shape.moveRightByOne();
                moveRightCounter++;
            }
            shape = shape.moveUpByOne();
            shape = shape.moveLeft(moveRightCounter);
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
