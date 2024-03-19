package com.javarush.island.hayriyan;

import com.javarush.island.hayriyan.simulation.Location;
import java.util.UUID;

public class Plant {
    public UUID id;

    public final Integer weight = 1;
    public String image = "\uD83C\uDF31";

    public Plant(UUID id) {
        this.id = id;
    }

    public void die(Plant plant, Location location) {
        location.plants.removeIf(p -> p.id == plant.id);
    }
}
