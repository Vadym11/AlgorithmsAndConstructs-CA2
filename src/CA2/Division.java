package CA2;

/**
 * Represents a named division within a department.
 */
public class Division {

    // Name of the division
    private final String name;

    /**
     * Constructs a Division object with the given name.
     * @param name name of the division
     */
    public Division(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the division.
     * @return division name as a string
     */
    public String getName() {
        return name;
    }
}
