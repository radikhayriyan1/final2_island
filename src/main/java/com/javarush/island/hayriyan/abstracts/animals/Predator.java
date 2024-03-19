package com.javarush.island.hayriyan.abstracts.animals;

import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.simulation.Location;
import com.javarush.island.hayriyan.simulation.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Predator extends Animal {
    public void eat(Animal animal, Location location) {
        ArrayList<Animal> animals = location.animals;
        List<Herbivores> herbivores = animals.stream()
                .filter(a -> a instanceof Herbivores)
                .map(a -> (Herbivores) a)
                .collect(Collectors.toList());

        Herbivores herbivorous = null;
        if (!herbivores.isEmpty()) {
            herbivorous = getPriorityHerbivorous(herbivores, (Predator) animal);
        }

        if (herbivorous != null) {
            double amountToEat = Math.min(herbivorous.weight, animal.satisfiedKg);
            animal.weight += amountToEat;
            herbivorous.die(herbivorous, location);
        } else {
            animal.weight -= animal.weight / 10;
        }

        if (animal.weight <= animal.minimumWeight) {
            animal.die(animal, location);
        }
    };

    private Herbivores getPriorityHerbivorous(List<Herbivores> herbivores, Predator predator ) {
        Map<String, Integer> currentPredatorPriority = Settings.PREDATOR_PRIORITY.get(predator.name);
        if (currentPredatorPriority == null) {
            return herbivores.get(0);
        }

        int randomInt = ThreadLocalRandom.current().nextInt(101);
        Herbivores herbivorous = null;
        int maxPriority = 0;

        for (Herbivores h : herbivores) {
            Integer priority = currentPredatorPriority.get(h.name);
            if (priority != null && priority >= maxPriority && priority >= randomInt) {
                herbivorous = h;
                maxPriority = priority;
            }
        }

        return herbivorous == null ? herbivores.get(0) : herbivorous;
    }
}
