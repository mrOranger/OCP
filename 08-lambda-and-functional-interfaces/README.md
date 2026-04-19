# Lambda and Functional Interfaces

- [Basis About Lambdas](#basis-about-lambda)
- [Basis About Functional Interfaces](#basis-about-functional-interfaces)
- [Method References](#method-references)
    - [Method Reference and Static Methods](#method-reference-and-static-methods)
    - [Using Method Reference on a Particular Object](#method-reference-on-objects)
    - [How Method Reference Works with Parameters](#method-reference-with-parameters)
    - [Method Reference and Constructors](#method-reference-and-constructors)
- [Built-In Functional Interfaces](#built-in-functional-interfaces)
- [Variables and Lambdas](#variables-and-lambdas)

Since Java 8, _lambda_ were introduced. A lambda function is an anonymous function used to specify code that most of the
time will be run once only. Java is a pure object-oriented programming language, therefore pure lambda functions cannot
be implemented. Another name of lambda is _closure_

On the other hand, Java allows to create lambda as aliases for anonymous instances of _functional interfaces_. A
functional interface is an interface having a single method. Java includes some functional interfaces in its
specifications.

## Basis About Lambdas <a id="basis-about-lambda"></a>

Understand lambda, requires a complex example first:

```java
public record Person (String firstName, String lastName, LocalDate birthDate) {}

public interface Printer {
    public abstract void print (Person person);
}

public final class PrinterName implements Printer {
    public void print (Person person) {
        System.out.printf("%s - %s - %s\n", person.firstName(), person.lastName(), person.birthDate());
    }
}

public class Application {
    
    public static void main (String[] args) {

        final List<Person> people = List.of(
            new Person("Mario", "Rossi", LocalDate.of(1990, 1, 1),
            new Person("Luigi", "Neri", LocalDate.of(1991, 2, 2)),
            new Person("Francesca", "Rossi", LocalDate.of(1992, 3, 3)),
            new Person("Elisa", "Bruni", LocalDate.of(1993, 4, 4))
        );

        final Printer printName = new PrinterName();

        Application.print(people, printName);
    }

    public static void print (List<Person> people, Printer printer) {
        for (final Person currentPerson : people) {
            printer.print(currentPerson);
        }
    }

}
```

Using lambdas, we can reduce the code just to this:

```java
public class Application {
    
    public static void main (String[] args) {

        final List<Person> people = List.of(
            new Person("Mario", "Rossi", LocalDate.of(1990, 1, 1),
            new Person("Luigi", "Neri", LocalDate.of(1991, 2, 2)),
            new Person("Francesca", "Rossi", LocalDate.of(1992, 3, 3)),
            new Person("Elisa", "Bruni", LocalDate.of(1993, 4, 4))
        );

        Application.print(
            people,
            (person) -> System.out.printf("%s %s %s", person.firstName(), person.lastName(), person.birthDate()
        );
    }

    public static void print (List<Person> people, Printer printer) {
        for (final Person currentPerson : people) {
            printer.print(currentPerson);
        }
    }

}
```

An implementation of the class `Printer` is no more needed. We replaced the implementation the functional interface
`Printer` with a lambda.

Notice that the expanded form of the lambda is the following:

```java
(Person person) -> { 
    System.out.printf("%s %s %s\n", person.firstName(), person.lastName(), person.birthDate());
}
```

- Types of the parameters can be omitted, if the lambda can infer them automatically.
- Parenthesis used to separate the parameters can be omitted, if the parameter is only one.
- Body of the lambda function consisting of at least two statement, requires curly braces.
- Body of the lambda function requires `return` statement, if the lambda returns a value different of `void`.
- If the body of the function is composed of a single statement, and the function returns a value different to `void`,
  the `returns` statement can be omitted. On the other hand, the value to return is the evaluation of the single
  expression.

```java
final var expression = (Person person) -> System.out.print(person);
```

The following expression is not valid. Java requires a context to determine the type of `var`. Lambdas does not contain
a context, therefore Java cannot infer the return type of the lambda, it must be specified.

## Basis About Functional Interfaces <a id="basis-about-functional-interfaces"></a>

A functional interface is an interface containing only one abstract method. The annotation `@FunctionalInterface` is an
useful annotation defined in the `java.lang` package, used to indicate the compiler that the interface must satisfy the
basic condition to be a functional interface:

These examples are valid functional interfaces:

```java
public Vehichle {
    public abstract void start ();
}

public Car extends Vehicle {}

public Motocicle extends Vehicle {
    public default move () {
        System.out.println("Moving ...");
    }
}
```

However, any object inherits from `java.lang.Object`, interfaces too. Therefore, some methods like `equals`, `toString`
are already defined inside the definition of a functional interface. How does Java handle interfaces considering these
theoretical concepts? If an interface re-declare a public method with the same signature of a method contained in the
`java.lang.Object` class, that method does not represent a single abstract method valid to declare the interface a functional
interface.

This example shows a valid functional interface re-implementing some methods already defined in `java.lang.Object`
class:

```java
@FunctionalInterface
public interface Phone {
    public abstract String toString ();
    public abstract int hashCode ();
    public abstract boolean equals (Object objectoToCompare);
    public abstract void startup ();
}
```

## Method References <a id="method-references"></a>

Method reference is a shorter syntax to use in declaration of lambdas. Considering the previous example, we can re-write
the same method using this new syntax, in the following way:

```java
public class Application {
    
    public static void main (String[] args) {

        final List<Person> people = List.of(
            new Person("Mario", "Rossi", LocalDate.of(1990, 1, 1),
            new Person("Luigi", "Neri", LocalDate.of(1991, 2, 2)),
            new Person("Francesca", "Rossi", LocalDate.of(1992, 3, 3)),
            new Person("Elisa", "Bruni", LocalDate.of(1993, 4, 4))
        );

        Application.print(
            people,
            System.out::println
        );
    }

    public static void print (List<Person> people, Printer printer) {
        for (final Person currentPerson : people) {
            printer.print(currentPerson);
        }
    }

}
```

The syntax `System.out::println`, is a shorter way to declare `(Person person) -> System.out.println(person)`. Java can
automatically understand that the parameter `person` is used as a single parameter for the function `println`.

Working with method reference requires to understand how does this syntax works in different situations like:

- Static methods.
- Instance methods of a specific object.
- Instance method having a parameter declared at runtime.
- Constructors.

### Method Reference with Static Methods <a id="method-reference-and-static-methods"></a>

Java has no problem in using static methods, referencing them though the `::` syntax. If the `println` method were a
static method, no changes were needed.

### Using Method Reference on a Particular Object <a id="method-reference-on-objects"></a>

Let's consider the following example, showing how we can use both lambda and method reference to implement the same
behaviour:

```java
public interface ComparableNumber {
    public abstract int compare (Integer number);
}

public class Application {
    public static void main (String[] args) {
        Integer randomNumber = 1;
        ComparableNumber methodReference = randomNumber::compareTo;
        ComparableNumber lambdaFunction = (Integer aNumber) -> randomNumber.compareTo(aNumber);

        System.out.printf("Method Reference %d\n", methodReference.compare(1));
        System.out.printf("Lambda Function %d\n", lambdaFunction.compare(2));
    }
}
```

### How Method Reference Works with Parameters <a id="method-reference-with-parameters"></a>

Java can infer automatically the instance on which a method is called. For instance, considering the following example:

```java
public interface AnInterface {
    public abstract void doSomething (String aString);
}

public class Application {
    public static void main (String[] args) {
        String aString = "Hello World from Java!";

        AnInterface anInterface = String::isEmpty;
        AnInterface anotherInterface = (String randomString) -> randomString.isEmpty();
    }
}
```

Differently from the previous example, where we used a variable to indicate the target of the method `isEmpty`, in this
example, Java infers that the unique variable `aString` of the functional interface, will be used to invoke the method
`isEmpty`.

### Method Reference and Constructors <a id="method-reference-and-constructors"></a>

Finally, we are approaching to the last method to use a functional reference. This time, we are using constructors in
conjunction with method reference. In fact, the `new` operator can be used also as the right-hand part of a method
reference, to invoke the constructor of a class:

```java
public inteface CreationInterface {
    public abstract String create ();
}

public Applicaion {
    public static void main (String[] args) {
        CreationInterface methodReference = String::new;
        CreationInterface lambdaFunction = () -> new String ();
    }
}
```

Java is smart enough to pass a parameter to the constructor, using the method reference, if any of them is defined in
the functional interface's abstract method.

## Built-In Functional Interfaces <a id="built-in-functional-interfaces"></a>

Java provides a set of built-in functional interface, based on the most common patterns in working with lambda:

- `Supplier`. Requires no arguments and returns a value. The abstract method contained in this interface is `get`.
- `Consumer` and `BiConsumer`. These functional interfaces are implemented in order to do something with one or two
  parameters and not returning any value. The abstract method contained in these interfaces is `accept`.
- `Predicate` and `BiPredicate`. Are used to predicate something bases on one or two parameters, and returning a boolean
  value. The abstract method contained in these interfaces is `test`.
- `Function` and `BiFunction`. The basis `Function` functional interface, takes a parameter and returns a value. The
  enhanced interface `BiFunction`, takes two parameters and returns a value. The abstract method implemented in these
  interfaces is `apply`.
- Finally, `UnaryOperator` and `BinaryOperator` have been designed as a specific implementation of `Function` and 
  `BiFunction`. `UnaryOperator` requires a parameter and returns a value having the same type of the input parameter. 
  `BinaryOperator` requires two parameters and returns another value, all of them have the same type. The abstract
  method implemented in these interface is `apply`.

```java
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
        consumer.accept(1);
        biConsumer.accept(1, 2);
        predicate.test(1);
        biPredicate.test(1, 2);
        function.apply(1);
        biFunction.apply(1, 2);
        unaryOperator.apply(1);
        binaryOperator.apply(2, 2);
    }

}
```

Functional interfaces must contain at most one abstract method. However, it does not mean that a functional interface
must contain only one method. In fact, there are some utility default methods implemented in those functional interfaces
provided by Java:

- `andThen`, can be applied to a consumer to concatenate the execution with another consumer. On the other hand, it can
  also be applied to `Function`.
- `compose` composes two `Function`, passing the result of the first, as input to the second.
- `and`, `or`, `negate` are function working with `Predicate` and `BiPredicate`.

```java
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BiFunction;

public class Application {

    public static void main (String[] args) {

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

    }

}
```


## Variables and Lambdas <a id="variables-and-lambdas"></a>
