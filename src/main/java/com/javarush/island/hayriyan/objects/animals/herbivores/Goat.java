package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Goat extends Herbivores {

    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC10");
        put("satisfiedKg", 10.0);
        put("minimumWeight", 40.0);
        put("name", "goat");
        put("maxMoveCount", 3);
        put("maxAnimalsCountInLocation", 140);
    }};
    public Goat(UUID id) {
        this.id = id;
        this.weight = 60.0;
    }
}
