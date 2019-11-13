import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LabyrinthGUI extends JFrame implements ActionListener{
	
	private JLayeredPane boardPanel = new JLayeredPane();
	private Board board = new Board();
	//60x60 tiles 540x540 board
	
	private JPanel cardPanel = new JPanel();
	
	private JLabel player1Label = new JLabel(Assets.treasureTileBat[0]);
	private JLabel player2Label = new JLabel(Assets.p2);
	private JLabel player3Label = new JLabel(Assets.p3);
	private JLabel player4Label = new JLabel(Assets.p4);
	
	private JButton rotateButton = new JButton("Rotate");
	private JButton confirmButton = new JButton("Confirm");
	
	public LabyrinthGUI() {
		
		buttonSetup();
		cardPanelSetup();
		boardPanelSetup();
		placePlayer();
		frameSetup();
		
	}
	
	//This method places the player on the board
	private void placePlayer() {
		player1Label.setBounds(75, 75, 30, 30);
		boardPanel.add(player1Label, new Integer(2));
	}

	//This method sets up the buttons used to rotate and place
	private void buttonSetup() {
		
		rotateButton.setBounds(650, 540, 120, 60);
		rotateButton.addActionListener(this);
		add(rotateButton);
		
		confirmButton.setBounds(1070, 540, 120, 60);
		confirmButton.addActionListener(this);
		add(confirmButton);
		
	}

	//This method sets up the panel that contains the cards
	private void cardPanelSetup() {

		//Set the bounds of the card panel
		cardPanel.setLayout(null);
		cardPanel.setBounds(650, 90, Tile.TILE_SIZE * 9, 400);
		cardPanel.setBorder(BorderFactory.createLineBorder(Color.black));

	}
	
	//This method sets up the panel that contains the board
	private void boardPanelSetup() {
		
		//Set the bounds of the board panel
		boardPanel.setLayout(null);
		boardPanel.setBounds(90, 90, Tile.TILE_SIZE * 9, Tile.TILE_SIZE * 9);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Setup the layered pane
		
		//Load the board
		loadBoard();

	}
	
	//This method loads the game board
	private void loadBoard(){
		
		//Fill the board
		board.fillBoard();
		
		//Placeholder image - yellowTile
		//Add arrows to the top and bottom of the board
		for(int col = 1; col < 8; col++) {
			
			if(col == 2 || col == 4 || col == 6) {
				
				JButton arrow1 = new JButton(Assets.yellowTile);
				arrow1.setBounds(col * Tile.TILE_SIZE, 0 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				arrow1.addActionListener(this);
				boardPanel.add(arrow1, new Integer(1));
				
				JButton arrow2 = new JButton(Assets.yellowTile);
				arrow2.setBounds(col * Tile.TILE_SIZE, 8 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				arrow2.addActionListener(this);
				boardPanel.add(arrow2, new Integer(1));
				
			}
			
		}
		
		//Add arrows on the sides of the board
		for(int row = 1; row < 8; row++) {
			
			if(row == 2 || row == 4 || row == 6) {
				
				JButton arrow1 = new JButton(Assets.yellowTile);
				arrow1.setBounds(0 * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				arrow1.addActionListener(this);
				boardPanel.add(arrow1, new Integer(1));
				
				JButton arrow2 = new JButton(Assets.yellowTile);
				arrow2.setBounds(8 * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				arrow2.addActionListener(this);
				boardPanel.add(arrow2, new Integer(1));
				
			}
			
		}
		
		//Add the rest of the tiles
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				board.getBoard()[col][row].addActionListener(this);
				boardPanel.add(board.getBoard()[col][row], new Integer(1));
			}
		}
		
	}
	
	//This method sets up the frame
	private void frameSetup() {

		//Set the title and frame size
		setTitle("Labyrinth");
		setSize(1280, 720);
		setLayout(null);
		
		//Add the panels
		add(boardPanel);
		add(cardPanel);

		//Stop the program from running when the frame is closed, prevent the 
		//frame from being resized, and make the frame visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				if(e.getSource() == board.getBoard()[row][col]) {
					player1Label.setLocation(board.getBoard()[row][col].getX() + 
							15, board.getBoard()[row][col].getY() + 15);
					board.getBoard()[row][col].setIcon(Assets.whiteTile);
					
				}
			}
		}
		
	}
	
}
