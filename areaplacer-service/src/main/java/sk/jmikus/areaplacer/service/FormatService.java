package sk.jmikus.areaplacer.service;

import java.util.*;

import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.Point;
import sk.jmikus.areaplacer.model.Shape;

@Service
public class FormatService {

    private final ShapeService shapeService;
    private final FileService fileService;

    public FormatService(ShapeService shapeService, FileService fileService) {
        this.shapeService = shapeService;
        this.fileService = fileService;
    }

    public List<String> formatOutput(List<List<Shape>> results) {
        List<String> outputLines = new ArrayList<>();
        Map<String, int[]> topLeftVectors = loadTopLeftVectors();
        for (List<Shape> result : results) {
            StringBuilder outputLine = new StringBuilder();
            for (Shape shape : result) {
                outputLine.append(shape.getName());
                outputLine.append('(');
                outputLine.append(3 - 1 - (shape.getBottomLeftPoint().getY() + topLeftVectors.get(shape.getName())[1])); // A
                outputLine.append(',');
                outputLine.append(shape.getBottomLeftPoint().getX() + topLeftVectors.get(shape.getName())[0]); // B
                outputLine.append(") ");
            }
            outputLines.add(outputLine.toString().trim());
        }
        return outputLines;
    }

    public Map<String, int[]> loadTopLeftVectors() {
        List<String> furnitureFileContent = fileService.readFile("classpath:input/inFurniture.txt");
        List<Shape> loadedShapes = parseShapes(furnitureFileContent);

        Map<String, int[]> topLeftVectors = new HashMap<>();
        for (String line : furnitureFileContent) {
            String name = Character.toString(line.charAt(0));
            topLeftVectors.put(name, parseTopLeftVector(line, loadedShapes));
        }
        return topLeftVectors;
    }

    /* Calculates vector from bottom-left point of the shape to top-left boundary.*/
    private int[] parseTopLeftVector(String line, List<Shape> loadedShapes) {
        String name = Character.toString(line.charAt(0));
        int width = Integer.parseInt(Character.toString(line.charAt(1)));
        int height = (line.length() - 2) / width;

        Shape currentShape = loadedShapes.stream()
                .filter(shape -> shape.getName().equals(name))
                .findAny().get();
        Point bottomLeftPoint = currentShape.getBottomLeftPoint();

        int[] topLeftVector = new int[2];
        topLeftVector[0] = 0 - bottomLeftPoint.getX();
        topLeftVector[1] = height - 1 - bottomLeftPoint.getY();
        return topLeftVector;
    }

    private List<Shape> parseShapes(List<String> furnitureFileContent) {
            List<Shape> shapes = new ArrayList<>();
            for (String line : furnitureFileContent) {
                shapes.add(parseShapeXy(Character.toString(line.charAt(0)),
                                        Integer.parseInt(Character.toString(line.charAt(1))),
                                        line.substring(2).toCharArray()));
            }
            return shapes;
    }

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

}
