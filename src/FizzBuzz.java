import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FizzBuzz {
    private int n;
    private BlockingQueue<String> queue;
    private volatile int currentNumber;

    public FizzBuzz(int n) {
        this.n = n;
        this.queue = new ArrayBlockingQueue<>(n);
        this.currentNumber = 1;
    }

    public FizzBuzz() {
        this(30);
    }

    public void fizz() {
        while (true) {
            int number = currentNumber;
            if (number > n) return;

            if (number % 3 == 0 && number % 5 != 0) {
                enqueue("fizz");
                currentNumber++;
            }
        }
    }

    public void buzz() {
        while (true) {
            int number = currentNumber;
            if (number > n) return;

            if (number % 5 == 0 && number % 3 != 0) {
                enqueue("buzz");
                currentNumber++;
            }
        }
    }

    public void fizzbuzz() {
        while (true) {
            int number = currentNumber;
            if (number > n) return;

            if (number % 3 == 0 && number % 5 == 0) {
                enqueue("fizzbuzz");
                currentNumber++;
            }
        }
    }

    public void number() {
        while (true) {
            int number = currentNumber;
            if (number > n) return;

            if (number % 3 != 0 && number % 5 != 0) {
                enqueue(String.valueOf(number));
                currentNumber++;
            }
        }
    }

    private void enqueue(String value) {
        try {
            queue.put(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void runFizzBuzz() {
        Thread threadA = new Thread(this::fizz);
        Thread threadB = new Thread(this::buzz);
        Thread threadC = new Thread(this::fizzbuzz);
        Thread threadD = new Thread(this::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        while (!queue.isEmpty()) {
            System.out.print(queue.poll());
            if (!queue.isEmpty()) {
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        fizzBuzz.runFizzBuzz();
    }
}
