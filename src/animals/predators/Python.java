package animals.predators;

import animals.Animal;
import animals.herbivores.Caterpillar;
import animals.herbivores.Duck;
import animals.herbivores.Mouse;
import animals.herbivores.Rabbit;

public class Python extends Predator {

    public Python(int x, int y) {
        super(x, y);
    }

    public Python() {
        super();
    }

    @Override
    public double getChanceToEat(Animal other) {
        if (other instanceof Fox) return 0.15;
        else if (other instanceof Rabbit) return 0.2;
        else if (other instanceof Mouse) return 0.4;
        else if (other instanceof Duck) return 0.1;
        return 0.0;
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[] {new Fox(), new Rabbit(), new Mouse(), new Duck()};
    }


}
