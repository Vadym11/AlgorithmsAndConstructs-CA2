package CA2;

public abstract class Worker {

    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final String salary;
    private final String positionType;

    public Worker(String firstName, String lastName, String gender, String email, String salary, String positionType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.positionType = positionType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getSalary() {
        return salary;
    }

    public String getPositionType() {
        return positionType;
    }
}
