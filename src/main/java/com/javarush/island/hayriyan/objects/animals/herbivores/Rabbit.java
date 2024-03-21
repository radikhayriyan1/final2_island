package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Rabbit extends Herbivores {

    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC07");
        put("satisfiedKg", 0.45);
        put("minimumWeight", 1.5);
        put("name", "rabbit");
        put("maxMoveCount", 2);
        put("maxAnimalsCountInLocation", 150);
    }};
    public Rabbit(UUID id) {
        this.id = id;
        this.weight = 2.0;
    }
}
