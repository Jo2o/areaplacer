package sk.jmikus.areaplacer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final FileService fileService;

    @Autowired
    public MyService(FileService fileService) {
        this.fileService = fileService;
    }

    public void printMsg(String... msg) {
        System.out.println("Message: " + msg);
        List<String> roomFileContent = fileService.readFile("classpath:input/inRoom.txt");
        System.out.println(roomFileContent);
    }

}
