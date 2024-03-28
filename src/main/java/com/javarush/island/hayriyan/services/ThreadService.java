package com.javarush.island.hayriyan.services;
import com.javarush.island.hayriyan.abstracts.Animal;
import com.javarush.island.hayriyan.abstracts.animals.Herbivores;
import com.javarush.island.hayriyan.abstracts.animals.Predator;
import com.javarush.island.hayriyan.config.Settings;
import com.javarush.island.hayriyan.island.Location;
import com.javarush.island.hayriyan.objects.Plant;
import com.javarush.island.hayriyan.utils.Helper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import static com.javarush.island.hayriyan.island.Simulation.island;

public class ThreadService {

    private static final ScheduledExecutorService islandLifeCycleExecutor = Executors.newScheduledThreadPool(1);

    private static final ReentrantLock lock = new ReentrantLock();
    public static void startIslandLifeCycle() {
        islandLifeCycleExecutor.scheduleAtFixedRate((() -> {
            lock.lock();
            try {
                addPlants();
            } finally {
                lock.unlock();
            }
        }), 0, 1, TimeUnit.SECONDS);
        islandLifeCycleExecutor.scheduleAtFixedRate((() -> {
            lock.lock();
            try {
                startAnimalLifeCycle();
            } finally {
                lock.unlock();
            }
        }), 0, 1, TimeUnit.SECONDS);
        stop();
    }

    private static void addPlants() {
        ScheduledExecutorService plantExecutor = Executors.newScheduledThreadPool(Settings.WIDTH * Settings.HEIGHT);
        for (Location[] location : island) {
            for (Location value : location) {
                plantExecutor.schedule(() -> {
                    for (int i = 0; i < Settings.PLANTS_COUNT; i++) {
                        if (value.plants.size() < Settings.maxPlantsCountInLocation) {
                            value.plants.add(new Plant(UUID.randomUUID()));
                        }
                    }
                }, 0, TimeUnit.SECONDS);
            }
        }
        plantExecutor.shutdown();
        showIslandStateByAction(plantExecutor, "Island after the plants have grown");
    }

    private static void startAnimalLifeCycle() {
        eat();
        multiply();
        move();
    }
    private static void eat() {
        ScheduledExecutorService eatExecutor = Executors.newScheduledThreadPool(Settings.WIDTH * Settings.HEIGHT);
        for (Location[] location : island) {
            for (Location value : location) {
                eatExecutor.schedule(() -> {
                    for (Animal animal : value.animals) {
                        if (animal instanceof Herbivores) {
                            ((Herbivores) animal).herbivoresEat(animal, value);
                        } else {
                            ((Predator) animal).predatorEat(animal, value);
                        }
                    }
                }, 0, TimeUnit.SECONDS);
            }
        }
        eatExecutor.shutdown();
        showIslandStateByAction(eatExecutor, "The island after the animals have eaten");
    }

    private static void multiply() {
        ScheduledExecutorService multiplyExecutor = Executors.newScheduledThreadPool(Settings.WIDTH * Settings.HEIGHT);
        for (Location[] location : island) {
            for (Location value : location) {
                multiplyExecutor.schedule(() -> {
                    ArrayList<Animal> uniqueAnimals = new ArrayList<>();
                    ArrayList<Animal> animals = value.animals;
                    for (Animal item : animals) {
                        boolean found = false;
                        for (Animal animal : uniqueAnimals) {
                            if (Objects.equals(Helper.getObjectsStaticPropertyByName(animal, "name"),
                                    Helper.getObjectsStaticPropertyByName(item, "name"))) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            uniqueAnimals.add(item);
                        }
                    }
                    for (Animal animal : uniqueAnimals) {
                        animal.multiply(animal, animals);
                    }
                }, 0, TimeUnit.SECONDS);
            }
        }
        multiplyExecutor.shutdown();
        showIslandStateByAction(multiplyExecutor, "The island after the animals have been multiplied");
    }

    private static void move() {
        Location[][] copyArray = Arrays.stream(island).map(Location[]::clone).toArray(Location[][]::new);
        for (int i = 0; i < copyArray.length; i++) {
            for (int j = 0; j < copyArray[i].length; j++) {
                for (int k = 0; k < copyArray[i][j].animals.size(); k++) {
                    Animal animal = island[i][j].animals.get(k);
                    animal.move(animal, island[i][j]);
                }
            }
        }
        System.out.println("The island after the animals have been moved");
        showResult();
    }

    private static void stop() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.schedule(() -> {
            islandLifeCycleExecutor.shutdown();
            showIslandStateByAction(islandLifeCycleExecutor, "The island life cycle has stopped!");
        }, Settings.STOP_SIMULATION_TIME, TimeUnit.SECONDS);
        executor.shutdown();
    }

    private static void showIslandStateByAction(ScheduledExecutorService executor, String message) {
        try {
            if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println(message);
                showResult();
            }
        } catch (InterruptedException e) {
            System.err.println("Action were interrupted");
        }
    }

    private static void showResult() {
        for (Location[] location : island) {
            for (Location value : location) {
                System.out.print(value.getObjects() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
