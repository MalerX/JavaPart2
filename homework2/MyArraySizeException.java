package polymorphism.homework2;

public class MyArraySizeException extends Exception {
    private int n1 = 0, n2 = 0;

    public MyArraySizeException(String[][] brokenArray) {
        dimensioning(brokenArray);
    }

    private void dimensioning(String[][] strings) {
        if (strings.length != 4) n1 = strings.length;
        else for (int i = 0; i < strings.length; i++) {
            if (strings[i].length != 4) {
                n1 = i;
                n2 = strings[i].length;
            }
        }
    }

    @Override
    public String toString() {
        return "Размерность массива не соответствует 4х4, thisArray[" + n1 + "][" + n2 + "].";
    }
}
