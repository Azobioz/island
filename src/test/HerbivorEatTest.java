package test;

import animals.Animal;
import animals.herbivores.Mouse;
import animals.herbivores.Sheep;
import land.Island;
import land.Location;
import land.Plant;
import org.junit.Assert;
import org.junit.Test;

public class HerbivorEatTest {

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
    public void herbivorEat() {

        Sheep sheep1 = new Sheep();
        sheep1.setX(1);
        sheep1.setY(1);

        Plant plant1 = new Plant();

        locations[1][1].addAnimal(sheep1);
        locations[1][1].addPlant(plant1);

        boolean result = sheep1.eat(plant1);
        Assert.assertTrue(result);


        Plant plant2 = new Plant();
        locations[3][3].addPlant(plant2);
        sheep1.setY(5);
        sheep1.setX(4);
        boolean negativeResult = sheep1.eat(plant2);
        Assert.assertFalse(negativeResult);
    }

}
