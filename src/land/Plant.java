package land;

import java.util.Random;

public class Plant {

    private int changeToSpawn;

    public Plant() {
        createChangeToSpawn();
    }

    public void createChangeToSpawn() {
        Random random = new Random();
        int change = random.nextInt(5);

        switch (change) {
            case 0:
                changeToSpawn = 10;
                break;
            case 1:
                changeToSpawn = 15;
                break;
            case 2:
                changeToSpawn = 20;
                break;
            case 3:
                changeToSpawn = 30;
                break;
            case 4:
                changeToSpawn = 35;
                break;
        }

    }



    public int getChangeToSpawn() {
        return changeToSpawn;
    }
}
