package sk.jmikus.areaplacer.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import sk.jmikus.areaplacer.exception.ValidationException;

@Service
public class FileService {

    public List<String> readFile(String path) {
        try {
            return Files.readAllLines(ResourceUtils.getFile(path).toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ValidationException("File cannot be read: " + path, e);
        }
    }

}
