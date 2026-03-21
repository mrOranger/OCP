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

## Variables <a id="variables"></a>

## Garbage Collector <a id="garbage-collector"></a>
