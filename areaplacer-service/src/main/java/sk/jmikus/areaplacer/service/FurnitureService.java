package sk.jmikus.areaplacer.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sk.jmikus.areaplacer.exception.ValidationException;
import sk.jmikus.areaplacer.model.*;

@Service
public class FurnitureService {

    private final FileService fileService;

    @Autowired
    public FurnitureService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<Shape> loadFurniture() {
        List<String> furnitureFileContent = fileService.readFile("classpath:input/inFurniture.txt");
        validateFurnitureFileContent(furnitureFileContent);
        try {
            List<Shape> furnitures = new ArrayList<>();
            for (String line : furnitureFileContent) {
                furnitures.add(parseFurniture(Integer.parseInt(Character.toString(line.charAt(1))),
                        line.substring(2).toCharArray()));
            }
            return furnitures;
        } catch (RuntimeException e) {
            throw new ValidationException("Furniture parse exception!", e);
        }
    }

    private Shape parseFurniture(int width, char[] characters) {
        validateFurniture(width, characters);
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
                        .y(length - 1 - y)
                        .build());
            }
            x++;
        }
        return Shape.builder().points(shapePoints).build();
    }

    private void validateFurnitureFileContent(List<String> furnitureFileContent) {
        if (CollectionUtils.isEmpty(furnitureFileContent)) {
            throw new ValidationException("Furniture is null or empty!");
        }
    }

    private void validateFurniture(int width, char[] furnitureCharacters) {
        if ((furnitureCharacters.length % width) != 0) {
            throw new ValidationException("Found invalid furniture: " + Arrays.toString(furnitureCharacters));
        }
    }

}
