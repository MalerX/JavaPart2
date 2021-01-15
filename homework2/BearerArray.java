package polymorphism.homework2;

public class BearerArray {
    public static void main(String[] args) {
        String[][] stringsArray = new String[][] {{"564", "567", "219", "14"}, {"45", "213", "1003", "34"},
                {"875", "428", "234", "9845"}, {"16", "12", "255", "32"}, {"16", "12", "255", "32"}};
        try {
            System.out.println(ArrayCaretaker.sumValuesAtArray(stringsArray));
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e);
        }
    }
}
