package com.javarush.island.hayriyan.config;
import com.javarush.island.hayriyan.objects.animals.herbivores.Goat;
import com.javarush.island.hayriyan.objects.animals.herbivores.Rabbit;
import com.javarush.island.hayriyan.objects.animals.predator.Wolf;
import com.javarush.island.hayriyan.objects.Plant;
import java.util.*;

public class Settings {
    public static final Integer WIDTH = 2;
    public static final Integer HEIGHT = 2;
    public static final Integer PLANTS_COUNT = 2;
    public static final Integer maxPlantsCountInLocation = 200;
    public static final Integer STOP_SIMULATION_TIME = 10;
    public static final Map<Class<?>, Integer> ANIMALS_COUNT = new HashMap<>() {{
        put(Wolf.class, 4);
        put(Goat.class, 4);
        put(Rabbit.class, 4);
    }};

    public static final Map<String, Map<String, Integer>> ANIMAL_EATING_PRIORITY = new HashMap<>() {{
        put((String)Wolf.characteristics.get("name"), new HashMap<>() {{
            put((String)Rabbit.characteristics.get("name"), 60);
            put((String)Goat.characteristics.get("name"), 60);
        }});
        put((String)Rabbit.characteristics.get("name"), new HashMap<>() {{
            put((String)Plant.characteristics.get("name"), 100);
        }});
    }};
}
