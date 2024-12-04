package test;

import animals.herbivores.Deer;
import animals.predators.Bear;
import land.Island;
import land.Location;
import org.junit.Assert;
import org.junit.Test;

public class PredatorEatTest {

    Location[][] locations = new Location[10][10];

    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                locations[i][j] = new Location();
            }
        }
        Island.setLocations(locations);
    }

    @Test
    public void predatorEat() {


        Bear bear1 = new Bear();
        bear1.setX(1);
        bear1.setY(1);

        Deer deer1 = new Deer();
        deer1.setX(1);
        deer1.setY(1);

        locations[1][1].addAnimal(bear1);
        locations[1][1].addAnimal(deer1);

        boolean result = bear1.eat(deer1);
        Assert.assertTrue(result);
    }


}
