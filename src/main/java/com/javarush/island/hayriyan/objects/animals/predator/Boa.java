package com.javarush.island.hayriyan.objects.animals.predator;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import com.javarush.island.hayriyan.anotations.CanEat;
import java.util.HashMap;
import java.util.UUID;

@CanEat({"fox"})
public class Boa extends Predator {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC0D");
        put("satisfiedKg", 3.0);
        put("minimumWeight", 10.0);
        put("name", "boa");
        put("maxMoveCount", 1);
        put("maxAnimalsCountInLocation", 30);
    }};
    public Boa(UUID id) {
        this.id = id;
        this.weight = 15.0;
    }
}
