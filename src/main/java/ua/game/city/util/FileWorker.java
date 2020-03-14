package ua.game.city.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FileWorker {

    private static final String CITIES_LIST = "src/main/resources/static/cities.txt";

    public static Set<String> getListOfCities() {
        Set<String> cities = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CITIES_LIST));
            cities = reader.lines().map(String::strip).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
