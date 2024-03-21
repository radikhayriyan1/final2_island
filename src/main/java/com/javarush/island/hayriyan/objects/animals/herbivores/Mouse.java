package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.anotations.CanEat;
import java.util.HashMap;
import java.util.UUID;

@CanEat({"caterpillar"})
public class Mouse extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC01");
        put("satisfiedKg", 0.01);
        put("minimumWeight", 0.03);
        put("name", "mouse");
        put("maxMoveCount", 1);
        put("maxAnimalsCountInLocation", 500);
    }};
    public Mouse(UUID id) {
        this.id = id;
        this.weight = 0.05;
    }
}
