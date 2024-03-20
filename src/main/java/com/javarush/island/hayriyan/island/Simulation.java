package com.javarush.island.hayriyan.island;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import com.javarush.island.hayriyan.objects.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.config.Settings;
import java.util.*;

public class Simulation {

    public static Location[][] island = new Location[Settings.WIDTH][Settings.HEIGHT];
    public static void start() {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                ArrayList<Animal> animals = new ArrayList<>();
                ArrayList<Plant> plants = new ArrayList<>();
                for (Map.Entry<Class<?>, Integer> entry : Settings.ANIMALS_COUNT.entrySet()) {
                    Class<?> animal = entry.getKey();
                    Integer count = entry.getValue();
                    createAnimalList(animals, animal, count);
                }
                createPlantList(plants);
                island[i][j] = new Location(
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
                for (Location[] location : island) {
                    for (Location value : location) {
                        for (Animal animal : value.animals) {
                            if (animal instanceof Herbivores) {
                                ((Herbivores) animal).herbivoresEat(animal, value);
                            } else {
                                ((Predator) animal).predatorEat(animal, value);
                            }
                        }
                    }
                }
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Location[] location : island) {
                    for (Location value : location) {
                        createPlantList(value.plants);
                    }
                }
            }
        }, 0, 2000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Location[] location : island) {
                    for (Location value : location) {
                        ArrayList<Animal> uniqueAnimals = new ArrayList<>();
                        ArrayList<Animal> animals = value.animals;
                        for (Animal item : animals) {
                            boolean found = false;
                            for (Animal animal : uniqueAnimals) {
                                if (animal.emoji.equals(item.emoji)) {
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
        }, 0, 4000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Location[][] copyArray = Arrays.stream(island).map(Location[]::clone).toArray(Location[][]::new);
                for (int i = 0; i < copyArray.length; i++) {
                    for (int j = 0; j < copyArray[i].length; j++) {
                        for (int k = 0; k < copyArray[i][j].animals.size(); k++) {
                            Animal animal = island[i][j].animals.get(k);
                            animal.move(animal, island[i][j]);
                        }
                    }
                }
            }
        }, 0, 8000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Simulation.showResult();
            }
        }, 0, 1000);
    }

    public static void showResult() {
        for (Location[] location : island) {
            for (Location value : location) {
                System.out.print(value.getObjects() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void createAnimalList(ArrayList<Animal> animals, Class<?> animal, Integer count) {
        try {
            Animal currentAnimal = (Animal) animal.getDeclaredConstructor(UUID.class).newInstance(UUID.randomUUID());
            long countOfCurrentAnimalsInLocation = animals.stream()
                    .filter(a-> a.name.equals(currentAnimal.name))
                    .count();
            for (int i = 0; i < count; i++) {
                if (countOfCurrentAnimalsInLocation < currentAnimal.maxAnimalsCountInLocation) {
                    countOfCurrentAnimalsInLocation++;
                    animals.add((Animal) animal.getDeclaredConstructor(UUID.class).newInstance(UUID.randomUUID()));
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to create Animal: " + e.getMessage());
        }
    }

    private static void createPlantList(ArrayList<Plant> plants) {
        for (int i = 0; i < Settings.PLANTS_COUNT; i++) {
            if (plants.size() < Settings.maxPlantsCountInLocation) {
                plants.add(new Plant(UUID.randomUUID()));
            }
        }
    }
}
