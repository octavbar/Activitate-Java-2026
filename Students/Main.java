import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> students = new ArrayList<>();

        students.add("Maria");
        students.add("Alex");
        students.add("John");
        students.add("Emma");
        students.add("David");

        System.out.println("All students:");
        for (String s : students) {
            System.out.println(s);
        }

        students.remove(2);

        System.out.println("\nAfter removing 3rd student:");
        System.out.println(students);


        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        int sum = 0;

        for (int n : numbers) {
            sum += n;
        }

        double average = (double) sum / numbers.size();

        System.out.println("\nSum: " + sum);
        System.out.println("Average: " + average);


        List<Integer> reverseList = new ArrayList<>();

        reverseList.add(1);
        reverseList.add(2);
        reverseList.add(3);
        reverseList.add(4);
        reverseList.add(5);

        List<Integer> reversed = new ArrayList<>();

        for (int i = reverseList.size() - 1; i >= 0; i--) {
            reversed.add(reverseList.get(i));
        }

        System.out.println("\nOriginal list: " + reverseList);
        System.out.println("Reversed list: " + reversed);


        String sentence = "java is easy and java is powerful";

        String[] words = sentence.split(" ");

        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            uniqueWords.add(word);
        }

        System.out.println("\nUnique words:");
        System.out.println(uniqueWords);

        System.out.println("Count: " + uniqueWords.size());


        String text = "apple banana apple orange banana apple";

        String[] textWords = text.split(" ");

        Map<String, Integer> frequency = new HashMap<>();

        for (String word : textWords) {

            if (frequency.containsKey(word)) {
                frequency.put(word, frequency.get(word) + 1);
            } else {
                frequency.put(word, 1);
            }
        }

        System.out.println("\nWord frequency:");
        System.out.println(frequency);


        Map<String, String> phoneBook = new HashMap<>();

        phoneBook.put("Maria", "0711111111");
        phoneBook.put("Alex", "0722222222");
        phoneBook.put("John", "0733333333");

        System.out.println("\nPhone number for Alex:");
        System.out.println(phoneBook.get("Alex"));

        System.out.println("\nAll contacts:");

        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("Maria", 9));
        studentList.add(new Student("Alex", 7));
        studentList.add(new Student("John", 10));
        studentList.add(new Student("Emma", 8));

        System.out.println("\nStudents:");

        for (Student s : studentList) {
            System.out.println(s);
        }

        Student bestStudent = studentList.get(0);

        for (Student s : studentList) {
            if (s.grade > bestStudent.grade) {
                bestStudent = s;
            }
        }

        System.out.println("\nBest student:");
        System.out.println(bestStudent);


        studentList.sort(Comparator.comparing(s -> s.name));

        System.out.println("\nSorted by name:");

        for (Student s : studentList) {
            System.out.println(s);
        }

        studentList.sort((s1, s2) -> s2.grade - s1.grade);

        System.out.println("\nSorted by grade descending:");

        for (Student s : studentList) {
            System.out.println(s);
        }


        List<Student> duplicates = new ArrayList<>();

        duplicates.add(new Student("Maria", 9));
        duplicates.add(new Student("Alex", 7));
        duplicates.add(new Student("Maria", 9));
        duplicates.add(new Student("John", 10));
        duplicates.add(new Student("Alex", 7));

        Set<Student> uniqueStudents = new HashSet<>(duplicates);

        System.out.println("\nStudents without duplicates:");

        for (Student s : uniqueStudents) {
            System.out.println(s);
        }
    }
}
