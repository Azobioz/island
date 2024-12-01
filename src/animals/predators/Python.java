package animals.predators;

import animals.Animal;

public class Python extends Predator {

    public Python(int x, int y) {
        super(x, y);
    }

    public Python() {
        super();
    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
