package animals;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import Land.Location;

public abstract class Animal {

    public boolean isDead = false;
    public boolean isMoved = false;
    public int hunger = 0;
    public static int counter;
    public int id;

    public int x;
    public int y;

    public Animal(int x, int y) {
        this.x = x;
        this.y = y;
        id = ++counter;
    }

    public Animal() {
        id = ++counter;
    }

    public abstract <T> void eat(T food);

    public abstract Animal[] canEatOnly();

    public void walk(Location[][] island) {

        Random random = new Random();
        int newMove = random.nextInt(4); // 0 - up, 1 - right, 2 - down, 3 - left

        int newX = x;
        int newY = y;

        switch (newMove) {
            case 0: // up
                newY = Math.max(0, y - 1);
                break;

            case 1: // right
                newX = Math.min(island[0].length - 1, x + 1);
                break;
            case 2: // down
                newY = Math.min(island.length - 1, y + 1);
                break;
            case 3: // left
                newX = Math.max(0, x - 1);
                break;
        }

        island[x][y].removeAnimal(this);
        island[newX][newY].addAnimal(this);
        System.out.println(this.getClass().getSimpleName() + " " + id + " moved");
        this.x = newX;
        this.y = newY;
        isMoved = true;
        hunger++;
    }


    public <T> void multiply(T otherAnimal, Location[][] island) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (otherAnimal.getClass() == this.getClass()) {
            Animal newAnimal = this.getClass().getDeclaredConstructor().newInstance();
            island[x][y].addAnimal(newAnimal);
        }
    }

}
