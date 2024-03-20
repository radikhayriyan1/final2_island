package com.javarush.island.hayriyan.utils;
import com.javarush.island.hayriyan.abstracts.Animal;

public class InitAnimal {
    public static void initializeAnimal(
            Animal animal,
            String emoji,
            Double satisfiedKg,
            Double weight,
            Double minimumWeight,
            String name,
            Integer maxMoveCount,
            Integer maxAnimalsCountInLocation
    ) {
        animal.emoji = emoji;
        animal.satisfiedKg = satisfiedKg;
        animal.weight = weight;
        animal.minimumWeight = minimumWeight;
        animal.name = name;
        animal.maxMoveCount = maxMoveCount;
        animal.maxAnimalsCountInLocation = maxAnimalsCountInLocation;
    }
}
