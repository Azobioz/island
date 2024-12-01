package animals.predators;

import animals.Animal;

public class Wolf extends Predator {

    public Wolf(int x, int y) {
        super(x, y);
    }

    public Wolf() {
        super();
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
