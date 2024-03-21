package com.javarush.island.hayriyan.objects.animals.predator;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import java.util.HashMap;
import java.util.UUID;

public class Fox extends Predator {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83E\uDD8A");
        put("satisfiedKg", 2.0);
        put("minimumWeight", 5.0);
        put("name", "fox");
        put("maxMoveCount", 2);
        put("maxAnimalsCountInLocation", 30);
    }};
    public Fox(UUID id) {
        this.id = id;
        this.weight = 8.0;
    }
}
