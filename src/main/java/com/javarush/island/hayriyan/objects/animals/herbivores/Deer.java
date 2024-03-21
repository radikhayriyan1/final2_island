package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import java.util.HashMap;
import java.util.UUID;

public class Deer extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83E\uDD8C");
        put("satisfiedKg", 50.0);
        put("minimumWeight", 180.0);
        put("name", "deer");
        put("maxMoveCount", 4);
        put("maxAnimalsCountInLocation", 20);
    }};
    public Deer(UUID id) {
        this.id = id;
        this.weight = 300.0;
    }
}
