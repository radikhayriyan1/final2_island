package com.javarush.island.hayriyan.abstracts.animals;

import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.simulation.Location;
import com.javarush.island.hayriyan.simulation.Settings;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Predator extends Animal {
    public void eat(Animal animal, Location location) {
        ArrayList<Animal> animals = location.animals;
        ArrayList<Animal> herbivores = animals.stream().filter(a -> a instanceof Herbivores).collect(Collectors.toCollection(ArrayList::new));
        Animal herbivorous = null;
        if (!herbivores.isEmpty()) {
            herbivorous = getPriorityHerbivorous(herbivores, (Predator) animal);
        }
        if (herbivorous != null) {
            animal.weight += herbivorous.weight > animal.satisfiedKg ? animal.satisfiedKg : herbivorous.weight;
            herbivorous.die(herbivorous, location);
        } else {
            animal.weight -= animal.weight / 10;
        }
        if (animal.weight <= animal.minimumWeight) {
            animal.die(animal, location);
        }
    };

    private Animal getPriorityHerbivorous(ArrayList<Animal> herbivores, Predator predator ) {
        Map<String, Integer> currentPredatorPriority = null;
        Animal herbivorous = null;
        Integer randomInt = ThreadLocalRandom.current().nextInt(101);
        Integer maxPriority = 0;
        for (Map.Entry<String, Map<String, Integer>> entry : Settings.predatorPriority.entrySet()) {
            String predatorName = entry.getKey();
            if (predator.name.equals(predatorName)) {
                currentPredatorPriority = entry.getValue();
                break;
            }
        }
        for (Animal h : herbivores) {
            if (currentPredatorPriority != null && currentPredatorPriority.get(h.name) >= maxPriority && currentPredatorPriority.get(h.name) >= randomInt) {
                herbivorous = h;
                maxPriority = currentPredatorPriority.get(h.name);
            }
        }
        return herbivorous == null ? herbivores.get(0) : herbivorous;
    }
}
