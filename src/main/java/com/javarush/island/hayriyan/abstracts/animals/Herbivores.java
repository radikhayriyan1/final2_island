package com.javarush.island.hayriyan.abstracts.animals;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.abstracts.IslandObject;
import com.javarush.island.hayriyan.island.Location;
import java.util.ArrayList;
import java.util.stream.Collectors;
import static com.javarush.island.hayriyan.utils.Helper.isAnimalCanBeEaten;

public abstract class Herbivores extends Animal {

    public void herbivoresEat(Animal animal, Location location) {
        ArrayList<IslandObject> objects = new ArrayList<>();
        objects.addAll(location.plants);
        objects.addAll(location.animals.stream()
                .filter(a -> (a instanceof Herbivores) && isAnimalCanBeEaten(animal, a))
                .collect(Collectors.toCollection(ArrayList::new)));
        super.eat(animal, location, objects);
    }
}
