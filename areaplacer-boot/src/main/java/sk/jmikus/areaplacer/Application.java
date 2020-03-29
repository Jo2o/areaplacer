package sk.jmikus.areaplacer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sk.jmikus.areaplacer.service.MyService;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private final MyService myService;

    @Autowired
    public Application(MyService myService) {
        this.myService = myService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        myService.printMsg(args);
    }

}
