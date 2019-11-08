import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabyrinthGUI extends JFrame {
	
	private JPanel boardPanel = new JPanel();
	private Tile[][] board = new Tile[9][9];
	//60x60 tiles 540x540 board
	
	private final int TILE_SIZE = 60;
	/*
	setIcon(new ImageIcon(new javax.swing.ImageIcon
			(getClass().getResource(String.format("Images/Phone%d.jpg", scoreIndex[index]))).getImage().getScaledInstance
			(250,170, Image.SCALE_SMOOTH)));
	new ImageIcon(new javax.swing.ImageIcon
			(getClass().getResource(String.format("Images/Phone%d.jpg", scoreIndex[index]))).getImage().getScaledInstance
			(250,170, Image.SCALE_SMOOTH))
	*/
	
	
	public LabyrinthGUI() {
		
		boardPanelSetup();
		frameSetup();
		
	}
	
	//This method sets up the panel that contains the board
	private void boardPanelSetup() {

		//Set the bounds of the board panel
		boardPanel.setLayout(null);
		boardPanel.setBounds(90, 90, TILE_SIZE * 9, TILE_SIZE * 9);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		loadBoard();

	}
	
	//This method loads the game board
	private void loadBoard(){
		
		//Placeholder image - yellowTile
		//Add arrows to the top and bottom of the board
		for(int col = 1; col < 8; col++) {
			
			if(col == 2 || col == 4 || col == 6) {
				
				JLabel arrow1 = new JLabel(Tile.yellowTile);
				arrow1.setBounds(col * TILE_SIZE, 0 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				boardPanel.add(arrow1);
				
				JLabel arrow2 = new JLabel(Tile.yellowTile);
				arrow2.setBounds(col * TILE_SIZE, 8 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				boardPanel.add(arrow2);
				
			}
			
		}
		
		//Add arrows on the sides of the board
		for(int row = 1; row < 8; row++) {
			
			if(row == 2 || row == 4 || row == 6) {
				
				JLabel arrow1 = new JLabel(Tile.yellowTile);
				arrow1.setBounds(0 * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				boardPanel.add(arrow1);
				
				JLabel arrow2 = new JLabel(Tile.yellowTile);
				arrow2.setBounds(8 * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				boardPanel.add(arrow2);
				
			}
			
		}
		
		//Add the rest of the tiles
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				if(row % 2 == 1) {
					if(col % 2 == 1) {
						board[col][row] = new Tile("I", Tile.blueTile);
					} else {
						board[col][row] = new Tile("L", Tile.greenTile);
					}
				} else {
					if(col % 2 == 1) {
						board[col][row] = new Tile("L", Tile.greenTile);
					} else {
						board[col][row] = new Tile("I", Tile.blueTile);
					}
				}
				
				board[col][row].setBounds(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				boardPanel.add(board[col][row]);
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
		//add(cardPanel);

		//Stop the program from running when the frame is closed, prevent the 
		//frame from being resized, and make the frame visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}
	
}
