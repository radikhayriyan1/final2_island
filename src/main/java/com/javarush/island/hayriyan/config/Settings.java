package com.javarush.island.hayriyan.config;
import com.javarush.island.hayriyan.objects.animals.herbivores.*;
import com.javarush.island.hayriyan.objects.animals.predator.*;
import com.javarush.island.hayriyan.objects.Plant;
import java.util.*;

public class Settings {
    public static final Integer WIDTH = 2;
    public static final Integer HEIGHT = 2;
    public static final Integer PLANTS_COUNT = 4;
    public static final Integer maxPlantsCountInLocation = 200;
    public static final Integer STOP_SIMULATION_TIME = 10;

    public static final Map<Class<?>, Integer> ANIMALS_COUNT = new HashMap<>() {{
        put(Wolf.class, 2);
        put(Boa.class, 2);
        put(Fox.class, 2);
        put(Bear.class, 2);
        put(Eagle.class, 2);
        put(Horse.class, 2);
        put(Deer.class, 2);
        put(Rabbit.class, 2);
        put(Mouse.class, 2);
        put(Goat.class, 2);
        put(Sheep.class, 2);
        put(Boar.class, 2);
        put(Buffalo.class, 2);
        put(Duck.class, 2);
        put(Caterpillar.class, 2);
    }};

    public static final Map<String, Map<String, Integer>> ANIMAL_EATING_PRIORITY = new HashMap<>() {{
        put((String) Wolf.characteristics.get("name"), new HashMap<>() {{
            put((String) Horse.characteristics.get("name"), 10);
            put((String) Deer.characteristics.get("name"), 15);
            put((String) Rabbit.characteristics.get("name"), 60);
            put((String) Mouse.characteristics.get("name"), 80);
            put((String) Goat.characteristics.get("name"), 60);
            put((String) Sheep.characteristics.get("name"), 70);
            put((String) Boar.characteristics.get("name"), 15);
            put((String) Buffalo.characteristics.get("name"), 10);
            put((String) Duck.characteristics.get("name"), 40);
        }});
        put((String) Boa.characteristics.get("name"), new HashMap<>() {{
            put((String) Fox.characteristics.get("name"), 15);
            put((String) Rabbit.characteristics.get("name"), 20);
            put((String) Mouse.characteristics.get("name"), 40);
            put((String) Duck.characteristics.get("name"), 10);
        }});
        put((String) Fox.characteristics.get("name"), new HashMap<>() {{
            put((String) Rabbit.characteristics.get("name"), 70);
            put((String) Mouse.characteristics.get("name"), 90);
            put((String) Duck.characteristics.get("name"), 60);
            put((String) Caterpillar.characteristics.get("name"), 40);
        }});
        put((String) Bear.characteristics.get("name"), new HashMap<>() {{
            put((String) Boa.characteristics.get("name"), 80);
            put((String) Horse.characteristics.get("name"), 40);
            put((String) Deer.characteristics.get("name"), 80);
            put((String) Rabbit.characteristics.get("name"), 80);
            put((String) Mouse.characteristics.get("name"), 90);
            put((String) Goat.characteristics.get("name"), 70);
            put((String) Sheep.characteristics.get("name"), 70);
            put((String) Boar.characteristics.get("name"), 50);
            put((String) Buffalo.characteristics.get("name"), 20);
            put((String) Duck.characteristics.get("name"), 80);
        }});
        put((String) Eagle.characteristics.get("name"), new HashMap<>() {{
            put((String) Fox.characteristics.get("name"), 10);
            put((String) Rabbit.characteristics.get("name"), 90);
            put((String) Mouse.characteristics.get("name"), 90);
            put((String) Duck.characteristics.get("name"), 80);
        }});
        put((String) Horse.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Deer.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Rabbit.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Mouse.characteristics.get("name"), new HashMap<>() {{
            put((String) Caterpillar.characteristics.get("name"), 90);
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Goat.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Sheep.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Boar.characteristics.get("name"), new HashMap<>() {{
            put((String) Mouse.characteristics.get("name"), 50);
            put((String) Caterpillar.characteristics.get("name"), 90);
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Buffalo.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Duck.characteristics.get("name"), new HashMap<>() {{
            put((String) Caterpillar.characteristics.get("name"), 90);
            put((String) Plant.characteristics.get("name"), 100);
        }});
        put((String) Caterpillar.characteristics.get("name"), new HashMap<>() {{
            put((String) Plant.characteristics.get("name"), 100);
        }});
    }};
}
