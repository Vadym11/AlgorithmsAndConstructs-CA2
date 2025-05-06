package CA2.constants;

public enum PDDivisionName {

    PM("Product Management"),
    UX("UX Research"),
    UI("UI Design");

    private final String divisionName;

    PDDivisionName(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
