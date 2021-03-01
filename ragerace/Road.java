package multithreads.homework.ragerace;

public class Road extends Stage{
    public Road(int length) {
        this.length = length;
        this.description = String.format("Дорога %d метров",this.length);
    }

    @Override
    public void go(Car car) {
        try {
            System.out.printf("%s начал этап: %s%n", car.getName(), this.description);
            Thread.sleep(this.length / car.getSpeed() * 1000L);
            System.out.printf("%s закончил этап: %s%n", car.getName(), this.description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
