package animals.predators;

import Land.Island;
import animals.Animal;
import animals.herbivores.*;

public class Bear extends Animal {


    @Override
    public <T> void eat(T food) {
        Animal[] eatableAnimals = canEatOnly();
        if (getHunger() <= 10 && getHunger() >= 0) {
            for (Animal eatableAnimal : eatableAnimals) {
                if (food.getClass().equals(eatableAnimal.getClass())) {
                    Island.getLocations()[getX()][getY()].removeAnimal(eatableAnimal);
                    setHunger(getHunger() - 1);
                    System.out.println("Bear " + getId() + " ate "
                            + food.getClass().getSimpleName() + " " + ((Animal) food).getId()
                            + " in [" + ((Animal) food).getX() + ", " + ((Animal) food).getY() + "]");
                }

            }
        }
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[]{new Python(), new Horse(), new Deer(), new Rabbit(),
                new Mouse(), new Goat(), new Sheep(), new Hog(), new Buffalo(), new Duck()};
    }





}
