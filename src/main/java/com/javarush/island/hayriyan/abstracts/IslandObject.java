package com.javarush.island.hayriyan.abstracts;
import com.javarush.island.hayriyan.island.Location;
import com.javarush.island.hayriyan.objects.Plant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class IslandObject {
    public UUID id;
    public String emoji;
    public Double weight;
    public String name;
    public void die(IslandObject islandObject, Location location) {
        if (islandObject instanceof Plant) {
            location.plants = location.plants.stream().filter(a -> a.id != islandObject.id).collect(Collectors.toCollection(ArrayList::new));
        } else {
            location.animals = location.animals.stream().filter(a -> a.id != islandObject.id).collect(Collectors.toCollection(ArrayList::new));

        }
    }
}
