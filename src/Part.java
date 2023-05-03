// to work on
public class Part {

    private int number;
    private static final int LAST_PART = 100;

    public Part(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isLastPart() {
        return number == LAST_PART;
    }

    public boolean isNext(Part part) {
        return this.number == part.getNumber() + 1;
    }

}
