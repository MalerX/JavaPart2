package polymorphism.homework.competition;

import polymorphism.homework.competition.interfaces.Obstacle;
import polymorphism.homework.competition.interfaces.Participant;

public class Competition {
    public static void main(String[] args) {
        Obstacle[] obstacles = new Obstacle[] {new Treadmill(400), new Wall(2)};
        Participant[] participants = new Participant[] {new Human(450,1.01), new Robot(100,3), new Cat(500, 1.5)};
        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                if (!obstacle.overcome(participant)) break;
            }
        }
    }
}
