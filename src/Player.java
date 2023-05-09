
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
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	public Part getLastPartCollected() {
		return this.lastMachinePartCollected;
	}
	public int getPlayerNum() {
		return this.number;
	}
	public boolean hasTools() {
		return toolsCollected;
	}
	
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
