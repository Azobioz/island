package animals.predators;

import Land.Island;
import Land.Plant;
import animals.Animal;
import animals.herbivores.Herbivore;

public class Predator extends Animal {


    public void eat(Object food) {
        Object[] eatableAnimals = canEatOnly();
        if (getHunger() <= 5 && getHunger() >= 0) {
            for (Object eatableAnimal : eatableAnimals) {
                if (food.getClass().equals(eatableAnimal.getClass())) {
                    Island.getLocations()[getX()][getY()].removeAnimal((Animal) food);
                    setHunger(0);
                    Island.setHowManyAnimals(Island.getHowManyAnimals() - 1);
                    System.out.println(this.getClass().getSimpleName() + " " + getId() + " ate "
                            + food.getClass().getSimpleName() + " " + ((Animal) food).getId()
                            + " in [" + ((Animal) food).getX() + ", " + ((Animal) food).getY() + "]");
                    break;
                }

            }
        }
    }

    @Override
    public Herbivore[] canEatOnly() {
        return new Herbivore[] {new Herbivore()};
    }



}
