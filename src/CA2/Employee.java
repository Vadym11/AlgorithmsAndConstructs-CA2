package CA2;

import java.util.List;

public class Employee extends Worker implements GetDivisions{

    private final List<String> divisions;
    private final String department;
    private final String jobTitle;

    public Employee(String firstName, String lastName, String gender, String email, String salary, String positionType, List<String> divisions, String department, String jobTitle) {
        super(firstName, lastName, gender, email, salary, positionType);
        this.divisions = divisions;
        this.department = department;
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public List<String> getDivisions() {
        return divisions;
    }

    @Override
    public String getDivisionsAsString() {
        StringBuilder divisionString = new StringBuilder();

        for (String division : getDivisions()) {
            divisionString.append(division).append(" | ");
        }

        return divisionString.substring(0, divisionString.length() - 3);
    }

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
