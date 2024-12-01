package animals.predators;

import Land.Island;
import animals.Animal;
import animals.herbivores.*;

public class Bear extends Animal {

    public Bear(int x, int y) {
        super(x, y);
    }

    public Bear() {
        super();
    }


    @Override
    public Object[] canEatOnly() {
        return new Object[]{new Python(), new Horse(), new Deer(), new Rabbit(),
                new Mouse(), new Goat(), new Sheep(), new Hog(), new Buffalo(), new Duck()};
    }





}
