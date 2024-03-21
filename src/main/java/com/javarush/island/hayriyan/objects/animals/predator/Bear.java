package com.javarush.island.hayriyan.objects.animals.predator;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import com.javarush.island.hayriyan.anotations.CanEat;
import java.util.HashMap;
import java.util.UUID;

@CanEat({"boa"})
public class Bear extends Predator {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC3B");
        put("satisfiedKg", 80.0);
        put("minimumWeight", 350.0);
        put("name", "bear");
        put("maxMoveCount", 2);
        put("maxAnimalsCountInLocation", 5);
    }};
    public Bear(UUID id) {
        this.id = id;
        this.weight = 500.0;
    }
}
