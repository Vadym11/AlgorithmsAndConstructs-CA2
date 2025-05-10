package CA2;

/**
 * Interface for classes that can provide a string representation of their divisions,
 * such as Manager and Department classes.
 */
public interface GetDivisions {

    /**
     * Returns the list of divisions as a formatted string.
     * @return pipe-separated string of division names
     */
    String getDivisionsAsString();
}
