package com.javarush.island.hayriyan.objects.animals.herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.anotations.CanEat;
import java.util.HashMap;
import java.util.UUID;

@CanEat({"mouse", "caterpillar"})
public class Boar extends Herbivores {
    public static final HashMap<String, Object> characteristics = new HashMap<>() {{
        put("emoji", "\uD83D\uDC17");
        put("satisfiedKg", 50.0);
        put("minimumWeight", 250.0);
        put("name", "boar");
        put("maxMoveCount", 2);
        put("maxAnimalsCountInLocation", 50);
    }};
    public Boar(UUID id) {
        this.id = id;
        this.weight = 400.0;
    }
}
