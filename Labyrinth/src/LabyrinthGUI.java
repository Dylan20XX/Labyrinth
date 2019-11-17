import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LabyrinthGUI extends JFrame implements ActionListener{
	
	private JLayeredPane boardPanel = new JLayeredPane();
	private Board board = new Board();
	private JLabel titleLabel = new JLabel("Labyrinth");
	//60x60 tiles 540x540 board
	
	private JPanel cardPanel = new JPanel();
	
	private Player[] players = new Player[4];
//	private Player player1 = new Player(Assets.tileBat[0]);
//	private Player player2 = new Player(Assets.p2);
//	private Player player3 = new Player(Assets.p3);
//	private Player player4 = new Player(Assets.p4);
	
	private JLabel player1CardHeading = new JLabel("P1");
	private JLabel player2CardHeading = new JLabel("P2");
	private JLabel player3CardHeading = new JLabel("P3");
	private JLabel player4CardHeading = new JLabel("P4");
	
	private JLabel[] cardOutlines = new JLabel[4];
	
	private Tile tileInHand;
	private JLabel tileInHandLabel = new JLabel();
	private int turn; //Determines whose turn it is
	private int phase; // 0 = time to place tile // 1 = time to move character
	private int lastPush = -1; //Holds the index of the last push button that was used
	private int selectedPush = -1; //Holds the index of the last push button that was used
	
	private JLabel turnLabel = new JLabel();
	private JLabel phaseLabel = new JLabel(); //Displays which phase of the turn the player is on (tile placement or movement)
	
	private JButton[] pushButton = new JButton[12];
	// Push button index layout
	//    0 1 2
	// 11       3
	// 10       4
	// 9        5
	//    8 7 6
	private JButton rotateButton = new JButton("Rotate");
	private JButton confirmButton = new JButton("Confirm");
	
	private int selectedRow = 0;
	private int selectedCol = 0;
	
	public LabyrinthGUI() {
		
		buttonSetup();
		cardPanelSetup();
		boardPanelSetup();
		placePlayer();
		frameSetup();
		//board.pathfind(1, 1);
		
		startTurn(0);
		
	}
	
	//This method places the player on the board
	private void placePlayer() {
		players[0].setBounds(75, 75, 30, 30);
		boardPanel.add(players[0], new Integer(3));
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
		
		player1CardHeading.setBounds(20, 35, 50, 30);
		player1CardHeading.setFont(new Font("Ultra", Font.BOLD, 36));
		cardPanel.add(player1CardHeading);
		
		player2CardHeading.setBounds(20, 135, 50, 30);
		player2CardHeading.setFont(new Font("Ultra", Font.BOLD, 36));
		cardPanel.add(player2CardHeading);
		
		player3CardHeading.setBounds(20, 235, 50, 30);
		player3CardHeading.setFont(new Font("Ultra", Font.BOLD, 36));
		cardPanel.add(player3CardHeading);
		
		player4CardHeading.setBounds(20, 335, 50, 30);
		player4CardHeading.setFont(new Font("Ultra", Font.BOLD, 36));
		cardPanel.add(player4CardHeading);
		
		players[0] = new Player(Assets.tileBat[0], 1, 1);
		players[1] = new Player(Assets.p2, 1, 7);
		players[2] = new Player(Assets.p3, 7, 1);
		players[3] = new Player(Assets.p4, 7, 7);
		
		for(int i = 0; i < 4; i++) {
			cardOutlines[i] = new JLabel(Assets.cardOutline);
			cardOutlines[i].setBounds(100, i * 100, 80, 100);
			cardPanel.add(cardOutlines[i]);
		}
		
		displayCards();

	}
	
	//This method displays the cards on card panel
	private void displayCards() {
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < players[i].getHand().size(); j++) {
				players[i].getHand().get(j).setBounds(100 + (j * 80), i * 100, 80, 100);
				cardPanel.add(players[i].getHand().get(j));
			}
		}

