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

    public Cat () {
        super(4);
        System.out.println("I'm inside the constructor of Cat class!");
    }

}
