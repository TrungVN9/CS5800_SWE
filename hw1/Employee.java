package hw1;

// Abstract Employee class
public abstract class Employee {
    // Set immutable final attributes first name, last name and social security number 
    private final String firstName;
    private final String lastName;
    private final String ssn;

    // Constructor
    public Employee(String firstName, String lastName, String ssn) {
        if (firstName == null || lastName == null || ssn == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Method to set and get social security number
    public String getSsn() {
        return ssn;
    }
}
