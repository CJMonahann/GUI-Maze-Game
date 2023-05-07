
public class Player {
	
	
	private int number;
	private Room currentRoom;
	private boolean toolsCollected;
	private Part lastMachinePartCollected;
	
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
		return toolsCollected; //FIXME: this may be wrong?
	}
	
	public String move(int direction) throws Exception{ //FIXME: this may be wrong as well?
		Room roomDirect = currentRoom.getDoor(direction);
		if(roomDirect != null) {
			currentRoom.setDoor(roomDirect.getNumber(), currentRoom);
			return currentRoom.printMessage();
		}
		else {
			return "No door in this direction";
		}
		
	}
	public String collectPart() {
		if(currentRoom.hasPart()) { //FIXME: wrong, another condition must be met
			return "should have a part";
		}
		else {
			return "This room doesn't have a part";
		}
	}
	public String collectTools() {
		if(currentRoom.hasTools() && !toolsCollected) {
			this.toolsCollected = true;
			return "You have successfully collected tools";
		}
		else if(toolsCollected) {
			return "Tools already collected";
		}
		else {
			return "Room does not have tools";
		}
	}
	public String build() {
		if(lastMachinePartCollected.isLastPart() && toolsCollected && currentRoom.isWorkshop()) {
			return "CONGRATULATIONS: you have won the game!";
		}
		else if (lastMachinePartCollected.isLastPart() && toolsCollected){
			return "You are not in the workshop";
		}
		else if(lastMachinePartCollected.isLastPart() && currentRoom.isWorkshop()) {
			return "You don't have the tools";
		}
		else {
			return "You don't have all the parts";
		}
	}
}
