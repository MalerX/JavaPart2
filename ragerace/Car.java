package multithreads.homework.ragerace;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable, Callable {
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;

    private final CyclicBarrier raceManager;
    private final CountDownLatch startingGun;
    private final CountDownLatch girlWithFlag;
    private final CountDownLatch gameOver;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier raceManager, CountDownLatch startingGun, CountDownLatch girlWithFlag, CountDownLatch gameOver) {
        this.race = race;
        this.speed = speed;
        this.startingGun = startingGun;
        this.raceManager = raceManager;
        this.girlWithFlag = girlWithFlag;
        this.gameOver = gameOver;
        CARS_COUNT++;
        this.name = "Участник №" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s готовится%n", this.name);
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.printf("%s готов%n", this.name);
            startingGun.countDown();
            raceManager.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++)
            race.getStages().get(i).go(this);
        girlWithFlag.countDown();
        gameOver.countDown();
    }

    @Override
    public String call() throws Exception {
        return String.format("Победил %s",this.name);
    }
}
