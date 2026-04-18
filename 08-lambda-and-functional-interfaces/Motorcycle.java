@FunctionalInterface
public interface Motorcycle extends Vehicle {

    default public void move () {
        System.out.println("I'm moving ...");
    }

}
