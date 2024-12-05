package test;

import animals.predators.Bear;
import animals.predators.Wolf;
import land.Island;
import land.Location;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class AnimalMultiplyTest {

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
    public void animalMultiply() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Wolf wolf1 = new Wolf();
        wolf1.setX(1);
        wolf1.setY(1);

        Wolf wolf2 = new Wolf();
        wolf2.setX(1);
        wolf2.setY(1);

        locations[1][1].addAnimal(wolf2);
        locations[1][1].addAnimal(wolf2);

        boolean result = wolf1.multiply(wolf2, locations);
        Assert.assertTrue(result);

        Wolf wolf3 = new Wolf();
        wolf3.setX(1);
        wolf3.setY(1);

        Bear bear1 = new Bear();
        bear1.setX(1);
        bear1.setY(1);

        boolean negativeResult = wolf3.multiply(bear1, locations);
        Assert.assertFalse(negativeResult);

    }

}
