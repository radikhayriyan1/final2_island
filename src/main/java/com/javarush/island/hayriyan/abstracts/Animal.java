package com.javarush.island.hayriyan.abstracts;
import com.javarush.island.hayriyan.island.Location;
import com.javarush.island.hayriyan.config.Settings;
import com.javarush.island.hayriyan.island.Simulation;
import com.javarush.island.hayriyan.utils.Helper;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal extends IslandObject {

    public void eat(Animal animal, Location location, ArrayList<IslandObject> objects) {
        IslandObject objectToEat = null;
        if (!objects.isEmpty()) {
            objectToEat = getPriorityObject(objects, animal);
        }

        if (objectToEat != null) {
            double amountToEat = Math.min(objectToEat.weight, (Double) Helper.getObjectsStaticPropertyByName(animal, "satisfiedKg"));
            animal.weight += amountToEat / 10;
            objectToEat.die(objectToEat, location);
        } else {
            animal.weight -= animal.weight / 10;
        }

        if (animal.weight <= (Double) Helper.getObjectsStaticPropertyByName(animal, "minimumWeight")) {
            animal.die(animal, location);
        }
    }

    public void move(Animal animal, Location location) {
        int movesToAdd = ThreadLocalRandom.current().nextInt((Integer) Helper.getObjectsStaticPropertyByName(animal, "maxMoveCount") + 1);
        int newX = location.x;
        int newY = location.y;
        for (int i = 0; i < movesToAdd; i++) {
            if (newX == Settings.WIDTH - 1 && newY == Settings.HEIGHT - 1) {
                newY--;
            } else if (newY < Settings.HEIGHT - 1) {
                newY++;
            } else if (newX < Settings.WIDTH - 1) {
                newX++;
            }
        }
        if (Simulation.island[newX][newY].animals.size() < (Integer) Helper.getObjectsStaticPropertyByName(animal, "maxAnimalsCountInLocation")) {
            location.animals.removeIf(a -> a.id == animal.id);
            Simulation.island[newX][newY].animals.add(animal);
        }
    }

    public void multiply(Animal animal, ArrayList<Animal> animals) {
        try {
            ArrayList<Animal> sameAnimals = animals.stream()
                    .filter(a -> Objects.equals(Helper.getObjectsStaticPropertyByName(a, "name"), Helper.getObjectsStaticPropertyByName(animal, "name")))
                    .collect(Collectors.toCollection(ArrayList::new));

            int animalsCount = sameAnimals.size();
            int count = animalsCount / 2;
            for (int i = 0; i < count; i++) {
                if (animalsCount < (Integer) Helper.getObjectsStaticPropertyByName(animal, "maxAnimalsCountInLocation")) {
                    animalsCount ++;
                    animals.add(animal.getClass().getConstructor(UUID.class).newInstance(UUID.randomUUID()));
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to create Animal: " + e.getMessage());
        }
    }


    private IslandObject getPriorityObject(List<IslandObject> objects, Animal animal) {
        Map<String, Integer> currentPredatorPriority = Settings.ANIMAL_EATING_PRIORITY.get(Helper.getObjectsStaticPropertyByName(animal, "name"));
        if (currentPredatorPriority == null) {
            return null;
        }

        int randomInt = ThreadLocalRandom.current().nextInt(101);
        IslandObject object = null;
        int maxPriority = 0;

        for (IslandObject h : objects) {
            Integer priority = currentPredatorPriority.get(Helper.getObjectsStaticPropertyByName(h, "name"));
            if (priority != null && priority >= maxPriority && priority >= randomInt) {
                object = h;
                maxPriority = priority;
            }
        }

        if (object == null) {
            for (IslandObject h : objects) {
                Integer priority = currentPredatorPriority.get(Helper.getObjectsStaticPropertyByName(h, "name"));
                if (priority != null) {
                    object = h;
                    break;
                }
            }
        }

        return object;
    }
}
