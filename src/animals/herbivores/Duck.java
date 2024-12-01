package animals.herbivores;

import Land.Island;
import Land.Plant;
import animals.Animal;

import java.util.Random;

public class Duck extends Herbivore {


    public Duck(int x, int y) {
        super(x, y);
    }

    public Duck() {
        super();
    }

    public void eatCaterpillar(Caterpillar caterpillar) {
        double changeToEatCaterpillar = 0.9;
        Random random = new Random();
        if (getHunger() <= 5 && getHunger() >= 0) {
                if (random.nextDouble() < changeToEatCaterpillar) {
                    Island.getLocations()[this.getY()][this.getX()].removeAnimal(caterpillar);

                    System.out.println(this.getClass().getSimpleName() + " " + this.getId() + " ate "
                            + caterpillar.getClass().getSimpleName() + " in [" + this.getY() + ", " + this.getX() + "]");
                }
                else {
                    System.out.println(this.getClass().getSimpleName() + " " + this.getId() + " tried to eat "
                            + caterpillar.getClass().getSimpleName() + " ["  + changeToEatCaterpillar + "]");
                }
                Island.setHowManyAnimals(Island.getHowManyAnimals() - 1);
            this.setHunger(0);
            this.setMoved(true);
        }
    }

}
