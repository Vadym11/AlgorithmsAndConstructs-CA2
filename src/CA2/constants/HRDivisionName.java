package CA2.constants;

public enum HRDivisionName {

    RECR("Recruitment"),
    PEOPLE_OP("People Operations"),
    ADMIN("Administration");

    private final String divisionName;

    HRDivisionName(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
