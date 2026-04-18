import java.util.List;
import java.time.LocalDate;

public class Application {

    public static void main (String[] args) {
        final List<Person> people = List.of(
           new Person("Mario", "Rossi", LocalDate.of(1990, 1, 1))
        );

        final Printer printer = new PrinterName();

        Application.print(people, printer);

        Car car;
        Motorcycle motorcycle;
        Phone phone;
        
        Integer randomNumber = 1;
        ComparableNumber methodReference = randomNumber::compareTo;
        ComparableNumber lambdaFunction = (Integer aNumber) -> randomNumber.compareTo(aNumber);
        System.out.printf("methodReference: %d\n", methodReference.compare(1));
        System.out.printf("lambdaFunction: %d\n", lambdaFunction.compare(1));


        String aString = "Hello World from Java!";

        AnInterface anInterface = String::isEmpty;
        AnInterface anotherInterface = (String randomString) -> randomString.isEmpty();

        CreationInterface createMethodReference = String::new;
        CreationInterface lambdaCreateMethod = () -> new String();

        System.out.println(createMethodReference.create());
        System.out.println(lambdaCreateMethod.create());
    }

    public static void print (List<Person> people, Printer printer) {
        for (final Person currentPerson : people) {
            printer.print(currentPerson);
        }
    }

}
