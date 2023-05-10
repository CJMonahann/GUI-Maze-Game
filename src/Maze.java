import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class represents the GUI for the two-player Maze game.
 * @author CJ and Jose
 * @version 1.0
 */
public class Maze extends Application {
	/**
	 * This method changes the status of the game by switching the
	 * current player and enabling their buttons.
	 * @param game   the game being played
	 * @param button the array of buttons for each player
	 */
	public void changeStatus(Game game, Button[][] button) {
		Player currPlayer = game.getCurrentPlayer();
		int numPlayer = currPlayer.getPlayerNum() - 1;
		
		for(int i = 0; i < button.length; i++) {
			button[i][numPlayer].setDisable(true);
		}
		game.switchPlayer();
		currPlayer = game.getCurrentPlayer();
		numPlayer = currPlayer.getPlayerNum() - 1;
		
		for(int i = 0; i < button.length; i++) {
			button[i][numPlayer].setDisable(false);
		}
	}
	/**
	 * This method disables all buttons on the interface.
	 * @param button the array of buttons for each player
	 */
	public void disableAll(Button[][] button) {
		for(int i = 0; i < button.length; i++) {
			for(int j = 0; j < button[i].length; j++) {
				button[i][j].setDisable(true);
			}
		}
	}
	/**
	 * This method creates the graphical interface for the maze game using a
	 * GridPane layout and various elements.
	 * @param appStage the primary stage for the application
	 */
	@Override
	public void start(Stage appStage) {
		//create grid pane and style
		GridPane pane = new GridPane();
		Insets padding = new Insets(50,50,50,50);
		pane.setPadding(padding);
		pane.setVgap(10.0);
		pane.setHgap(2.0);
		Scene scene = new Scene(pane);
		//create arrays to hold various elements
		Button[] Up = new Button[2];
		Button[] Left = new Button[2];
		Button[] Right = new Button[2];
		Button[] Down = new Button[2];
		Button[] Part = new Button[2];
		Button[] Tools = new Button[2];
		Button[] Build = new Button[2];
		Button[] Help = new Button[2];
		TextField[] playerText  = new TextField[2];
		//create elements and put them into their arrays
		Up[0] = new Button("Up");
		Up[1] = new Button("Up");
		Left[0] = new Button("Left");
		Left[1] = new Button("Left");
		Right[0] = new Button("Right");
		Right[1] = new Button("Right");
		Down[0] = new Button("Down");
		Down[1] = new Button("Down");
		Part[0] = new Button("Collect Part");
		Part[1] = new Button("Collect Part");
		Tools[0] = new Button("Collect Tools");
		Tools[1] = new Button("Collect Tools");
		Build[0] = new Button("Build Machine");
		Build[1] = new Button("Build Machine");
		Help[0] = new Button("Help! I am lost!!");
		Help[1] = new Button("Help! I am lost!!");
		playerText[0] = new TextField("Starting in Room 1");
		playerText[0].setEditable(false);
		playerText[1] = new TextField("Starting in Room 1");
		playerText[1].setEditable(false);
		Label player1 = new Label("PLAYER 1");
		Label player2 = new Label("PLAYER 2");
		Button restart = new Button("Restart");
		//add all button arrays into an array
		Button[][] allBttns = {Up,Left,Right,Down,Part,Tools,Build,Help};
		//make all buttons the same width
		for(int i = 0; i < allBttns.length; i++) {
			for(int j = 0; j < allBttns[i].length; j++) {
				allBttns[i][j].setPrefWidth(120.0);
			}
		}
		restart.setPrefWidth(120.0);
		//add first player's elements to grid pane
		pane.add(player1, 1, 0);
		pane.add(Up[0], 1, 1);
		pane.add(Left[0], 0, 2);
		pane.add(Right[0], 2, 2);
		pane.add(Down[0], 1, 3);
		pane.add(Part[0], 0, 4);
		pane.add(Tools[0], 1, 4);
		pane.add(Build[0], 2, 4);
		pane.add(playerText[0], 0, 5);
		GridPane.setColumnSpan(playerText[0], 3);
		pane.add(Help[0], 1, 6);
		//add second player's elements to grid pane
		pane.add(player2, 5, 0);
		pane.add(Up[1], 5, 1);
		pane.add(Left[1], 4, 2);
		pane.add(Right[1], 6, 2);
		pane.add(Down[1], 5, 3);
		pane.add(Part[1], 4, 4);
		pane.add(Tools[1], 5, 4);
		pane.add(Build[1], 6, 4);
		pane.add(playerText[1], 4, 5);
		GridPane.setColumnSpan(playerText[1], 3);
		pane.add(Help[1], 5, 6);
		pane.add(restart, 3, 6);
		//to start the game, disable player 2 controls (Player 1 goes first)
		for(int i = 0; i < allBttns.length; i++) {
			allBttns[i][1].setDisable(true);
		}
		
		try {
			Game mazeGame = new Game();
			mazeGame.InitGame();
			appStage.setScene(scene);
			appStage.setTitle("Maze Game");
			appStage.show();
			/**
			 * This class handles the action event when the Up button is pressed.
			 */
			class UpHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					try {
						playerText[numPlayer].setText(currPlayer.move(Direction.up));
					} 
					catch (Exception e) {
						e.getMessage();
					}
					changeStatus(mazeGame,allBttns);
				}
			}
			/**
			 * This class handles the action event when the Right button is pressed.
			 */
			class RightHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					try {
						playerText[numPlayer].setText(currPlayer.move(Direction.right));
					} 
					catch (Exception e) {
						e.getMessage();
					}
					changeStatus(mazeGame,allBttns);
				}
			}
			/**
			 * This class handles the action event when the Down button is pressed.
			 */
			class DownHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					try {
						playerText[numPlayer].setText(currPlayer.move(Direction.down));
					} 
					catch (Exception e) {
						e.getMessage();
					}
					changeStatus(mazeGame,allBttns);
				}
			}
			/**
			 * This class handles the action event when the Left button is pressed.
			 */
			class LeftHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					try {
						playerText[numPlayer].setText(currPlayer.move(Direction.left));
					} 
					catch (Exception e) {
						e.getMessage();
					}
					changeStatus(mazeGame,allBttns);
				}
			}
			/**
			 * This class handles the action event when the Part button is pressed.
			 */
			class PartHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.collectPart());
					changeStatus(mazeGame,allBttns);
				}
			}
			/**
			 * This class handles the action event when the Tools button is pressed.
			 */
			class ToolsHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.collectTools());
					changeStatus(mazeGame,allBttns);
				}
			}
			/**
			 * This class handles the action event when the Build button is pressed.
			 */
			class BuildHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.build());
					if(Player.endGame) { //event in which true, all buttons will be disabled
						disableAll(allBttns);
					}
					else {
						changeStatus(mazeGame,allBttns);
					}
				}
			}
			/**
			 * This class handles the action event when the Help button is pressed.
			 */
			class HelpHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.getCurrentRoom().helpMessage());
				}
			}
			/**
			 * This class handles the action event when the Restart button is pressed.
			 */
			class RestartHandler implements EventHandler<ActionEvent> { //FIXME: needs work
				// Boolean flag used to track if the button is clicked.
				private boolean clickedOnce = false;
				// Define the timeout period for the restart confirmation.
				// Delete this method if you do not want restart confirmation.
				private Timeline resetTimeline = new Timeline(
						new KeyFrame(Duration.seconds(5), e -> {
							((Button) e.getSource()).setText("Restart");
							clickedOnce = false;
						}));

				@Override
				public void handle(ActionEvent event) {
					//
					if (!clickedOnce) {
						// Confirmation of restarting the game.
						((Button) event.getSource()).setText("Are you sure?");
						clickedOnce = true;

						// Confirmation timeout if the button has not been pressed.
						// Delete this method if you do not want restart confirmation.
						resetTimeline.stop();
						resetTimeline.getKeyFrames().setAll(new KeyFrame(Duration.seconds(5), e -> {
							((Button) event.getSource()).setText("Restart");
							clickedOnce = false;
						}));
						resetTimeline.play();

					} else {
						// Restarts the game
						start(appStage);
						Player.endGame = false; //change back to false so that disableAll isn't falsely called
						// Change the text of the button back to "Restart"
						((Button) event.getSource()).setText("Restart");
						clickedOnce = false;

					}
				}
			}
			//attach all handlers to buttons
			for(int player = 0; player <= 1; player++) {
				Up[player].setOnAction(new UpHandler());
				Right[player].setOnAction(new RightHandler());
				Down[player].setOnAction(new DownHandler());
				Left[player].setOnAction(new LeftHandler());
				Part[player].setOnAction(new PartHandler());
				Tools[player].setOnAction(new ToolsHandler());
				Build[player].setOnAction(new BuildHandler());
				Help[player].setOnAction(new HelpHandler());
			}
			restart.setOnAction(new RestartHandler());
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * The main method which is the entry point of the GUI application.
	 * Launches the application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args); //launch the application
	}
}
