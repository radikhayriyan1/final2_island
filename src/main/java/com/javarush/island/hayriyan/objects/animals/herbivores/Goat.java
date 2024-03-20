package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.utils.InitAnimal;
import java.util.UUID;

public class Goat extends Herbivores {

    public static final String animalName = "goat";
    public Goat(UUID id) {
        this.id = id;
        InitAnimal.initializeAnimal(this, "\uD83D\uDC10", 10.0, 60.0, 40.0, "goat", 3, 140);
    }
}
