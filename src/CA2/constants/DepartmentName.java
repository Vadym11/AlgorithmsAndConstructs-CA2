package CA2.constants;

public enum DepartmentName {

    IT("IT Department"),
    HR("Human Resources Department"),
    PD("Product Department");

    private final String departmentName;

    DepartmentName(String department) {
        this.departmentName = department;
    }

    public String getFullName() {
        return departmentName;
    }
}
