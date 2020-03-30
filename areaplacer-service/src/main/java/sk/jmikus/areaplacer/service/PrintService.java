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

    public void print(List<List<Shape>> results) {
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
}
