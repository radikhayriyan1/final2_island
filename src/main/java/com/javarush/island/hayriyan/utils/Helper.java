package com.javarush.island.hayriyan.utils;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.abstracts.IslandObject;
import com.javarush.island.hayriyan.anotations.CanEat;

import java.util.Arrays;
import java.util.HashMap;

public class Helper {
    public static Object getObjectsStaticPropertyByName(IslandObject obj, String name) {
        Class<?> clazz = obj.getClass();
        try {
            HashMap<String, Object> characteristics = (HashMap<String, Object>) clazz.getField("characteristics").get(null);
            return characteristics.get(name);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Boolean isAnimalCanBeEaten(Animal canEatAnimal, Animal canBeEatenAnimal) {
        CanEat annotation = canEatAnimal.getClass().getAnnotation(CanEat.class);
        if (annotation != null) {
            String[] animals = annotation.value();
            return Arrays.asList(animals).contains((String)getObjectsStaticPropertyByName(canBeEatenAnimal, "name"));
        }
        return false;
    }
}
