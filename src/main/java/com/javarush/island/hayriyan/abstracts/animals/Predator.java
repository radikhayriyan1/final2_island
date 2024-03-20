package com.javarush.island.hayriyan.abstracts.animals;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.abstracts.IslandObject;
import com.javarush.island.hayriyan.island.Location;
import java.util.ArrayList;

public abstract class Predator extends Animal {
    public void predatorEat(Animal animal, Location location) {
        ArrayList<IslandObject> objects = new ArrayList<>(location.animals);
        super.eat(animal, location, objects);
    }
}
