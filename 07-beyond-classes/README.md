- [Interfaces](#interfaces)

# Interfaces <a id="interfaces"></a>

An __interface__ is a data-type containing only abstract methods, that any class implementing them must provide.
Interfaces can contain only constant values, in other words, fields declared as `public static final`. An important
difference between interfaces an abstract classes is that methods in interfaces have an implicit modifier that is
`public abstract`.

It is not necessary that an interface must declare at least a method. Interfaces with zero methods are allowed.

Interfaces cannot be marked with `final` modifier. That is quite obvious, since an interface marked with `final` cannot
be implemented by any class, making the interface useless. 

Respect to abstract classes, `@Override` it is optional to indicate that the method is an overridden version of those
inherited by an interface.

An interface can extend one or more interfaces. In this case, multiple inheritance is permitted since interfaces do not
contain constructors and are not part of the instance initialization.

Java support inheriting two abstract methods having a compatible method declaration. Compatible means that the
implemented method must have the same signature of the two inherited methods, and a covariant return type. 

```java
public interface Swimmable {
    public abstract void move ();
}

public interface Flyable {
    public astract void move ();
}

public class Character implents Swimmable, Flyable {
    public void move () {
        System.out.println("I'm moving ...");
    }
}
```

Latest versions of Java allow the definition of __default methods__. A default method is a method declared inside an
interface, with the `default` keyword. There are some rules that must be followed for default methods:

- A default method can be defined only inside an interface.
- A default method must be marked with the `default` keyword.
- A default method is implicitly public.
- A default method cannot be marked `abstract`, `public` or `static`.
- A default method may be or not implemented by a class that implements the interface.
- If a class inherits two default methods with the same signature, then the class must override the default method.

```java
public interface Swimmable {
    public default void move () {
        System.out.println("Swimming ...");
    }
}

public interface Flyable {
    public default void move () {
        System.out.println("Flying ...");
    }
}

public class Character implements Swimmable, Flyable {
    public void move () {
        System.out.println("Moving ...");
    }
}
```

In the previous example, the class `Character` must override necessarily the method `move`, since Java cannot determine
which version of `move` must be executed. However, it is still possible to access a specific version of `move`, using
the following syntax:

```java
public class Character implements Swimmable, Flyable {
    public void move () {
        Swimmable.super.move();
    }
}
```

Interfaces can declare static methods:

- A static method must be marked with the `static` keyword, and must have a body.
- A static method must be implicitly `public`.
- A static method cannot be marked with `abstract` or `final`.
- A static method is not inherited by a class implementing the interface. Cannot be used by the class implementing the
  interface, without having a reference to the interface declaring the method.

With the introduction of default method, we can introduction duplicate code too. How can we avoid code duplication,
defining a method that is not inherited by a class? Java allows the definition of `private` methods, as long as a
private method contains a body, and is used at lest by one of the default methods declared inside the interface.
