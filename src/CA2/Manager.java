package CA2;

import CA2.base.GetDivisions;

import java.util.List;

public class Manager extends Employee implements GetDivisions {

    public Manager(String firstName, String lastName, String gender, String email, String salary, List<String> divisions, String positionType, String department, String jobTitle) {
        super(firstName, lastName, gender, email, salary, positionType, divisions, department, jobTitle);
    }
}
