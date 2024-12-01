package animals.predators;

import animals.Animal;
import animals.herbivores.*;

public class Eagle extends Predator {

    public Eagle(int x, int y) {
        super(x, y);
    }

    public Eagle() {
        super();
    }

    @Override
    public double getChanceToEat(Animal other) {
        if (other instanceof Fox) return 0.1;
        else if (other instanceof Rabbit) return 0.9;
        else if (other instanceof Mouse) return 0.9;
        else if (other instanceof Duck) return 0.8;
        return 0.0;
    }


    @Override
    public Animal[] canEatOnly() {
        return new Animal[] {new Fox(), new Rabbit(), new Mouse(), new Duck()};
    }


}
