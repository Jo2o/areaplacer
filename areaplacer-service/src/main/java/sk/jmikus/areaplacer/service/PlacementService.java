package sk.jmikus.areaplacer.service;

import static java.util.stream.Collectors.toList;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sk.jmikus.areaplacer.model.*;

@Service
public class PlacementService {

    private final AreaService areaService;
    private final ShapeService shapeService;

    private List<List<Shape>> results = new ArrayList<>();

    @Autowired
    public PlacementService(AreaService areaService, ShapeService shapeService) {
        this.areaService = areaService;
        this.shapeService = shapeService;
    }

    public List<List<Shape>> calculatePlacementCombinations() {
        gatherResultsRecursively(
                ListWithPointer.builder()
                        .pointer(0)
                        .shapes(shapeService.loadShapes())
                        .build(),
                areaService.loadArea());
        return results;
    }

    private void gatherResultsRecursively(ListWithPointer shapes, Area area) {
        if (shapes.getShapes().isEmpty()) {
            return;
        }
        Shape currentShape = shapes.getShapes().get(shapes.getPointer());
        List<Shape> allShapePlacements = getAllPlacements(currentShape, area);
        if (CollectionUtils.isEmpty(allShapePlacements)) {
            return;
        }
        if (shapes.getPointer() == (shapes.getShapes().size() - 1)) {
            for (Shape lastResultShape : allShapePlacements) {
                results.add(createOneResultCombination(lastResultShape, shapes));
            }
            return;
        }
        for (Shape shape : allShapePlacements) {
            int pointer = shapes.getPointer();
            pointer++;
            gatherResultsRecursively(
                    ListWithPointer.builder()
                            .pointer(pointer)
                            .shapes(shapes.getShapes())
                            .build(),
                    getModifiedArea(shape, area));
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

    private List<Shape> createOneResultCombination() {

    }

}
