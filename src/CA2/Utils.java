package CA2;

import CA2.base.Department;
import CA2.base.Division;
import CA2.constants.DepartmentName;
import CA2.constants.HRDivisionName;
import CA2.constants.ITDivisionName;
import CA2.constants.PDDivisionName;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class providing helper methods for:
 * - Employee and manager generation
 * - Reading/writing employee and applicant data
 * - Sorting, searching, and printing data in CSV format
 */
public class Utils {

    // Generates a timestamped file name for storing employee data
    private static final String EMPLOYEES_FILE_NAME = filename();

    /**
     * Array of sample first names used to randomly generate employee names.
     */
    private static final String[] FIRST_NAMES = {
            "Abby", "Benjamin", "Cassandra", "Derek", "Ella", "Felix", "Grace", "Hannah", "Isaac", "Jasmine",
            "Kevin", "Luna", "Mason", "Nora", "Oliver", "Piper", "Quinn", "Riley", "Sophia", "Thomas",
            "Uma", "Victor", "Willow", "Xander", "Yasmin", "Zachary", "Adrian", "Bella", "Caleb", "Diana",
            "Ethan", "Freya", "Gavin", "Hazel", "Ivy", "Jacob", "Kira", "Leo", "Miles", "Naomi",
            "Oscar", "Paige", "Quincy", "Ryan", "Stella", "Tristan", "Ulysses", "Violet", "Wesley", "Zoey"
    };

    /**
     * Array of sample last names used to randomly generate employee names.
     */
    private static final String[] LAST_NAMES = {
            "Lulham", "Siaskowski", "Blinkhorn", "Tamburo", "Hartzenberg", "Coniam", "Field", "Carthew", "Ramsey", "Alderton",
            "Pattle", "Emblow", "Witchell", "Forsdicke", "Chrispin", "Johnson", "Smith", "Williams",
            "Brown", "Taylor", "Davies", "Evans", "Wilson", "Moore", "Anderson", "Thomas", "Jackson", "White",
            "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
            "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Torres", "Nguyen", "Murphy",
            "Kelly", "Reed"
    };

    /**
     * Array of email domains used for generating random email addresses.
     */
    private static final String[] DOMAINS = {
            "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com", "aol.com", "live.com"
    };

    // Random number generator used throughout the class
    private static final Random random = new Random();

    /**
     * Returns the header line for employee CSV export.
     * @return comma-separated column headers
     */
    private static String getTableHeader() {
        return "First Name,Last Name,Gender,Email,Salary,Department,Position,Job Title,Division(s)";
    }

    /**
     * Generates a timestamp-based file name for the employee CSV.
     * @return generated file name string
     */
    private static String filename() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");

