package generic.homework.boxwithfruits;

public class Orange extends Fruit{                  //Задание 3.a
    private final float WEIGHT_OF_ONE = 1.5f;

    @Override
    float getWeightOfOne() {
        return WEIGHT_OF_ONE;
    }
}