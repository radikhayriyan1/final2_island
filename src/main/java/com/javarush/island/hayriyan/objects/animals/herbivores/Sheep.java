package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Sheep extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC11");
        put("satisfiedKg", 15.0);
        put("minimumWeight", 40.0);
        put("name", "sheep");
        put("maxMoveCount", 3);
        put("maxAnimalsCountInLocation", 140);
    }};
    public Sheep(UUID id) {
        this.id = id;
        this.weight = 70.0;
    }
}
