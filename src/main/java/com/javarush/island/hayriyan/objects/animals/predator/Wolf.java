package com.javarush.island.hayriyan.objects.animals.predator;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import java.util.HashMap;
import java.util.UUID;

public class Wolf extends Predator {

    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC3A");
        put("satisfiedKg", 8.0);
        put("minimumWeight", 30.0);
        put("name", "wolf");
        put("maxMoveCount", 3);
        put("maxAnimalsCountInLocation", 8);
    }};
    public Wolf(UUID id) {
        this.id = id;
        this.weight = 50.0;
    }
}
