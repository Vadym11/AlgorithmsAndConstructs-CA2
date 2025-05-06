package CA2.constants;

public enum ITDivisionName {

    NETWORK("Development"),
    SERVER("Server & Storage"),
    TECHNICAL("Technical Support");

    private final String divisionName;

    ITDivisionName(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
