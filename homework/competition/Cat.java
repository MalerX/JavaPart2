package polymorphism.homework.competition;

import polymorphism.homework.competition.interfaces.Participant;

public class Cat implements Participant {
    private double limitRun;
    private double limitJump;

    public Cat(double limitRun, double limitJump) {
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public boolean run(double distance) {
        if (limitRun > distance) {
            System.out.println("Кот успешно пробежал препятствие");
            return true;
        } else {
            System.out.println("Кот не смог пробежать препятствие");
            return false;
        }
    }

    @Override
    public boolean jump(double distance) {
        if (limitJump > distance) {
            System.out.println("Кот успешно перепрыгнул препятствие");
            return true;
        } else {
            System.out.println("Кот не смог перепрыгнуть препятствие");
            return false;
        }
    }
}
