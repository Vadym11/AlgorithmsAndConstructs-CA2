package CA2;

import java.util.List;

public abstract class Department implements GetDivisions{

    private final String departmentName;
    private final Manager manager;
    private final List<Employee> employees;
    private final List<Division> divisions;

    public Department(Manager manager) {
        this.departmentName = manager.getDepartment();
        this.manager = manager;
        this.employees = generateEmployees();
        this.divisions = generateDivisions();
    }

    /**
     * Generate employees belonging to divisions generated in generateDivisions()
     */
    protected abstract List<Employee> generateEmployees();

    public abstract List<Division> generateDivisions();

    public String getDepartmentName() {
        return departmentName;
    }

    public Manager getManager() {
        return manager;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Division> getDivisions() {
        return this.divisions;
    }

    public String getDivisionsAsString() {
        StringBuilder divisionString = new StringBuilder();

        for (Division division : this.divisions) {
            divisionString.append(division.getName()).append(" | ");
        }

        return divisionString.substring(0, divisionString.length() - 2);
    }
}
