package CA2;

import CA2.constants.DepartmentNames;
import CA2.constants.HRDivisionNames;
import CA2.constants.ITDivisionNames;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    static Random random = new Random();
    static String[] names = {"Abby", "Abdul", "Ada", "Addison", "Adelbert", "Adelina", "Adella", "Adolf", "Adolpho", "Adriane", "Adrianne", "Adrien", "Aigneis", "Ailee", "Alex", "Alice", "Aaron", "Ava"};
    static String[] lastNames = {"Lulham", "Siaskowski", "Blinkhorn", "Tamburo", "Hartzenberg", "Coniam", "Field", "Carthew", "Ramsey", "Alderton", "Pattle", "Emblow", "Witchell", "Forsdicke", "Chrispin", "Johnson", "Smith", "Williams"};
    static String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com", "aol.com", "live.com"};

    private static String generateRandomName() {
        return names[random.nextInt(names.length)];
    }

    private static String generateRandomLastName() {
        return lastNames[random.nextInt(lastNames.length)];
    }

    private static String generateRandomDomain() {
        return domains[random.nextInt(domains.length)];
    }

    private static String generateRandomGender() {
        return random.nextInt(2)%2 == 0 ? "Male" : "Female";
    }

    private static String generateEmailBasedOnName(String firstName, String lastName) {
        return (firstName.charAt(0) + "." + lastName + "@" + generateRandomDomain()).toLowerCase();
    }

    private static List<String> generateRandomDivisionName(Department department) {
        return List.of(department.generateDivisions().get(random.nextInt(department.generateDivisions().size())).getName());
    }

    public static Employee generateRandomEmployee(Department department, double salary) {
        String firstName = generateRandomName();
        String lastName = generateRandomLastName();

        return generateRandomEmployeeCustomName(firstName, lastName, department, salary);
    }

    public static Employee generateRandomEmployeeCustomName(String firstName, String lastName, Department department, double salary) {
        String jobTitle = "No Department Provided";
        if (department.getDepartmentName().equals(DepartmentNames.HR.getFulltName())) {
            jobTitle = List.of("Technical Recruiter", "Vendor Coordination", "Employee Engagement")
                    .get(random.nextInt(3));
        } else if (department.getDepartmentName().equals(DepartmentNames.IT.getFulltName())) {
            jobTitle = List.of("Backend Developer", "Frontend Developer", "Automation QA")
                    .get(random.nextInt(3));
        }

        return new Employee(
                firstName,
                lastName,
                generateRandomGender(),
                generateEmailBasedOnName(firstName, lastName),
                String.format("%.2f", salary),
                "Employee",
                generateRandomDivisionName(department),
                department.getDepartmentName(),
                jobTitle
        );
    }

    public static Manager generateRandomManager(String departmentName) {
        String firstName = generateRandomName();
        String lastName = generateRandomLastName();
        List<String> divisions = new ArrayList<>();
        if (departmentName.equals(DepartmentNames.IT.getFulltName())) {
            divisions = Arrays.stream(ITDivisionNames.values())
                    .map(ITDivisionNames::getDivisionName)
                    .collect(Collectors.toList());
        } else if (departmentName.equals(DepartmentNames.HR.getFulltName())) {
            divisions = Arrays.stream(HRDivisionNames.values())
                    .map(HRDivisionNames::getDivisionName)
                    .collect(Collectors.toList());
        }
        return new Manager(
                firstName,
                lastName,
                generateRandomGender(),
                generateEmailBasedOnName(firstName, lastName),
                String.format("%.2f", random.nextDouble(2000.00, 5000.00)),
                divisions,
                "Management",
                departmentName,
                "Head of Department");
    }

    public static void writeToCSV(List<Employee> users, String filename) {
        File file = new File(filename);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(filename, true)) { // Append mode = true
            if (!fileExists) {
                // Write header only if file doesn't exist
                writer.write("First Name,Last Name,Gender,Email,Salary,Position,Division(s),Department,Job Title\n");
            }

            // Write each user's data
            for (Employee user : users) {
                writer.write(user.getInfo());
            }
            System.out.println("Data successfully written to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
