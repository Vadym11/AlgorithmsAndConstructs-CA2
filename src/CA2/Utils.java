package CA2;

import CA2.constants.DepartmentNames;
import CA2.constants.HRDivisionNames;
import CA2.constants.ITDivisionNames;
import CA2.constants.PDDivisionNames;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    private static final String EMPLOYEES_FILE_NAME = filename();

    private static final String[] FIRST_NAMES = {
            "Abby", "Benjamin", "Cassandra", "Derek", "Ella", "Felix", "Grace", "Hannah", "Isaac", "Jasmine",
            "Kevin", "Luna", "Mason", "Nora", "Oliver", "Piper", "Quinn", "Riley", "Sophia", "Thomas",
            "Uma", "Victor", "Willow", "Xander", "Yasmin", "Zachary", "Adrian", "Bella", "Caleb", "Diana",
            "Ethan", "Freya", "Gavin", "Hazel", "Ivy", "Jacob", "Kira", "Leo", "Miles", "Naomi",
            "Oscar", "Paige", "Quincy", "Ryan", "Stella", "Tristan", "Ulysses", "Violet", "Wesley", "Zoey"
    };

    private static final String[] LAST_NAMES = {
            "Lulham", "Siaskowski", "Blinkhorn", "Tamburo", "Hartzenberg", "Coniam", "Field", "Carthew", "Ramsey", "Alderton",
            "Pattle", "Emblow", "Witchell", "Forsdicke", "Chrispin", "Johnson", "Smith", "Williams",
            "Brown", "Taylor", "Davies", "Evans", "Wilson", "Moore", "Anderson", "Thomas", "Jackson", "White",
            "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
            "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Torres", "Nguyen", "Murphy",
            "Kelly", "Reed"
    };

    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com", "aol.com", "live.com"};

    private static final Random random = new Random();

    private static String getTableHeader() {
        return "First Name,Last Name,Gender,Email,Salary,Department,Position,Job Title,Division(s)";
    }

    private static String filename() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
        return "employees_" + LocalDateTime.now().format(formatter) + ".csv";
    }

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
        return random.nextBoolean() ? "Male" : "Female";
    }

    private static String generateEmailBasedOnName(String firstName, String lastName) {
        return (firstName.charAt(0) + "." + lastName + "@" + generateRandomDomain()).toLowerCase();
    }

    private static List<String> generateRandomDivisionName(Department department) {
        List<Division> divisions = department.generateDivisions();

        return List.of(divisions.get(random.nextInt(divisions.size())).getName());

    }

    public static Employee generateRandomEmployee(String firstName, String lastName, Department department) {
        double salary = random.nextDouble(2000.00, 5000.00);

        return generateRandomEmployeeCustomNameSalary(firstName, lastName, department, salary);
    }

    public static Employee generateRandomEmployeeCustomNameSalary(String firstName, String lastName, Department department, double salary) {
        String jobTitle;
        if (department.getDepartmentName().equals(DepartmentNames.HR.getFullName())) {
            jobTitle = List.of("Technical Recruiter", "Vendor Coordination", "Employee Engagement")
                    .get(random.nextInt(3));
        } else if (department.getDepartmentName().equals(DepartmentNames.IT.getFullName())) {
            jobTitle = List.of("Backend Developer", "Frontend Developer", "Automation QA")
                    .get(random.nextInt(3));
        } else {
            jobTitle = List.of("Scrum Master", "Project Manager", "Business Analyst")
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

    public static String getEmployeesFileName() {
        return EMPLOYEES_FILE_NAME;
    }

    public static Employee generateRandomEmployeeForDepartmentInit(Department department, double salary) {
        String firstName = generateRandomName();
        String lastName = generateRandomLastName();

        return generateRandomEmployeeCustomNameSalary(firstName, lastName, department, salary);
    }

    public static Manager generateRandomManager(String firstName, String lastName, String departmentName) {
        List<String> divisions;
        if (departmentName.equals(DepartmentNames.IT.getFullName())) {
            divisions = Arrays.stream(ITDivisionNames.values())
                    .map(ITDivisionNames::getDivisionName)
                    .collect(Collectors.toList());
        } else if (departmentName.equals(DepartmentNames.HR.getFullName())) {
            divisions = Arrays.stream(HRDivisionNames.values())
                    .map(HRDivisionNames::getDivisionName)
                    .collect(Collectors.toList());
        } else {
            divisions = Arrays.stream(PDDivisionNames.values())
                    .map(PDDivisionNames::getDivisionName)
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

    public static Manager generateRandomManagerDepartment(String departmentName) {
        String firstName = generateRandomName();
        String lastName = generateRandomLastName();

        return generateRandomManager(firstName, lastName, departmentName);
    };

    public static void writeToCSV(List<Employee> employees) {
        File file = new File(EMPLOYEES_FILE_NAME);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(EMPLOYEES_FILE_NAME, true)) { // Append mode = true
            if (!fileExists) {
                // Write header only if file doesn't exist
                writer.write(getTableHeader().concat("\n"));
            }
            // Write each user's data
            for (Employee employee : employees) {
                writer.write(employee.getInfo() + "\n");
            }
            System.out.println("Data successfully written to " + EMPLOYEES_FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static List<String> readEmployeeFile() {
        return readFileWithFilename(EMPLOYEES_FILE_NAME);
    }

    public static List<String> readApplicantsFile() {
        return readFileWithFilename("Applicants_Form.txt");
    }

    public static List<String> readFileWithFilename(String filename) {
        List<String> fileList = new ArrayList<>();
        try {
            //must have a tr-catch because the file might not exist
            BufferedReader myFile = new BufferedReader(new FileReader(filename));
            //there is something in the file. Let's see what it is!
            String input = myFile.readLine();
            //now a loop to read all the data from the file
            //USe while() loop because the file *might* be empty!
            while (input != null){
                fileList.add(input);
                input = myFile.readLine();
            }
//            System.out.println("Finished reading from " + filename);

            // return file content without header
            return fileList.subList(1, fileList.size());
        }catch (Exception e){
            //error opening file
            System.out.println("Error - unable to find file " + filename);
        }

        return null;
    }

    public static void insertionSortCustom(List<String> myList, int sortOption) {
        int pos;
        String keyElement;

        for (int i = 0; i < myList.size(); i++) {
            keyElement = myList.get(i);
            pos = i;
            while (pos > 0 && (myList.get(pos - 1).split(",")[sortOption])
                    .compareTo(keyElement.split(",")[sortOption]) > 0) {
                String elemAtPosMinusOne = myList.get(pos - 1);
                myList.set(pos, elemAtPosMinusOne);
                pos = pos - 1;
            }
            myList.set(pos, keyElement);
        }
    }

    public static void mergeSortCustom(List<String> myList, int sortColumn) {
        if (myList == null || myList.size() <= 1) return;
        mergeSortHelper(myList, 0, myList.size() - 1, sortColumn);
    }

    private static void mergeSortHelper(List<String> myList, int left, int right, int sortColumn) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSortHelper(myList, left, mid, sortColumn);
        mergeSortHelper(myList, mid + 1, right, sortColumn);
        merge(myList, left, mid, right, sortColumn);
    }

    private static void merge(List<String> myList, int left, int mid, int right, int sortColumn) {
        List<String> merged = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            String leftVal = myList.get(i).split(",")[sortColumn];
            String rightVal = myList.get(j).split(",")[sortColumn];

            if (leftVal.compareTo(rightVal) <= 0) {
                merged.add(myList.get(i++));
            } else {
                merged.add(myList.get(j++));
            }
        }

        while (i <= mid) merged.add(myList.get(i++));
        while (j <= right) merged.add(myList.get(j++));

        // Write back to original list
        for (int k = 0; k < merged.size(); k++) {
            myList.set(left + k, merged.get(k));
        }
    }

    public static void sortAndPrintEmployeeFile(int records, int sortOption) {
        List<String> fileList = readEmployeeFile();

        if (fileList != null) {
            mergeSortCustom(fileList, sortOption);
            printCSVAsTable(fileList.subList(0, records));
        }
    }

    public static void sortAndPrintApplicantsFile(int records, int sortOption) {
        List<String> fileList = readApplicantsFile();

        if (fileList != null) {
            mergeSortCustom(fileList, sortOption);
            printCSVAsTable(fileList.subList(0, records));
        }
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
        // return null if search did not find matches
        if (matchIndex == -1) return null;

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

    public static void searchAndPrintEmployees(String searchQuery, int option) {
        printCSVAsTable(searchRecords(searchQuery, option, readEmployeeFile()));
    }

    public static void searchAndPrintApplicants(String searchQuery, int option) {
        printCSVAsTable(searchRecords(searchQuery, option, readApplicantsFile()));
    }

    public static List<String> searchRecords(String searchQuery, int option, List<String> fileList) {

        if (fileList != null) {
            List<String> recordsFound = new ArrayList<>();

            mergeSortCustom(fileList, option);

            List<Integer> foundIndexes = binarySearchAllDuplicates(
                    fileList, option, searchQuery, 0, (fileList.size() - 1));

            if (foundIndexes != null) {
                for (int index : foundIndexes) {
                    recordsFound.add(fileList.get(index));
                }
                System.out.println("Search finished. " + recordsFound.size() + " records found:");

                return recordsFound;
            } else {
                System.out.println("No records found for query: " + searchQuery);
            }
        }

        return null;
    }

    public static void printCSVAsTable(List<String> file) {
        List<String[]> fileArray = new ArrayList<>();

        if (file != null) {
            fileArray.add("No,".concat(getTableHeader()).split(","));
            // change last column name in header to "Company" if printing Applicants_Form.txt file
            try {
                String lastColumnName = file.get(0).split(",")[8];
                if (lastColumnName.equals("DataVision") || lastColumnName.equals("TechInnovators")) {
                    fileArray.get(0)[9] = "Company";
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Make sure there are no empty lines in Applicants_Form.txt file!");
                System.out.println(e.getMessage());
            }

            for (int i = 0; i < file.size(); i++) {
                String lineNumber = i + 1 + ",";
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

    public static int getUserChoice(Scanner sc) {
        return getUserInputInt(sc, List.of(1, 2));
    }

    public static int getUserOption(Scanner sc) {
        return getUserInputInt(sc, List.of(1, 2, 3));
    }

    public static int getUserInputInt(Scanner sc, List<Integer> optionsArray) {
        int userChoice;
        String options = "";
        for (int option : optionsArray) {
            options += option + " or ";
        }
        String errorMessage = String.format("Invalid input. Please enter %s:\n", options.substring(0, options.length() - 4));

        while (true) {
            try {
                String input = sc.nextLine().trim();
                userChoice = Integer.parseInt(input);

                if (optionsArray.contains(userChoice)) {
                    break;
                } else {
                    System.out.print(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.print(errorMessage);
            }
        }

        return userChoice;
    }
}
