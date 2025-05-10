package CA2.base;

import CA2.Employee;
import CA2.Manager;

import java.util.List;

/**
 * Abstract base class representing a company department.
 * Holds department manager, list of employees, and list of divisions.
 * All departments inherit from this class and implement employee/division generation logic.
 */
public abstract class Department implements GetDivisions {

    // department manager
    private Manager manager;
    // department name (e.g., "IT", "HR", "PD")
    private final String departmentName;
    // list of employees in the department
    private final List<Employee> employees;
    // list of divisions within the department
    private final List<Division> divisions;

    /**
     * Constructor that initializes department name from manager's department field,
     * generates employees and divisions during instantiation.
     * @param manager department manager
     */
    public Department(Manager manager) {
        this.departmentName = manager.getDepartment();
        this.manager = manager;
        this.employees = generateEmployees();
        this.divisions = generateDivisions();
    }

    /**
     * Abstract method to be implemented by subclasses to generate employees for the department.
     * @return list of department employees as Employee objects
     */
    protected abstract List<Employee> generateEmployees();

    /**
     * Abstract method to be implemented by subclasses to generate divisions within the department.
     * @return list of department divisions as Division objects
     */
    public abstract List<Division> generateDivisions();

    /**
     * Returns the department name.
     * @return department name as a string
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Returns the department manager.
     * @return manager object
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Returns the list of employees in the department.
     * @return list of Employee objects
     */
    public List<Employee> getEmployees() {
        return this.employees;
    }

    /**
     * Returns the list of divisions in the department.
     * @return list of Division objects
     */
    public List<Division> getDivisions() {
        return this.divisions;
    }

    /**
     * Replaces the current department manager with a new one.
     * @param manager new manager object
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Returns the department's division names as a single string, separated by " | ".
     * @return string of division names
     */
    public String getDivisionsAsString() {
        StringBuilder divisionString = new StringBuilder();

        for (Division division : this.divisions) {
            divisionString.append(division.getName()).append(" | ");
        }

        // Remove the trailing separator
        return divisionString.substring(0, divisionString.length() - 2);
    }
}
