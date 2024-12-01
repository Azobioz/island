package animals.herbivores;

import animals.Animal;

public class Duck extends Animal {
    @Override
    public void eat(Object food) {

    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
