package polymorphism.homework.competition;

import polymorphism.homework.competition.interfaces.Obstacle;
import polymorphism.homework.competition.interfaces.Participant;

public class Treadmill implements Obstacle {
    private double distance;

    public Treadmill(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.run(distance);
    }
}
