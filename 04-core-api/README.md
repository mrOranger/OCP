- [Understanding String](#understanding-string)
    - [String Pool](#string-pool)
    - [String Equality](#string-equality)
- [Understanding `StringBuilder` Class](#understanding-string-builder-class)
- [Understanding Array](#understanding-array)
    - [Comparing Arrays](#comparing-arrays)
- [Understanding Dates and Times](#understanding-dates-and-times)
    - [Working with `Period` and `Duration`](#working-with-perion-and-duration)

In this section, we will understand the classes and interfaces defining the "Core API of Java". With _Core API_ we are
referring to a set of classes and interfacing, thanks to we can define a minimal working program. We will focus on
`String` class, how this class it is implemented and managed by Java, since String has an interesting lifecycle in a
Java application.

# Understanding String <a id="understanding-string"></a>

Strings are everywhere in a Java program. It is fundamental to understand how strings are managed internally, and how
can we optimize their usage in our application. Be careful in understand concepts like string pool and `StringBuilder`
class.

## String Pool <a id="string-pool"></a>

The String class is an __immutable__ class. An immutable class is a call that cannot change its internal representation.
Everytime we use the String methods, a new String is created and returned.

This interesting behaviour, requires a lot of memory, therefore how can Java handle in an efficient way strings?
Internally, the JVM uses a memory known as __string pool__. The string pool is a location of the JVM where strings are
handled in an efficient way. Let's consider the following example:

```java
public class Application {
   
    public static void main (String[] args) {

        Application.stringPoolEquality();

    }

    public static void stringPoolEquality () {

        final var aString = "Edoardo Oranger";
        final var anotherString = "Edoardo Oranger";

        System.out.println(aString == anotherString); // true

    }

}
```

Surprisingly, the program will print `true`. Java will create a single object inside the string pool, then two different
referencing variables will be pointed to the same block of memory, inside the string pool. However, there is another
way, forcing Java to not use the string pool, that is creating a string using the `new` operator:


```java
public class Application {

     public static void main (String[] args) {

        Application.normalStringEquality();

    }

    public static void normalStringEquality () {

        final var aString = "Edoardo Oranger";
        final var anotherString = new String("Edoardo Oranger");

        System.out.println(aString == anotherString); // false

    }
   
}
```

While the former string is memorized inside the string pool, the latter is memorized in the regular memory allocated by
the operating system to the java program. We can also force Java to use the string pool, although the object has been
created outside the string pool, using the `intern` method:

```java
public class Application {

     public static void main (String[] args) {

        Application.forceUseStringPool();

    }

    public static void forceUseStringPool () {

        final var aString = "Edoardo Oranger";
        final var anotherString = new String("Edoardo Oranger").intern();

        System.out.println(aString == anotherString); // true

    }
   
}
```

## String Equality <a id="string-equality"></a>

The previous example shows how to compare strings using the `==` operator. Equality expressed using this operators,
checks only if two reference variables points to the same object. While `==` is an useful operator to understand if we
are referencing to the same object, it is completely useless comparing the content of two objects.

Luckily, Java defines the method `equals` inside the `Object` class. Since `String` is a class, it inherits
automatically all the methods defines inside the `Object` class, including the `equals` method. We conclude this
section, showing how to use properly the `equals` method:

```java
public class Application {

     public static void main (String[] args) {

        Application.stringComparison();

    }

    public static void stringComparison() {

        final var aString = "Edoardo Oranger";
        final var anotherString = new String("Edoardo Oranger");

        System.out.println(aString == anotherString); // false 
        System.out.println(aString.equals(anotherString)); // true

    }
   
}
```

# Understanding `StringBuilder` Class <a id="understanding-string-builder-class"></a>

Since String is an immutable class, how can we avoid creating multiple objects? Java introduced the `StringBuilder`
class, to handle strings in a more efficient way. Differently from the `String` class, `StringBuilder` methods does not
return a new instance each time a method is invoked.

```java
public class Application {
    
    public static void main (String[] args) {
        Application.handleStringBuilder();
    }

    public static void handleStringBuilder () {
       
       final var aString = new StringBuilder();

       aString.append("Hello World!").append('\n')
            .append("from 'append' method of 'StringBuilder' class").append('\n');

        System.out.println(aString);

    }

}
```

Using the `append` method, the internal representation of the same object `aString` is updated. Therefore, the same
instance of `StringBuilder` is returned at the end of the concatenation of `append`.


# Understanding Array <a id="understanding-array"></a>

An array is represented as a sequence of memory blocks in the heap. Java allows to create an array in two different
ways:

- Declaring an array explicitly specifying the size of the array.
- Declaring the array implicitly, by initialization of its content.

Array can be declared as set of primitive or references. Let's consider the following example:

```java
public class Application {
    
    public static void main (String[] args) {
        Application.arrayOfReferences();
    }

    public static void arrayOfReferences () {
        String[] arrayOfStrings = { "Hello World!" };
        Object[] arrayOfObjects = arrayOfStrings;
        arrayOfStrings[0] = new StringBuilder(); 
        arrayOfObjects[0] = new StringBuilder();
    }

}
```

The code won't compile. However, while `arrayOfStrings[0] = new StringBuilder()` will generate a compile-time exception,
`arrayOfObjects[0] = new StringBuilder` will generate an exception at runtime, since it is not allowed to store a
different object inside an array declared as a set of incompatible types.

Working with array of array, also known as matrices, it is allowed to specify only the former dimensione, while the
latter can be omitted:

```java
public class Application {
    
    public static void main (String[] args) {
        final Integer[][] randomArray = new Integer[4][];
    }

}
```

This is a completely valid syntax, considering that arrays are referencing variables, having a `null` value as
initialization. In the former example, we are defining a set of 4 referencing variables, each one representing a pointer
to an array initialized as `null`.

## Comparing Arrays <a id="comparing-arrays"></a>

Unlike `String` class, there are two methods to compare arrays in Java.

- Using the ad-hoc `equals` inherited from `Object` class, that performs reference equality.
- Using the `compare` method of the `java.utils.Arrays` class.

Comparison using the `compare` method, checks if one array is smaller, bigger or equals compared to another. The
behaviour of the `compare` method, can be describe in the following way:

- If both arrays contains the same elements in the same order, 0 is returned.
- If the second array contains more element than the former, -1 is returned.
- If the former array contains more elements rather then the latter, 1 is returned.
- If the first element that differs is smaller, -1 is returned.
- If the first element that differs is greater, 1 is returned.


# Understanding Dates and Times <a id="understanding-dates-and-times"></a>

Java exposes a set of immutable classes to work with time and days:

- `LocalDate` contains just dates, no time or time zone.
- `LocalTime` contains just time, no date or time zone.
- `LocaleDateTime` contains both time and date, without time zone indications.
- `ZonedDateTime` contains information about time and date, with time zone indications.

All of these classes contains a factory method `of`, used to create and return a new instance of the relative class.
Moreover, there are different overrides of this function, used to specify different combination of data to be used.

```java
import java.time.Month;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Application {
    
    public static void main (String[] args) {
        Application.localDate();
        Application.localTime();
        Application.localDateTime();
        Application.zonedDateTime();
    }

    public static void localDate () {
        final var aLocalDate = LocalDate.of(2000, Month.JANUARY, 1);

        System.out.println(aLocalDate.toString());
    }

    public static void localTime () {
        final var aLocalTime = LocalTime.of(0, 0, 0);

        System.out.println(aLocalTime);
    }

    public static void localDateTime () {
        final var aLocalDateTime = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0);

        System.out.println(aLocalDateTime.toString());
    }

    public static void zonedDateTime () {
        final var aLocalDate = LocalDate.of(2000, Month.JANUARY, 1);
        final var aLocalTime = LocalTime.of(0, 0, 0)
        final var aLocalDateTime = LocalDateTime.of(aLocalDate, aLocalTime, ZoneId.of("Europe/Rome"));

        System.out.println(aLocalDateTime.toString());
    }

}
```

Interestingly, we can create a `LocalDateTime` from a `LocalDate` with a `LocalTime`, using the `asTime` operator.
`asTime` requires to be used on a `LocalDate`, passing a `LocalTime` as the input time to "concatenate" to the input
`LocalDate`.

Working with dates and time, be careful in remember daylight saving time. According to EU directives, the March
changeover takes places the 2-nd of March at 2:00 AM. While the November changeover takes place the last Sunday of
October at 3:00 AM.

## Working with `Period` and `Duration` <a id="working-with-perion-and-duration"></a>

`Period` and `Duration` classes have been develop to define time and date interval. While `Period` defines
macro-intervals between dates, `Duration` defines time interval with nanoseconds precision. 

```java
import java.time.Period;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Application {
    
    public static void main (String[] args) {
        Application.period();
        Application.duration();
    }

    public static void period () {
        final var aPeriod = Period.of(1, 0, 0);

        System.out.println(aPeriod);
    }

    public static void duration () {
        final var aDuration = Duration.of(1, ChronoUnit.DAYS);

        System.out.println(aDuration);
    }
    
}
```

Respect to `Period`, `Duration` has a factory method, including an utility class `ChronoUnit`, implementing the
`TemporalUnit` interface. In its implementation, `ChronoUnit` defines all the available time units, that can be used to
define a `Duration` instance.
