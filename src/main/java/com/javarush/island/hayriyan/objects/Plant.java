package com.javarush.island.hayriyan.objects;
import com.javarush.island.hayriyan.abstracts.IslandObject;
import java.util.HashMap;
import java.util.UUID;

public class Plant extends IslandObject {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("name", "plant");
        put("emoji", "\uD83C\uDF31");
    }};

    public Plant(UUID id) {
        this.id = id;
        this.weight = 1.0;
    }
}
