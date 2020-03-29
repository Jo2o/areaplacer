package sk.jmikus.areaplacer.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.Area;
import sk.jmikus.areaplacer.model.Shape;

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

        Area area = areaService.loadArea();
        List<Shape> shapes = shapeService.loadShapes();

        for (Shape shape : shapes) {

        }

        return results;
    }

    private Shape placeShape(Shape shape, Area area) {
        return new Shape("name", Collections.EMPTY_LIST);
    }

}
