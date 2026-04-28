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

    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();

        stringBuilder.append("Tax Code: ").append(this.taxCode).append('\n')
            .append("First Name: ").append(this.firstName).append('\n')
            .append("Last Name: ").append(this.lastName);

        return stringBuilder.toString();
    }
    
}
