package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Horse extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC0E");
        put("satisfiedKg", 60.0);
        put("minimumWeight", 250.0);
        put("name", "horse");
        put("maxMoveCount", 4);
        put("maxAnimalsCountInLocation", 20);
    }};
    public Horse(UUID id) {
        this.id = id;
        this.weight = 400.0;
    }
}
