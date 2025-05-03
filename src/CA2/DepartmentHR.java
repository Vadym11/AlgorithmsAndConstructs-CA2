package CA2;

import CA2.constants.HRDivisionNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DepartmentHR extends Department{

    public DepartmentHR(Manager manager) {
        super(manager);
    }

    @Override
    protected List<Employee> generateEmployees() {
        Random random = new Random();
        List<Employee> listOfEmployees = new ArrayList<>();
        int numOfEmployees = random.nextInt(20,30);

        for (int i = 0; i < numOfEmployees; i++) {
            double bonusCoefficient = numOfEmployees > 5 ? 1.3 : 1.2;
            double salary = random.nextDouble(1000, 1111.00 * bonusCoefficient);;
            listOfEmployees.add(Utils.generateRandomEmployeeForDepartmentInit(this, salary));
        }

        return listOfEmployees;
    }

    @Override
    public List<Division> generateDivisions() {
        List<Division> listOfDivisions = new ArrayList<>();
        for (HRDivisionNames c : HRDivisionNames.values()) {
            listOfDivisions.add(new Division(c.getDivisionName()));
        }

        return listOfDivisions;
    }
}
