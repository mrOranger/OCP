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

## Variables and Lambdas <a id="variables-and-lambdas"></a>
