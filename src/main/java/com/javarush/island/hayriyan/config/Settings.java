package com.javarush.island.hayriyan.config;
import com.javarush.island.hayriyan.objects.animals.herbivores.Goat;
import com.javarush.island.hayriyan.objects.animals.herbivores.Rabbit;
import com.javarush.island.hayriyan.objects.animals.predator.Wolf;
import com.javarush.island.hayriyan.objects.Plant;
import java.util.*;

public class Settings {
    public static final Integer WIDTH = 2;
    public static final Integer HEIGHT = 3;
    public static final Integer PLANTS_COUNT = 2;
    public static final Integer maxPlantsCountInLocation = 200;
    public static final Map<Class<?>, Integer> ANIMALS_COUNT = new HashMap<>() {{
        put(Wolf.class, 2);
        put(Goat.class, 3);
        put(Rabbit.class, 9);
    }};

    public static final Map<String, Map<String, Integer>> ANIMAL_EATING_PRIORITY = new HashMap<>() {{
        put(Wolf.animalName, new HashMap<>() {{
            put(Rabbit.animalName, 60);
            put(Goat.animalName, 60);
        }});
        put(Rabbit.animalName, new HashMap<>() {{
            put(Plant.plantName, 100);
        }});
    }};
}
