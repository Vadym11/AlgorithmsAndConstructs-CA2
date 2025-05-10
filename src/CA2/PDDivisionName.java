package CA2;

public enum PDDivisionName {

    // Product Management division
    PM("Product Management"),
    // UX Research division
    UX("UX Research"),
    // UI Design division
    UI("UI Design");

    // Full name of the Product Department division
    private final String divisionName;

    // Constructor to assign the full division name
    PDDivisionName(String division) {
        this.divisionName = division;
    }

    /**
     * Returns the full name of the Product Department division.
     * @return division name as a string
     */
    public String getDivisionName() {
        return divisionName;
    }
}
