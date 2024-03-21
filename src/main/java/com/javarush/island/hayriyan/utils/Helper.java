package com.javarush.island.hayriyan.utils;
import com.javarush.island.hayriyan.abstracts.IslandObject;
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
}
