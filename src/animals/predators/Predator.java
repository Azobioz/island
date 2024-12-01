package animals.predators;

import Land.Island;
import animals.Animal;

public class Predator extends Animal {

    public Predator(int x, int y) {
        super(x, y);
    }

    public Predator() {
        super();
    }




    public boolean eat(Animal food) {
        Object[] eatableAnimals = canEatOnly();
        if (getHunger() <= 5 && getHunger() >= 0) {
            for (Object eatableAnimal : eatableAnimals) {
                if (food.getClass().equals(eatableAnimal.getClass())) {
                    Island.getLocations()[getX()][getY()].removeAnimal(food);
                    setHunger(0);
                    Island.setHowManyAnimals(Island.getHowManyAnimals() - 1);
                    System.out.println(this.getClass().getSimpleName() + " " + getId() + " ate "
                            + food.getClass().getSimpleName() + " " + food.getId()
                            + " in [" + food.getX() + ", " + food.getY() + "]");
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[] {(Animal) new Object()};
    }



}
