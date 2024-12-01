package animals.predators;

import animals.Animal;

public class Eagle extends Predator {

    public Eagle(int x, int y) {
        super(x, y);
    }

    public Eagle() {
        super();
    }


    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
