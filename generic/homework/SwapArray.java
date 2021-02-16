package generic.homework;

import java.util.ArrayList;
import java.util.Collections;

public class SwapArray<T> {
    private T[] array;

    public SwapArray(T... array) {
        this.array = array;
    }

    public void swapArray(int i1, int i2) {                                 //Задание 1.
        if (i1 < array.length && i2 < array.length && i1 != i2) {
            T tmp = array[i1];
            array[i1] = array[i2];
            array[i2] = tmp;
        } else {
            System.out.println("Bad index");
        }
    }

    public ArrayList<T> arrayToArrayList() {                               //Задание 2.
        ArrayList<T> output = new ArrayList<>();
        Collections.addAll(output, array);
        return output;
    }

    public static void main(String[] args) {
        SwapArray<String> swapArray = new SwapArray<String>("hello", "my", "dear", "friend");
        swapArray.swapArray(3, 2);
        System.out.println(swapArray.arrayToArrayList());
        SwapArray<StringBuffer> swapArray1 = new SwapArray<>(new StringBuffer("How"), new StringBuffer("are"), new StringBuffer("you?"));
        swapArray1.swapArray(0,2);
        System.out.println(swapArray1.arrayToArrayList());
    }
}
