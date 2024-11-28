package animals;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import Land.Location;

public abstract class Animal {

    private boolean isMoved = false;
    private int hunger = 0;
    private static int counter;
    private int id;
    private int x;
    private int y;

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

    public boolean isMoved() {
        return isMoved;
    }

    public int getHunger() {
        return hunger;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public static void setCounter(int counter) {
        Animal.counter = counter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
