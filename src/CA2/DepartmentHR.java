package CA2;

import CA2.base.Department;
import CA2.base.Division;
import CA2.constants.HRDivisionName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Human Resources (HR) department in the company.
 * Extends the abstract Department class and provides specific logic for
 * generating HR employees and HR-specific divisions.
 */
public class DepartmentHR extends Department {

    /**
     * Constructs an HR department with the specified manager.
     * @param manager HR department manager
     */
    public DepartmentHR(Manager manager) {
        super(manager);
    }

    /**
     * Generates a list of employees specific to the HR department.
     * The number of employees is randomly chosen between 20 and 29.
     * Salaries are adjusted based on department size using a bonus coefficient.
     * @return list of generated HR employees
     */
    @Override
    protected List<Employee> generateEmployees() {
        Random random = new Random();
        List<Employee> listOfEmployees = new ArrayList<>();
        int numOfEmployees = random.nextInt(20, 30); // number of HR employees

        for (int i = 0; i < numOfEmployees; i++) {
            // Apply a bonus coefficient depending on team size
            double bonusCoefficient = numOfEmployees > 25 ? 1.3 : 1.2;
            double salary = random.nextDouble(1000, 1111.00) * bonusCoefficient;

            // Generate employee with department-specific initialization
            listOfEmployees.add(Utils.generateRandomEmployeeForDepartmentInit(this, salary));
        }

        return listOfEmployees;
    }

    /**
     * Generates the list of HR divisions using predefined HR division names.
     * @return list of HR-specific divisions
     */
    @Override
    public List<Division> generateDivisions() {
        List<Division> listOfDivisions = new ArrayList<>();

        // Create Division objects from HRDivisionName enum values
        for (HRDivisionName c : HRDivisionName.values()) {
            listOfDivisions.add(new Division(c.getDivisionName()));
        }

        return listOfDivisions;
    }
}
