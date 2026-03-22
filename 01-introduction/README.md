- [Defining a Class](#defining-a-class)
- [Understand Package Declaration](#understand-package-declaration)
- [Data Types](#data-types)
- [Variables](#variables)
- [Garbage Collector](#garbage-collector)

# Introduction and Basis Concepts

Java is an object-oriented programming language. Differently from C and C++ programming languages, a Java program is
run inside the Java Virtual Machine (JVM), making it a semi-compiled programming language. Source code defined inside
the `.java` files, is compiled first in an intermediate programming language defined in `.class` files, that is the
__bytecode__.

Working with Java, you probably notice many acronyms such as: JVM, JRE or JDK. The JVM is the Java Virtual Machine, that
is an abstract virtual machine, where bytecode is executed independently by the actual machine where is running. The JDK
is the Java Development Kit, that is a set of tools including the JVM, thanks to we can compile and run Java code.
Moreover, the JRE is the Java Runtime Environment, that is a subset of the JDK used only for running Java programs.

## Defining a Class <a id="defining-a-class"></a>

A __class__ is the basis building block of a Java program. Actually, a class is a data structure containing variables
(named __fields__) and functions (named __methods__). If we would like to create a Java program to print the message
"Hello World from Java!", we have to define a class in the following way:

```java
package com.github.mrOranger;

public class Application {
    
    public static void main (String[] args) {
        System.out.println("Hello World from Java!");
    }

}
```

The syntax `public class Application` defines a class named Application. For the moment, we have to ignore the keywords
`public static void`, however, it is enough to know that we defined a method named `main`, and `System.out.println` is
used to print the message "Hello World from Java!".

The function `main` declares a set of parameters under the name of `args`. These parameters are passed to the
application, directly from the terminal, using the command to execute a Java program. Let's suppose that we defined this
class inside a file known as `Application.java`, to compile this file in a bytecode, we have to use the command:

```sh
javac Application.java
```

The output of this command is a `.class` file, containing the bytecode of the program. Finally, we can execute the
program and passing a set of parameters to it, using the following command:

```sh
java Application <parameter> ...
```

The `java` command, we will looking for a `.class` file named `Application`, will load this program inside the JVM and
will execute it.

## Understand Package Declaration <a id="understand-package-declaration"></a>

The first statement of the Java program is `package com.github.mrOranger`, this statement defines the __package__ of the
class. Java organizes classes using packages, that are folders and subfolders of the filesystem. In other words, Java
expects that the class is defined inside a file named `Application.java`, inside the folders `com/github/mrOranger`.

Let's suppose that we are going to compile our application, outside the sub-folders `com/github/mrOranger`, and we would
like to place the `.class` files inside another folder, such as `classes`. We can achieve this using the following
command:

```sh
javac -d classes com/github/mrOranger/Application.java
```

The output of this command will be the file `Application.class` inside the `classes` folder. Now, if we would like to
execute the `classes/Application.class` file, we have to specify the __classpath__ to the `java` command. The classpath
is the path of the folder where `.class` files can be found by the compiler. 

```sh
java --classpath classes Application.class
```

Using the `--classpath classes` argument, we are indicating that the file `Application.class` can be found in `classes`
directory. 

## Data Types <a id="data-types"></a>

Data types in Java are classified in: __primitive__ or __reference__ types. Java includes eight primitive data types:

* `boolean` values.
* `byte` values are 8-bit integer numbers.
* `short` values are 16-bit integer numbers.
* `int` values are 32-bit integer numbers.
* `long` values are 64-bit integer values.
* `float` values are single precision, 32-bit floating point numbers.
* `double` values are double precision, 64-bit floating point numbers.
* `char` are 16-bit Unicode characters.

Differently from primitive data types, reference data types are any other values referencing an object. Objects are
stored in the heap memory space, and referenced using a pointer. Variables representing objects, are nothing more than a
pointer to a space in memory. Allocating an object is done using the `new` keyword, excepts for String data type, that
can be created also without explicitly using `new`.

Latest version of Java, includes the definition of __text blocks__, using the """ characters, like in the following way:

```java
final String blockString = """
    This is a block string.
    Block string can be defined in multiple rows.
"""
```

## Variables <a id="variables"></a>

A __variable__ is the name of a piece of memory. Any string can be used as an identifier of a variable. There are four
rules to follow for assigning a name to a variable:

* Identifier must begin with a letter, a currency symbol or an _.
* Identifier can include numbers, but cannot start with a number.
* A single underscore is not a valid identifier.
* You cannot use a reserved word as an identifier.

We can create multiple identifier using the same type, like the following example:

```java
int aVariable, anotherVariable;
```

However, we cannot assign multiple identifier to different types. The previous example cannot be executed, if one of the
two variables has a different type. 

Nowadays, it is possible to define a variable using the `var` keyword, making inference about its type. However,
declaring a variable using `var`, requires to assign immediately a value, as soon as the variable is declared. `null`
cannot be assigned to a variable declared as `var`, however, we can assign `null` only after the variable has been
initialized. 

## Garbage Collector <a id="garbage-collector"></a>

Java includes a __Garbage Collector__ that frees automatically spaces that are no longer needed. We do not have control
over the garbage collector, `System.gc` suggests to clean the memory, however, the method is not guaranteed to do
anything.
