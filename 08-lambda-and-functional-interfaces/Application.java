import java.util.List;
import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

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

        final Supplier<Integer> supplier = () -> Integer.valueOf(2);
        final Consumer<Integer> consumer = (x) -> System.out.println("Consumer");
        final BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.println("BiConsumer");
        final Predicate<Integer> predicate = (x) -> true;
        final BiPredicate<Integer, Integer> biPredicate = (x, y) -> true;
        final Function<Integer, Double> function = (x) -> Double.valueOf(x);
        final BiFunction<Integer, Integer, Double> biFunction = (x, y) -> Double.valueOf(x + y);
        final UnaryOperator<Integer> unaryOperator = (x) -> x;
        final BinaryOperator<Integer> binaryOperator = (x, y) -> Integer.valueOf(x + y);

        supplier.get();
        biConsumer.accept(1, 2);
        predicate.test(1);
        biPredicate.test(1, 2);
        biFunction.apply(1, 2);
        unaryOperator.apply(1);
        binaryOperator.apply(2, 2);

        final Predicate<Integer> truePredicate = (x) -> true;
        final Predicate<Integer> falsePredicate = (x) -> false;
        final Consumer<Integer> anotherConsumer = (y) -> System.out.printf("%d\n", y);
        final BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
        final Function<Integer, Integer> sum = (x) -> x + 2;

        consumer
            .andThen(anotherConsumer)
            .accept(1);

        final var result = multiply
            .andThen(sum)
            .apply(1, 2);

        System.out.printf("Result: %d\n", result);

        final var statement = truePredicate
            .and(falsePredicate)
            .or(truePredicate)
            .test(1);

        System.out.printf("Result: %b\n", statement);

        final var randomParameter = 1;
        final Consumer<Integer> randomConsumer = (randomParameter) -> System.out.printf("randomConsumer = %d\n", randomConsumer);
    }

    public static void print (List<Person> people, Printer printer) {
        for (final Person currentPerson : people) {
            printer.print(currentPerson);
        }
    }

}
