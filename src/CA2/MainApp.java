package CA2;

import CA2.constants.DepartmentNames;
import jdk.jshell.execution.Util;

public class MainApp {

    public static void main(String[] args) {

        Manager HRManager = Utils.generateRandomManagerDepartment(DepartmentNames.HR.getFullName());
        DepartmentHR HRDepartment = new DepartmentHR(HRManager);

        Manager ITManager = Utils.generateRandomManagerDepartment(DepartmentNames.IT.getFullName());
        DepartmentIT ITDepartment = new DepartmentIT(ITManager);

//        Employee employee = Utils.generateRandomEmployee("Aaron", "Man", HRDepartment);
        Manager manager = Utils.generateRandomManagerDepartment(DepartmentNames.HR.getFullName());
//        Utils.writeToCSV(HRDepartment.generateEmployees());

//        List<String> found = Utils.searchRecords("Albertin", 0);

//        Utils.printCSVAsTable(Utils.searchRecords("People Operations", 7));

//        Utils.sortAndPrint(10, 0);
//        Utils.searchAndPrint("Derek", 0);
        ITCompany company = new ITCompany("BestCompanyEver");
//        company.seaxrchEmployee("Derek", 0);
//        company.getDepartmentEmployees(DepartmentNames.IT.getFullName());
        try {
            Utils.sortAndPrintApplicantsFile(20, 0);
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Check stack Trace below to troubleshoot!");
        }
    }
}
