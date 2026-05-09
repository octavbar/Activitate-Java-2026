import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<Student> clazz = Student.class;

        System.out.println("1. Class information");
        printClassInfo(clazz);

        System.out.println("\n2. Fields");
        printFields(clazz);

        System.out.println("\n3. Methods");
        printMethods(clazz);

        System.out.println("\n4. Create object dynamically");
        Student s1 = clazz.getDeclaredConstructor().newInstance();
        s1.sayHello();

        System.out.println("\n5. Call public method");
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(s1);

        System.out.println("\n6. Access private field");
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(s1, "Ana");
        System.out.println("Private name value: " + nameField.get(s1));

        System.out.println("\n7. Invoke private method");
        Method secret = clazz.getDeclaredMethod("secretMethod");
        secret.setAccessible(true);
        secret.invoke(s1);

        System.out.println("\n8. Constructor selection");
        Student a = clazz.getDeclaredConstructor().newInstance();
        Student b = clazz.getDeclaredConstructor(String.class).newInstance("Maria");
        Student c = clazz.getDeclaredConstructor(String.class, int.class).newInstance("Ion", 22);

        inspect(a);
        inspect(b);
        inspect(c);

        System.out.println("\n9. Object inspector");
        inspect(c);

        System.out.println("\n10. JSON serializer");
        System.out.println(toJson(c));

        System.out.println("\n11. CSV mapper");
        String csvHeader = "name,age,grade";
        String csvRow = "Elena,21,9.5";

        Student csvStudent = fromCsv(csvHeader, csvRow, Student.class);
        inspect(csvStudent);
    }

    public static void printClassInfo(Class<?> clazz) {
        System.out.println("Class name: " + clazz.getName());

        Package pkg = clazz.getPackage();
        System.out.println("Package: " + (pkg != null ? pkg.getName() : "No package"));

        Class<?> superclass = clazz.getSuperclass();
        System.out.println("Superclass: " + superclass.getName());

        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("Interfaces:");
        for (Class<?> i : interfaces) {
            System.out.println("- " + i.getName());
        }
    }

    public static void printFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(
                    Modifier.toString(field.getModifiers()) + " " +
                            field.getType().getSimpleName() + " " +
                            field.getName()
            );
        }
    }

    public static void printMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            System.out.print(
                    Modifier.toString(method.getModifiers()) + " " +
                            method.getReturnType().getSimpleName() + " " +
                            method.getName() + "("
            );

            Class<?>[] parameters = method.getParameterTypes();

            for (int i = 0; i < parameters.length; i++) {
                System.out.print(parameters[i].getSimpleName());

                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(")");
        }
    }

    public static void inspect(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();

        System.out.println("Object of class: " + clazz.getSimpleName());

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            System.out.println(
                    field.getName() + " = " + field.get(obj)
            );
        }
    }

    public static String toJson(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder json = new StringBuilder();
        json.append("{");

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            json.append("\"")
                    .append(fields[i].getName())
                    .append("\":");

            Object value = fields[i].get(obj);

            if (value instanceof String) {
                json.append("\"").append(value).append("\"");
            } else {
                json.append(value);
            }

            if (i < fields.length - 1) {
                json.append(",");
            }
        }

        json.append("}");

        return json.toString();
    }

    public static <T> T fromCsv(String header, String row, Class<T> clazz) throws Exception {
        String[] columns = header.split(",");
        String[] values = row.split(",");

        T obj = clazz.getDeclaredConstructor().newInstance();

        for (int i = 0; i < columns.length; i++) {
            String fieldName = columns[i].trim();
            String fieldValue = values[i].trim();

            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            if (field.getType() == String.class) {
                field.set(obj, fieldValue);
            } else if (field.getType() == int.class) {
                field.set(obj, Integer.parseInt(fieldValue));
            } else if (field.getType() == double.class) {
                field.set(obj, Double.parseDouble(fieldValue));
            }
        }

        return obj;
    }
}
