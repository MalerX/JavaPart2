package generic.homework.boxwithfruits;

import java.util.ArrayList;

public class Box<T extends Fruit> {                                             //Задание 3.b
    private final ArrayList<T> boxWithFruits = new ArrayList<>();               //Задание 3.c

    public void addFruit(T fruit) {                                             //Задание 3.g
        boxWithFruits.add(fruit);
    }

    public float getWeightBox() {                                               //Задание 3.d
        return boxWithFruits.size() * boxWithFruits.get(0).getWeightOfOne();
    }

    public boolean compare(Box<?> box) {                                        //Задание 3.e
        return Math.abs(this.getWeightBox() - box.getWeightBox()) < 0.0001;
    }

    public void intersperse(Box<T> newBox) {                                    //Задание 3.f
        newBox.boxWithFruits.addAll(this.boxWithFruits);
        this.boxWithFruits.clear();
    }
}