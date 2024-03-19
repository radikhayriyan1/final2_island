package com.javarush.island.hayriyan.animals.predator;

import com.javarush.island.hayriyan.abstracts.animals.Predator;

import java.util.UUID;

public class Wolf extends Predator {

    public static final String animalName = "wolf";
    public Wolf(UUID id) {
        this.id = id;
        this.image = "\uD83D\uDC3A";
        this.satisfiedKg = 8.0;
        this.weight = 50.0;
        this.minimumWeight = 30.0;
        this.name = "wolf";
        this.maxMoveCount = 3;
    }
}
