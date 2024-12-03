package animals.predators;

import land.Island;
import animals.Animal;

import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(int x, int y) {
        super(x, y);
    }

    public Predator() {
        super();
    }

    public abstract double getChanceToEat(Animal other);

    public boolean eat(Animal food) {
        Object[] eatableAnimals = canEatOnly();
        Random random = new Random();
        double changeToEat = getChanceToEat(food);
        if (getHunger() <= 5 && getHunger() >= 0) {
            for (Object eatableAnimal : eatableAnimals) {
                if (food.getClass().equals(eatableAnimal.getClass())) {
                    if (random.nextDouble() < changeToEat) {
                        Island.getLocations()[getY()][getX()].removeAnimal(food);
                        setHunger(0);
                        setMoved(true);
                        Island.setHowManyAnimals(Island.getHowManyAnimals() - 1);
                        System.out.println(this.getClass().getSimpleName() + " " + getId() + " ate "
                                + food.getClass().getSimpleName() + " " + food.getId()
                                + " in [" + food.getY() + ", " + food.getX() + "]");
                        return true;
                    }
                    else {
                        setMoved(true);
                        setHunger(getHunger() + 1);
                        System.out.println(this.getClass().getSimpleName() + " " + getId()
                                + " tried to eat " + food.getClass().getSimpleName()
                                + " " + food.getId() + " [" + changeToEat + "]");
                        return false;
                    }
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
