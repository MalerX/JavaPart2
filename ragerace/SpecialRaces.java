package multithreads.homework.ragerace;

import java.util.concurrent.*;

public class SpecialRaces {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        ExecutorService trackWorker = Executors.newFixedThreadPool(CARS_COUNT);
        CountDownLatch startingGun = new CountDownLatch(CARS_COUNT);
        CountDownLatch girlWithFlag = new CountDownLatch(1);
        CountDownLatch gameOver = new CountDownLatch(CARS_COUNT);
        CyclicBarrier raceManager = new CyclicBarrier(CARS_COUNT);
        Semaphore tunnelManager = new Semaphore(CARS_COUNT / 2 - 1);        //чёртов костыль который режет мне глаза.

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(tunnelManager), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++)
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), raceManager, startingGun, girlWithFlag, gameOver);
        for (Car car : cars) new Thread(car).start();
        try {
            startingGun.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            girlWithFlag.await();
            if (girlWithFlag.getCount() == 0)
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> У нас есть победитель!");
            gameOver.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
