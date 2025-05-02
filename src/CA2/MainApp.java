package CA2;

import CA2.constants.DepartmentNames;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        Manager HRManager = Utils.generateRandomManager(DepartmentNames.HR.getFullName());;
        DepartmentHR HRDepartment = new DepartmentHR(HRManager);

        Manager ITManager = Utils.generateRandomManager(DepartmentNames.IT.getFullName());
        DepartmentIT ITDepartment = new DepartmentIT(ITManager);

        Employee employee = Utils.generateRandomEmployeeCustomName("Baboon", "Healey", HRDepartment, 3333.33);
        Utils.writeToCSV(List.of(employee));

        List<String> found = Utils.searchRecords("Baboon", 0);

        Utils.printCSVAsTable(found);
    }
}
