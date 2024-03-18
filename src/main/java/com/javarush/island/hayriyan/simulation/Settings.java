package com.javarush.island.hayriyan.simulation;

import com.javarush.island.hayriyan.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.animals.herbivores.Goat;
import com.javarush.island.hayriyan.animals.herbivores.Rabbit;
import com.javarush.island.hayriyan.animals.predator.Wolf;

import java.util.*;

public class Settings {
    public static Integer width = 3;
    public static Integer height = 3;
    public static Integer plantsCount = 2;

    public static Location[][] locations = new Location[height][width];
    private static final Map<Animal, Integer> objectsCount = new HashMap<>() {{
        put(new Wolf(UUID.randomUUID()), 2);
        put(new Goat(UUID.randomUUID()), 2);
        put(new Rabbit(UUID.randomUUID()), 2);
    }};

    public static void start() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                ArrayList<Animal> animals = new ArrayList<>();
                ArrayList<Plant> plants = new ArrayList<>();
                for (Map.Entry<Animal, Integer> entry : objectsCount.entrySet()) {
                    Animal animal = entry.getKey();
                    Integer count = entry.getValue();
                    createAnimalList(animals, animal, count);
                }
                createPlantList(plants, plantsCount);
                locations[i][j] = new Location(
                        animals,
                        plants,
                        i, j, UUID.randomUUID()
                );
            }
        }
        showResult();
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
                showResult();
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Location[] location : locations) {
                    for (Location value : location) {
                        createPlantList(value.plants, plantsCount);
                    }
                }
            }
        }, 3000, 3000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("multiply");
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
        }, 6000, 6000);
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

    public static final Map<String, Map<String, Integer>> predatorPriority = new HashMap<>() {{
        put("wolf", new HashMap<>() {{
            put("rabbit", 60);
            put("goat", 61);
        }});
    }};

    public static void createAnimalList(ArrayList<Animal> animals, Animal animal, Integer count) {
        try {
            for (int i = 0; i < count; i++) {
                animals.add(animal.getClass().getConstructor(UUID.class).newInstance(UUID.randomUUID()));
            }
        }
        catch (Exception ignored) { }
    };

    private static void createPlantList(ArrayList<Plant> plants, Integer count) {
        try {
            for (int i = 0; i < count; i++) {
                plants.add((Plant) ((Class<?>) Plant.class).getDeclaredConstructor(UUID.class).newInstance(UUID.randomUUID()));
            }
        }
        catch (Exception ignored) { }
    };
}
