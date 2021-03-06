package ua.game.city.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.game.city.util.FileWorker;

@RestController
@RequestMapping
public class GameController {
    private List<String> cities;
    private String city;

    @PostConstruct
    public void init() {
        cities = new ArrayList<>(FileWorker.getListOfCities());
    }

    @GetMapping("/begin")
    public String runGame() {
        int randomCity = new Random().nextInt(cities.size());
        city = cities.get(randomCity);
        cities.remove(randomCity);
        return city;
    }

    @GetMapping("/next")
    public String processGame(@RequestParam String word) {
        if (city == null) {
            return "Игра ещё не началась!";
        }
        if (word.length() < 1) {
            return "Название города слишком маленькое";
        }
        return nextCity(word);
    }

    @PostMapping("/end")
    public String finishGame() {
        return "Спасибо за игру!";
    }

    private String nextCity(String userWord) {
        String userCity = getUserCity(userWord);
        if (city.equals(userCity)) {
            return "Вы не угадали, попробуйте еще!";
        }
        if (userWord.substring(0, 1).equalsIgnoreCase(city.substring(city.length() - 1))) {
            if (isLetterPresent(userCity) && findNewCity(userCity)) {
                city = cities.stream()
                        .filter(s -> s.substring(0, 1).equalsIgnoreCase(userCity.substring(userCity.length() - 1)))
                        .findFirst().get();
                cities.remove(city);
                return city;
            }
            return "Система не знает городов на такую букву. Вы выиграли \n" + finishGame();
        } else {
            return "Вы ввели слово не на ту букву";
        }
    }

    private String getUserCity(String userWord) {
        String userCity = cities.stream()
                .filter(s -> s.equalsIgnoreCase(userWord))
                .findFirst()
                .orElse(city);
        cities.remove(userCity);
        return userCity;
    }

    private boolean findNewCity(String userCity) {
        return cities.stream()
                .anyMatch(s -> s.substring(0, 1).equalsIgnoreCase(userCity.substring(userCity.length() - 1)));
    }

    private boolean isLetterPresent(String word) {
        long countCities = cities.stream()
                .filter(s -> s.substring(0, 1).equalsIgnoreCase(word.substring(word.length() - 1)))
                .count();
        return countCities > 0;
    }

}
