package polymorphism.homework.competition;

import polymorphism.homework.competition.interfaces.Obstacle;
import polymorphism.homework.competition.interfaces.Participant;

public class Wall implements Obstacle {
    private double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.jump(height);
    }
}
