package com.javarush.island.hayriyan.abstracts;
import com.javarush.island.hayriyan.island.Location;
import com.javarush.island.hayriyan.config.Settings;
import com.javarush.island.hayriyan.island.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal extends IslandObject {
    public Double satisfiedKg;

    public Double minimumWeight;

    public Integer maxMoveCount;

    public Integer maxAnimalsCountInLocation;
    public void eat(Animal animal, Location location, ArrayList<IslandObject> objects) {
        IslandObject objectToEat = null;
        if (!objects.isEmpty()) {
            objectToEat = getPriorityObject(objects, animal);
        }

        if (objectToEat != null) {
            double amountToEat = Math.min(objectToEat.weight, animal.satisfiedKg);
            animal.weight += amountToEat;
            objectToEat.die(objectToEat, location);
        } else {
            animal.weight -= animal.weight / 10;
        }

        if (animal.weight <= animal.minimumWeight) {
            animal.die(animal, location);
        }
    }
    public void move(Animal animal, Location location) {
        int movesToAdd = ThreadLocalRandom.current().nextInt(animal.maxMoveCount + 1);
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
        if (Simulation.island[newX][newY].animals.size() < animal.maxAnimalsCountInLocation) {
            location.animals.removeIf(a -> a.id == animal.id);
            Simulation.island[newX][newY].animals.add(animal);
        }
    }
    public void multiply(Animal animal, ArrayList<Animal> animals) {
        try {
            ArrayList<Animal> sameAnimals = animals.stream()
                    .filter(a -> a.name.equals(animal.name))
                    .collect(Collectors.toCollection(ArrayList::new));

            int animalsCount = sameAnimals.size();
            int count = animalsCount / 2;
            for (int i = 0; i < count; i++) {
                if (animalsCount < animal.maxAnimalsCountInLocation) {
                    animalsCount ++;
                    animals.add(animal.getClass().getConstructor(UUID.class).newInstance(UUID.randomUUID()));
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to create Animal: " + e.getMessage());
        }
    }

    private IslandObject getPriorityObject(List<IslandObject> objects, Animal animal) {
        Map<String, Integer> currentPredatorPriority = Settings.ANIMAL_EATING_PRIORITY.get(animal.name);
        if (currentPredatorPriority == null) {
            return null;
        }

        int randomInt = ThreadLocalRandom.current().nextInt(101);
        IslandObject object = null;
        int maxPriority = 0;

        for (IslandObject h : objects) {
            Integer priority = currentPredatorPriority.get(h.name);
            if (priority != null && priority >= maxPriority && priority >= randomInt) {
                object = h;
                maxPriority = priority;
            }
        }

        if (object == null) {
            for (IslandObject h : objects) {
                Integer priority = currentPredatorPriority.get(h.name);
                if (priority != null) {
                    object = h;
                    break;
                }
            }
        }

        return object;
    }
}
