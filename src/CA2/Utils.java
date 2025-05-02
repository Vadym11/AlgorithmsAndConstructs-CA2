package CA2;

import CA2.constants.DepartmentNames;
import CA2.constants.HRDivisionNames;
import CA2.constants.ITDivisionNames;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    private static final String MY_FILE_NAME = "employees.csv";

    static Random random = new Random();

    static String[] FIRST_NAMES = {
            "Abby", "Abdul", "Ada", "Addison", "Adelbert", "Adelina", "Adella", "Adolf", "Adolpho", "Adriane",
            "Adrianne", "Adrien", "Aigneis", "Ailee", "Alex", "Alice", "Aaron", "Ava",
            "Benjamin", "Bianca", "Cameron", "Cassandra", "Dante", "Diana", "Edward", "Ella", "Felix", "Fiona",
            "George", "Grace", "Henry", "Hazel", "Isaac", "Ivy", "Jacob", "Jasmine", "Kevin", "Kira",
            "Leo", "Luna", "Miles", "Mia", "Nathan", "Nora", "Oscar", "Olivia", "Paul", "Piper",
            "Quinn", "Quincy"
    };

    static String[] LAST_NAMES = {
            "Lulham", "Siaskowski", "Blinkhorn", "Tamburo", "Hartzenberg", "Coniam", "Field", "Carthew", "Ramsey", "Alderton",
            "Pattle", "Emblow", "Witchell", "Forsdicke", "Chrispin", "Johnson", "Smith", "Williams",
            "Brown", "Taylor", "Davies", "Evans", "Wilson", "Moore", "Anderson", "Thomas", "Jackson", "White",
            "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
            "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Torres", "Nguyen", "Murphy",
            "Kelly", "Reed"
    };

    static String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com", "aol.com", "live.com"};

    private static String generateRandomName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    private static String generateRandomLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    private static String generateRandomDomain() {
        return DOMAINS[random.nextInt(DOMAINS.length)];
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
        if (department.getDepartmentName().equals(DepartmentNames.HR.getFullName())) {
            jobTitle = List.of("Technical Recruiter", "Vendor Coordination", "Employee Engagement")
                    .get(random.nextInt(3));
        } else if (department.getDepartmentName().equals(DepartmentNames.IT.getFullName())) {
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
        if (departmentName.equals(DepartmentNames.IT.getFullName())) {
            divisions = Arrays.stream(ITDivisionNames.values())
                    .map(ITDivisionNames::getDivisionName)
                    .collect(Collectors.toList());
        } else if (departmentName.equals(DepartmentNames.HR.getFullName())) {
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

    public static void writeToCSV(List<Employee> employees) {
        File file = new File(MY_FILE_NAME);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(MY_FILE_NAME, true)) { // Append mode = true
            if (!fileExists) {
                // Write header only if file doesn't exist
                writer.write("First Name,Last Name,Gender,Email,Salary,Position,Division(s),Department,Job Title\n");
            }
            // Write each user's data
            for (Employee employee : employees) {
                writer.write(employee.getInfo());
            }
            System.out.println("Data successfully written to " + MY_FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static List<String> readFile() {
        List<String> fileList = new ArrayList<>();
        try {
            //must have a tr-catch because the file might not exist
            BufferedReader myFile = new BufferedReader(new FileReader(MY_FILE_NAME));
            //there is something in the file. Let's see what it is!
            String input = myFile.readLine();
            //now a loop to read all the data from the file
            //USe while() loop because the file *might* be empty!
            while (input != null){
                fileList.add(input);
                input = myFile.readLine();
            }
            System.out.println("Finished reading from File");
        }catch (Exception e){
            //error opening file
            System.out.println("Error - unable to find file " + MY_FILE_NAME);
        }

        return fileList;
    }

    public static void insertionSortCustom(List<String> myList, int sortOption) {
        int pos;
        String keyElement;

        for (int i = 1; i < myList.size(); i++) {
            keyElement = myList.get(i);
            pos = i;
            while (pos > 1 && (myList.get(pos - 1).split(",")[sortOption])
                    .compareTo(keyElement.split(",")[sortOption]) > 0) {
                String elemAtPosMinusOne = myList.get(pos - 1);
                myList.set(pos, elemAtPosMinusOne);
                pos = pos - 1;
            }
            myList.set(pos, keyElement);
        }
    }

    public static void sortAndPrint(int records, int sortOption) {
        List<String> fileList = readFile();

        insertionSortCustom(fileList, sortOption);

        printCSVAsTable(fileList.subList(0, records + 1));
    }

    public static int binarySearchRecursiveCustom(List<String> myList, int option, String key, int start, int end) {
        if (start > end) {
            return -1; // Key not found
        }

        int middle = (end + start)/2;
        String word = myList.get(middle).split(",")[option];

        if (word.compareTo(key) == 0) {
            return middle;
        } else if (word.compareTo(key) < 0) {
            return binarySearchRecursiveCustom(myList, option, key, middle + 1, end);
        } else {
            return binarySearchRecursiveCustom(myList, option, key, start, middle - 1);
        }
    }

    public static List<Integer> binarySearchAllDuplicates(List<String> myList, int option, String key, int start, int end) {
        List<Integer> result = new ArrayList<>();

        // First, find one match using binary search
        int matchIndex = binarySearchRecursiveCustom(myList, option, key, start, end);
        if (matchIndex == -1) return result;

        // Add the match
        result.add(matchIndex);

        // Look left
        int left = matchIndex - 1;
        while (left >= start) {
            if (myList.get(left).split(",")[option].equals(key)) {
                result.add(left);
                left--;
            } else {
                break;
            }
        }

        // Look right
        int right = matchIndex + 1;
        while (right <= end) {
            if (myList.get(right).split(",")[option].equals(key)) {
                result.add(right);
                right++;
            } else {
                break;
            }
        }

        return result;
    }


    public static List<String> searchRecords(String searchQuery, int option) {
        List<String> recordsFound = new ArrayList<>();
        List<String> fileList = readFile();

        // add header to the records found
        recordsFound.add(fileList.get(0));
        insertionSortCustom(fileList, option);

        List<Integer> foundIndexes = binarySearchAllDuplicates(
                fileList, option, searchQuery, 0, (fileList.size() - 1));

        for (int index : foundIndexes) {
            recordsFound.add(fileList.get(index));
        }

        return recordsFound;
    }

    public static void printCSVAsTable(List<String> file) {
        List<String[]> fileArray = new ArrayList<>();

        for (int i = 0; i < file.size(); i++) {
            String lineNumber = i == 0 ? "No," :  i + ",";
            fileArray.add(lineNumber.concat(file.get(i)).split(","));
        }
        // Determine column widths
        int numCols = fileArray.get(0).length;
        int[] colWidths = new int[numCols];
        for (String[] row : fileArray) {
            for (int i = 0; i < numCols; i++) {
                colWidths[i] = Math.max(colWidths[i], row[i].length());
            }
        }

        // Build separator row
        StringBuilder separator = new StringBuilder();
        for (int width : colWidths) {
            separator.append("+").append("-".repeat(width + 2));
        }
        separator.append("+");
        String sepRow = separator.toString();

        // Print top border
        System.out.println(sepRow);

        // Print rows with formatting
        for (String[] row : fileArray) {
            System.out.print("|");
            for (int i = 0; i < numCols; i++) {
                System.out.printf(" %-" + colWidths[i] + "s |", row[i]);
            }
            System.out.println();

            // Print separator after header and last row
            System.out.println(sepRow);
        }
    }
}
