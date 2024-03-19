package com.javarush.island.hayriyan.simulation;

import com.javarush.island.hayriyan.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;

import java.util.*;

public class Simulation {

    public static Location[][] locations = new Location[Settings.WIDTH][Settings.HEIGHT];
    public static void start() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                ArrayList<Animal> animals = new ArrayList<>();
                ArrayList<Plant> plants = new ArrayList<>();
                for (Map.Entry<Class<?>, Integer> entry : Settings.ANIMALS_COUNT.entrySet()) {
                    Class<?> animal = entry.getKey();
                    Integer count = entry.getValue();
                    createAnimalList(animals, animal, count);
                }
                createPlantList(plants, Settings.PLANTS_COUNT);
                locations[i][j] = new Location(
                        animals,
                        plants,
                        i, j, UUID.randomUUID()
                );
            }
        }
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Location[] location : locations) {
                    for (Location value : location) {
                        for (Animal animal : value.animals) {
                            animal.eat(animal, value);
                        }
                    }
                }
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Location[] location : locations) {
                    for (Location value : location) {
                        createPlantList(value.plants, Settings.PLANTS_COUNT);
                    }
                }
            }
        }, 2000, 2000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Location[] location : locations) {
                    for (Location value : location) {
                        ArrayList<Animal> uniqueAnimals = new ArrayList<>();
                        ArrayList<Animal> animals = value.animals;
                        for (Animal item : animals) {
                            boolean found = false;
                            for (Animal animal : uniqueAnimals) {
                                if (animal.image.equals(item.image)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                uniqueAnimals.add(item);
                            }
                        }
                        for (Animal animal : uniqueAnimals) {
                            animal.multiply(animal, animals);
                        }
                    }
                }
            }
        }, 4000, 4000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Location[][] copyArray = Arrays.stream(locations).map(Location[]::clone).toArray(Location[][]::new);
                for (int i = 0; i < copyArray.length; i++) {
                    for (int j = 0; j < copyArray[i].length; j++) {
                        for (int k = 0; k < copyArray[i][j].animals.size(); k++) {
                            Animal animal = locations[i][j].animals.get(k);
                            animal.move(animal, locations[i][j]);
                        }
                    }
                }
            }
        }, 6000, 6000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Simulation.showResult();
            }
        }, 2000, 2000);
    };

    public static void showResult() {
        for (Location[] location : locations) {
            for (Location value : location) {
                System.out.print(value.getObjects() + " ");
            }
            System.out.println();
        }
        System.out.println();
    };

    public static void createAnimalList(ArrayList<Animal> animals, Class<?> animal, Integer count) {
        try {
            for (int i = 0; i < count; i++) {
                animals.add((Animal) animal.getDeclaredConstructor(UUID.class).newInstance(UUID.randomUUID()));
            }
        } catch (Exception e) {
            System.out.println("Failed to create Animal: " + e.getMessage());
        }
    };

    private static void createPlantList(ArrayList<Plant> plants, Integer count) {
        for (int i = 0; i < count; i++) {
            plants.add(new Plant(UUID.randomUUID()));
        }
    };
}
