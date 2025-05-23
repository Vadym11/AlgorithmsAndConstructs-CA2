package CA_2;

import java.util.*;

public final class ITCompany {

    // Name of the company
    private final String companyName;
    // Name of the CEO
    private final String CEOName;
    // Manager of the HR department
    private final Manager HRManager;
    // Manager of the IT department
    private final Manager ITManager;
    // Manager of the PD department
    private final Manager PDManager;
    // HR department, initialized with a manager
    private final DepartmentHR HRDepartment;
    // IT department, initialized with a manager
    private final DepartmentIT ITDepartment;
    // PD department, initialized with a manager
    private final DepartmentPD PDDepartment;
    // List of all employees in the company including managers
    private final List<Employee> companyEmployees;
    // Raw, unsorted list of employee info strings
    private List<String> employeesInfoUnsorted;
    // Sorted list of employee info strings
    private List<String> companyEmployeesInfoSorted;

    /**
     * Constructor that initializes the company with a name and CEO.
     * Departments and their managers are generated automatically.
     * Employees are collected and sorted upon instantiation.
     *
     * @param companyName name of the company
     * @param ceoName name of the CEO
     */
    public ITCompany(String companyName, String ceoName) {
        // two arguments are passed during instantiation
        this.companyName = companyName;
        this.CEOName = ceoName;
        // the rest is initialized at runtime
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

    /**
     * Iterates over company departments and adds all employees to the
     * list of company employees
     * @return list of company employees as Employee objects
     */
    private List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        for (Department department : List.of(HRDepartment, ITDepartment, PDDepartment)) {
            employeeList.addAll(department.getEmployees());
        }

        employeeList.addAll(List.of(HRManager, ITManager, PDManager));

        return employeeList;
    }

    /**
     * Iterates over all company employees, gets all employee info,
     * adds it to a list and then sorts it
     * @return company employees info as a List of strings
     */
    public List<String> getAndSortAllEmployeesInfo() {
        List<String> employeesInfo = new ArrayList<>();
        for (Employee e : companyEmployees) {
            employeesInfo.add(e.getInfo());
        }

        employeesInfoUnsorted = employeesInfo;

        Utils.mergeSortCustom(employeesInfo, 0);

        return employeesInfo;
    }

    /**
     * Returns all company employees' sorted info. In case more employees were
     * added, triggers re-sorting before returning.
     * @return list of company employees
     */
    public List<String> getCompanyEmployeesInfoSorted() {
        if (companyEmployeesInfoSorted.size() != companyEmployees.size()) {
            System.out.println("Employee(s) has/have been added. Resorting...");
            companyEmployeesInfoSorted = getAndSortAllEmployeesInfo();
        }

        return companyEmployeesInfoSorted;
    }

    /**
     * Writes all company employees' info into a file
     */
    public void writeAllEmployeesToFile() {
        Utils.writeToCSV(getCompanyEmployees());
    }

    /**
     * Searches company employees by a query and prints results
     * @param employees list of company employees info
     * @param query search query (first or last name)
     * @param option sorting option depending on user choice
     */
    public void searchEmployee(List<String> employees, String query, int option) {
        Utils.searchAndPrintEmployees(employees, query, option);
    }

    /**
     * Prints first 20 entries of sorted employee info
     * @param emplInfo list of employees information
     */
    public void printSortedEmployeesInfo(List<String> emplInfo) {
        Utils.printCSVAsTable(emplInfo.subList(0, 20));
    }

