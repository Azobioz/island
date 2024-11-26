package animals.predators;

import animals.Animal;

public class Eagle extends Animal {
    @Override
    public <T> void eat(T food) {

    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
