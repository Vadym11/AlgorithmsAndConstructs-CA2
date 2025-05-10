package CA_2;

import java.util.List;

/**
 * Represents a manager in the company.
 * Inherits all fields and methods from the Employee class,
 * but is distinguished by the "Manager" position type and divisions representation
 * when generating Manager information.
 */
public class Manager extends Employee {

    /**
     * Constructs a Manager object with the specified attributes.
     * Inherits full employee structure and passes arguments to Employee constructor.
     * @param firstName manager's first name
     * @param lastName manager's last name
     * @param gender manager's gender
     * @param email manager's email address
     * @param salary manager's salary (as formatted string)
     * @param divisions list of divisions the manager oversees
     * @param positionType should be "Manager"
     * @param department department the manager is assigned to
     * @param jobTitle manager's job title (e.g., "Team Lead", "Senior Manager")
     */
    public Manager(String firstName, String lastName, String gender, String email, String salary,
                   List<String> divisions, String positionType, String department, String jobTitle) {
        super(firstName, lastName, gender, email, salary, positionType, divisions, department, jobTitle);
    }
}
