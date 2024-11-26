package animals.predators;

import animals.Animal;
import animals.herbivores.*;

public class Bear extends Animal {


    @Override
    public <T> void eat(T food) {
        Animal[] eatableAnimal = canEatOnly();
        if (hunger <= 10) {
            for (int i = 0; i < eatableAnimal.length; i++) {
                if (food.getClass().equals(eatableAnimal[i].getClass())) {
                    hunger++;
                    System.out.println("Bear nom-nom");
                }

            }
            Animal animalFood = (Animal) food;
            animalFood.isDead = true;
        }
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[]{new Python(), new Horse(), new Deer(), new Rabbit(),
                new Mouse(), new Goat(), new Sheep(), new Hog(), new Buffalo(), new Duck()};
    }



    @Override
    public void multiply() {

    }


}
