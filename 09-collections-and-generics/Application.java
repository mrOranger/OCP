import java.util.List;

public class Application {

    public static void main (String[] args) {
        final var person = new Person();
        final var employee = new Employee();

        person.print("Hello World!");
        employee.print("Hello World!");
    }
}

class Person {
    public Object print (Object charactersToPrint) {
        System.out.printf("Person.print\n");

        return "Person.print";
    }
}

class Employee extends Person {

    @Override
    public String print (Object charactersToPrint) {
        System.out.printf("Employee.print\n");

        return "Employee.print";
    }

}
