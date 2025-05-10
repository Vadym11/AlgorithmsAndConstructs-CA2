package CA_2;

public enum DepartmentName {

    // Represents the IT Department
    IT("IT Department"),
    // Represents the Human Resources Department
    HR("Human Resources Department"),
    // Represents the Product Development Department
    PD("Product Department");

    // Full name of the department
    private final String departmentName;

    // Constructor to assign full department name
    DepartmentName(String department) {
        this.departmentName = department;
    }

    /**
     * Returns the full name of the department.
     * @return department full name as a string
     */
    public String getFullName() {
        return departmentName;
    }
}