    /**
     * Prints information of all employees in a specified department
     * depending on the department name provided as argument
     * @param departmentName department name (e.g., IT, HR, PD)
     */
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
        for (Employee employee : department.getEmployees()) {
            employeeNames.add(employee.getInfo());
        }
        Utils.printCSVAsTable(employeeNames);
    }

    /**
     * Adds a new employee (manual or autogenerated) to a selected department
     * @param sc Scanner object for reading user input
     */
    public void addEmployeeToDepartment(Scanner sc) {
        sc.nextLine(); // Clear the input buffer

        String returnMessage = String.format("You entered 0. Returning to %s company main menu...\n", this.companyName);
        int userChoice;
        Employee employee;

        // Ask user how they want to add the employee
        System.out.println("To create employee manually enter 1, to autogenerate - enter 2: ");
        userChoice = Utils.getUserOption(sc);

        // return to main menu if 0 entered
        if (userChoice == 0) {
            System.out.println(returnMessage);
        } else if (userChoice == 1) {
            // --- Manual employee creation ---
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

            // Prompt user to select department
            System.out.println("Please select department:");
            System.out.println("1. IT Department");
            System.out.println("2. HR Department");
            System.out.println("3. PD Department");
            userChoice = Utils.getUserOption(sc);

            // return to main menu if 0 entered
            if (userChoice == 0) {
                System.out.println(returnMessage);
                return;
            }

            // Assign the appropriate department based on user input
            Department department;
            if (userChoice == 1) department = this.getITDepartment();
            else if (userChoice == 2) department = this.getHRDepartment();
            else department = this.getPDDepartment();

            // Prompt for employee position type
            System.out.println("Please select position type:");
            System.out.println("1. Regular Employee");
            System.out.println("2. Manager");
            userChoice = Utils.getUserChoice(sc);

            if (userChoice == 0) {
                System.out.println(returnMessage);
                return;
            } else if (userChoice == 1) {
                // Create a regular employee with the given names
                System.out.println("Employee " + fName + " " + lName + " has been added to " + department.getDepartmentName() + ":");
                employee = Utils.generateRandomEmployee(fName, lName, department);
                department.getEmployees().add(employee); // Add to department's list
            } else {
                // Create a manager and assign to the department
                System.out.println("Manager " + fName + " " + lName + " has been added to " + department.getDepartmentName() + ":");
                employee = Utils.generateRandomManager(fName, lName, department.getDepartmentName());
                department.setManager((Manager) employee); // Replace department's manager
            }

            // Add to company-wide list and display/save employee info
            companyEmployees.add(employee);
            Utils.printCSVAsTable(List.of(employee.getInfo()));
            Utils.writeToCSV(List.of(employee));
        } else {
            // --- Autogenerate employee without user input ---
            double salary = new Random().nextDouble(1000, 1111.00); // Random salary
            Department department = this.getDepartments().get(new Random().nextInt(0, 3)); // Random department
            employee = Utils.generateRandomEmployeeForDepartmentInit(department, salary);

            // Add autogenerated employee to lists and output info
            System.out.println("Employee " + employee.getFirstName() + " " + employee.getLastName() + " has been added to "
                    + department.getDepartmentName() + ":");
            companyEmployees.add(employee);
            Utils.printCSVAsTable(List.of(employee.getInfo()));
            Utils.writeToCSV(List.of(employee));
        }
    }


    /**
     * Returns the company name
     * @return company name as a string
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Returns the HR manager
     * @return HR manager as a Manager object
     */
    public Manager getHRManager() {
        return HRManager;
    }

    /**
     * Returns the IT manager
     * @return IT manager as a Manager object
     */
    public Manager getITManager() {
        return ITManager;
    }

    /**
     * Returns the HR department
     * @return HR department object
     */
    public DepartmentHR getHRDepartment() {
        return HRDepartment;
    }

    /**
     * Returns the IT department
     * @return IT department object
     */
    public DepartmentIT getITDepartment() {
        return ITDepartment;
    }

    /**
     * Returns the PD manager
     * @return PD manager as a Manager object
     */
    public Manager getPDManager() {
        return PDManager;
    }

    /**
     * Returns the PD department
     * @return PD department object
     */
    public DepartmentPD getPDDepartment() {
        return PDDepartment;
    }

    /**
     * Returns a list of all departments in the company
     * @return list of departments as Department objects
     */
    public List<Department> getDepartments() {
        return List.of(ITDepartment, HRDepartment, PDDepartment);
    }

    /**
     * Returns the CEO name
     * @return CEO name as a string
     */
    public String getCEOName() {
        return CEOName;
    }

    /**
     * Returns a list of all employees in the company
     * @return list of Employee objects
     */
    public List<Employee> getCompanyEmployees() {
        return companyEmployees;
    }
}
