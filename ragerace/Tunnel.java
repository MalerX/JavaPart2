package multithreads.homework.ragerace;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore tunnelManager;

    public Tunnel(Semaphore tunnelManager) {
        this.tunnelManager = tunnelManager;
        this.length = 80;
        this.description = String.format("Тоннель %d метров", this.length);
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.printf("%s готовится к этапу (ждёт): %s%n", car.getName(), this.description);
                tunnelManager.acquire();
                Thread.sleep(this.length / car.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.printf("%s закончил этап: %s%n", car.getName(), this.description);
                tunnelManager.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
