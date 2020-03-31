package sk.jmikus.areaplacer.service;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.*;

import sk.jmikus.areaplacer.exception.ValidationException;

@Service
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    public List<String> readFile(String path) {
        try {
            return Files.readAllLines(ResourceUtils.getFile(path).toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ValidationException("Input file cannot be read: " + path, e);
        }
    }

    public void writeFile(String path, List<String> data) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        try {
            Files.write(Paths.get(path), convertStringListToByteArray(data));
            log.info("Output written to file: {}", path);
        } catch (IOException e) {
            throw new ValidationException("Input file cannot be read: " + path, e);
        }
    }

    private byte[] convertStringListToByteArray(List<String> data) {
        return data.stream().collect(joining("\n")).getBytes();
    }

}
