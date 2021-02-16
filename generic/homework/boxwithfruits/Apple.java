package generic.homework.boxwithfruits;

public class Apple extends Fruit{                   //Задание 3.a
    private final float WEIGHT_OF_ONE = 1.0f;

    @Override
    float getWeightOfOne() {
        return WEIGHT_OF_ONE;
    }
}
