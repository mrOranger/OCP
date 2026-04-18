public final class PrinterName implements Printer {
    public void print(Person person) {
        System.out.printf("%s %s %s\n", person.firstName(), person.lastName(), person.birthDate());
    }
}
