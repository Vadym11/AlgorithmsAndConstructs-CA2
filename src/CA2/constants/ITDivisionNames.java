package CA2.constants;

public enum ITDivisionNames {

    NETWORK("Network Division"),
    SERVER("Server & Storage"),
    TECHNICAL("Technical Support");

    private final String divisionName;

    ITDivisionNames(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
