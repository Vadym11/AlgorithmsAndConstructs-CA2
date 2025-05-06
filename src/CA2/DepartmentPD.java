package CA2;

import CA2.base.Department;
import CA2.base.Division;
import CA2.constants.PDDivisionName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DepartmentPD extends Department {

    public DepartmentPD(Manager manager) {
        super(manager);
    }

    @Override
    protected List<Employee> generateEmployees() {
        Random random = new Random();
        List<Employee> listOfEmployees = new ArrayList<>();
        int numOfEmployees = random.nextInt(20,30);

        for (int i = 0; i < numOfEmployees; i++) {
            double bonusCoefficient = numOfEmployees > 25 ? 1.35 : 1.25;
            double salary = random.nextDouble(1300, 1411.00) * bonusCoefficient;
            listOfEmployees.add(Utils.generateRandomEmployeeForDepartmentInit(this, salary));
        }

        return listOfEmployees;
    }

    @Override
    public List<Division> generateDivisions() {
        List<Division> listOfDivisions = new ArrayList<>();
        for (PDDivisionName c : PDDivisionName.values()) {
            listOfDivisions.add(new Division(c.getDivisionName()));
        }

        return listOfDivisions;
    }
}
