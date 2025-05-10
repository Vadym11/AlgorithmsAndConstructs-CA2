package CA2.base;

/**
 * Abstract base class representing a generic worker in the company.
 * Encapsulates shared attributes such as name, gender, email, salary,
 * and position type. Subclasses such as Employee or Manager extend this class.
 */
public abstract class Worker {

    // Worker personal and professional details
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final String salary;
    private final String positionType;

    /**
     * Constructs a Worker object with the provided attributes.
     * @param firstName worker's first name
     * @param lastName worker's last name
     * @param gender worker's gender
     * @param email worker's email address
     * @param salary worker's salary as a formatted string
     * @param positionType type of position (e.g., "Manager", "Employee")
     */
    public Worker(String firstName, String lastName, String gender, String email, String salary, String positionType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.positionType = positionType;
    }

    /**
     * Returns the worker's first name.
     * @return first name as a string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the worker's last name.
     * @return last name as a string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the worker's gender.
     * @return gender as a string
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns the worker's email address.
     * @return email as a string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the worker's salary.
     * @return salary as a formatted string
     */
    public String getSalary() {
        return salary;
    }

    /**
     * Returns the position type of the worker.
     * @return position type as a string
     */
    public String getPositionType() {
        return positionType;
    }
}
