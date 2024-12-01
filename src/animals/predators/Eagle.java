package animals.predators;

import animals.Animal;

public class Eagle extends Predator {


    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
