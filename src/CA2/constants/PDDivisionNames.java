package CA2.constants;

public enum PDDivisionNames {

    PM("Product Management"),
    UX("UX Researchers"),
    UI("UI Designers");

    private final String divisionName;

    PDDivisionNames(String division) {
        this.divisionName = division;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
