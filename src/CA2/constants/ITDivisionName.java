package CA2.constants;

public enum ITDivisionName {

    // Development division (possibly network/software development)
    NETWORK("Development"),
    // Server and storage infrastructure division
    SERVER("Server & Storage"),
    // Technical support division
    TECHNICAL("Technical Support");

    // Full name of the IT division
    private final String divisionName;

    // Constructor to assign full division name
    ITDivisionName(String division) {
        this.divisionName = division;
    }

    /**
     * Returns the full name of the IT division.
     * @return division name as a string
     */
    public String getDivisionName() {
        return divisionName;
    }
}
