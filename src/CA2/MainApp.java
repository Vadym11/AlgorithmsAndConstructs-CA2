package CA2;

import CA2.constants.DepartmentNames;

import java.util.Scanner;

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
        ITCompany company = new ITCompany("BestCompanyEver", "Vadym Tymeichuk");
//        company.writeAllEmployeesToFile();
//        company.searchEmployee("ChisoraD", 0);
        System.out.println(company.getITDepartment().getManager().getInfo());
        Employee employee = Utils.generateRandomManager("Addis", "Abeba", company.getITDepartment().getDepartmentName());
        company.getITDepartment().setManager((Manager) employee);
        System.out.println(company.getITDepartment().getManager().getInfo());

        for (Employee e : company.getCompanyEmployees()) {
            System.out.println(e.getInfo());
        }

        System.out.println("_______________________");

        company.addEmployeeToDepartment(new Scanner(System.in));

        for (Employee e : company.getCompanyEmployees()) {
            System.out.println(e.getInfo());
        }
//        company.searchEmployee("Addis", 0);
//        company.getDepartmentEmployees(DepartmentNames.IT.getFullName());
//        try {
//            Utils.sortAndPrintApplicantsFile(20, 0);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            System.out.println("Check stack Trace below to troubleshoot!");
//        }
    }
}
