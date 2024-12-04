package land;

import animals.Animal;
import animals.herbivores.*;
import animals.predators.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Island implements Runnable {

    private static Location[][] locations;
    private int height;
    private int width;
    private static int howManyAnimals;
    private int howManyPlants;



    public Island(int height, int width, int howManyAnimals, int howManyPlants) {
        this.height = height;
        this.width = width;
        Island.howManyAnimals = howManyAnimals;
        this.howManyPlants = howManyPlants;
    }

    public void run () {

        while (howManyAnimals > 0) {
            locations = new Location[height][width]; //Добавление локаций и растений
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    locations[i][j] = new Location();
                }
            }
            Location.spawnPlant(howManyPlants); //Добавление растений

            Random random = new Random();
            setHowManyAnimals(howManyAnimals);

            for (int i = 0; i < getHowManyAnimals(); i++) { // Создание и добавление животных на остров
                int whatAnimal = random.nextInt(15);
                int whereItWillBeX = random.nextInt(width);
                int whereItWillBeY = random.nextInt(height);
                if (whatAnimal == 0) {
                    Buffalo buffalo = new Buffalo(whereItWillBeX, whereItWillBeY);
                    locations[buffalo.getY()][buffalo.getX()].addAnimal(buffalo);
                }
                else if (whatAnimal == 1) {
                    Caterpillar caterpillar = new Caterpillar(whereItWillBeX, whereItWillBeY);
                    locations[caterpillar.getY()][caterpillar.getX()].addAnimal(caterpillar);
                }
                else if (whatAnimal == 2) {
                    Deer deer = new Deer(whereItWillBeX, whereItWillBeY);
                    locations[deer.getY()][deer.getX()].addAnimal(deer);
                }
                else if (whatAnimal == 3) {
                    Duck duck = new Duck(whereItWillBeX, whereItWillBeY);
                    locations[duck.getY()][duck.getX()].addAnimal(duck);
                }
                else if (whatAnimal == 4) {
                    Goat goat = new Goat(whereItWillBeX, whereItWillBeY);
                    locations[goat.getY()][goat.getX()].addAnimal(goat);
                }
                else if (whatAnimal == 5) {
                    Hog hog = new Hog(whereItWillBeX, whereItWillBeY);
                    locations[hog.getY()][hog.getX()].addAnimal(hog);
                }
                else if (whatAnimal == 6) {
                    Horse horse = new Horse(whereItWillBeX, whereItWillBeY);
                    locations[horse.getY()][horse.getX()].addAnimal(horse);
                }
                else if (whatAnimal == 7) {
                    Mouse mouse = new Mouse(whereItWillBeX, whereItWillBeY);
                    locations[mouse.getY()][mouse.getX()].addAnimal(mouse);
                }
                else if (whatAnimal == 8) {
                    Rabbit rabbit = new Rabbit(whereItWillBeX, whereItWillBeY);
                    locations[rabbit.getY()][rabbit.getX()].addAnimal(rabbit);
                }
                else if (whatAnimal == 9) {
                    Sheep sheep = new Sheep(whereItWillBeX, whereItWillBeY);
                    locations[sheep.getY()][sheep.getX()].addAnimal(sheep);
                }
                else if (whatAnimal == 10) {
                    Bear bear = new Bear(whereItWillBeX, whereItWillBeY);
                    locations[bear.getY()][bear.getX()].addAnimal(bear);
                }
                else if (whatAnimal == 11) {
                    Eagle eagle = new Eagle(whereItWillBeX, whereItWillBeY);
                    locations[eagle.getY()][eagle.getX()].addAnimal(eagle);
                }
                else if (whatAnimal == 12) {
                    Fox fox = new Fox(whereItWillBeX, whereItWillBeY);
                    locations[fox.getY()][fox.getX()].addAnimal(fox);
                }
                else if (whatAnimal == 13) {
                    Python python = new Python(whereItWillBeX, whereItWillBeY);
                    locations[python.getY()][python.getX()].addAnimal(python);
                }
                else {
                    Wolf wolf = new Wolf(whereItWillBeX, whereItWillBeY);
                    locations[wolf.getY()][wolf.getX()].addAnimal(wolf);
                }

            }

            // Цикл для каждого животного на острове
            while (getHowManyAnimals() != 0) {

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Location location = locations[i][j];
                        List<Animal> animalsInLocation = location.getAnimals();
                        List<Plant> plantsInLocation = location.getPlants();
                        if (animalsInLocation.size() > 1 || !plantsInLocation.isEmpty() && !animalsInLocation.isEmpty()) { //Может ли хищник съесть другого
                            for (int k = 0; k < animalsInLocation.size(); k++) {
                                if (animalsInLocation.get(k) instanceof Predator currentPredator) {
                                    for (Animal otherAnimal : animalsInLocation) {
                                        if (currentPredator != otherAnimal) {
                                            if (currentPredator.eat(otherAnimal)) {
                                                break; //Прерывание т. к. хищник съел животное
                                            }
                                        }
                                    }
                                } else if (animalsInLocation.get(k) instanceof Herbivore currentHerbivore) {
                                    if (currentHerbivore instanceof Duck currentDuck) {
                                        for (int f = 0; f < animalsInLocation.size(); f++) {
                                            Animal otherAnimal = animalsInLocation.get(f);
                                            if (otherAnimal instanceof Caterpillar) {
                                                currentDuck.eatCaterpillar((Caterpillar) otherAnimal);
                                            }
                                        }
                                    } else {
                                        for (Plant plant : plantsInLocation) {
                                            if (currentHerbivore.eat(plant)) { // Прерывание цикла, так как травоядный съел растение
                                                break;
                                            }
                                        }
                                    }
                                }
                            }


                            if (animalsInLocation.size() > 1) {
                                // Для размножения животных
                                boolean needToBreak = false;
                                for (int k = 0; k < animalsInLocation.size(); k++) {
                                    if (needToBreak) {
                                        break;
                                    }
                                    Animal currentAnimal = animalsInLocation.get(k);
                                    for (int d = 0; d < animalsInLocation.size(); d++) {
                                        try {
                                            if (animalsInLocation.get(d) != currentAnimal && currentAnimal.multiply(animalsInLocation.get(d), locations)) {
                                                needToBreak = true;
                                                break; //только одно размножение в одной локации за каждый ход
                                            }
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }

                            }

                        }

                        // Для перемещения животных из одной локации в другую, если они там есть
                        for (int f = 0; f < animalsInLocation.size(); f++) {
                             animalsInLocation.get(f).walk(locations);
                        }


                    }
                }

                // Проверка голода
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        List<Animal> animalsInLocation = Island.getLocations()[i][j].getAnimals();
                        for (int k = 0; k < animalsInLocation.size(); k++) {
                            animalsInLocation.get(k).checkHunger();
                        }
                    }
                }

                //Даем животным ходить
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Location location = locations[i][j];
                        for (Animal animal : location.getAnimals()) {
                            animal.setMoved(false);
                        }
                    }
                }


                System.out.println("Status: animals left - " + Island.howManyAnimals);
                System.out.println("----------------------");
                Location.spawnPlant(1);

            }
            System.out.println("All animal are dead");
            return;
        }
    }


    public static Location[][] getLocations() {
        return locations;
    }

    public static int getHowManyAnimals() {
        return howManyAnimals;
    }

    public static void setHowManyAnimals(int howManyAnimals) {
        Island.howManyAnimals = howManyAnimals;
    }

    public static void setLocations(Location[][] locations) {
        Island.locations = locations;
    }
}
