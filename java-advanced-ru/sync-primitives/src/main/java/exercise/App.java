package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList safetyList = new SafetyList();

        ListThread listThread1 = new ListThread(safetyList);
        ListThread listThread2 = new ListThread(safetyList);

        listThread1.start();
        listThread2.start();

        try {
            listThread1.join();
            listThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(safetyList.getSize());
        // END
    }
}

