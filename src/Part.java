/**
 * A class representing a part in sequence to other parts.
 * @author Jose
 * @version 1.0
 */
public class Part {

    private int number;
    public static final int LAST_PART = 4;
    /**
     * Constructs a new part object with the given part number.
     * @param number the part number
     */
    public Part(int number) {
        this.number = number;
    }
    /**
     * Returns the part number.
     * @return int the part number
     */
    public int getNumber() {
        return number;
    }
    /**
     * Returns true if the part is the last part in the
     * sequence. Otherwise, false.
     * @return boolean
     */
    public boolean isLastPart() {
        return number == LAST_PART;
    }
    /**
     * Returns true if the part is the next part is after the
     * given part. Returns false if it is not after the
     * specified part.
     * @param part
     * @return boolean
     */
    public boolean isNext(Part part) {
        return this.number == part.getNumber() + 1;
    }

}
