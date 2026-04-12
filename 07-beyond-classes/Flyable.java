public interface Flyable {
    
    public default void move () {
        System.out.println("Flying ...");
    }

}
