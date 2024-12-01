package animals.predators;

import animals.Animal;

public class Fox extends Predator {

    public Fox(int x, int y) {
        super(x, y);
    }

    public Fox() {
        super();
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