        return "employees_" + LocalDateTime.now().format(formatter) + ".csv";
    }

    /**
     * Selects a random first name from the list.
     * @return randomly selected first name
     */
    private static String generateRandomName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    /**
     * Selects a random last name from the list.
     * @return randomly selected last name
     */
    private static String generateRandomLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    /**
     * Selects a random email domain from the list.
     * @return randomly selected domain
     */
    private static String generateRandomDomain() {
        return DOMAINS[random.nextInt(DOMAINS.length)];
    }

    /**
     * Randomly returns either "Male" or "Female".
     * @return randomly selected gender
     */
    private static String generateRandomGender() {
        return random.nextBoolean() ? "Male" : "Female";
    }

    /**
     * Generates an email address based on the first and last name.
     * @param firstName employee's first name
     * @param lastName employee's last name
     * @return constructed email address
     */
    private static String generateEmailBasedOnName(String firstName, String lastName) {
        return (firstName.charAt(0) + "." + lastName + "@" + generateRandomDomain()).toLowerCase();
    }

    /**
     * Selects a random division name from a given department.
     * @param department department to pick division from
     * @return list containing one randomly selected division name
     */
    private static List<String> generateRandomDivisionName(Department department) {
        List<Division> divisions = department.generateDivisions();

        return List.of(divisions.get(random.nextInt(divisions.size())).getName());
    }

    /**
     * Generates a random employee with a randomly assigned salary.
     * @param firstName employee's first name
     * @param lastName employee's last name
     * @param department department the employee belongs to
     * @return generated Employee object
     */
    public static Employee generateRandomEmployee(String firstName, String lastName, Department department) {
        double salary = random.nextDouble(2000.00, 5000.00);

        return generateRandomEmployeeCustomNameSalary(firstName, lastName, department, salary);
    }

    /**
     * Generates a random employee with a specified salary and name.
     * @param firstName employee's first name
     * @param lastName employee's last name
     * @param department department the employee belongs to
     * @param salary salary to assign
     * @return generated Employee object
     */
    public static Employee generateRandomEmployeeCustomNameSalary(String firstName, String lastName, Department department, double salary) {
        String jobTitle;

        // Select a job title based on department type
        if (department.getDepartmentName().equals(DepartmentName.HR.getFullName())) {
            jobTitle = List.of("Technical Recruiter", "Vendor Coordination", "Employee Engagement")
                    .get(random.nextInt(3));
        } else if (department.getDepartmentName().equals(DepartmentName.IT.getFullName())) {
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

    /**
     * Returns the file name for employee data export.
     * @return CSV file name
     */
    public static String getEmployeesFileName() {
        return EMPLOYEES_FILE_NAME;
    }

    /**
     * Generates a random employee with a specified salary and randomly generated name.
     * Used during department initialization.
     * @param department department the employee belongs to
     * @param salary employee's salary
     * @return generated Employee object
     */
    public static Employee generateRandomEmployeeForDepartmentInit(Department department, double salary) {
        // first and last name are randomly generated
        String firstName = generateRandomName();
        String lastName = generateRandomLastName();

        return generateRandomEmployeeCustomNameSalary(firstName, lastName, department, salary);
    }

    /**
     * Generates a manager for the specified department using the provided name.
     * Assigns all relevant divisions to the manager based on department type.
     * @param firstName manager's first name
     * @param lastName manager's last name
     * @param departmentName name of the department
     * @return generated Manager object
     */
    public static Manager generateRandomManager(String firstName, String lastName, String departmentName) {
        List<String> divisions;

        // Assign division list based on department
        if (departmentName.equals(DepartmentName.IT.getFullName())) {
            divisions = Arrays.stream(ITDivisionName.values())
                    .map(ITDivisionName::getDivisionName)
                    .collect(Collectors.toList());
        } else if (departmentName.equals(DepartmentName.HR.getFullName())) {
            divisions = Arrays.stream(HRDivisionName.values())
                    .map(HRDivisionName::getDivisionName)
                    .collect(Collectors.toList());
        } else {
            divisions = Arrays.stream(PDDivisionName.values())
                    .map(PDDivisionName::getDivisionName)
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
                "Head of Department"
        );
    }

    /**
     * Generates a manager with a randomly assigned name for the specified department.
     * @param departmentName name of the department
     * @return generated Manager object
     */
    public static Manager generateRandomManagerDepartment(String departmentName) {
        // first and last name are randomly generated
        String firstName = generateRandomName();
        String lastName = generateRandomLastName();

        return generateRandomManager(firstName, lastName, departmentName);
    }

    /**
     * Writes a list of employees to the output CSV file.
     * Appends records to the file and creates it with headers if it does not exist.
     * @param employees list of employees to write to file
     */
    public static void writeToCSV(List<Employee> employees) {
        File file = new File(EMPLOYEES_FILE_NAME);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(EMPLOYEES_FILE_NAME, true)) { // Open file in append mode
            if (!fileExists) {
                // Add header row if the file is being created for the first time
                writer.write(getTableHeader().concat("\n"));
            }

            // Write each employee record info
            for (Employee employee : employees) {
                writer.write(employee.getInfo() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the employee CSV file and returns the records (excluding header).
     * @return list of employee data lines
     */
    public static List<String> readEmployeeFile() {
        return readFileWithFilename(EMPLOYEES_FILE_NAME);
    }

    /**
     * Reads the applicants form file and returns the records (excluding header).
     * @return list of applicant data lines
     */
    public static List<String> readApplicantsFile() {
        return readFileWithFilename("Applicants_Form.txt");
    }

    /**
     * Reads a file line-by-line and returns its contents, excluding the header.
     * @param filename name of the file to read
     * @return list of file lines excluding the first line (header), or null if the file can't be read
     */
    public static List<String> readFileWithFilename(String filename) {
        List<String> fileList = new ArrayList<>();

        try {
            // Attempt to open and read the file
            BufferedReader myFile = new BufferedReader(new FileReader(filename));

            // Read and skip the header line
            String input = myFile.readLine();

            // Read the remaining lines
            while (input != null) {
                fileList.add(input);
                input = myFile.readLine();
            }

            // Return contents without header
            return fileList.subList(1, fileList.size());

        } catch (Exception e) {
            // If file cannot be opened or read
            System.out.println("Error - unable to find file " + filename);
        }

        return null;
    }

    /**
     * Sorts a list of CSV records using merge sort based on a specific column.
     * @param myList list of CSV strings to sort
     * @param sortColumn index of the column to sort by
     */
    public static void mergeSortCustom(List<String> myList, int sortColumn) {
        if (myList == null || myList.size() <= 1) return; // Nothing to sort
        mergeSortHelper(myList, 0, myList.size() - 1, sortColumn); // Start recursive sort
    }

    /**
     * Recursively splits the list into halves and sorts each half.
     * @param myList list to sort
     * @param left starting index of the sublist
     * @param right ending index of the sublist
     * @param sortColumn column index to sort by
     */
    private static void mergeSortHelper(List<String> myList, int left, int right, int sortColumn) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        // Recursively sort left and right halves
        mergeSortHelper(myList, left, mid, sortColumn);
        mergeSortHelper(myList, mid + 1, right, sortColumn);

        // Merge the sorted halves
        merge(myList, left, mid, right, sortColumn);
    }

    /**
     * Merges two sorted sublists of myList based on a specific column.
     * @param myList full list being sorted
     * @param left start index of the first sublist
     * @param mid end index of the first sublist
     * @param right end index of the second sublist
     * @param sortColumn column index to compare for sorting
     */
    private static void merge(List<String> myList, int left, int mid, int right, int sortColumn) {
        List<String> merged = new ArrayList<>();
        int i = left, j = mid + 1;

        // Merge elements from both halves in sorted order
        while (i <= mid && j <= right) {
            String leftVal = myList.get(i).split(",")[sortColumn];
            String rightVal = myList.get(j).split(",")[sortColumn];

            if (leftVal.compareTo(rightVal) <= 0) {
                merged.add(myList.get(i++));
            } else {
                merged.add(myList.get(j++));
            }
        }

        // Append remaining elements from left half
        while (i <= mid) merged.add(myList.get(i++));

        // Append remaining elements from right half
        while (j <= right) merged.add(myList.get(j++));

        // Copy merged results back to the original list
        for (int k = 0; k < merged.size(); k++) {
            myList.set(left + k, merged.get(k));
        }
    }

    /**
     * Sorts and prints the first N employee records from a list.
     * @param emplInfo list of employee CSV lines
     * @param records number of records to print
     * @param sortOption column index to sort by
     */
    public static void sortAndPrintEmployeesInfo(List<String> emplInfo, int records, int sortOption) {
        if (emplInfo != null) {
            mergeSortCustom(emplInfo, sortOption); // Sort employee records
            printCSVAsTable(emplInfo.subList(0, records)); // Display top N
        } else {
            System.out.println("Ooops, an error occurred, no employee data exists...");
        }
    }

    /**
     * Reads employee file, sorts and prints top N records.
     * @param records number of records to print
     * @param sortOption column index to sort by
     */
    public static void sortAndPrintEmployeeFile(int records, int sortOption) {
        List<String> fileList = readEmployeeFile();
        sortAndPrintEmployeesInfo(fileList, records, sortOption);
    }

    /**
     * Reads applicant file, sorts and prints top N records.
     * @param records number of records to print
     * @param sortOption column index to sort by
     */
    public static void sortAndPrintApplicantsFile(int records, int sortOption) {
        List<String> fileList = readApplicantsFile();
        if (fileList != null) {
            mergeSortCustom(fileList, sortOption);
            printCSVAsTable(fileList.subList(0, records));
        }
    }

    /**
     * Recursively performs binary search to find the index of a matching record in a sorted list.
     * @param myList list of CSV records
     * @param option index of the column to search by
     * @param searchQuery query string (case-insensitive)
     * @param start start index of the search range
     * @param end end index of the search range
     * @return index of the first matching record, or -1 if not found
     */
    public static int binarySearchRecursiveCustom(List<String> myList, int option, String searchQuery, int start, int end) {
        if (start > end) {
            return -1; // Key not found
        }

        int middle = (end + start) / 2;
        String word = myList.get(middle).split(",")[option].toLowerCase();

        if (word.compareTo(searchQuery) == 0) {
            return middle;
        } else if (word.compareTo(searchQuery) < 0) {
            return binarySearchRecursiveCustom(myList, option, searchQuery, middle + 1, end);
        } else {
            return binarySearchRecursiveCustom(myList, option, searchQuery, start, middle - 1);
        }
    }

    /**
     * Uses binary search to find all indices of records matching the query (including duplicates).
     * @param myList list of CSV records
     * @param option index of the column to search
     * @param searchQuery query string (case-insensitive)
     * @param start start index of the search range
     * @param end end index of the search range
     * @return list of indices of all matching records, or null if not found
     */
    public static List<Integer> binarySearchAllDuplicates(List<String> myList, int option, String searchQuery, int start, int end) {
        List<Integer> result = new ArrayList<>();

        // First, find one matching index
        int matchIndex = binarySearchRecursiveCustom(myList, option, searchQuery, start, end);

        // Return null if no match was found
        if (matchIndex == -1) return null;

        // Add the match
        result.add(matchIndex);

        // Search to the left of the match
        int left = matchIndex - 1;
        while (left >= start) {
            if (myList.get(left).split(",")[option].toLowerCase().equals(searchQuery)) {
                result.add(left);
                left--;
            } else {
                break;
            }
        }

        // Search to the right of the match
        int right = matchIndex + 1;
        while (right <= end) {
            if (myList.get(right).split(",")[option].toLowerCase().equals(searchQuery)) {
                result.add(right);
                right++;
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * Searches for matching employee records and prints them as a table.
     * @param employees list of employee CSV strings
     * @param searchQuery query string (first or last name)
     * @param option column index to search
     */
    public static void searchAndPrintEmployees(List<String> employees, String searchQuery, int option) {
        printCSVAsTable(searchRecords(searchQuery, option, employees));
    }

    /**
     * Searches for matching applicant records and prints them as a table.
     * @param searchQuery query string (first or last name)
     * @param option column index to search
     */
    public static void searchAndPrintApplicants(String searchQuery, int option) {
        printCSVAsTable(searchRecords(searchQuery, option, readApplicantsFile()));
    }

    /**
     * Sorts and searches a list of records for all matches to the search query.
     * @param searchQuery query string
     * @param option column index to sort and search by
     * @param fileList list of CSV records to search
     * @return list of matching records, or null if no match is found
     */
    public static List<String> searchRecords(String searchQuery, int option, List<String> fileList) {
        if (fileList != null) {
            List<String> recordsFound = new ArrayList<>();

            // Sort the list first
            mergeSortCustom(fileList, option);

            // Find all matching indices
            List<Integer> foundIndexes = binarySearchAllDuplicates(fileList, option, searchQuery, 0, fileList.size() - 1);

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

    /**
     * Prints a list of CSV records in a formatted table layout.
     * Automatically adjusts column widths.
     * @param file list of CSV strings to display
     */
    public static void printCSVAsTable(List<String> file) {
        List<String[]> fileArray = new ArrayList<>();

        if (file != null) {
            fileArray.add("No,".concat(getTableHeader()).split(","));

            // Adjust header if printing applicant records
            try {
                String lastColumnName = file.get(0).split(",")[8];
                if (lastColumnName.equals("DataVision") || lastColumnName.equals("TechInnovators")) {
                    fileArray.get(0)[9] = "Company";
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Make sure there are no empty lines in Applicants_Form.txt file!");
                System.out.println(e.getMessage());
            }

            // Add line numbers to rows
            for (int i = 0; i < file.size(); i++) {
                String lineNumber = i + 1 + ",";
                fileArray.add(lineNumber.concat(file.get(i)).split(","));
            }

            // Calculate max width for each column
            int numCols = fileArray.get(0).length;
            int[] colWidths = new int[numCols];
            for (String[] row : fileArray) {
                for (int i = 0; i < numCols; i++) {
                    colWidths[i] = Math.max(colWidths[i], row[i].length());
                }
            }

            // Build row separator
            StringBuilder separator = new StringBuilder();
            for (int width : colWidths) {
                separator.append("+").append("-".repeat(width + 2));
            }
            separator.append("+");
            String sepRow = separator.toString();

            // Print formatted table
            System.out.println(sepRow);
            for (String[] row : fileArray) {
                System.out.print("|");
                for (int i = 0; i < numCols; i++) {
                    System.out.printf(" %-" + colWidths[i] + "s |", row[i]);
                }
                System.out.println();
                System.out.println(sepRow);
            }
        }
    }

    /**
     * Prompts user to enter a value from {0, 1, 2}.
     * @param sc Scanner instance
     * @return user input
     */
    public static int getUserChoice(Scanner sc) {
        return getUserInputInt(sc, List.of(0, 1, 2));
    }

    /**
     * Prompts user to enter a value from {0, 1, 2, 3}.
     * @param sc Scanner instance
     * @return user input
     */
    public static int getUserOption(Scanner sc) {
        return getUserInputInt(sc, List.of(0, 1, 2, 3));
    }

    /**
     * Reads and validates integer input from the user based on allowed options.
     * Keeps prompting until valid input is provided.
     * @param sc Scanner instance
     * @param optionsArray list of valid integer options
     * @return validated user input
     */
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
