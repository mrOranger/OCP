- [Understanding Inheritance](#understanding-inheritance)
    - [`this` and `super`](#this-and-super)
- [Constructors](#constructors)
- [Initialization](#initialization)
    - [Initialization of the Class](#initialization-of-the-class)
    - [Initialization of the Read-only Fields](#initialization-of-the-read-only-fields)
    - [Initialization of the Instances](#initialization-of-the-instances)
- [Inheriting Members](#inheriting-members)
- [Abstract Classes](#abstract-classes)
- [Immutable Classes](#immutable-classes)

# Understanding Inheritance <a id="understanding-inheritance"></a>

__Inheritance__ is an object-oriented principle, by which a sub-class automatically includes fields and methods declared
in a super-class or parent-class. Inheritance is implemented thanks to the `extends` keyword, just like the following
way:

```java
public class Cat extends Animal {}
```

Java implements only the single-inheritance, meaning that a class can inherit a behaviour only from a parent-class.

## `this` and `super` <a id="this-and-super"></a>

A child-class inheriting from a parent-class can inherit multiple version of the same field or the same object. How can
a child-class distinguish between a method define inside itself respect to the same method defined inside the
parent-class?

`this` can be used to get a reference of a method or a field defined inside the current class. If no match has been
found, the searched is moved in the parent-class. Oh the other hand, `super` is a keyword used to get a reference of the
field, or the method, defined inside the parent-class. Differently from `this`, `super` makes a jump, moving the
reference directly to the parent class.

# Constructors <a id="constructors"></a>

Using `new` keyword we are able to instantiate a class, calling the constructor of the class itself. The __constructor__
is a special method defined inside a class, where the initialization process takes place.

By default Java create a default constructor if none have been defined. Moreover, the `this` keyword, used as a method,
allows us to call other constructors of class. Just like, `this()`, also `super()` allows to call constructors defined
inside the parent-class. 

Java includes automatically a default constructor, calling the parent-class default constructor. However, what is
happening if a parent-class defines a custom constructor, and the child-class has defined only the default constructor?
Let's consider the following example:

```java
public class Animal {
    protected String specie;
    protected Integer numberOfLegs;

    public Animal(String specie, Integer numberOfLegs) {
        this.specie = specie;
        this.numberOfLegs = numberOfLegs;
    }
}

public class Cat extends Animal {}
```

This code wont' compile, since the default constructor created automatically by Java and attached to the class `Animal`,
does not call the unique constructor defined inside the `Animal` class. In Java, it is mandatory to call the constructor
of the parent-class in order to instantiate a child-class. 

# Initialization <a id="initialization"></a>

The initialization process of a class is quite complex, and involves many elements. In this chapter, we will analyze the
initialization process of the class, step by step.

## Initialization of the Class <a id="initialization-of-the-class"></a>

The basic initialization process stands in the following steps:

1. Initialization of the super-class.
2. Initialization of the static variables declarations in order in which they appear.
3. Initialization of the static initializers in the order in which they appear.

```java
public class Animal {
    static {
        System.out.println("I'm inside the Animal class");
    }
}

public class Cat extends Animal {
    static {
        System.out.println("I'm inside the Cat class");
    }
}

public class Application {
    
    public static void main (String[] args) {
        final var cat = new Cat();
    }

}
```

Following the previous guidelines, the order

## Initialization of the Read-only Fields <a id="initialization-of-the-read-only-fields"></a>

Fields marked with `final` must be initialized in Java. If a read-only field has not been initialized after the
constructor, a compile-error will be thrown.

## Initialization of the Instances <a id="initialization-of-the-instances"></a>

We conclude this section with the most cumbersome part, the initialization process of an instance. From a general point
of view, the initialization process of an instance of class X child of a parent class Y, consists in the following
steps:

1. __Initialization of the parent class__. Both static blocks and fields are initialized in the order they appear.
2. __Initialization of the child class__. Both static blocks and fields are initialized in the order they appear.
3. __Initialization of the instance of the parent class__. Instance blocks and fields are initialized in the order they
   appear. Constructors are the last step in the initialization process.
4. __Initialization of the instance of the child class__. Instance blocks and fields are initialized in the order they
   appear. Constructors are the last step in the initialization process.

```java
public class Application {
    
    public static void main (String[] args) {
        new Cat();
    }

}

class Cat {

    static {
        System.out.printf("I'm inside the static block initializer!\n");
    }

    {
        System.out.printf("I'm inside the instance block initializer!\n");
    }

    private String name = "Nala";
    private static final Integer numberOfLegs = 4;

    public Cat () {
        System.out.println("I'm inside the constructor!");
    }

}
```

Let's analyze step by step how the initialization process works:

1. Initialization of the parent class. No parent class, thus this step is skipped.
2. Initialization of the child class. Static block is processed before the static variable `numberOfLegs`. Thus: `I'm
   inside the static block!` is printed at first, before the assignment `numberOfLegs = 4`.
3. Initialization of the instance of the parent class. No parent class, the step is skipped.
4. Initialization of the instance of the child class. Initialization block is processed before the assignment of the
   variable `name`. `I'm inside the instance block initializer` is printed. Last but not least, `I'm inside the
   constructor` is printed as last message.

Let's see a more complex example, involving inheritance:

```java
public class Application {
    
    public static void main (String[] args) {
        new Cat();
    }

}

class Animal {

    static {
        System.out.printf("I'm inside the static block initializer of Animal class!\n");
    }

    {
        System.out.printf("I'm inside the instance block initializer of Animal class!\n");
    }

    private Integer numberOfLegs;

    public Animal (Integer numberOfLegs) {
        System.out.println("I'm inside the constructor of Animal class!");

        this.numberOfLegs = numberOfLegs;
    }

}

class Cat extends Animal {

    static {
        System.out.printf("I'm inside the static block initializer of Cat class!\n");
    }

    {
        System.out.printf("I'm inside the instance block initializer of Cat class!\n");
    }

    private String name = "Nala";
    private static final Integer numberOfLegs;

    public Cat () {
        super(4);
        System.out.println("I'm inside the constructor of Cat class!");
    }

}
```

1. Initialization of the parent class. `I'm inside the static block initializer of Animal class!` is printed as first message.
2. Initialization of the child class. `I'm inside the static block initializer of Cat class!` is printed.
3. Initialization of the instance of the parent class. `I'm inside the instance block initializer of Animal class!` is
   printed before `I'm inside the constructor of Animal class!`.
4. Initialization of the instance of the child class. Last but not least, `I'm inside the instance block initializer of
   the Cat class!` and `I'm inside the constructor of Cat class!` are the last two messages printed.

# Inheriting Members <a id="inheriting-members"></a>

What happens if a method with the same name is declared both inside the parent and child class? The method defined
inside the child class is the __overridden__ version of the counter part defined inside the parent-class. To override a
method, the compiler performs the following checks:

1. The method defined inside the child-class, must have the same signature of the method defined in the parent-class.
2. The method defined inside the child-class, must be at least accessible as the method in the parent-class.
3. The method defined inside the child-class, must not declare an exception new or broader respect to the method defined
   in the parent-class.
4. If the method defined inside the parent-class returns a value, the value returned by the same method in the
   child-class must be the same or a sub-type of the method defined in the parent-class.
5. If a method defined in the parent-class is marked as `static`, it must be marked as well `static` as in the
   child-class.

Notice that methods marked as `private` cannot be overridden, since `private` methods are not inherited. On the other
hand, making a `private` method `final` also, is redundant but allowed.

Java does not allow variables to be overridden. However, variables can be hidden. An __hidden variable__ is a
re-declaration of a variable defined inside the parent-class, in the child-class. Java treats hidden variables are
distinct variables as well. 

The `@Override` annotation helps the programmer in checks if the overridden version of a method is correct or not. Using
this annotation, Java checks at compile-time, if the overridden version of the method follows or not the previous rule.

Up to this moment, we are talking about methods. What happens with fields shared between instances? Differently to
methods, fields are hidden in different instances of the same class.

# Abstract Classes <a id="abstract-classes"></a>
    
An abstract class is a class marked with the `abstract` modifier, that cannot be instantiated. There are some rules to
follow, working with abstract class:

1. Only instance and methods can be marked with the `abstract` modifier.
2. An abstract class can include zero or more abstract methods.
3. A concrete class implementing an abstract-class, must implement all abstract methods defined in the abstract class.
4. Overriding abstract methods, requires to follow all the previous rules defined for the inherited methods.

`abstract` cannot be used in conjunction with some other modifiers, for example:

- A method declared with `final` keyword, cannot be declared `abstract` too. If we are making a method `abstract` means
  that the method must be implemented by someone, on the other hand, if the method is `final`, we are prohibit the
  method to be implemented.

- Methods marked with `abstract` cannot be marked with `private` too. Since `private` methods are not inherited by
  child-classes, a method marked as `private abstract` cannot be implemented in any way.

- A method marked with `static` is bind statically to a class rather than an instance. `static` methods can be hidden by
  another method defined inside a child-class, but cannot be overridden. Therefore, we cannot declare a method as
  `static abstract` since they cannot be also hidden by another implementation.

# Immutable Classes <a id="immutable-classes"></a>


The __immutable object pattern__ is an object-oriented design pattern, where an object cannot be updated once has been
instantiated. There are various techniques that can be used to mark an object immutable, the most common are:

1. Mark the class as `final` or the constructors `private`.
2. Mark all fields as `private final`.
3. Do not define any setter method.
4. Do not allow any object to be modified.
5. Use a constructor to set all properties of the object.

```java

public final class Person {

    private final String firstName;
    private final String lastName;

    public Person (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFristName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

}
```

The previous is an example of immutable class. We cannot update the fields `firstName` or `lastName` once the object is
created. On the other hand, the only way to update the fields, is to create a new instance, passing different values to
the constructor.

Why an immutable class must be marked with `final`? The answer is: if a class is not marked with `final`, a child-class
can implement methods making the parent-class not immutable.
