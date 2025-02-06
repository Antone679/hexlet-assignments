package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[10];
    private int counter;


    public int get(int index) {
        return array[index];
    }

    public synchronized void add(int number) {
        checkBound();
        array[counter] = number;
        counter++;
    }

    public int getSize() {
        return this.counter;
    }

    private void checkBound() {
        if (counter == array.length) {
            int[] newArray = new int[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.array = newArray;
        }
    }

    // END
}
