package sk.jmikus.areaplacer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.*;

@Service
public class PrintService {

    private final AreaService areaService;

    public PrintService(AreaService areaService) {
        this.areaService = areaService;
    }

    public void printArea() {
        Area area = areaService.loadArea();
        List<Point> areaPoints =  area.getPoints();
        System.out.print("AREA: \n-----");
        int currentPointIdx = 0;
        Point currentPoint = areaPoints.get(currentPointIdx);
        for (int i = area.getTopBoundary(); i >= 0; i--) {
            System.out.print('\n');
            for (int j = 0; j <= area.getRightBoundary(); j++) {
                if(currentPoint.getX() == j) {
                    System.out.print('□');
                    currentPointIdx++;
                    if (currentPointIdx < areaPoints.size()) {
                        currentPoint = areaPoints.get(currentPointIdx);
                    } else {
                        System.out.print(".\n");
                        break;
                    }
                } else {
                    System.out.print('.');
                }
            }
        }
    }

    public void printResults(List<List<Shape>> results) {
        Area area = areaService.loadArea();
        List<Point> areaPoints =  area.getPoints();
        int resultCounter = 1;
        for (List<Shape> result : results) {
            System.out.print("\nRESULT "+ resultCounter + ": \n--------");
            int currentPointIdx = 0;
            Point currentPoint = areaPoints.get(currentPointIdx);
            for (int i = area.getTopBoundary(); i >= 0; i--) {
                System.out.print('\n');
                for (int j = 0; j <= area.getRightBoundary(); j++) {
                    if(currentPoint.getX() == j) {
                        System.out.print(getSign(result, i, j));
                        currentPointIdx++;
                        if (currentPointIdx < areaPoints.size()) {
                            currentPoint = areaPoints.get(currentPointIdx);
                        } else {
                            System.out.print(".\n");
                            break;
                        }
                    } else {
                        System.out.print('.');
                    }
                }
            }
            resultCounter++;
        }
    }

    private char getSign(List<Shape> shapes, int i, int j) {
        for (Shape shape : shapes) {
            for (Point point : shape.getPoints()) {
                if ((point.getX() == j) && (point.getY() == i)) {
                    return shape.getName().charAt(0);
                }
            }
        }
        return '□';
    }

}
