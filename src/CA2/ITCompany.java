package CA2;

import CA2.constants.DepartmentNames;

import java.util.ArrayList;
import java.util.List;

public class ITCompany {

    private final String companyName;
    private Manager HRManager;
    private Manager ITManager;
    private DepartmentHR HRDepartment;
    private DepartmentIT ITDepartment;

    public ITCompany(String companyName) {
        this.companyName = companyName;
        HRManager = Utils.generateRandomManagerDepartment(DepartmentNames.HR.getFullName());
        ITManager = Utils.generateRandomManagerDepartment(DepartmentNames.IT.getFullName());
        HRDepartment = new DepartmentHR(HRManager);
        ITDepartment = new DepartmentIT(ITManager);
    }

    public void writeAllEmployeesToFile() {
        Utils.writeToCSV(ITDepartment.getEmployees());
        Utils.writeToCSV(HRDepartment.getEmployees());
        Utils.writeToCSV(List.of(HRManager, ITManager));
    }

    public void searchEmployee(String query, int option) {
        Utils.searchAndPrintEmployees(query, option);
    }

    public void sortAndPrintEmployees() {
        Utils.sortAndPrintEmployeeFile(20, 0);
    }

    public void getDepartmentEmployees(String departmentName) {
        Department department = null;
        if (departmentName.equals(DepartmentNames.HR.getFullName())) {
            department = HRDepartment;
        } else if (departmentName.equals(DepartmentNames.IT.getFullName())) {
            department = ITDepartment;
        }

        List<String> employeeNames = new ArrayList<>();
        for (Employee employee: department.getEmployees()) {
            employeeNames.add(employee.getInfo());
        }
        Utils.printCSVAsTable(employeeNames);
    }

    public String getCompanyName() {
        return companyName;
    }

    public Manager getHRManager() {
        return HRManager;
    }

    public Manager getITManager() {
        return ITManager;
    }

    public DepartmentHR getHRDepartment() {
        return HRDepartment;
    }

    public DepartmentIT getITDepartment() {
        return ITDepartment;
    }
}
