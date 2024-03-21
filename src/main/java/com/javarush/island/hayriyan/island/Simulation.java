package com.javarush.island.hayriyan.island;
import com.javarush.island.hayriyan.objects.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.config.Settings;
import com.javarush.island.hayriyan.services.ThreadService;
import com.javarush.island.hayriyan.utils.Helper;
import java.util.*;

public class Simulation {

    public static Location[][] island = new Location[Settings.WIDTH][Settings.HEIGHT];
    public static void start() {
        createIsland();
        ThreadService.startIslandLifeCycle();
    }

    private static void createIsland() {
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
    }

    private static void createAnimalList(ArrayList<Animal> animals, Class<?> animal, Integer count) {
        try {
            Animal currentAnimal = (Animal) animal.getDeclaredConstructor(UUID.class).newInstance(UUID.randomUUID());
            long countOfCurrentAnimalsInLocation = animals.stream()
                    .filter(a-> Objects.equals(Helper.getObjectsStaticPropertyByName(a, "name"), Helper.getObjectsStaticPropertyByName(currentAnimal, "name")))
                    .count();
            for (int i = 0; i < count; i++) {
                if (countOfCurrentAnimalsInLocation < (Integer)Helper.getObjectsStaticPropertyByName(currentAnimal, "maxAnimalsCountInLocation")) {
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
