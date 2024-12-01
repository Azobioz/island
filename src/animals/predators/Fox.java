package animals.predators;

import animals.Animal;

public class Fox extends Predator {


    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
