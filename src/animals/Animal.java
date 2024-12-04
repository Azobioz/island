package animals;


import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import land.Island;
import land.Location;


public abstract class Animal {

    private boolean isMoved = false;
    private int hunger = 0;
    private int rechargeToMultiplyAgain = 10;
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

    }

    public abstract <T> T[] canEatOnly();

    public boolean walk(Location[][] island) {
        if (!isMoved()) {
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

            if (newX == x && newY == y) {
                switch (newMove) {
                    case 0:
                        System.out.println(this.getClass().getSimpleName() + " " + id + " can't move to up because he's in [" + newY + ", " + newX + "]");
                        break;
                    case 1:
                        System.out.println(this.getClass().getSimpleName() + " " + id + " can't move to right because he's in [" + newY + ", " + newX + "]");
                        break;
                    case 2:
                        System.out.println(this.getClass().getSimpleName() + " " + id + " can't move to down because he's in [" + newY + ", " + newX + "]");
                        break;
                    case 3:
                        System.out.println(this.getClass().getSimpleName() + " " + id + " can't move to left because he's in [" + newY + ", " + newX + "]");
                        break;

                }
                return false;
            }
            else {
                island[y][x].removeAnimal(this);
                island[newY][newX].addAnimal(this);
                System.out.println(this.getClass().getSimpleName() + " " + id + " moved to [" + newY + ", " + newX + "]");
                this.x = newX;
                this.y = newY;
            }
            isMoved = true;
            hunger++;
            rechargeToMultiplyAgain++;
            return true;
        }
        return false;
    }


    public boolean multiply(Object otherAnimal, Location[][] island) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (otherAnimal.getClass() == this.getClass() && rechargeToMultiplyAgain >= 10) {
            Animal newAnimal = this.getClass().getDeclaredConstructor().newInstance();
            setCounter(getCounter() + 1);
            newAnimal.setId(counter);
            newAnimal.setMoved(true);
            newAnimal.setX(x);
            newAnimal.setY(y);
            island[y][x].addAnimal(newAnimal);
            Island.setHowManyAnimals(Island.getHowManyAnimals() + 1);
            this.rechargeToMultiplyAgain = 0;
            ((Animal) otherAnimal).rechargeToMultiplyAgain = 0;
            newAnimal.rechargeToMultiplyAgain = 0;
            System.out.println(this.getClass().getSimpleName() + " " + this.id
                    + " + " + otherAnimal.getClass().getSimpleName() + " " + ((Animal) otherAnimal).getId() + " = "
                    + newAnimal.getClass().getSimpleName() + " " + newAnimal.getId());
            return true;
        }
        return false;
    }

    public void checkHunger() {
        if (hunger >= 5) {
            Island.getLocations()[this.getY()][this.getX()].removeAnimal(this);
            Island.setHowManyAnimals(Island.getHowManyAnimals() - 1);
            System.out.println(Animal.this.getClass().getSimpleName() + " " + this.id + " is starved to death");
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

    public int isRechargeToMultiplyAgain() {
        return rechargeToMultiplyAgain;
    }

    public void setRechargeToMultiplyAgain(int rechargeToMultiplyAgain) {
        this.rechargeToMultiplyAgain = rechargeToMultiplyAgain;
    }
}
