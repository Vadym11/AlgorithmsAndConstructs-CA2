package CA2;

import CA2.base.Department;
import CA2.constants.DepartmentName;

import java.util.*;

public final class ITCompany {

    private final String companyName;
    private final String CEOName;
    private final Manager HRManager;
    private final Manager ITManager;
    private final Manager PDManager;
    private final DepartmentHR HRDepartment;
    private final DepartmentIT ITDepartment;
    private final DepartmentPD PDDepartment;
    private final List<Employee> companyEmployees;
    private List<String> employeesInfoUnsorted;
    private List<String> companyEmployeesInfoSorted;

    public ITCompany(String companyName, String ceoName) {
        this.companyName = companyName;
        this.CEOName = ceoName;
        HRManager = Utils.generateRandomManagerDepartment(DepartmentName.HR.getFullName());
        ITManager = Utils.generateRandomManagerDepartment(DepartmentName.IT.getFullName());
        PDManager = Utils.generateRandomManagerDepartment(DepartmentName.PD.getFullName());
        HRDepartment = new DepartmentHR(HRManager);
        ITDepartment = new DepartmentIT(ITManager);
        PDDepartment = new DepartmentPD(PDManager);
        companyEmployees = getAllEmployees();
        companyEmployeesInfoSorted = getAndSortAllEmployeesInfo();
        Utils.printCSVAsTable(employeesInfoUnsorted);
    }

    private List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        for (Department department : List.of(HRDepartment, ITDepartment, PDDepartment)) {
            employeeList.addAll(department.getEmployees());
        }

        employeeList.addAll(List.of(HRManager, ITManager, PDManager));

        return employeeList;
    }

    public  List<String> getAndSortAllEmployeesInfo() {
        List<String> employeesInfo = new ArrayList<>();
        for (Employee e : companyEmployees) {
            employeesInfo.add(e.getInfo());
        }

        employeesInfoUnsorted = employeesInfo;

        Utils.mergeSortCustom(employeesInfo, 0);

        return employeesInfo;
    }

    public List<String> getCompanyEmployeesInfoSorted() {
        if (companyEmployeesInfoSorted.size() != companyEmployees.size()) {
            System.out.println("Employee(s) has/have been added. Resorting...");
            companyEmployeesInfoSorted = getAndSortAllEmployeesInfo();
        }

        return companyEmployeesInfoSorted;
    }

    public void writeAllEmployeesToFile() {
        Utils.writeToCSV(getCompanyEmployees());
    }

    public void searchEmployee(List<String> employees, String query, int option) {
        Utils.searchAndPrintEmployees(employees, query, option);
    }

    public void printSortedEmployeesInfo(List<String> emplInfo) {
        Utils.printCSVAsTable(emplInfo.subList(0, 20));
    }

    public void getDepartmentEmployees(String departmentName) {
        Department department;
        if (departmentName.equals(DepartmentName.HR.getFullName())) {
            department = HRDepartment;
        } else if (departmentName.equals(DepartmentName.IT.getFullName())) {
            department = ITDepartment;
        } else {
            department = PDDepartment;
        }

        List<String> employeeNames = new ArrayList<>();
        for (Employee employee: department.getEmployees()) {
            employeeNames.add(employee.getInfo());
        }
        Utils.printCSVAsTable(employeeNames);
    }

    public void addEmployeeToDepartment(Scanner sc) {
        sc.nextLine(); // clear scanner
        String returnMessage = String.format("You entered 0. Returning to %s company main menu...\n", this.companyName);
        int userChoice;
        Employee employee;

        System.out.println("To create employee manually enter 1, to autogenerate - enter 2: ");
        userChoice = Utils.getUserOption(sc);

        if (userChoice == 0) {
            System.out.println(returnMessage);
        } else if (userChoice == 1) {
            System.out.println("Please enter employee First Name: ");
            String fName = sc.nextLine();

            if (fName.equals("0")) {
                System.out.println(returnMessage);
                return;
            }

            System.out.println("Please enter employee Last Name: ");
            String lName = sc.nextLine();

            if (lName.equals("0")) {
                System.out.println(returnMessage);
                return;
            }

            System.out.println("Please select department:");
            System.out.println("1. IT Department");
            System.out.println("2. HR Department");
            System.out.println("3. PD Department");
            userChoice = Utils.getUserOption(sc);

            if (userChoice == 0) {
                System.out.println(returnMessage);
                return;
            }

            Department department;
            if (userChoice == 1) department = this.getITDepartment();
            else if (userChoice == 2) department = this.getHRDepartment();
            else department = this.getPDDepartment();

            System.out.println("Please select position type:");
            System.out.println("1. Regular Employee");
            System.out.println("2. Manager");
            userChoice = Utils.getUserChoice(sc);

            if (userChoice == 0) {
                System.out.println(returnMessage);
                return;
            } else if (userChoice == 1) {
                System.out.println("Employee " + fName + " " + lName + " has been added to " + department.getDepartmentName() + ":");
                employee = Utils.generateRandomEmployee(fName, lName, department);
                department.getEmployees().add(employee);
            } else {
                System.out.println("Manager " + fName + " " + lName + " has been added to " + department.getDepartmentName() + ":");
                employee = Utils.generateRandomManager(fName, lName, department.getDepartmentName());
                department.setManager((Manager) employee);
            }
            companyEmployees.add(employee);
            Utils.printCSVAsTable(List.of(employee.getInfo()));
            Utils.writeToCSV(List.of(employee));
        } else {
            // autogenerate an employee
            double salary = new Random().nextDouble(1000, 1111.00);
            Department department = this.getDepartments().get(new Random().nextInt(0, 3));
            employee = Utils.generateRandomEmployeeForDepartmentInit(department, salary);
            System.out.println("Employee " + employee.getFirstName() + " " + employee.getLastName() + " has been added to "
                    + department.getDepartmentName() + ":");
            companyEmployees.add(employee);
            Utils.printCSVAsTable(List.of(employee.getInfo()));
            Utils.writeToCSV(List.of(employee));
        }
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

    public Manager getPDManager() {
        return PDManager;
    }

    public DepartmentPD getPDDepartment() {
        return PDDepartment;
    }

    public List<Department> getDepartments() {
        return List.of(ITDepartment, HRDepartment, PDDepartment);
    }

    public String getCEOName() {
        return CEOName;
    }

    public List<Employee> getCompanyEmployees() {
        return companyEmployees;
    }
}
