package polymorphism.homework.competition;

import polymorphism.homework.competition.interfaces.Participant;

public class Human implements Participant {
    private double limitRun;
    private double limitJump;

    public Human(double limitRun, double limitJump) {
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public boolean run(double distance) {
        if (limitRun > distance) {
            System.out.println("Человек успешно пробежал препятствие");
            return true;
        } else {
            System.out.println("Человек не смог пробежать препятствие");
            return false;
        }
    }

    @Override
    public boolean jump(double distance) {
        if (limitJump > distance) {
            System.out.println("Человек успешно перепрыгнул препятствие");
            return true;
        } else {
            System.out.println("Человек не смог перепрыгнуть препятствие");
            return false;
        }
    }
}
