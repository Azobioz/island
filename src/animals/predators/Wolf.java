package animals.predators;

import animals.Animal;
import animals.herbivores.*;

public class Wolf extends Predator {

    public Wolf(int x, int y) {
        super(x, y);
    }

    public Wolf() {
        super();
    }

    @Override
    public double getChanceToEat(Animal other) {
        if (other instanceof Horse) return 0.1;
        else if (other instanceof Deer) return 0.15;
        else if (other instanceof Rabbit) return 0.6;
        else if (other instanceof Mouse) return 0.8;
        else if (other instanceof Goat) return 0.6;
        else if (other instanceof Sheep) return 0.7;
        else if (other instanceof Hog) return 0.15;
        else if (other instanceof Buffalo) return 0.1;
        else if (other instanceof Duck) return 0.4;
        return 0.0;
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[] {new Horse(), new Deer(), new Rabbit(), new Mouse(), new Goat(), new Sheep(), new Hog(), new Buffalo(), new Duck()};
    }


}
