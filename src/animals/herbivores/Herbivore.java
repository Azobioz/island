package animals.herbivores;

import Land.Island;
import Land.Plant;
import animals.Animal;

public class Herbivore extends Animal {

    public Herbivore(int x, int y) {
        super(x, y);
    }

    public Herbivore() {
        super();
    }


    public boolean eat(Plant food) {
        if (getHunger() <= 5 && getHunger() >= 0) {
            if (food.getClass().equals(canEatOnly()[0].getClass())) {
                Island.getLocations()[this.getY()][this.getX()].removePlant(food);
                this.setHunger(0);
                this.setMoved(true);
                System.out.println(this.getClass().getSimpleName() + " " + this.getId() + " ate "
                        + food.getClass().getSimpleName() + " in [" + this.getY() + ", " + this.getX() + "]");
                return true;
            }

        }
        return false;
    }

    @Override
    public Plant[] canEatOnly() {
        return new Plant[]{ new Plant()};
    }

}
