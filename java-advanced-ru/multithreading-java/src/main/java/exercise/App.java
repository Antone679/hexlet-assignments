package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static void main(String[] args) {

        int[] numbers = {10, -4, 67, 100, -100, 8};
        try {
            System.out.println(App.getMinMax(numbers));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Integer> getMinMax(int[] array) throws InterruptedException {
        MaxThread maxThread = new MaxThread(array);
        MinThread minThread = new MinThread(array);

        maxThread.start();
        LOGGER.log(Level.INFO, maxThread.getName() + " started");
        minThread.start();
        LOGGER.log(Level.INFO, minThread.getName() + " started");

        maxThread.join();
        minThread.join();

        LOGGER.log(Level.INFO, maxThread.getName() + " finished");
        LOGGER.log(Level.INFO, minThread.getName() + " finished");

        return Map.of("min", minThread.getResult(),
                "max", maxThread.getResult());
    }
    // END
}
