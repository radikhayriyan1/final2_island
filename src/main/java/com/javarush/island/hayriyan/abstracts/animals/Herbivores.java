package com.javarush.island.hayriyan.abstracts.animals;

import com.javarush.island.hayriyan.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.simulation.Location;

import java.util.ArrayList;

public abstract class Herbivores extends Animal {
    public void eat(Animal animal, Location location) {
        ArrayList<Plant> plants = location.plants;
        Plant plant = plants.stream().findFirst().orElse(null);

        if (plant != null) {
            double amountToEat = Math.min(plant.weight, animal.satisfiedKg);
            animal.weight += amountToEat;
            plant.die(plant, location);
        } else {
            animal.weight -= animal.weight / 10;
        }
        if (animal.weight <= animal.minimumWeight) {
            animal.die(animal, location);
        }
    }
}
