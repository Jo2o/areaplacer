package sk.jmikus.areaplacer.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sk.jmikus.areaplacer.exception.ValidationException;
import sk.jmikus.areaplacer.model.Point;
import sk.jmikus.areaplacer.model.Shape;

@Service
public class ShapeService {

    private static final Logger log = LoggerFactory.getLogger(ShapeService.class);

    private final FileService fileService;

    public ShapeService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<Shape> loadShapes(String filePath) {
        List<String> furnitureFileContent = fileService.readFile(filePath);
        validateFurnitureFileContent(furnitureFileContent);
        try {
            List<Shape> shapes = new ArrayList<>();
            for (String line : furnitureFileContent) {
                String name = Character.toString(line.charAt(0));
                int width = Integer.parseInt(Character.toString(line.charAt(1)));
                char[] shapeData = line.substring(2).toCharArray();

                validateFurniture(name, width, shapeData);
                Shape parsedShape = parseShapeXy(name, width, shapeData);
                Shape shapeMovedToOrigin = moveCloseToOrigin(name, parsedShape.getPoints());
                shapes.add(shapeMovedToOrigin);
            }
            log.info("Shapes loaded successfully from: {}", "file");
            return shapes;
        } catch (RuntimeException e) {
            throw new ValidationException("Furniture parse exception!", e);
        }
    }

    /**
     * Function parses shapes into Cartesian XY coordinates.
     * It parses only '#' characters.
     */
    public Shape parseShapeXy(String name, int width, char[] characters) {
        int length = characters.length / width;
        int x = 0;
        int y = 0;
        List<Point> shapePoints = new ArrayList<>();
        for (int i = 0; i < characters.length; i++) {
            if ((i != 0) && ((i % width) == 0)) {
                x = 0;
                y++;
            }
            if (characters[i] == '#') {
                shapePoints.add(Point.builder()
                        .x(x)
                        .y(length - 1 - y) // this makes Y to go from DOWN to UP
                        .build());
            }
            x++;
        }
        return Shape.builder()
                .name(name)
                .points(shapePoints).build();
    }

    /**
     * This strips off unnecessary '.' between [0,0] and bottom-left corner of the shape.
     */
    private Shape moveCloseToOrigin(String name, List<Point> points) {
        Shape shape = Shape.builder().name(name).points(points).build();
        int minX = points.stream().mapToInt(Point::getX).min().orElse(0);
        int minY = points.stream().mapToInt(Point::getY).min().orElse(0);
        shape = shape.moveDown(minY);
        shape = shape.moveLeft(minX);
        return shape;
    }

    private void validateFurnitureFileContent(List<String> furnitureFileContent) {
        if (CollectionUtils.isEmpty(furnitureFileContent)) {
            throw new ValidationException("Furniture is null or empty!");
        }
    }

    private void validateFurniture(String name, int width, char[] furnitureCharacters) {
        if ((furnitureCharacters.length % width) != 0) {
            throw new ValidationException("Found invalid furniture: " + name + Arrays.toString(furnitureCharacters));
        }
    }

}
