package animals.herbivores;

import animals.Animal;

public class Caterpillar extends Animal {
    @Override
    public <T> void eat(T food) {

    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }

    @Override
    public void multiply() {

    }
}