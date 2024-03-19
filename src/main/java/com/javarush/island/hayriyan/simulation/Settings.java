package com.javarush.island.hayriyan.simulation;
import com.javarush.island.hayriyan.animals.herbivores.Goat;
import com.javarush.island.hayriyan.animals.herbivores.Rabbit;
import com.javarush.island.hayriyan.animals.predator.Wolf;

import java.util.*;

public class Settings {
    public static final Integer WIDTH = 120;
    public static final Integer HEIGHT = 20;
    public static final Integer PLANTS_COUNT = 8;
    public static final Map<Class<?>, Integer> ANIMALS_COUNT = new HashMap<>() {{
        put(Wolf.class, 2);
        put(Goat.class, 8);
        put(Rabbit.class, 9);
    }};

    public static final Map<String, Map<String, Integer>> PREDATOR_PRIORITY = new HashMap<>() {{
        put(Wolf.animalName, new HashMap<>() {{
            put(Rabbit.animalName, 60);
            put(Goat.animalName, 60);
        }});
    }};
}
