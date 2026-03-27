- [Numeric Promotion](#numeric-promotion)
- [Casting](#casting)

# Numeric Promotion <a id="numeric-promotion"></a>

Working with numeric data types, we have to understand the _numeric promotion_ concept. Java applies some rules
working with numeric data types:

1. If two values have different data types, Java automatically converts the smaller data type in the larger one.
2. If an operator involves an integer and a floating-point number, the integer value is converted in floating-point.
3. Any time a "small" data type like byte, short, char is involved in a binary operator, it is automatically
   converted in an integer.
4. Only after all the promotions has been applied, the evaluation of the expression is made.

Let's consider the following example:

java
byte x = 1;
float y = 2;
double z = 3;

var k = x * y / z;

Evaluating this expression consists in the following steps:

1. x will be converted in an int value, since it is involved in a binary expression.
2. x will promote once again x to float.
3. Finally, x * y will be converted in a double to be divided by z.

# Casting <a id="casting"></a>

_Casting_ is an unary operator used to convert a data type in another. Converting a smaller data type in a larger one,
does not require an explicit casting. On the other hand, converting a larger data type in a smaller type requires an
explicit casting. The following example shows how to use a casting:

java
long a = 1; // 1 is automatically casted in a long;
int b = (int)1f; // requires to be casted explicitly

## instanceof Operator

instanceof is a binary operator used to check if a variable is an instance of a specific class. Using instanceof
requires to be careful about the following scenario:

1. To check if a variable is an instance of a specific class, requires that the variable type and the class are at least
   compatible.
2. Using instanceof on null always returns false.

java
final String aString = null;

if (aString instanceof String) {
    System.out.println("aString instanceof String");
} else {
    System.out.println("!(aString instanceof String)");
}
