package animals.herbivores;

import animals.Animal;

public class Rabbit extends Animal {

    public Rabbit(int x, int y) {
        super(x, y);
    }

    public Rabbit() {
        super();
    }

    @Override
    public <T> void eat(T food) {

    }

    @Override
    public Animal[] canEatOnly() {
        return new Animal[0];
    }


}
