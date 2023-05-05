
public class Player {
	
	@SuppressWarnings("unused")
	private int number;
	private Room currentRoom;
	private boolean toolsCollected;
	private Part lastMachinePartCollected;
	
	public Player(int number, Room room) {
		this.number = number;
		this.currentRoom = room;
		this.toolsCollected = false;
	}
	
	public int getCurrentRoom() {
		return this.currentRoom.getNumber();
	}
	public int getLastPartCollected() {
		return this.lastMachinePartCollected.getNumber();
	}
	public boolean hasTools() {
		return toolsCollected; //FIXME: this may be wrong?
	}
	
	public void move(int direction) throws Exception{ //FIXME: this may be wrong as well?
		if(currentRoom.getDoor(direction) != null) {
			currentRoom.setDoor(direction, currentRoom);
			currentRoom.printMessage();
		}
		else {
			System.out.print("No door in this direction");
		}
	}
	public void collectPart() {
		if(currentRoom.hasPart() && lastMachinePartCollected.isNext(lastMachinePartCollected)) { //FIXME: wrong, another condition must be met
			System.out.print("FIXME: needs to be a new part");
		}
	}
	public void collectTools() {
		if(currentRoom.hasTools() && !toolsCollected) {
			this.toolsCollected = true;
			System.out.print("You have successfully collected tools");
		}
		else if(toolsCollected) {
			System.out.print("Tools already collected");
		}
		else {
			System.out.print("Room does not have tools");
		}
	}
	public void build() {
		if(lastMachinePartCollected.isLastPart() && toolsCollected && currentRoom.isWorkshop()) {
			System.out.print("CONGRATULATIONS: you have won the game!");
		}
		else if (lastMachinePartCollected.isLastPart() && toolsCollected && !currentRoom.isWorkshop()){
			System.out.print("You are not in the workshop");
		}
		else if(lastMachinePartCollected.isLastPart() && !toolsCollected && currentRoom.isWorkshop()) {
			System.out.print("You don't have the tools");
		}
		else {
			System.out.print("You don't have all the parts");
		}
	}
}
