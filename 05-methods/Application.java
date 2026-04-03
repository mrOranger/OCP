public class Application {

    public static void main (String[] args) {
        Application.variableArguments(new int[] {1, 2, 3, 4});
        Application.instanceCallingStaticMethods();

        int aParameter = 1;
        String anotherParameter = "Hello World!";
        System.out.println(">>> aParameter = " + aParameter + ", anotherParameter = " + anotherParameter);
        Application.parameters(aParameter, anotherParameter);
        System.out.println(">>> aParameter = " + aParameter + ", anotherParameter = " + anotherParameter);

        Application.overloading(1);
        Application.overloading("Hello World!");
        Application.overloading(1L);

        Application.unboxingAndAutoboxing();
        Application.unboxingWithNull();
    }

    public static void variableArguments (int... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(" -> " + numbers[i]);
        }
    }

    public static void instanceCallingStaticMethods () {
        final Application anInstance = new Application();
        final Application anotherInstance = null;
        
        anInstance.printHelloWorld();
        anotherInstance.printHelloWorld();
    }

    public static void printHelloWorld () {
        System.out.println("Hello World from Java 21!");
    }

    public static void parameters (int aParameter, String anotherParameter) {
        
        System.out.println("\t>>> aParameter = " + aParameter + ", anotherParameter = " + anotherParameter);

        aParameter = aParameter + 1;
        anotherParameter = anotherParameter + "!!!";

        System.out.println("\t>>> aParameter = " + aParameter + ", anotherParameter = " + anotherParameter);

    }

    public static void unboxingAndAutoboxing () {

        Integer wrappedInteger = 1;
        int primitiveInteger = wrappedInteger; 

        System.out.printf("-> wrappedInteger = %d, primitiveInteger = %d\n", wrappedInteger, primitiveInteger);

    }

    public static void unboxingWithNull () {
        Integer randomInteger = null;
        int anotherRandomInteger = randomInteger;

        System.out.printf("-> anotherRandomInteger = %d\n", anotherRandomInteger);
    }

    public static void overloading(int aParameter) {
        System.out.printf(">>> (int) aParameter = %d\n", aParameter);
    }

    public static void overloading(CharSequence aParameter) {
        System.out.printf(">>> (CharSequence) aParameter = %s\n", aParameter);
    }

    public static void overloading(Object aParameter) {
        System.out.printf(">>> (Object) aParameter => %d\n", aParameter);
    }

}
