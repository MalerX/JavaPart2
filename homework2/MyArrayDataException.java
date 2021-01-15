package polymorphism.homework2;

public class MyArrayDataException extends Exception {
    private int n1 = 0, n2 = 0;

    public MyArrayDataException(String[][] brokenArray) {
        getIndexArray(brokenArray);
    }

    private void getIndexArray(String[][] brokenArray) {
        for (int i = 0; i < brokenArray.length; i++) {
            for (int j = 0; j < brokenArray[i].length; j++) {
                if(!CheckValue.isInteger(brokenArray[i][j])) {
                    n1 = i;
                    n2 = j;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Преобразование не удалось. В ячейке [" + n1 + "][" + n2 + "] не верные данные";
    }
}
