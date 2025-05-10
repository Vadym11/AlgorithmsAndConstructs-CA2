package CA_2;

public enum HRDivisionName {

    // Recruitment division
    RECR("Recruitment"),
    // People Operations division
    PEOPLE_OP("People Operations"),
    // Administrative division
    ADMIN("Administration");
    // Full name of the HR division
    private final String divisionName;

    // Constructor to assign full division name
    HRDivisionName(String division) {
        this.divisionName = division;
    }

    /**
     * Returns the full name of the HR division.
     * @return division name as a string
     */
    public String getDivisionName() {
        return divisionName;
    }
}
