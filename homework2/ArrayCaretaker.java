package polymorphism.homework2;

public class ArrayCaretaker {
    public static int sumValuesAtArray(String[][] stringArray) throws MyArrayDataException, MyArraySizeException {
        int sumArray = 0;
        if (stringArray.length != 4) throw new MyArraySizeException(stringArray);
        for (String[] value : stringArray) {
            if (value.length != 4) throw new MyArraySizeException(stringArray);
        }
        for (String[] strings : stringArray) {
            for (String string : strings) {
                if (!CheckValue.isInteger(string)) throw new MyArrayDataException(stringArray);
                sumArray += Integer.parseInt(string);
            }
        }
        return sumArray;
    }
}