package ua.game.city.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public class FileWorker {

    private static final String CITIES_LIST = "src/main/resources/static/cities.txt";

    public static Set<String> getListOfCities() {
        Set<String> cities = new HashSet<>();
        String thisLine;
        try {
            Reader reader = new FileReader(CITIES_LIST);
            BufferedReader br = new BufferedReader(reader);
            while ((thisLine = br.readLine()) != null) {
                cities.add(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
