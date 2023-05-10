/**
 * A class representing the player in the Maze game. It contains
 * information about the player's number, current room, and
 * whether they have collected the necessary tools and machine parts
 * to build the machine or not in order to win the game.
 * @author CJ and Jose
 * @version 1.0
 */
public class Player {
	
	
	private int number;
	private Room currentRoom;
	private boolean toolsCollected;
	private Part lastMachinePartCollected = new Part(0); //initialize to irrelevant part so that it isn't null
	public static boolean endGame = false; //to signal if game should be ended during build event in class Maze
	
	public Player(int number, Room room) {
		this.number = number;
		this.currentRoom = room;
		this.toolsCollected = false;
	}
	/**
	 * Returns the room number the player is in.
	 * @return Room the current room
	 */
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	/**
	 * Returns the last machine part collected by the player.
	 * @return Part the last machine part collected
	 */
	public Part getLastPartCollected() {
		return this.lastMachinePartCollected;
	}
	/**
	 * Returns the player's number.
	 * @return the player's number
	 */
	public int getPlayerNum() {
		return this.number;
	}
	/**
	 * Returns true if the player has collected the tools. Otherwise, false.
	 * @return boolean
	 */
	public boolean hasTools() {
		return toolsCollected;
	}
	/**
	 * "Moves" the player a given direction by indexing their current room for the 
	 * next room object in the rooms array of class Game.
	 * @param direction the direction to move
	 * @return String the message indicating the result of the move
	 * @throws Exception throws an Exception if the direction is invalid
	 */
	public String move(int direction) throws Exception{
		Room roomDirect = currentRoom.getDoor(direction);
		if(roomDirect != null) {
			currentRoom = roomDirect;
			return currentRoom.printMessage();
		}
		else {
			return "No door in this direction";
		}
		
	}
	/**
	 * Collects the machine part from the current room if it exists to store
	 * in the player's inventory. If the machine part has already been collected,
	 * or if the parts are not collected in order, it notifies the user.
	 * @return String
	 */
	public String collectPart() {
		if(!currentRoom.hasPart()) {
			return "This room does not have a part";
		}
		else {
			Part nextPart = ((RoomWithMachinePart)currentRoom).getPart();
			if(nextPart.getNumber() <= lastMachinePartCollected.getNumber()) {
				return "You've already collected this part";
			}
			else if(!nextPart.isNext(lastMachinePartCollected)) {
				return "The parts must be collected in order";
			}
			else {
				lastMachinePartCollected = nextPart;
				return "You've sucessfully collected the part!";
			}
		}
	}
	/**
	 * Collects the tools from the current room if it exists to store
	 * in the player's inventory. If the tools have already been collected,
	 * it notifies the user.
	 * @return String
	 */
	public String collectTools() {
		if(!currentRoom.hasTools()) {
			return "This room has no tools";
		}
		else {
			if(toolsCollected) {
				return "Tools already collected";
			}
			else {
				toolsCollected = true;
				return "You've sucessfully collected the tools";
			}
		}
	}
	/**
	 * Builds the machine with the collected parts and tools in the workshop.
	 * If the required tools/parts are not collected by the player, a message will
	 * be displayed indicating that the necessary tools/parts are absent.
	 * If the player is not present in the workshop, a message will appear
	 * indicating that they are not in the workshop. 
	 * Once the player collects all of the required tools and machine parts to be built, 
	 * the player wins, and the game will end.
	 * 
	 * @return String message indicating whether the build was successful or not.
	 */
	public String build() {
		if(!currentRoom.isWorkshop()) {
			return "You are not in the workshop";
		}
		else {
			if(!toolsCollected) {
				return "You don't have the tools";
			}
			else if(lastMachinePartCollected.getNumber() != Part.LAST_PART) {
				return "You don't have all the parts";
			}
			else {
				endGame = true; //signal that game should be ended. Boolean evaluated in class Maze
				return "Congratulations! You've won the game!";
			}
		}
	}
}
