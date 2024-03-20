package com.javarush.island.hayriyan.objects.animals.predator;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import com.javarush.island.hayriyan.utils.InitAnimal;
import java.util.UUID;

public class Wolf extends Predator {

    public static final String animalName = "wolf";
    public Wolf(UUID id) {
        this.id = id;
        InitAnimal.initializeAnimal(this, "\uD83D\uDC3A", 8.0, 50.0, 30.0, "wolf", 3, 8);
    }
}
