package com.javarush.island.hayriyan.objects.animals.predator;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import com.javarush.island.hayriyan.anotations.CanEat;
import java.util.HashMap;
import java.util.UUID;

@CanEat({"fox"})
public class Eagle extends Predator {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83E\uDD85");
        put("satisfiedKg", 1.0);
        put("minimumWeight", 4.0);
        put("name", "eagle");
        put("maxMoveCount", 3);
        put("maxAnimalsCountInLocation", 20);
    }};
    public Eagle(UUID id) {
        this.id = id;
        this.weight = 6.0;
    }
}
