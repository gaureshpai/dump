class MyThread extends Thread {
    // Constructor calling base class constructor using super
    public MyThread(String name) {
        super(name);
        start(); // Start the thread in the constructor
    }

    // The run method that will be executed when the thread starts
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " Count: " + i);
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " Thread interrupted.");
            }
        }
    }
}

public class ThreadConcurrentExample {
    public static void main(String[] args) {
        // Create an instance of MyThread
        MyThread myThread = new MyThread("Child Thread");

        // Main thread
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " Thread Count: " + i);
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " Thread interrupted.");
            }
        }
    }
}
