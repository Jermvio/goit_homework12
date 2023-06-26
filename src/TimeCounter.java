import java.util.concurrent.TimeUnit;

public class TimeCounter {


    public void startClock() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread fiveSecCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Минуло 5 секунд");
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(5));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        fiveSecCounter.start();
        while (true) {
            System.out.println("Загалом пройшло" + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime));
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
    }
}