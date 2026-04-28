# Collections and Generics

- [Working with `Collection` Interface](#working-with-collection-interface)
- [Working with `List` Interface](#working-with-list-interface)
- [Working with `Set` Interface](#working-with-set-interface)
- [Working with `Queue` and `Deque` Interfaces](#working-with-queue-and-deque-interfaces)
- [Working with `Map` Interface](#working-with-map-interface)
- [Comparing Objects](#comparing-objects)
- [Introducing the Sequences Collections](#introducting-the-sequenced-collections)
- [Working with Generics](#working-with-generics)
    - [Bounding Generics](#bouding-generics)

## Working with `Collection` Interface

The `Collection` interface is the main interface, from which most of the concrete data structures are created. A
collection is represented as a set of elements, having an order or not. All the data structures that we will analyse,
are defined inside the `java.util` package. The main methods used in this collection are:

- `public boolean add(E element)` to add a new element into the collection. Returns `true` if the new element has been
  added, `false` otherwise.
- `public boolean remove(E element)` to remove an element from the collection. Returns `true` if the element has been
  removed, `false` otherwise.
- `public boolean isEmpty()` checks if the collection is empty or not.
- `public int size()` returns the size of the collection.
- `public void clear()` removes all the elements from the collection.
- `public boolean contains(Object element)` checks if the collection contains an element or not.
- `public boolean removeIf(Predicate <? super E> filter)` removes all the elements in the collection, matching the
  predicate `filter`.
- `public void forEach(Consumer<? super E> action)` applies the `Consumer` for each element of the collection.
- `public boolean equals(Object object)` checks if the collection is equal to another collection.

## Working with `List` Interface

The `List` interface is another interface based on `Collection` used for represent linear collection of elements. This
interface is used for two more concrete data structures:

- `ArrayList` uses array to store elements. Its advantages is the reading operations, requiring costant time. On the
  other hand, operations requiring update the collection, are more expensive than its counter-part `LinkedList`.
- `LinkedList` is the counter-part of `ArrayList`. It stores elements using a set of connected nodes. Reading operations
  are more expensive rather than `ArrayList`. On the other hand, updating the collection is less expensive respect to
  `ArrayList`.

Creating a list can be done in two ways:

- Using the `of` method defined in the `List` interface.
- Passing a collection or an array of elements into the constructor of `ArrayList` or `LinkedList`.

On the other hand, we can convert a list into an array, using the method `toArray()`. There are two version of this
method. The first one converts the list in an array of `Object`. While the latter `toArray(E[] elems)` is used to get an
array of specific type of elements.

```java
final var elements = List.of("A", "B");
String[] convertedArray = elements.toArray(new String[0]);
```

In the following code `new String[0]` is used only to infer the right type to return. Nothing more is done with this
parameter.

## Working with `Set` Interface <a id="working-with-set-interface"></a>

`Set` interface defines data structures that do not admit duplicates. There are three concrete classes, made by
implementing the `Set` interface:

- `HashSet` this data structure uses an hash map to guarantee uniqueness between elements. The key of an entry is
  calculated using the `hashCode` method.
- `LinkedHashSet`, elements of this data structure are implemented using a `LinkedList`.
- `TreeSet`, finally, uses a tree to store all the elements. Moreover, using a tree, the set of all the elements is
  already sorted.

## Working with `Queue` and `Deque` Interfaces <a id="working-with-queue-and-deque-interfaces"></a>

`Queue` and `Deque` are interfaces used to implement `ArrayDeque` and `LinkedList`. `Deque` adds some extra methods to
`Queue` to add/remove elements both from the top and the bottom. Main methods implemented in `Queue` are:

- `boolean add(E element)`, to add an element on the top of the queue.
- `E peek()`, to read the element on the top.
- `E pool()`, to remove an element from the bottom of the list.

## Working with `Map` Interface <a id="working-with-map-interface"></a>

A `Map` is a set of key-values. There are three implementation of this interface:

- `HashMap`, uses the `hashCode()` method of the class, to store values inside an hash map.
- `LinkedHashMap` stores elements linearly inside a `LinkedList`.
- `TreeMap` uses a tree to store elements with a sorting order.

Main methods to known with these classes are:

- `V get(Object key)` reads the element store below the input key. If no element is found, an exception in thrown.
- `V put(K key, V value)` stores the `value` using the input `key`. If a conflict stands, an exception is thrown.
- `V getOrDefault(Object key, V defaultValue)` reads the element identified by the `key`, or returns a `defaultValue`
  if no value is found.
- `V putIfAbsent(K key, V value)` stores the `value` using the input `key`, if and only if the `key` is not already
  used.
- `V merge(K key, V value, BiFunction <V, V, V> function)`, sets the value if the key is not set. Otherwise, runs the
  function to check which value to consider.

## Comparing Objects <a id="comparing-objects"></a>

There are two interfaces that can be used to compare objects, `java.lang.Comparable` and `java.util.Comparator`.

`Comparable` is an interface defining the method `compareTo`. Implemented by a class, defines the comparison strategy
between objects of a specific class, with another object of the same class:

```java
public class Person implements Comparable<Person> {
    
    private String taxCode;
    private String firstName;
    private String lastName;

    public Person (String taxCode, String firstName, String lastName) {
        this.taxCode = taxCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getTaxCode () {
        return this.taxCode;
    }

    public String getFirstName () {
        return this.firstName;
    }

    public String getLastName () {
        return this.lastName;
    }
    
    @Override
    public int compareTo (Person aPerson) {
        if (aPerson == null) {
            return 1;
        }

        return aPerson.getTaxCode().compareTo(this.getTaxCode());
    }

}
```

`java.lang.Comparable` is a functional interface useful for implementing comparison login inside the class itself.
`java.util.Comparator` is another functional interface for defining comparison between two classes as parameter, using
the `compare` method. A common usage of `java.util.Comparator` is passing it as a lambda to sorting functions.

Some data structures like `TreeSet` requires that the input classes must implement the `Comparable` interface, in order
to identify the sorting order.

## Introducing the Sequenced Collections <a id="introducting-the-sequenced-collections"></a>

Before Java 21, there was not a standard way to access first and last element of a collection. `ArrayList`, `LinkedList`
and other collections had their own specific method.

Java 21 introduced the sequenced collections. A sequenced collection is an interface including a well-defined order of
reading and writing elements.

Sequenced collections introduced are:

- `SequencedCollection`.
- `SequencedSet`.
- `SequencedMap`.

## Working with Generics <a id="working-with-generics"></a>

Before generics, designing a method or a class accepting a variable type of element, requires to use `Object` and
casting the element in its specific type.

Rather than other programming language, Java substitute the references of the generic type, with `Object`. This
procedure is known as _type erasure_ and it has been implemented to make newer version of Java compatible with older
ones. The cast to a specific type is made automatically by the compiler.

Considering the following example:

```java
public class Cage<T> {
    public T content;
}
```

Java will automatically convert this class into:

```java
public class Cage {
    public Object content;
}
```

This mechanism has implications in the overloading of the methods. For instance, since Java converts `List<Person>` into
`List`, methods having same signature but different generic type parameters, are considered duplications:

```java
public void open (Cage<Lion> cage); // -> public void open (Cage cage);
public void open (Cage<Tiger> cage); // -> public void (Cage cage);
```

When the compiler will transform the generic parameters, duplication will be created. Therefore, these overloaded
version of the code, cannot be used inside a class.

Working with generic, the return type of a method must be covariant, respect to the type of the method overridden. For
instance, if a method returns `ArrayList` and overrides another method returning `List`, the overridden form is valid.
However, the generic type must exactly match with the generic type of the overridden method.

### Bounding Generics <a id="bounding-generics"></a>

Bounds can be applied to generic parameters. If we would like to permit any generic type to be applied to a generic
parameter, we can use the `?` wildcard 

An upper-bound can be achieved using the 'extends' keyword:

```java
public void print (List<? extends Number> numbersToPrint) {
    for (final var currentNumber : numbersToPrint) {
        System.out.println(currentNumber);
    }
}
```

In the previous example, the function accepts a list of whatever object that extends the `Number` class, or the class
`Number` itself.

On the other hand, a lower-bound can be achieved using the `super` keyword:

```java
public void print (List<? super Number> numbersToPrint) {
    for (final var currentNumber : numbersToPrint) {
        System.out.println(currentNumber);
    }
}
```

The same example, with this syntax, indicate that a list of whatever object super-class of `Number` can be passed as
parameter to the method.
