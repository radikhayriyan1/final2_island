package com.javarush.island.hayriyan.simulation;

import com.javarush.island.hayriyan.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;

import java.util.ArrayList;
import java.util.UUID;

public class Location {
    public ArrayList<Animal> animals;
    public ArrayList<Plant> plants;
    public Integer x;
    public Integer y;

    public UUID id;

    public Location(ArrayList<Animal> animals, ArrayList<Plant> plants, Integer x, Integer y, UUID id) {
        this.animals = animals;
        this.plants = plants;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public ArrayList<String> getObjects() {
        ArrayList<String> result = new ArrayList<>();
        animals.forEach((animal) -> result.add(animal.image));
        plants.forEach((animal) -> result.add(animal.image));
        return result;
    }
}
