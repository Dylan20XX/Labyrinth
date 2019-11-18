import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * This class displays the title screen for the game.
 * It allows the player to select to start a new game or load a game,
 * and start playing the game.
 */

public class LabyrinthTitleScreen extends JFrame implements ActionListener {

	//Frame components
	private JPanel titleScreenPanel = new JPanel();
	private JPanel savedGamePanel = new JPanel();
	private JLabel titleLabel = new JLabel("LABYRINTH");
	private JLabel titleImageLabel = new JLabel(Assets.titleScreen);
	private JLabel savedGamesLabel = new JLabel("SAVED GAMES");
	private JButton playButton = new JButton("Play");
	private JButton startButton = new JButton("Start");
	private JButton deleteButton = new JButton("Delete");
	private JButton savedGameButton = new JButton("Saved Games");
	private JButton backButton = new JButton("Back");
	private JPanel savedGameButtonPanel = new JPanel();
	private JScrollPane savedGamesScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private ArrayList<JButton> savedGames= new ArrayList<JButton>();
	
	private ArrayList<File> savedGameFiles = new ArrayList<File>();

	private int state = 0;//Stores which panel is active
	private String selectedGame;

	//Constructor method
	public LabyrinthTitleScreen(){

		titleScreenPanelSetup();
		savedGamePanelSetup();
		buttonSetup();
		frameSetup();
		setupFileList();

	}

	//This method sets up the title screen panel
	private void titleScreenPanelSetup(){

		//Setup the panel
		titleScreenPanel.setBounds(0,0,1280,720);
		titleScreenPanel.setLayout(null);
		titleScreenPanel.setBackground(new Color(191,231,247));

		//Setup the label
		titleLabel.setBounds(375, 30, 700, 120);
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 105));
		titleScreenPanel.add(titleLabel);
		
		//titleImageLabel.setBounds(650, 100, 500, 500);
		//titleScreenPanel.add(titleImageLabel);

		//Revalidate and repaint the panel so the image labels appear
		titleScreenPanel.revalidate();
		titleScreenPanel.repaint();

	}

	//This method sets up the highscore panel
	private void savedGamePanelSetup() {

		//Setup the panel
		savedGamePanel.setBounds(0,0,1280,720);
		savedGamePanel.setLayout(null);
		savedGamePanel.setBackground(new Color(191,231,247));
		savedGamePanel.setVisible(false); //Initially hide the highscore panel 

		//Setup the label
		savedGamesLabel.setBounds(515, 30, 250, 50);
		savedGamesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		savedGamePanel.add(savedGamesLabel);
		
		//Setup the button panel
		savedGameButtonPanel.setLayout(new BoxLayout(savedGameButtonPanel, BoxLayout.Y_AXIS));
		//savedGameButtonPanel.setBounds(220, 100 ,250, 550);
		savedGameButtonPanel.setBackground(Color.DARK_GRAY);
				
		//Add the scroll pane
		savedGamesScrollPane = new JScrollPane(savedGameButtonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		savedGamesScrollPane.setBounds(220,100,400,550);
		savedGamePanel.add(savedGamesScrollPane);

	}
	
	private void setupFileList() {
		
		//reset the array lists
		savedGameFiles.clear();
		
		//setup the world select buttons
		File files = new File("SavedGames");
		System.out.println(files.listFiles().length);
		
		for(int i = 0; i < files.listFiles().length; i++) {
			savedGameFiles.add(files.listFiles()[i]);
			savedGames.add(new JButton(files.listFiles()[i].getName()));
			savedGames.get(i).addActionListener(this);
			savedGames.get(i).setSize(400, 150);
			savedGames.get(i).setMaximumSize(savedGames.get(i).getSize());
			savedGames.get(i).setMinimumSize(savedGames.get(i).getSize());
			savedGames.get(i).setPreferredSize(savedGames.get(i).getSize());
			savedGames.get(i).setFont(new Font("Ultra", Font.BOLD, 36));
			savedGameButtonPanel.add(savedGames.get(i));
		}

	}

	//This method sets up the buttons for each panel
	private void buttonSetup() {

		//Setup buttons for title screen
		playButton.setBounds(500, 200, 300, 100);
		playButton.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
		playButton.setBackground(Color.ORANGE);
		playButton.addActionListener(this);
		titleScreenPanel.add(playButton);

		savedGameButton.setBounds(500, 350, 300, 100);
		savedGameButton.setBackground(Color.ORANGE);
		savedGameButton.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
		savedGameButton.addActionListener(this);
		titleScreenPanel.add(savedGameButton);
		
		startButton.setBounds(700, 100, 250, 150);
		startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
		startButton.addActionListener(this);
		savedGamePanel.add(startButton);

		deleteButton.setBounds(700, 350, 250, 150);
		deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
		deleteButton.addActionListener(this);
		savedGamePanel.add(deleteButton);
		
		backButton.setBounds(1000, 550, 200, 100);
		backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
		backButton.addActionListener(this);
		savedGamePanel.add(backButton);

	}

	//This method sets up the frame
	private void frameSetup(){

		add(titleScreenPanel);
		add(savedGamePanel);
		setLayout(null);
		setTitle("Labyrinth");
		setSize(1280,720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	//This method updates the state of the frame by changing which panels are visible and hidden
	public void updateState(){

		if(state == 0) {
			titleScreenPanel.setVisible(true);
			savedGamePanel.setVisible(false);
		}else if(state == 1) {
			titleScreenPanel.setVisible(false);
			savedGamePanel.setVisible(true);
		}

	}

	//Getters and Setters
	public void setState(int state){
		this.state = state;
	}

	public int getState(){
		return state;
	}

	//This method detects when a button is pressed
	@Override
	public void actionPerformed(ActionEvent event) {

		//Enter the settings screen if the play button is pressed
		if(event.getSource() == playButton) {

			new LabyrinthGUI();
			this.dispose();

		}


		//Enter the instructions screen if the instructions button is pressed
		if(event.getSource() == savedGameButton) {

			setState(1);
			updateState();

		}

		//Return to the title screen when one of the back buttons is pressed
		if(event.getSource() == backButton) {
			
			selectedGame = null;
			
			setState(0);
			updateState();
		}
		
		for(int i = 0; i < savedGames.size(); i++) {
			if(event.getSource() == savedGames.get(i)) {
				
				for(int j = 0; j < savedGames.size(); j++) {
					savedGames.get(j).setBackground(new JButton().getBackground());
				}
				
				savedGames.get(i).setBackground(Color.YELLOW);
				selectedGame = savedGames.get(i).getText();
				
			}
		}
		
		if(selectedGame != null) {
			
			//Load or delete the selected game when the appropriate button is pressed
			if(event.getSource() == startButton) {
				
				new LabyrinthGUI(selectedGame);
				this.dispose();
				
			} else if(event.getSource() == deleteButton) {
				
				for(int i = 0; i < savedGames.size(); i++) {
					if(savedGames.get(i).getText().equals(selectedGame)) {
						savedGameButtonPanel.remove(savedGames.remove(i));
						savedGameButtonPanel.repaint();
						savedGameButtonPanel.revalidate();
					}	
				}
				
				deleteGame();
				
			}
			
		}
		
	}
	
	private void deleteGame() {
		
		String gameData = String.format("SavedGames/%s", selectedGame);
		File gameDataFilepath = new File(gameData);

		if(gameDataFilepath.exists()) {
			gameDataFilepath.delete();
		}
		
		selectedGame = null;
		
	}

}
