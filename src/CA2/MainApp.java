package CA2;

import CA2.constants.DepartmentNames;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        Manager HRManager = Utils.generateRandomManager(DepartmentNames.HR.getFullName());;
        DepartmentHR HRDepartment = new DepartmentHR(HRManager);

        Manager ITManager = Utils.generateRandomManager(DepartmentNames.IT.getFullName());
        DepartmentIT ITDepartment = new DepartmentIT(ITManager);

//        Employee employee = Utils.generateRandomEmployeeCustomName("Aaron", "Man", HRDepartment, 3133.33);
        Manager manager = Utils.generateRandomManager(DepartmentNames.HR.getFullName());
        Utils.writeToCSV(List.of(manager));

//        List<String> found = Utils.searchRecords("Albertin", 0);

//        Utils.printCSVAsTable(Utils.searchRecords("People Operations", 7));

        Utils.sortAndPrint(10, 0);
    }
}
