package animals.predators;

import animals.Animal;
import animals.herbivores.Caterpillar;
import animals.herbivores.Duck;
import animals.herbivores.Mouse;
import animals.herbivores.Rabbit;

public class Fox extends Predator {

    public Fox(int x, int y) {
        super(x, y);
    }

    public Fox() {
        super();
    }

    @Override
    public double getChanceToEat(Animal other) {
        if (other instanceof Rabbit) return 0.7;
        else if (other instanceof Mouse) return 0.9;
        else if (other instanceof Duck) return 0.6;
        else if (other instanceof Caterpillar) return 0.4;
        return 0.0;
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[] {new Rabbit(), new Mouse(), new Duck(), new Caterpillar()};
    }


}
