import java.util.Objects;

public class Student {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + " - " + grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Student)) return false;

        Student other = (Student) obj;

        return name.equals(other.name) && grade == other.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grade);
    }
}
