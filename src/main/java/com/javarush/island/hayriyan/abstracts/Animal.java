package com.javarush.island.hayriyan.abstracts;

import com.javarush.island.hayriyan.simulation.Location;
import com.javarush.island.hayriyan.simulation.Settings;
import com.javarush.island.hayriyan.simulation.Simulation;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal {
    public UUID id;

    public String name;
    public Double satisfiedKg;
    public String image;

    public Double weight;

    public Double minimumWeight;

    public Integer maxMoveCount;
    abstract public void eat(Animal animal, Location location);
    public void move(Animal animal, Location location) {
        int movesToAdd = ThreadLocalRandom.current().nextInt(animal.maxMoveCount + 1);
        location.animals.removeIf(a -> a.id == animal.id);
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
        Simulation.locations[newX][newY].animals.add(animal);
    };
    public void multiply(Animal animal, ArrayList<Animal> animals) {
        try {
            ArrayList<Animal> sameAnimals = animals.stream()
                    .filter(a -> a.name.equals(animal.name))
                    .collect(Collectors.toCollection(ArrayList::new));

            int count = sameAnimals.size() / 2;
            for (int i = 0; i < count; i++) {
                animals.add(animal.getClass().getConstructor(UUID.class).newInstance(UUID.randomUUID()));
            }
        } catch (Exception e) {
            System.out.println("Failed to create Animal: " + e.getMessage());
        }
    };
    public void die(Animal animal, Location location) {
        location.animals = location.animals.stream().filter(a -> a.id != animal.id).collect(Collectors.toCollection(ArrayList::new));
    };
}
