package com.javarush.island.hayriyan;

import com.javarush.island.hayriyan.simulation.Location;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class Plant {
    public UUID id;

    public final Integer weight = 1;
    public String image = "\uD83C\uDF31";

    public Plant(UUID id) {
        this.id = id;
    }

    public void die(Plant plant, Location location) {
        location.plants = location.plants.stream().filter(a -> a.id != plant.id).collect(Collectors.toCollection(ArrayList::new));
    };
}
