package polymorphism.homework3;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class WordFinder {
    private final Map<String, Integer> uniqueWords = new HashMap<>();

    public WordFinder(String[] strings) {
        find(strings);
    }

    private void find(String @NotNull [] strings) {
        for (String string : strings) {
            if (!uniqueWords.containsKey(string))
                uniqueWords.put(string, 1);      //если слово встретилось впервые
            else
                uniqueWords.replace(string, uniqueWords.get(string) + 1);        //для меня было неожиданностью, что операция инкремента здесь не работает, применил "магическое число".
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<String, Integer> entry : uniqueWords.entrySet())
            string.append("word: ").append(entry.getKey()).append(", entry: ").append(entry.getValue()).append("\n");
        return string.toString();
    }
}

public class BearerArray {
    public static void main(String[] args) {
        String[] strings = new String[]{"коля", "вася", "петя", "вася", "ира", "коля", "get",
                "set", "set", "new", "вася", "set", "table", "array", "array", "ира", "engine",
                "table", "set", "engine", "new", "get", "table"};
        WordFinder wordFinder = new WordFinder(strings);
        System.out.println(wordFinder.toString());
    }
}
