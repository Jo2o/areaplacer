package sk.jmikus.areaplacer.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    public List<String> readFile(String path) {

        List<String> result = new ArrayList<>();

        try {
            result = Files.readAllLines(ResourceUtils.getFile(path).toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Cannot read the file: {}", path);
        }

        return result;
    }

}
