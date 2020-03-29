package sk.jmikus.areaplacer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sk.jmikus.areaplacer.exception.ValidationException;
import sk.jmikus.areaplacer.model.*;

@Service
public class RoomService {

    private final FileService fileService;

    @Autowired
    public RoomService(FileService fileService) {
        this.fileService = fileService;
    }

    public Room loadRoom() {
        List<String> roomFileContent = fileService.readFile("classpath:input/inRoom.txt");
        validateRoomFileContent(roomFileContent);

        /* Add points of the room TOP to BOTTOM and from LEFT to RIGHT. */
        Room room = new Room();
        for (int i = 1; i < roomFileContent.size(); i++) {
            String line = roomFileContent.get(i);
            char[] lineChars = line.toCharArray();
            for (int j = 0; j < lineChars.length; j++) {
                if (lineChars[j] == '#') {
                    room.addPoint(Point.builder()
                            .x(j)
                            .y(roomFileContent.size() - 1 - i)
                            .build());
                }
            }
        }

        return room;
    }

    private void validateRoomFileContent(List<String> roomFileContent) {
        if (CollectionUtils.isEmpty(roomFileContent)) {
            throw new ValidationException("Room is null or empty!");
        }
        String roomSize = roomFileContent.get(0);
        int rows = Integer.parseInt(roomSize.split(",")[0]);
        if (roomFileContent.size() != (rows + 1)) {
            throw new ValidationException("Room does not have correct count of lines "
                    + "- should have exactly " + (rows + 1) + " lines!");
        }
        int columns = Integer.parseInt(roomSize.split(",")[1]);
        roomFileContent.stream()
                .skip(1)
                .map(String::length)
                .filter(integer -> integer != columns)
                .findAny()
                .ifPresent(o -> { throw new ValidationException("Room does not have correct count of columns "
                        + "- should have exactly " + columns + " columns on every line!"); });
    }

}
