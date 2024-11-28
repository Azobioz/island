package animals.predators;

import animals.Animal;
import animals.herbivores.*;

public class Bear extends Animal {


    @Override
    public <T> void eat(T food) {
        Animal[] eatableAnimal = canEatOnly();
        if (getHunger() <= 10 && getHunger() > 0) {
            for (int i = 0; i < eatableAnimal.length; i++) {
                if (food.getClass().equals(eatableAnimal[i].getClass())) {
                    setHunger(getHunger() - 1);
                    System.out.println("Bear " + getId() + ": nom-nom " + food.getClass().getSimpleName() + " " );
                }

            }
            Animal animalFood = (Animal) food;
        }
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[]{new Python(), new Horse(), new Deer(), new Rabbit(),
                new Mouse(), new Goat(), new Sheep(), new Hog(), new Buffalo(), new Duck()};
    }





}
