package com.javarush.island.hayriyan.animals.herbivores;

import com.javarush.island.hayriyan.abstracts.animals.Herbivores;

import java.util.UUID;

public class Rabbit extends Herbivores {

    public static final String animalName = "rabbit";
    public Rabbit(UUID id) {
        this.id = id;
        this.image = "\uD83D\uDC07";
        this.satisfiedKg = 0.45;
        this.weight = 2.0;
        this.minimumWeight = 1.5;
        this.name = "rabbit";
        this.maxMoveCount = 2;
    }
}
