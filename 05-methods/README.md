- [Designing a Method](#designing-a-method)
    - [Variables Inside a Method](#variables-inside-a-method)
- [Variable Number of Arguments](#variable-number-of-arguments)
- [`static` Methods](#static-methods)
- [Method Arguments](#method-arguments)
- [Overloading](#overloading)

# Designing a Method <a id="designing-a-method"></a>

A __method__ is a function defined inside a class. Java is a pure object-oriented programming language, unlike C or C++,
it is not possible to define a function outside the context of a class or an interface.

```java
public static void main (String[] args) throws Exception {

}
```

Let's consider the following method declaration, it is composed by the following parts:

- `public` is the __access modifier__. Allowed access modifiers are: `public`, `private` or `protected`. Omitting the access
  modifier, in Java, means associate the `package` modifier.

- `static` is the __optional specifier__. Optional specifiers add information to the context of the method. Available
  modifiers are: `static`, `abstract`, `final`, `default` and `synchronized`. You can specify none or any optional
  specifier, however, some combinations are not allowed. Optional specifier must appear before the return type, it is
  possible to specify one of these just before the access modifier.

- `void` is the __return type__. A return type must be specified just before the name of the method. It is mandatory to
  indicate the return type of a method.

- `main` is the __name__ of the method. Any name can contain letters, numbers or currency symbols, also `_` is admitted. It
  is not allowed to start a method name with a reserved word, or a number. Names with just an `_` symbol are not
  admitted.

- `String[] args` are the __method parameters__.

- `throws Exception` is the __exception list__.

The whole string `main (String[] args)` is the __method signature__. Java uses these information to get the right
version of the function to call. After recognized the target method, Java checks if the call is allowed or not. These
declarations are override of the same method.

```java
public static void main (String[] commandLineArgs);
public static void main (double randomParameter, String[] commandLineArgs);
public static void main (char anotherRandomParameter, double randomParameter, String[] commandLineArgs);
```

## Variables Inside a Method <a id="variables-inside-a-method"></a>

Declaring a variable inside a method can be done assigning a specifier to them. There are optional specifiers that can be
assigned to a variable: `final`, `volatile` and `transient`. `final` variables are read-only variables, moreover,
variables can be also __effectively final__. An effectively final variable is a variable not declared ad `final`, however,
its value is not updated in the program. 

# Variable Number of Arguments <a id="variable-number-of-arguments"></a>

A method can have a variable number of arguments, also known as __varargs__. Variable number of arguments are defined in
the following way:

```java
public static void main (String... args) {

}
```

There are some rules to take into account in defining varargs:

- A method can have at most one variable arguments;
- If a method contains a variable number of arguments, they must be specified as the last parameter.

It is possible to pass the variable list of parameters, as an effective list of parameters, like the following example:

```java
public class Application {
    
    public static void main (String[] args) {
        Application.variableArguments(new int[] {1, 2, 3, 4});
    }

    public static void variableArguments (int... numbers) {
        for (int i = 0; i < numbers.length(); i++) {
            System.out.println(numbers[i] + " - ")
        }
    }

}
```

In other words, variable parameters are treated ad regular arrays. However, attention must be payed working with arrays.
In fact, `null` is a valid parameter that can be passed to the function, just like the following way:

```java
public class Application {
    
    public static void main (String[] args) {
        Application.variableArguments(new int[] {1, 2, 3, 4});
        Application.variableArguments(null);
    }

    public static void variableArguments (int... numbers) {
        for (int i = 0; i < numbers.length(); i++) {
            System.out.println(numbers[i] + " - ")
        }
    }

}
```

This code will compile successfully, however a `NullPointerException` will be raised, since `numbers.lenght()` will be
invoked to `null`.

# `static` Methods <a id="static-methods"></a>

`static` is an optional specifier that binds a method to a class rather than an instance. Let's consider the `main`
method that we saw in every example:

```java
public class Application {
    
    public static void main (String[] args) {
        System.out.println("Hello World from Java 21!");
    }

}
```

The only way to execute the method `main` is through `Application.main()` instruction. Making a method static is useful
for the following purposes:

- For utility or helper methods that does not require any object state.
- For managing state that is shared among all instances of a class.

Static methods can be called by any instance of the same class. Java does not care about the value of the instance, it
is also possibile to call a static method from an instance variable having `null` as value:

```java
public class Application {
   
   public static void main (String[] args) {
        Application.instanceVariableUsingClassMethod();
   }

   public static void instanceVariableUsingClassMethod () {
       final Application anInstance = new Application();
       final Application anotherInstane = null;

       anInstance.printHelloWorld();
       anotherInstane.printHelloWorld();
   }

   public static void printHelloWorld () {
        System.out.println("Hello World from Java 21!");
   }

}
```

A static method cannot call an instance member without a reference to an instance of the class itself. This is quite
obvious, since the life-cycle of a static method is detached by the life-cycle of an instance of the same class. 

Static methods can be imported with a different syntax, respect to the classic `import` statement, that is `static
import`. The order of the keywords is important. Moreover, just like regular import, it is not possibile di import two
static methods with the same name!

# Methods Arguments <a id="method-arguments"></a>

In Java, parameters are handled with __passing by value__: that is, a copy of the parameter is made by Java, after
calling a method. 

Working with primitive values and their wrapped counterpart generates interesting behaviours. Java automatically applies
two mechanism known as __unboxing__ and __autoboxing__. Autoboxing is the process of convert a primitive value in a
wrapped class. While unboxing is the reverse process of convert a wrapped class in a primitive value:

```java
public class Application {

    public static void main (String[] args) {
        Application.unboxingAndAutoboxing(); 
    }

    public static void unboxingAndAutoboxing () {
        
        Integer wrappedInteger = 1;
        int primitiveInteger = wrappedInteger;

        System.out.println("-> wrappedInteger = %d, primitiveInteger = %d", wrappedInteger, primitiveInteger);

    }
}
```

However what happens if we apply the unbox using `null`? `NullPointerException` will be raised! The reason is behind the
mechanism used by java to convert primitive into wrapped and viceversa. In fact, unboxing and autoboxing are a syntetixc
way to write the following instructions:

```java
public class Application {

    public static void main (String[] args) {
        Application.unboxingAndAutoboxing(); 
    }

    public static void unboxingAndAutoboxing () {
        
        Integer wrappedInteger = Integer.valueOf(1);
        int primitiveInteger = wrappedInteger.intValue();

        System.out.println("-> wrappedInteger = %d, primitiveInteger = %d", wrappedInteger, primitiveInteger);

    }
}
```

If `wrappedInteger` is `null`, `wrappedInteger.intValue()` will raise `NullPointerException` as obvious!

Let's consider the following example:

```java
public class Application {

    public static void main (String[] args) {
        final Integer aRandomVariable = 1;
        final float anotherRandomVariable = 1; 
        final Float yetAnotherVariable = 1; // Compile error!
    }

}
```

Autoboxing and unboxing apply to single variables also. However, a variable cannot be promoted and autoboxed in the same
instruction.

# Overloading <a id="overloading"></a>

As we mentioned before, Java allows overloading of a method, based on its signature. The signature of the method is
represented by the name of the method itself, plus the list of the parameters. However, considering different
overloading of the same method, how Java resolves the chain of methods call? The answer is: Java picks always the most
specific version of a method that it can:

```java
public class Application {

    public static void main (String[] args) {
        Application.overloading(1);
        Application.overloading("Hello World!");
        Application.overloading(1L);
    }
    
    public static void overloading (int aParameter) {
        System.out.printf(">>> (int) aParameter = %d\n", aParameter);
    }

    public static void overloading(CharSequence aParameter) {
        System.out.println(">>> (CharSequence) aParameter = %s\n", aParameter);
    }

    public static void overloading (Object aParameter) {
        System.out.printf(">>> (Object) aParameter = %d\n", aParameter);
    }

}
```

Automatically, Java can apply autoboxing or unboxing of a parameter based on its needs. I.e., `1L` is automatically
converted in `Object` applying autoboxing of the primitive `long` value in the `Object` reference type.

Pay attention working with varargs and arrays. Internally, Java treats varargs just like arrays. Therefore, we cannot
overload a method declared with varargs, in a counter-part using array!
