package sk.jmikus.areaplacer.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.Room;
import sk.jmikus.areaplacer.model.Shape;

@Service
public class MainService {

    private static final Logger log = LoggerFactory.getLogger(MainService.class);

    private final RoomService roomService;
    private final FurnitureService furnitureService;

    @Autowired
    public MainService(RoomService roomService, FurnitureService furnitureService) {
        this.roomService = roomService;
        this.furnitureService = furnitureService;
    }

    public void invoke(String... args) {
        log.info("Program started with arguments: {}", Arrays.asList(args));
        Room room = roomService.loadRoom();
        log.info("ROOM: {}", room);
        List<Shape> furniture = furnitureService.loadFurniture();
        log.info("FURNITURE: {}", furniture);
    }

}
