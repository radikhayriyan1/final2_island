package com.javarush.island.hayriyan.objects;
import com.javarush.island.hayriyan.abstracts.IslandObject;
import java.util.UUID;

public class Plant extends IslandObject {

    public static final String plantName = "plant";

    public Plant(UUID id) {
        this.id = id;
        this.emoji = "\uD83C\uDF31";
        this.weight = 1.0;
        this.name = "plant";
    }
}
