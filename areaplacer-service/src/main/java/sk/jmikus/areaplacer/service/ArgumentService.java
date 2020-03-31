package sk.jmikus.areaplacer.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ArgumentService {

    public String getArgument(int position, String... args) {
        List<String> argsList = Arrays.asList(args);
        if (position > (argsList.size() - 1)) {
            return "";
        }
        return argsList.get(position);
    }

}
