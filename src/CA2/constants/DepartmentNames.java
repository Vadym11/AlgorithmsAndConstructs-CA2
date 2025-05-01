package CA2.constants;

public enum DepartmentNames {

    IT("IT Department"),
    HR("Human Resources Department"),
    PD("Product Department");

    private final String departmentName;

    DepartmentNames(String department) {
        this.departmentName = department;
    }

    public String getFulltName() {
        return departmentName;
    }
}
