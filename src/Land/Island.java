package Land;

import animals.Animal;
import animals.herbivores.Rabbit;
import animals.predators.Bear;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Island {

    Location[][] locations;

    public Island(int width, int height) throws InterruptedException {

        this.locations = new Location[width][height]; //Добавление локаций
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
            }
        }

        Random random = new Random();
//        int howManyAnimal = random.nextInt(2, 4);
        int howManyAnimal = 2; //Пока что, только 2 животного, но потом вернуть как на одной строке выше

        for (int i = 0; i < howManyAnimal; i++) { // Изначальное место животных
            int whatAnimal = random.nextInt(2);
            int whereItWillBeX = random.nextInt(width);
            int whereItWillBeY = random.nextInt(height);
            if (whatAnimal == 1) { //
                Bear bear = new Bear();
                bear.x = whereItWillBeX;
                bear.y = whereItWillBeY;
                locations[whereItWillBeX][whereItWillBeY].addAnimal(bear);
            }
            else {
                Rabbit rabbit = new Rabbit();
                rabbit.x = whereItWillBeX;
                rabbit.y = whereItWillBeY;
                locations[whereItWillBeX][whereItWillBeY].addAnimal(rabbit);
            }
        }

        // Цикл для каждого животного на острове
        while (howManyAnimal != 1) {
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
                                        Bear bear = (Bear) currentAnimal;
                                        bear.eat(otherAnimal);
                                        location.removeAnimal(otherAnimal);
                                        howManyAnimal--; // Уменьшение счетчика животных
                                        break; // Прерывание цикла, так как медведь уже съел одного зайца
                                    }


                                }
                            }
                        }
                    }

                     // Для хождения
                    List<Animal> animalsToMove = animalsInLocation;
                    for (int f = 0; f < animalsToMove.size(); f++) {
                        animalsToMove.get(f).walk(locations);
                        Thread.sleep(2000);
                    }


                }
            }
        }


    }

    public Location[][] getLocations() {
        return locations;
    }

}