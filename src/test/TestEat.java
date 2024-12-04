package test;

import animals.herbivores.Deer;
import animals.herbivores.Duck;
import animals.herbivores.Sheep;
import animals.predators.Bear;
import animals.predators.Wolf;
import land.Island;
import land.Island.*;
import land.Location;
import land.Plant;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestEat {

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
    }

    @Test
    public void animalWalk()  {


        Duck duck1 = new Duck();
        duck1.setX(5);
        duck1.setY(5);

        locations[1][1].addAnimal(duck1);


        boolean result = duck1.walk(locations);
        Assert.assertTrue(result);
    }



}
