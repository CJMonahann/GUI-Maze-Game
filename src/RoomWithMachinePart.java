/**
 * This class is for rooms in the game that have a machine part to be
 * collected by a player. 
 * @author CJ
 * @version 1.0
 */
public class RoomWithMachinePart extends Room{
	/**
	 * holds specific machine part this part-room object contains
	 */
	private Part machinePart;
	/**
	 * Class constructor that takes a room number and calls super class. Also
	 * takes a machine part of type Part and sets is for the instantiated object.
	 * @param roomNum
	 * @param machinePart
	 */
	public RoomWithMachinePart(int roomNum, Part machinePart) {
		super(roomNum);
		this.machinePart = machinePart;
	}
	/**
	 * Returns the machine part held in the part-room
	 * @return machinePart
	 */
	public Part getPart() {
		return this.machinePart;
	}
	/**
	 * Checks the last part collected by a Player to see if this room's
	 * current machine part is the next to be collected. Returns this
	 * rooms part if it is next to be collected, else it returns null.
	 * @param obj
	 * @return machine part if next to be collected, else returns null
	 */
	public Part collectPart(Player obj) {
		Part currPart= obj.getLastPartCollected();
		if(machinePart.isNext(currPart)) {
			return this.machinePart;
		}
		else {
			return null;
		}
	}
	/**
	 * Overrides super class hasPart method to return true. Denotes
	 * that this is a machine part room.
	 */
	@Override
	public boolean hasPart() {
		return true;
	}
	/**
	 * Overrides super class helpMessage method as per instruction in 
	 * assignment PDF. Tells player which room they're currently in.
	 */
	@Override
	public String helpMessage() {
		return "You are in room" + this.getNumber();
	}
}
