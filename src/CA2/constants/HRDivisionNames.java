package CA2.constants;

public enum HRDivisionNames {

    RECR("Recruitment"),
    PEOPLE_OP("People Operations"),
    ADMIN("Administration");

    private final String divisionName;

    HRDivisionNames(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
