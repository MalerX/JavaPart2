package polymorphism.homework3;

import java.util.*;

public class Phonebook {
    private final Map<String, String> phonebookDataBase = new HashMap<>();

    public Phonebook(String[] family, String[] number) {
        for (int i = 0; i < family.length; i++) {
            phonebookDataBase.put(number[i], family[i]);
        }
    }

    public void add(String family, String number) {
        this.phonebookDataBase.put(number, family);
    }

    public Set<String> get(String family) {
        Set<String> numbers = new HashSet<>();
        for (Map.Entry<String, String> entry : phonebookDataBase.entrySet()) {
            if (Objects.equals(family, entry.getValue()))
                numbers.add(entry.getKey());
        }
        return numbers;
    }

    public static void main(String[] args) {
        String[] numbersArray = new String[]
                {"89601248700", "89601248701", "89601248702", "89601248703", "89601248704",
                "89601248705", "89601248706", "89601248707", "89601248708", "89601248709",
                "89601248710", "89601248711", "89601248712", "89601248713", "89601248714",
                "89601248715", "89601248716", "89601248717", "89601248718", "89601248719"};

        String[] familyArray = new String[]
                {"O'Connor", "McMillan", "O'Braien", "Putin", "Vardonyn",
                "Chemezov", "Garhina", "Ganin", "O'Connor", "Vardonyn",
                "Bayron", "Kirlorov", "Krivcov", "Movchan", "Zhurik",
                "Ganin", "Movchan", "Buric", "Buric", "Bayron"};

        Phonebook phonebook = new Phonebook(familyArray, numbersArray);
        phonebook.add("Zbruev", "89563456722");
        System.out.println(phonebook.get("Ganin"));
        System.out.println(phonebook.get("Putin"));
        System.out.println(phonebook.get("Zbruev"));
    }
}
