# Lambda and Functional Interfaces

- [Basis About Lambdas](#basis-about-lambda)
- [Basis About Functional Interfaces](#basis-about-functional-interfaces)
- [Method References](#method-references)
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

## Method References <a id="method-references"></a>

## Built-In Functional Interfaces <a id="built-in-functional-interfaces"></a>

## Variables and Lambdas <a id="variables-and-lambdas"></a>
