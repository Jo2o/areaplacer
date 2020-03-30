package sk.jmikus.areaplacer.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.Shape;

@Service
public class MainService {

    private static final Logger log = LoggerFactory.getLogger(MainService.class);

    private final PlacementService placementService;
    private final PrintService printService;
    private final FormatService formatService;

    public MainService(PlacementService placementService, PrintService printService, FormatService formatService) {
        this.placementService = placementService;
        this.printService = printService;
        this.formatService = formatService;
    }

    public void invoke(String... args) {
        log.info("Program started with arguments: {}", Arrays.asList(args));
        printService.printArea();
        List<List<Shape>> results = placementService.calculatePlacementCombinations();
        printService.printResults(results);
        //formatService.formatOutput();
    }

}
