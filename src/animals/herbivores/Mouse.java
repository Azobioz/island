package animals.herbivores;

import Land.Plant;
import animals.Animal;

public class Mouse extends Herbivore {
    @Override
    public void eat(Object food) {

    }

    @Override
    public Plant[] canEatOnly() {
        return new Plant[0];
    }


}
