import land.Island;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new Island(3, 3, 10, 20));
        executor.shutdown();
    }
}
