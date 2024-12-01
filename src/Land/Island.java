package Land;

import animals.Animal;
import animals.herbivores.Rabbit;
import animals.predators.Bear;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

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
            int whatAnimal = random.nextInt(2);
            int whereItWillBeX = random.nextInt(width);
            int whereItWillBeY = random.nextInt(height);
            if (whatAnimal == 1) {
                Bear bear = new Bear(whereItWillBeX, whereItWillBeY);
                locations[bear.getX()][bear.getY()].addAnimal(bear);
            }
            else {
                Rabbit rabbit = new Rabbit(whereItWillBeX, whereItWillBeY);
                locations[rabbit.getX()][rabbit.getY()].addAnimal(rabbit);
            }
        }

        // Цикл для каждого животного на острове
        while (getHowManyAnimals() != 0) {

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Location location = locations[i][j];
                    List<Animal> animalsInLocation = location.getAnimals();
                    List<Plant> plantsInLocation = location.getPlants();
                    if (animalsInLocation.size() > 1 || !plantsInLocation.isEmpty()) { //Может ли животное съесть другого
                        for (int k = 0; k < animalsInLocation.size(); k++) {
                            Animal currentAnimal = animalsInLocation.get(k);
                            if (animalsInLocation.get(k) != currentAnimal) {
                                for (Animal otherAnimal : animalsInLocation) {
                                        currentAnimal.eat(otherAnimal);
//                                        Thread.sleep(2000);
                                        break; // Прерывание цикла, так как хищник съел добычу
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
