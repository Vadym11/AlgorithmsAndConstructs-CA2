package CA2;

/**
 * Enum representing the main menu options for user interaction.
 * Each option has a numeric code and a display description.
 */
public enum MenuOption {

    // Option to sort records
    SORT(1, "1. SORT"),
    // Option to search records
    SEARCH(2, "2. SEARCH"),
    // Option to add new records
    ADD(3, "3. ADD RECORDS"),
    // Option to exit the application
    EXIT(4, "4. EXIT");

    /** Numeric identifier for the menu option */
    private final int code;

    /** String label used for displaying the option */
    private final String description;

    /**
     * Constructor to assign code and description to each enum constant.
     * @param code numeric code representing the option
     * @param description text displayed to the user
     */
    MenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Returns the numeric code of the menu option.
     * @return numeric code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the string description of the menu option.
     * @return text description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the MenuOption enum matching the given code.
     * @param code numeric code to look up
     * @return matching MenuOption, or null if not found
     */
    public static MenuOption fromCode(int code) {
        for (MenuOption option : MenuOption.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null; // Invalid code
    }
}
