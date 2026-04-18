import java.util.List;
import java.time.LocalDate;

public class Application {

    public static void main (String[] args) {
        final List<Person> people = List.of(
           new Person("Mario", "Rossi", LocalDate.of(1990, 1, 1))
        );

        final Printer printer = new PrinterName();

        Application.print(people, printer);
    }

    public static void print (List<Person> people, Printer printer) {
        for (final Person currentPerson : people) {
            printer.print(currentPerson);
        }
    }

}
