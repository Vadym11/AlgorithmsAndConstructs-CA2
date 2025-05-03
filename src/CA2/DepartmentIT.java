package CA2;

import CA2.constants.ITDivisionNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DepartmentIT extends Department{

    public DepartmentIT(Manager manager) {
        super(manager);
    }

    @Override
    protected List<Employee> generateEmployees() {
        Random random = new Random();
        List<Employee> listOfEmployees = new ArrayList<>();
        int numOfEmployees = random.nextInt(25, 35);

        for (int i = 0; i < numOfEmployees; i++) {
            double bonusCoefficient = numOfEmployees > 7 ? 1.4 : 1.3;
            double salary = random.nextDouble(1000, 1111.00 * bonusCoefficient);;
            listOfEmployees.add(Utils.generateRandomEmployeeForDepartmentInit(this, salary));
        }

        return listOfEmployees;
    }

    @Override
    public List<Division> generateDivisions() {
        List<Division> listOfDivisions = new ArrayList<>();
        for (ITDivisionNames c : ITDivisionNames.values()) {
            listOfDivisions.add(new Division(c.getDivisionName()));
        }

        return listOfDivisions;
    }
}
