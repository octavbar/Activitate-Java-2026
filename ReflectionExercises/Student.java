public class Student implements Comparable<Student> {
    private String name;
    private int age;
    public double grade;

    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.grade = 0;
    }

    public Student(String name) {
        this.name = name;
        this.age = 0;
        this.grade = 0;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.grade = 0;
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }

    private void secretMethod() {
        System.out.println("This is a private method.");
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}
