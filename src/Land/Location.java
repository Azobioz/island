package Land;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();


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
}


