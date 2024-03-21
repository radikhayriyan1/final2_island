package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Buffalo extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC03");
        put("satisfiedKg", 100.0);
        put("minimumWeight", 500.0);
        put("name", "buffalo");
        put("maxMoveCount", 3);
        put("maxAnimalsCountInLocation", 10);
    }};
    public Buffalo(UUID id) {
        this.id = id;
        this.weight = 700.0;
    }
}
