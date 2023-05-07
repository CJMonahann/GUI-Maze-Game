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

public class Maze extends Application {
	
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
	
	@Override
	public void start(Stage appStage) {
		//create grid pane and style
		GridPane pane = new GridPane();
		Insets padding = new Insets(30,30,30,30);
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
		playerText[0] = new TextField("Welcome 1");
		playerText[0].setEditable(false);
		playerText[1] = new TextField("Welcome 2");
		playerText[1].setEditable(false);
		Label player1 = new Label("PLAYER 1");
		Label player2 = new Label("PLAYER 2");
		Button restart = new Button("Restart");
		//add all button arrays into an array
		Button[][] allBttns = {Up,Left,Right,Down,Part,Tools,Build,Help};
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
		//build button event handler
		
		try {
			Game mazeGame = new Game();
			mazeGame.InitGame();
			appStage.setScene(scene);
			appStage.setTitle("Maze Game");
			appStage.show();
			
			class UpHandler implements EventHandler<ActionEvent> { //FIXME: this doesn't work
				@Override
				public void handle(ActionEvent event) {
					
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					try {
						playerText[numPlayer].setText(currPlayer.move(Direction.up));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					changeStatus(mazeGame,allBttns);
					
				}
			}
			class PartHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.collectPart());
					changeStatus(mazeGame,allBttns);
					
				}
			}
			class ToolsHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.collectTools());
					changeStatus(mazeGame,allBttns);
					
				}
			}
			class BuildHandler implements EventHandler<ActionEvent> { //FIXME: needs to be fixed
				@Override
				public void handle(ActionEvent event) {
					
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.build());
					changeStatus(mazeGame,allBttns);
					
				}
			}
			class HelpHandler implements EventHandler<ActionEvent> {
				@Override
				public void handle(ActionEvent event) {
					
					Player currPlayer = mazeGame.getCurrentPlayer();
					int numPlayer = currPlayer.getPlayerNum() - 1;
					playerText[numPlayer].setText(currPlayer.getCurrentRoom().helpMessage());
				}
			}
			
			//attach all handlers to buttons
			for(int player = 0; player <= 1; player++) {
				Up[player].setOnAction(new UpHandler());
				Part[player].setOnAction(new PartHandler());
				Tools[player].setOnAction(new ToolsHandler());
				Build[player].setOnAction(new BuildHandler());
				Help[player].setOnAction(new HelpHandler());
			}
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String[] args) {
		launch(args); //launch the application
	}
}
