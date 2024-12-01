package Land;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Location {

    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();

    public static void spawnPlant(int howManyPlants) {
        Random random = new Random();
        while (howManyPlants != 0) {
            int randomX = random.nextInt(Island.getLocations()[0].length);
            int randomY = random.nextInt(Island.getLocations().length);
            int chance = random.nextInt(100);
            Plant plant = new Plant();
            if (chance < plant.getChangeToSpawn()) {
                Island.getLocations()[randomY][randomX].addPlant(plant);
                System.out.println("A new plant in [" + randomX + ", " + randomY + "]");
            }
            howManyPlants--;
        }

    }


    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }
}


