package test;

import animals.herbivores.Duck;
import land.Island;
import land.Location;
import org.junit.Assert;
import org.junit.Test;

public class AnimalWalkTest {

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
    public void animalWalk()  {


        Duck duck1 = new Duck();
        duck1.setX(1);
        duck1.setY(1);

        locations[1][1].addAnimal(duck1);


        boolean result = duck1.walk(locations);
        Assert.assertTrue(result);

        duck1.setX(0);
        duck1.setY(0);

        Location[][] smallLocations = new Location[1][1];

        smallLocations[0][0] = new Location();


        Island.setLocations(smallLocations);
        locations[0][0].addAnimal(duck1);
        boolean negativeResult = duck1.walk(locations);
        Assert.assertFalse(negativeResult);
    }

}