//		for(int i = 0; i < player1.getHand().size(); i++) {
//			player1.getHand().get(i).setBounds(100 + (i * 80), 0, 80, 100);
//			cardPanel.add(player1.getHand().get(i));
//		}
//		
//		for(int i = 0; i < player2.getHand().size(); i++) {
//			player2.getHand().get(i).setBounds(100 + (i * 80), 100, 80, 100);
//			cardPanel.add(player2.getHand().get(i));
//		}
//		
//		for(int i = 0; i < player3.getHand().size(); i++) {
//			player3.getHand().get(i).setBounds(100 + (i * 80), 200, 80, 100);
//			cardPanel.add(player3.getHand().get(i));
//		}
//		
//		for(int i = 0; i < player4.getHand().size(); i++) {
//			player4.getHand().get(i).setBounds(100 + (i * 80), 300, 80, 100);
//			cardPanel.add(player4.getHand().get(i));
//		}
		
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
		
		int buttonIndex = 0;
		//Placeholder image - yellowTile
		//Add arrows to the top and bottom of the board
		for(int col = 1; col < 8; col++) {
			
			if(col == 2 || col == 4 || col == 6) {
				
				pushButton[buttonIndex] = new JButton(Assets.yellowTile);
				pushButton[buttonIndex].setBounds(col * Tile.TILE_SIZE, 0 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				pushButton[buttonIndex].addActionListener(this);
				boardPanel.add(pushButton[buttonIndex], new Integer(1));
				
				pushButton[8 - buttonIndex] = new JButton(Assets.yellowTile);
				pushButton[8 - buttonIndex].setBounds(col * Tile.TILE_SIZE, 8 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				pushButton[8 - buttonIndex].addActionListener(this);
				boardPanel.add(pushButton[8 - buttonIndex], new Integer(1));
				
				buttonIndex++;
				
			}
			
		}
		
		//Add arrows on the sides of the board
		for(int row = 1; row < 8; row++) {
			
			if(row == 2 || row == 4 || row == 6) {

				pushButton[14 - buttonIndex] = new JButton(Assets.yellowTile);
				pushButton[14 - buttonIndex].setBounds(0 * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				pushButton[14 - buttonIndex].addActionListener(this);
				boardPanel.add(pushButton[14 - buttonIndex], new Integer(1));
				
				pushButton[buttonIndex] = new JButton(Assets.yellowTile);
				pushButton[buttonIndex].setBounds(8 * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				pushButton[buttonIndex].addActionListener(this);
				boardPanel.add(pushButton[buttonIndex], new Integer(1));
				
				buttonIndex++;
				
			}
			
		}
		
		//Add the rest of the tiles
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				board.getBoard()[row][col].addActionListener(this);
				boardPanel.add(board.getBoard()[row][col], new Integer(1));
				boardPanel.add(board.getHighlight()[row][col], new Integer(2));
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
		
		//Add the turn label
		turnLabel.setFont(new Font("Ultra", Font.BOLD, 30));
		turnLabel.setBounds(840, 35, 200, 40);
		add(turnLabel);
		
		//Add the tileInHand label
		tileInHandLabel.setBounds(870, 540, 100, 100);
		add(tileInHandLabel);
		
		//Add the tileInHand label
		phaseLabel.setFont(new Font("Ultra", Font.BOLD, 30));
		phaseLabel.setBounds(770, 495, 300, 40);
		phaseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(phaseLabel);
		
		//Add the title label
		titleLabel.setFont(new Font("Ultra", Font.BOLD, 36));
		titleLabel.setBounds(540, 15, 200, 40);
		add(titleLabel);

		//Stop the program from running when the frame is closed, prevent the 
		//frame from being resized, and make the frame visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}
	
	//This method starts the first turn of the game
	private void startTurn(int playerIndex) {
		
		turn = playerIndex;
		phase = 0;
		
		turnLabel.setText(String.format("P%d's Turn", playerIndex + 1));
		phaseLabel.setText("Place the tile");
		
		tileInHand = Tile.tileDeck.remove(0);
		
		tileInHandLabel.setIcon(new ImageIcon(((ImageIcon) tileInHand.getIcon()).
				getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
	}
	
	private void nextPhase() {
		
		if(phase == 0) {
			phase = 1;
			phaseLabel.setText("Move your character");
			board.pathfind(players[turn].getRow(), players[turn].getCol());
		} else if(phase == 1) {
			phase = 0;
			turn++;
			if(turn == 4)
				turn = 0;
			phaseLabel.setText("Place the tile");
			board.removeHighlight();
			turnLabel.setText(String.format("P%d's Turn", turn + 1));
		}
		
	}
	
	//This method moves the player and checks if they have collected a treasure
	private void movePlayer() {
		//Add animation to player later
		players[turn].setLocation(board.getBoard()[selectedRow][selectedCol].getX() + 
				15, board.getBoard()[selectedRow][selectedCol].getY() + 15);
		players[turn].setRow(selectedRow);
		players[turn].setCol(selectedCol);
		
		System.out.println("treasure needed = " + players[turn].getHand().get(0).getTreasure());
		System.out.println("treasure found = " + board.getBoard()[players[turn].getRow()][players[turn].getCol()].getTreasure());
		
		//Remove cards if treasure is collected
		if(players[turn].getHand().get(0).getTreasure().equalsIgnoreCase(
				board.getBoard()[players[turn].getRow()][players[turn].getCol()].getTreasure())) {
			
			cardPanel.remove(players[turn].getHand().remove(0));
			repaint();
			validate();
			
			displayCards();
			
			if(players[turn].getHand().isEmpty())
				playerVictory(turn + 1);
			
		}
	}
	
	//This method is to be used to update the player's location on the board
	//after a row or column of tiles is moved while the player is on the row or column
	private void updatePlayerLocation(int player) {
		players[player].setLocation(board.getBoard()[players[player].getRow()][players[player].getCol()].getX() + 
				15, board.getBoard()[players[player].getRow()][players[player].getCol()].getY() + 15);
	}
	
	private void playerVictory(int player) {
		System.out.printf("P%d Wins", player);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(phase == 1) {
			for(int row = 1; row < 8; row++) {
				for(int col = 1; col < 8; col++) {
					if(e.getSource() == board.getBoard()[row][col]) {
//						TEST PLAYER MOVEMENT
//						player1Label.setLocation(board.getBoard()[row][col].getX() + 
//								15, board.getBoard()[row][col].getY() + 15);
//						board.getBoard()[row][col].setIcon(Assets.whiteTile); 

//						TEST ROTATION AND PATHFINDING
//						if(board.getBoard()[row][col].getRotation() != 3)
//							board.getBoard()[row][col].setRotation(board.getBoard()[row][col].getRotation()+1);
//						else
//							board.getBoard()[row][col].setRotation(0);

//						if((row == 1 || row == 3 || row == 5 || row == 7) && (col == 1 || col == 3 || col == 5 || col == 7)) {
//							board.getBoard()[1][1] = new Tile("L", Assets.permenantTiles[0], 1);
//							board.getBoard()[1][3] = new Tile("T", Assets.permenantTiles[1], 0);
//							board.getBoard()[1][5] = new Tile("T", Assets.permenantTiles[2], 0);
//							board.getBoard()[1][7] = new Tile("L", Assets.permenantTiles[3], 2);
//
//							board.getBoard()[3][1] = new Tile("T", Assets.permenantTiles[4], 3);
//							board.getBoard()[3][3] = new Tile("T", Assets.permenantTiles[5], 3);
//							board.getBoard()[3][5] = new Tile("T", Assets.permenantTiles[6], 0);
//							board.getBoard()[3][7] = new Tile("T", Assets.permenantTiles[7], 1);
//
//							board.getBoard()[5][1] = new Tile("T", Assets.permenantTiles[8], 3);
//							board.getBoard()[5][3] = new Tile("T", Assets.permenantTiles[9], 2);
//							board.getBoard()[5][5] = new Tile("T", Assets.permenantTiles[10], 1);
//							board.getBoard()[5][7] = new Tile("T", Assets.permenantTiles[11], 1);
//
//							board.getBoard()[7][1] = new Tile("L", Assets.permenantTiles[12], 0);
//							board.getBoard()[7][3] = new Tile("T", Assets.permenantTiles[13], 2);
//							board.getBoard()[7][5] = new Tile("T", Assets.permenantTiles[14], 2);
//							board.getBoard()[7][7] = new Tile("L", Assets.permenantTiles[15], 3);
//							for(int y = 1; y < 8; y++) {
//								for(int x = 1; x < 8; x++) {
//									board.getBoard()[y][x].setNodeNum((y - 1) * 7 + x);
//								}
//							}
//						}
//
//						board.pathfind(1, 1);
						
						//Highlight available paths
						board.highlightTiles();

						if(board.getVis()[board.getBoard()[row][col].getNodeNum()]) {
							board.getHighlight()[row][col].setIcon(Assets.tileHighlightGreen);
							selectedRow = row;
							selectedCol = col;
						} else {
							board.getHighlight()[row][col].setIcon(Assets.tileHighlightRed);
							selectedRow = 0;
							selectedCol = 0;
						}
						
					}
				}
			}
		}
		
		
		//TEST ROW AND COL PUSH METHODS
//		for(int i = 0; i < 3; i++) {
//			if(e.getSource() == pushButton[i]) {
//				board.pushColDown((i + 1) * 2);
//			}
//		}
//		
//		for(int i = 3; i < 6; i++) {
//			if(e.getSource() == pushButton[i]) {
//				board.pushRowLeft((i - 2) * 2);
//			}
//		}
//		
//		for(int i = 6; i < 9; i++) {
//			if(e.getSource() == pushButton[i]) {
//				board.pushColUp((9 - i) * 2);
//			}
//		}
//		
//		for(int i = 9; i < 12; i++) {
//			if(e.getSource() == pushButton[i]) {
//				board.pushRowRight((12 - i) * 2);
//			}
//		}
		
		for(int i = 0; i < 12; i++) {
			if(e.getSource() == pushButton[i] && phase == 0) {
				selectedPush = i;
				for(int j = 0; j < 12; j++) {
					//Change the button image and revert all other button images to normal
					if(j == i) {
						pushButton[j].setIcon(Assets.tileHighlightBlue);
					} else {
						pushButton[j].setIcon(null);
					}
					
					//Change colour of the button opposite to the last row or column pushed
					if(j == lastPush)
						pushButton[j].setIcon(Assets.tileHighlightRed); 
				}
			}
		}
		
		//Allow the player to rotate the tile during the tile placement phase
		if(e.getSource() == rotateButton && phase == 0) {
			
			if(tileInHand.getRotation() != 3)
				tileInHand.setRotation(tileInHand.getRotation()+1);
			else
				tileInHand.setRotation(0);
			
			tileInHandLabel.setIcon(new ImageIcon(((ImageIcon) tileInHand.getIcon()).
					getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			
		}
		
		if(e.getSource() == confirmButton) {
			
			if(selectedPush >= 0 && selectedPush != lastPush && phase == 0) {
				
				//Detect which push button was pressed
				for(int i = 0; i < 3; i++) {
					if(selectedPush == i) {
						
						int col = (i + 1) * 2;
						
						board.getBoard()[0][col] = tileInHand;
						board.pushColDown(col);
						
						tileInHand.setType(board.getBoard()[8][col].getType());
						tileInHand.setHasTreasure(board.getBoard()[8][col].isHasTreasure());
						tileInHand.setTreasure(board.getBoard()[8][col].getTreasure());
						tileInHand.setImages(board.getBoard()[8][col].getImages());
						tileInHand.setRotation(board.getBoard()[8][col].getRotation());
						
						board.getBoard()[8][col] = new Tile();
						
						//Move a player if they are on the selected column
						for(int j = 0; j < 4; j++) {
							if(players[j].getCol() == col) {
								players[j].setRow(players[j].getRow() + 1);
								if(players[j].getRow() == 8)
									players[j].setRow(1);
								updatePlayerLocation(j);
							}
						}
						
					}
				}
				
				for(int i = 3; i < 6; i++) {
					if(selectedPush == i) {
						
						int row = (i - 2) * 2;
						
						board.getBoard()[row][8] = tileInHand;
						board.pushRowLeft(row);
						
						tileInHand.setType(board.getBoard()[row][0].getType());
						tileInHand.setHasTreasure(board.getBoard()[row][0].isHasTreasure());
						tileInHand.setTreasure(board.getBoard()[row][0].getTreasure());
						tileInHand.setImages(board.getBoard()[row][0].getImages());
						tileInHand.setRotation(board.getBoard()[row][0].getRotation());
						
						board.getBoard()[row][0] = new Tile();
						
						//Move a player if they are on the selected row
						for(int j = 0; j < 4; j++) {
							if(players[j].getRow() == row) {
								players[j].setCol(players[j].getCol() - 1);
								if(players[j].getCol() == 0)
									players[j].setCol(7);
								updatePlayerLocation(j);
							}
						}
						
					}
				}
				
				for(int i = 6; i < 9; i++) {
					if(selectedPush == i) {
						
						int col = (9 - i) * 2;
						
						board.getBoard()[8][col] = tileInHand;
						board.pushColUp(col);
						
						tileInHand.setType(board.getBoard()[0][col].getType());
						tileInHand.setHasTreasure(board.getBoard()[0][col].isHasTreasure());
						tileInHand.setTreasure(board.getBoard()[0][col].getTreasure());
						tileInHand.setImages(board.getBoard()[0][col].getImages());
						tileInHand.setRotation(board.getBoard()[0][col].getRotation());
						
						board.getBoard()[0][col] = new Tile();
						
						//Move a player if they are on the selected column
						for(int j = 0; j < 4; j++) {
							if(players[j].getCol() == col) {
								players[j].setRow(players[j].getRow() - 1);
								if(players[j].getRow() == 0)
									players[j].setRow(7);
								updatePlayerLocation(j);
							}
						}
						
					}
				}
				
				for(int i = 9; i < 12; i++) {
					if(selectedPush == i) {
						
						int row = (12 - i) * 2;
						
						board.getBoard()[row][0] = tileInHand;
						board.pushRowRight(row);
						
						tileInHand.setType(board.getBoard()[row][8].getType());
						tileInHand.setHasTreasure(board.getBoard()[row][8].isHasTreasure());
						tileInHand.setTreasure(board.getBoard()[row][8].getTreasure());
						tileInHand.setImages(board.getBoard()[row][8].getImages());
						tileInHand.setRotation(board.getBoard()[row][8].getRotation());
						
						board.getBoard()[row][8] = new Tile();
						
						//Move a player if they are on the selected row
						for(int j = 0; j < 4; j++) {
							if(players[j].getRow() == row) {
								players[j].setCol(players[j].getCol() + 1);
								if(players[j].getCol() == 8)
									players[j].setCol(1);
								updatePlayerLocation(j);
							}
						}
						
					}
				}
				
				//Update the label with the tile in hand image
				tileInHandLabel.setIcon(new ImageIcon(((ImageIcon) tileInHand.getIcon()).
						getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
				
				//return the push buttons to normal
				for(int j = 0; j < 12; j++) {
					//Change the button image and revert all other button images to normal
					pushButton[j].setIcon(null);
				}
				
				//Set last push variable and and reset selected push variable
				if(selectedPush == 0) {
					lastPush = 8;
				} else if(selectedPush == 1) {
					lastPush = 7;
				} else if(selectedPush == 2) {
					lastPush = 6;
				} else if(selectedPush == 3) {
					lastPush = 11;
				} else if(selectedPush == 4) {
					lastPush = 10;
				} else if(selectedPush == 5) {
					lastPush = 9;
				} else if(selectedPush == 6) {
					lastPush = 2;
				} else if(selectedPush == 7) {
					lastPush = 1;
				} else if(selectedPush == 8) {
					lastPush = 0;
				} else if(selectedPush == 9) {
					lastPush = 5;
				} else if(selectedPush == 10) {
					lastPush = 4;
				} else if(selectedPush == 11) {
					lastPush = 3;
				}
				selectedPush = -1;
				
				//Move to player movement phase
				nextPhase();
				
			} else if (phase == 1) {
				
				if(selectedRow != 0 && selectedCol != 0 && board.getVis()[board.getBoard()[selectedRow][selectedCol].getNodeNum()]) {
					movePlayer();
					nextPhase();
					pushButton[lastPush].setIcon(Assets.tileHighlightRed);
				}
				
			}
			
		}
		
	}
	
}
