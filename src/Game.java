/**
 * This class holds the whole game and its components (the two players and 10 rooms). 
 * This class should be instantiated in the main application class 
 * @author ADD YOUR NAME
 */

public class Game {
	private Player[] players;
	private Room[] rooms;
	private Player currentPlayer;

	   /**
	    * Assuming that Rooms has been initialized in the constructor 
	    * to hold 10 objects of type Room or a subclass of Room, this method 
	    * sets up the doors between the rooms, as described in the map
	    * @return void
	    * @throws Exception if inconsistencies found by setDoor
	    */
	
	private void setUpDoors() throws Exception {
		rooms[0].setDoor(Direction.up, rooms[3]);
		rooms[0].setDoor(Direction.left, rooms[8]);
		
		rooms[1].setDoor(Direction.up, rooms[4]);
		rooms[1].setDoor(Direction.down, rooms[7]);
		rooms[1].setDoor(Direction.right, rooms[9]);

		rooms[2].setDoor(Direction.down, rooms[8]);

		rooms[3].setDoor(Direction.down, rooms[0]);
		rooms[3].setDoor(Direction.right, rooms[4]);

		rooms[4].setDoor(Direction.down, rooms[1]);
		rooms[4].setDoor(Direction.right, rooms[5]);
		rooms[4].setDoor(Direction.left, rooms[3]);

		rooms[5].setDoor(Direction.down, rooms[9]);
		rooms[5].setDoor(Direction.left, rooms[4]);

		rooms[6].setDoor(Direction.up, rooms[8]);
		rooms[6].setDoor(Direction.right, rooms[7]);

		rooms[7].setDoor(Direction.up, rooms[1]);
		rooms[7].setDoor(Direction.left, rooms[6]);

		rooms[8].setDoor(Direction.up, rooms[2]);
		rooms[8].setDoor(Direction.down, rooms[6]);
		rooms[8].setDoor(Direction.right, rooms[0]);

		rooms[9].setDoor(Direction.up, rooms[5]);
		rooms[9].setDoor(Direction.left, rooms[1]);

	}	

}

