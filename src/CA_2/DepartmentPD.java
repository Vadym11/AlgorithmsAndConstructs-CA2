package CA_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the PD (Product Development) department in the company.
 * Inherits from the abstract Department class and implements logic specific to
 * generating PD employees and their internal divisions.
 */
public class DepartmentPD extends Department {

    /**
     * Constructs a PD department with the given manager.
     * @param manager PD department manager
     */
    public DepartmentPD(Manager manager) {
        super(manager);
    }

    /**
     * Generates a list of employees specific to the PD department.
     * The number of employees is randomly chosen between 20 and 33.
     * Salary is influenced by a bonus coefficient, which is slightly higher
     * for larger teams.
     * @return list of PD employees
     */
    @Override
    protected List<Employee> generateEmployees() {
        Random random = new Random();
        List<Employee> listOfEmployees = new ArrayList<>();
        int numOfEmployees = random.nextInt(20, 34); // number of PD employees

        for (int i = 0; i < numOfEmployees; i++) {
            // Adjust salary with bonus based on department size
            double bonusCoefficient = numOfEmployees > 25 ? 1.35 : 1.25;
            double salary = random.nextDouble(1300, 1411.00) * bonusCoefficient;

            // Create and add employee to the department
            listOfEmployees.add(Utils.generateRandomEmployeeForDepartmentInit(this, salary));
        }

        return listOfEmployees;
    }

    /**
     * Generates a list of PD-specific divisions using predefined names from the PDDivisionName enum.
     * @return list of divisions for the PD department as Division objects
     */
    @Override
    public List<Division> generateDivisions() {
        List<Division> listOfDivisions = new ArrayList<>();

        // Instantiate Division objects from enum constants
        for (PDDivisionName c : PDDivisionName.values()) {
            listOfDivisions.add(new Division(c.getDivisionName()));
        }

        return listOfDivisions;
    }
}
