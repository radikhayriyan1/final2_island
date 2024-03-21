package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.anotations.CanEat;
import java.util.HashMap;
import java.util.UUID;

@CanEat({"caterpillar"})
public class Duck extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83E\uDD86");
        put("satisfiedKg", 0.15);
        put("minimumWeight", 0.5);
        put("name", "duck");
        put("maxMoveCount", 4);
        put("maxAnimalsCountInLocation", 200);
    }};
    public Duck(UUID id) {
        this.id = id;
        this.weight = 1.0;
    }
}
