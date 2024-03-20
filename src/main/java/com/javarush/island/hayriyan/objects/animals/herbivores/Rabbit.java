package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.utils.InitAnimal;
import java.util.UUID;

public class Rabbit extends Herbivores {

    public static final String animalName = "rabbit";
    public Rabbit(UUID id) {
        this.id = id;
        InitAnimal.initializeAnimal(this, "\uD83D\uDC07", 0.45, 2.0, 1.5, "rabbit", 2, 150);
    }
}
