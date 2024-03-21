package com.javarush.island.hayriyan.island;
import com.javarush.island.hayriyan.objects.Plant;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.utils.Helper;
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
        animals.forEach((animal) -> result.add((String)Helper.getObjectsStaticPropertyByName(animal, "emoji")));
        plants.forEach((p) -> result.add((String)Helper.getObjectsStaticPropertyByName(p, "emoji")));
        return result;
    }
}
