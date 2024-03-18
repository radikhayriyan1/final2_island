package com.javarush.island.hayriyan.animals.herbivores;

import com.javarush.island.hayriyan.abstracts.animals.Herbivores;

import java.util.UUID;

public class Goat extends Herbivores {
    public Goat(UUID id) {
        this.id = id;
        this.image = "\uD83D\uDC10";
        this.satisfiedKg = 10.0;
        this.weight = 60.0;
        this.minimumWeight = 40.0;
        this.name = "goat";
    }
}
