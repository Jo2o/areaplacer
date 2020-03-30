package sk.jmikus.areaplacer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sk.jmikus.areaplacer.service.MainService;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private final MainService mainService;

    public Application(MainService mainService) {
        this.mainService = mainService;
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        mainService.invoke(args);
    }

}
