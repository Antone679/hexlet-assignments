package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    private int[] array;
    private int result;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
       result =  Arrays.stream(array).max().getAsInt();
    }

    public int getResult() {
        return result;
    }
}
// END
