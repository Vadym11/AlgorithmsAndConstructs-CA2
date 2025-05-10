package CA2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the IT (Information Technology) department in the company.
 * Extends the abstract Department class and provides logic specific to
 * generating IT employees and IT-related divisions.
 */
public class DepartmentIT extends Department {

    /**
     * Constructs an IT department with the specified manager.
     * @param manager IT department manager
     */
    public DepartmentIT(Manager manager) {
        super(manager);
    }

    /**
     * Generates a list of employees for the IT department.
     * The number of employees is randomly chosen between 25 and 34.
     * Salaries are calculated with a bonus coefficient that increases
     * depending on the department employees number.
     * @return list of generated IT employees
     */
    @Override
    protected List<Employee> generateEmployees() {
        Random random = new Random();
        List<Employee> listOfEmployees = new ArrayList<>();
        int numOfEmployees = random.nextInt(25, 35); // number of IT employees

        for (int i = 0; i < numOfEmployees; i++) {
            // Apply a bonus coefficient based on team size
            double bonusCoefficient = numOfEmployees > 27 ? 1.4 : 1.3;
            double salary = random.nextDouble(1000, 3111.00) * bonusCoefficient;

            // Generate and add employee to the department
            listOfEmployees.add(Utils.generateRandomEmployeeForDepartmentInit(this, salary));
        }

        return listOfEmployees;
    }

    /**
     * Generates a list of IT-specific divisions using values from the ITDivisionName enum.
     * @return list of IT-related divisions as Division objects
     */
    @Override
    public List<Division> generateDivisions() {
        List<Division> listOfDivisions = new ArrayList<>();

        // Create Division objects from enum constants
        for (ITDivisionName c : ITDivisionName.values()) {
            listOfDivisions.add(new Division(c.getDivisionName()));
        }

        return listOfDivisions;
    }
}
