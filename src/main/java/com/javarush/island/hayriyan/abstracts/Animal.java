package com.javarush.island.hayriyan.abstracts;

import com.javarush.island.hayriyan.simulation.Location;
import com.javarush.island.hayriyan.simulation.Settings;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class Animal {
    public UUID id;

    public String name;
    public Double satisfiedKg;
    public String image;

    public Double weight;

    public Double minimumWeight;
    abstract public void eat(Animal animal, Location location);
    public void move() {};
    public void multiply(Animal animal, ArrayList<Animal> animals) {
        try {
            ArrayList<Animal> sameAnimals = animals.stream().filter(a -> a.image.equals(animal.image)).collect(Collectors.toCollection(ArrayList::new));
            Integer count = sameAnimals.size() % 2 == 1 ? (sameAnimals.size() - 1) / 2 : sameAnimals.size() / 2;
            Settings.createAnimalList(animals, animal, count);
        }
        catch (Exception ignored) { }
    };
    public void die(Animal animal, Location location) {
        location.animals = location.animals.stream().filter(a -> a.id != animal.id).collect(Collectors.toCollection(ArrayList::new));
    };
}
