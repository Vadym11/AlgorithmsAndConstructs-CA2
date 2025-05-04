package CA2.constants;

public enum PDDivisionNames {

    PM("Product Management"),
    UX("UX Research"),
    UI("UI Design");

    private final String divisionName;

    PDDivisionNames(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
