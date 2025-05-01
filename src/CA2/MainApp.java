package CA2;

import CA2.constants.DepartmentNames;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        Manager HRManager = Utils.generateRandomManager(DepartmentNames.HR.getFulltName());;
        DepartmentHR HRDepartment = new DepartmentHR(HRManager);

        Manager ITManager = Utils.generateRandomManager(DepartmentNames.IT.getFulltName());
        DepartmentIT ITDepartment = new DepartmentIT(ITManager);

//        for (Employee employee : HRDepartment.getEmployees()) {
//            System.out.println(employee.getFirstName());
//            System.out.println(employee.getEmail());
//            System.out.println(employee.getDivisions());
//            System.out.println(employee.getSalary());
//        }
//
//        for (Division division : HRDepartment.getDivisions()) {
//            System.out.println(division.getName());
//        }
//
//        System.out.println(HRDepartment.getManager().getEmail());

        System.out.println("IT Department ============================");

        for (Employee employee : HRDepartment.getEmployees()) {
            System.out.println(employee.getFirstName());
            System.out.println(employee.getEmail());
            System.out.println(employee.getDivisionsAsString());
            System.out.println(employee.getSalary());
            System.out.println(employee.getJobTitle());
            System.out.println(employee.getInfo());
            System.out.println("-----------------------------------");
        }


        for (Division division : HRDepartment.getDivisions()) {
            System.out.println(division.getName());
        }

        System.out.println(HRDepartment.getManager().getEmail());
        System.out.println(HRDepartment.getManager().getDivisionsAsString());
        System.out.println(HRManager.getDivisionsAsString());
        System.out.println(HRDepartment.getDivisionsAsString());

        System.out.println(ITManager.getInfo());

//        Utils.writeToCSV(HRDepartment.getEmployees(), "employees.csv");
//        Utils.writeToCSV(List.of(HRManager, HRManager), "employees.csv");
//
//        Utils.writeToCSV(ITDepartment.getEmployees(), "employees.csv");
//        Utils.writeToCSV(List.of(ITManager, HRManager), "employees.csv");
        Employee employee = Utils.generateRandomEmployeeCustomName("Ken", "Healey", HRDepartment, 3333.33);
        Utils.writeToCSV(List.of(employee), "employees.csv");
    }
}
