/**
 * A subclass of room that holds the room object with a tool. 
 * @author Jose
 * @version 1.0
 */
public class RoomWithTools extends Room {
	/**
	 * Constructor that takes a number and calls the superclass constructor to instantiate object
	 * @param num
	 */
	public RoomWithTools(int num) {
		super(num);
	}
	/**
	 * Override of superclass hasTools method to return true
	 * @return true
	 */
	@Override
	public boolean hasTools() {
		return true;
	}
}
