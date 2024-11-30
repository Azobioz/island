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

    public Island(int width, int height, int howManyAnimals) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        locations = new Location[width][height]; //Добавление локаций
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
            }
        }

        Random random = new Random();
        setHowManyAnimals(howManyAnimals);

        for (int i = 0; i < getHowManyAnimals(); i++) { // Изначальное место животных на острове
            int whatAnimal = random.nextInt(2);
            int whereItWillBeX = random.nextInt(width);
            int whereItWillBeY = random.nextInt(height);
            if (whatAnimal == 1) { //
                Bear bear = new Bear();
                bear.setX(whereItWillBeX);
                bear.setY(whereItWillBeY);
                locations[whereItWillBeX][whereItWillBeY].addAnimal(bear);
            }
            else {
                Rabbit rabbit = new Rabbit();
                rabbit.setX(whereItWillBeX);
                rabbit.setY(whereItWillBeY);
                locations[whereItWillBeX][whereItWillBeY].addAnimal(rabbit);
            }
        }

        // Цикл для каждого животного на острове
        while (getHowManyAnimals() != 0) {

            // Проверка голода
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                   List<Animal> animalsInLocation = Island.getLocations()[i][j].getAnimals();
                   for (int k = 0; k < animalsInLocation.size(); k++) {
                       animalsInLocation.get(k).checkHunger();
                   }
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Location location = locations[i][j];
                    List<Animal> animalsInLocation = location.getAnimals();
                    if (animalsInLocation.size() > 1) { //Может ли животное съесть другого
                        for (int k = 0; k < animalsInLocation.size(); k++) {
                            Animal currentAnimal = animalsInLocation.get(k);
                            if (currentAnimal instanceof Bear) {
                                for (Animal otherAnimal : animalsInLocation) {
                                    if (otherAnimal instanceof Rabbit) {
                                        currentAnimal.eat(otherAnimal);
                                        setHowManyAnimals(getHowManyAnimals() - 1);
                                        Thread.sleep(2000);
                                        break; // Прерывание цикла, так как медведь уже съел одного зайца
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
                            Thread.sleep(2000);
                        }

                    }

                    // Для перемещения животных из одной локации в другую, если они там есть
                    for (int f = 0; f < animalsInLocation.size(); f++) {
                        animalsInLocation.get(f).walk(locations);
                        Thread.sleep(2000);
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


}
