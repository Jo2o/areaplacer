package sk.jmikus.areaplacer.service;

import java.util.*;

import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.Point;
import sk.jmikus.areaplacer.model.Shape;

@Service
public class OutputService {

    private final FileService fileService;
    private final ShapeService shapeService;

    public OutputService(FileService fileService, ShapeService shapeService) {
        this.fileService = fileService;
        this.shapeService = shapeService;
    }

    public List<String> formatOutput(List<List<Shape>> results, String shapesPath, String roomPath) {
        List<String> outputLines = new ArrayList<>();
        Map<String, int[]> topLeftVectors = loadTopLeftVectors(shapesPath);
        int roomHeight = loadRoomHeight(roomPath);
        for (List<Shape> result : results) {
            StringBuilder outputLine = new StringBuilder();
            for (Shape shape : result) {
                outputLine.append(shape.getName());
                outputLine.append('(');
                outputLine.append(roomHeight - 1 - (shape.getBottomLeftPoint().getY() + topLeftVectors.get(shape.getName())[1])); // A
                outputLine.append(',');
                outputLine.append(shape.getBottomLeftPoint().getX() + topLeftVectors.get(shape.getName())[0]); // B
                outputLine.append(") ");
            }
            outputLines.add(outputLine.toString().trim());
        }
        return outputLines;
    }

    private int loadRoomHeight(String roomPath) {
        List<String> roomFileContent = fileService.readFile(roomPath);
        return Integer.parseInt(roomFileContent.get(0).split(",")[0].trim());
    }

    /**
     * As '.' information was stripped off while loading the shapes, this function counts vectors
     * from bottom-left shape's point to top-left shape's boundary with '.' included.
     * These vectors are then used to recalculate the original top-left coordinates within the area
     * for requested output format.
     */
    public Map<String, int[]> loadTopLeftVectors(String shapesPath) {
        List<String> furnitureFileContent = fileService.readFile(shapesPath);
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

        Point bottomLeftPoint = loadedShapes.stream()
                .filter(shape -> shape.getName().equals(name))
                .findAny()
                .map(Shape::getBottomLeftPoint)
                .orElseThrow();

        int[] topLeftVector = new int[2];
        topLeftVector[0] = 0 - bottomLeftPoint.getX();
        topLeftVector[1] = height - 1 - bottomLeftPoint.getY();
        return topLeftVector;
    }

    private List<Shape> parseShapes(List<String> furnitureFileContent) {
            List<Shape> shapes = new ArrayList<>();
            for (String line : furnitureFileContent) {
                shapes.add(
                        shapeService.parseShapeXy(Character.toString(line.charAt(0)),
                                                  Integer.parseInt(Character.toString(line.charAt(1))),
                                                  line.substring(2).toCharArray()));
            }
            return shapes;
    }

}
