/**
 * This class is for the workshop room in which the player will create the 
 * machine in the game.
 * @author CJ
 * @version 1.0
 */
public class Workshop extends Room{
	/**
	 * Class constructor that takes a room number and calls super class
	 * constructor to instantiate workshop objects.
	 * @param roomNum
	 */
	public Workshop(int roomNum) {
		super(roomNum);
	}
	/**
	 * Returns boolean variable "true" value to denote that player is in
	 * the workshop room. Override from Room superclass.
	 * @return true
	 */
	@Override
	public boolean isWorkshop() {
		return true;
	}
}
