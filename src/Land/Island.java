package Land;

import animals.Animal;
import animals.herbivores.*;
import animals.predators.Bear;
import animals.predators.Predator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import static javax.management.Query.or;

public class Island {

    private static Location[][] locations;
    private static int howManyAnimals;

    public Island(int width, int height, int howManyAnimals, int howManyPlants) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        locations = new Location[width][height]; //Добавление локаций и растений
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
            }
        }
        Location.spawnPlant(howManyPlants); //Добавление растений

        Random random = new Random();
        setHowManyAnimals(howManyAnimals);

        for (int i = 0; i < getHowManyAnimals(); i++) { // Создание и добавление животных на остров
            int whatAnimal = random.nextInt(12);
            int whereItWillBeX = random.nextInt(width);
            int whereItWillBeY = random.nextInt(height);
            if (whatAnimal == 0) {
                Buffalo buffalo = new Buffalo(whereItWillBeX, whereItWillBeY);
                locations[buffalo.getX()][buffalo.getY()].addAnimal(buffalo);
            }
            else if (whatAnimal == 1) {
                Caterpillar caterpillar = new Caterpillar(whereItWillBeX, whereItWillBeY);
                locations[caterpillar.getX()][caterpillar.getY()].addAnimal(caterpillar);
            }
            else if (whatAnimal == 2) {
                Deer deer = new Deer(whereItWillBeX, whereItWillBeY);
                locations[deer.getX()][deer.getY()].addAnimal(deer);
            }
            else if (whatAnimal == 3) {
                Duck duck = new Duck(whereItWillBeX, whereItWillBeY);
                locations[duck.getX()][duck.getY()].addAnimal(duck);
            }
            else if (whatAnimal == 4) {
                Goat goat = new Goat(whereItWillBeX, whereItWillBeY);
                locations[goat.getX()][goat.getY()].addAnimal(goat);
            }
            else if (whatAnimal == 5) {
                Hog hog = new Hog(whereItWillBeX, whereItWillBeY);
                locations[hog.getX()][hog.getY()].addAnimal(hog);
            }
            else if (whatAnimal == 6) {
                Horse horse = new Horse(whereItWillBeX, whereItWillBeY);
                locations[horse.getX()][horse.getY()].addAnimal(horse);
            }
            else if (whatAnimal == 7) {
                Mouse mouse = new Mouse(whereItWillBeX, whereItWillBeY);
                locations[mouse.getX()][mouse.getY()].addAnimal(mouse);
            }
            else if (whatAnimal == 8) {
                Mouse mouse = new Mouse(whereItWillBeX, whereItWillBeY);
                locations[mouse.getX()][mouse.getY()].addAnimal(mouse);
            }
            else if (whatAnimal == 9) {
                Rabbit rabbit = new Rabbit(whereItWillBeX, whereItWillBeY);
                locations[rabbit.getX()][rabbit.getY()].addAnimal(rabbit);
            }
            else if (whatAnimal == 10) {
                Sheep sheep = new Sheep(whereItWillBeX, whereItWillBeY);
                locations[sheep.getX()][sheep.getY()].addAnimal(sheep);
            }

        }

        // Цикл для каждого животного на острове
        while (getHowManyAnimals() != 0) {

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Location location = locations[i][j];
                    List<Animal> animalsInLocation = location.getAnimals();
                    List<Plant> plantsInLocation = location.getPlants();
                    if (animalsInLocation.size() > 1 || !plantsInLocation.isEmpty() && !animalsInLocation.isEmpty()) { //Может ли хищник съесть другого
                        for (int k = 0; k < animalsInLocation.size(); k++) {
                            if (animalsInLocation.get(k) instanceof Predator currentPredator) {
                                for (Animal otherAnimal : animalsInLocation) {
                                    if (currentPredator == otherAnimal ) {
                                        if (currentPredator.eat(otherAnimal)) {
                                            break; //Прерывание т. к. хищник съел животное
                                        }
//                                      Thread.sleep(2000);

                                    }
                                }
                            }
                            else if (animalsInLocation.get(k) instanceof Herbivore currentHerbivore) {
                                for (Plant plant : plantsInLocation) {
                                    if (currentHerbivore.eat(plant)) { // Прерывание цикла, так как травоядный съел растение
                                        break;
                                    }
//                                   Thread.sleep(2000);


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
                                    if (animalsInLocation.get(d) != currentAnimal && currentAnimal.multiply(animalsInLocation.get(d), locations)) {
                                        needToBreak = true;
                                        break; //только одно размножение в одной локации за каждый ход
                                    }
                                }
                            }
//                            Thread.sleep(2000);
                        }

                    }

                    // Для перемещения животных из одной локации в другую, если они там есть
                    for (int f = 0; f < animalsInLocation.size(); f++) {
                        animalsInLocation.get(f).walk(locations);
//                        Thread.sleep(2000);
                    }


                }
            }
            //Даем животным ходить
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Location location = locations[i][j];
                    for (Animal animal : location.getAnimals()) {
                        animal.setMoved(false);
                    }
                }
            }

            // Проверка голода
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    List<Animal> animalsInLocation = Island.getLocations()[i][j].getAnimals();
                    for (int k = 0; k < animalsInLocation.size(); k++) {
                        animalsInLocation.get(k).checkHunger();
                    }
                }
            }

            System.out.println("------------------------");
            Location.spawnPlant(1);

        }
        System.out.println("All animal are dead");


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


}
