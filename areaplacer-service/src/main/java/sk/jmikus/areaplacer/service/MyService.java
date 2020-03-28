package sk.jmikus.areaplacer.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void printMsg(String... msg) {
        System.out.println("Message: " + msg);
    }

}
