package CA2.constants;

public enum MenuOption {
    SORT(1,"1. SORT"),
    SEARCH(2, "2. SEARCH"),
    ADD(3, "3. ADD RECORDS"),
    EXIT(4, "4. EXIT");

    private final int code;
    private final String description;

    MenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromCode(int code) {
        for (MenuOption option : MenuOption.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
}
