package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Caterpillar extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC1B");
        put("satisfiedKg", 0.0);
        put("minimumWeight", 0.0);
        put("name", "caterpillar");
        put("maxMoveCount", 0);
        put("maxAnimalsCountInLocation", 1000);
    }};
    public Caterpillar(UUID id) {
        this.id = id;
        this.weight = 0.01;
    }
}
