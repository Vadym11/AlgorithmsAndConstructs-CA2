package CA_2;

import java.util.List;

/**
 * Represents a regular employee in the company.
 * Inherits basic personal and position-related fields from the Worker class
 * and adds department-specific information including divisions and job title.
 */
public class Employee extends Worker implements GetDivisions {

    // List of division names the employee belongs to
    private final List<String> divisions;
    // Name of the department the employee works in
    private final String department;
    // Employee's job title within the department
    private final String jobTitle;

    /**
     * Constructs an Employee object with all required attributes.
     * @param firstName employee's first name
     * @param lastName employee's last name
     * @param gender employee's gender
     * @param email employee's email address
     * @param salary employee's salary (as formatted string)
     * @param positionType "Employee" (for regular employees)
     * @param divisions list of divisions assigned to this employee
     * @param department department name (e.g., "IT", "HR")
     * @param jobTitle job title of the employee
     */
    public Employee(String firstName, String lastName, String gender, String email,
                    String salary, String positionType, List<String> divisions,
                    String department, String jobTitle) {
        super(firstName, lastName, gender, email, salary, positionType);
        this.divisions = divisions;
        this.department = department;
        this.jobTitle = jobTitle;
    }

    /**
     * Returns the name of the department the employee works in.
     * @return department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the employee's job title.
     * @return job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Returns a list of division names the employee belongs to.
     * @return list of divisions
     */
    public List<String> getDivisions() {
        return divisions;
    }

    /**
     * Returns a formatted string of division names separated by " | ".
     * Implements the method from the GetDivisions interface.
     * @return formatted division string
     */
    @Override
    public String getDivisionsAsString() {
        StringBuilder divisionString = new StringBuilder();

        for (String division : getDivisions()) {
            divisionString.append(division).append(" | ");
        }

        // Remove trailing delimiter
        return divisionString.substring(0, divisionString.length() - 3);
    }

    /**
     * Returns a CSV-formatted string representing employee's full information.
     * Used for display and file export.
     * @return string containing employee details
     */
    protected String getInfo() {
        return getFirstName() + "," +
                getLastName() + "," +
                getGender() + "," +
                getEmail() + "," +
                getSalary() + "," +
                getDepartment() + "," +
                getPositionType() + "," +
                getJobTitle() + "," +
                getDivisionsAsString();
    }
}
