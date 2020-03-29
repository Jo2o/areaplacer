package sk.jmikus.areaplacer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.jmikus.areaplacer.model.Room;

@Service
public class MyService {

    private final RoomService service;

    @Autowired
    public MyService(RoomService service) {
        this.service = service;
    }

    public void printMsg(String... msg) {
        System.out.println("Message: " + msg);
        Room room = service.loadRoom();
        System.out.println(room);
    }

}
