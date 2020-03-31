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

    private final ArgumentService argumentService;
    private final PlacementService placementService;
    private final PrintService printService;
    private final OutputService outputService;
    private final FileService fileService;

    public MainService(PlacementService placementService, PrintService printService, OutputService outputService,
            ArgumentService argumentService, FileService fileService) {
        this.argumentService = argumentService;
        this.placementService = placementService;
        this.printService = printService;
        this.outputService = outputService;
        this.fileService = fileService;
    }

    public void invoke(String... args) {
        log.info("Program started with arguments: {}", Arrays.asList(args));

        String roomPath = argumentService.getArgument(0, args);
        String furniturePath = argumentService.getArgument(1, args);
        String outputPath = argumentService.getArgument(2, args);

        List<List<Shape>> results = placementService.calculatePlacementCombinations(furniturePath, roomPath);
        printService.printArea(roomPath);
        printService.printResults(results, roomPath);

        List<String> output = outputService.formatOutput(results, outputPath);
        fileService.writeFile(outputPath, output);
    }

}
