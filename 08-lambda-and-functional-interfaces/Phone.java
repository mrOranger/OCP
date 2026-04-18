@FunctionalInterface
public interface Phone {
    public abstract String toString ();
    public abstract int hashCode ();
    public abstract boolean equals (Object objectToCompare);
    public abstract void doSomething ();
}
