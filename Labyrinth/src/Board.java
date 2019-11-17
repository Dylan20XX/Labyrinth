import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JLabel;

public class Board {
	
	private Tile[][] board = new Tile[9][9];
	private JLabel[][] highlight = new JLabel[9][9];
	private Random r = new Random();
	
    private boolean vis[] = new boolean[50];
    private Queue<Integer> Q = new LinkedList<Integer>();
    private HashMap<Integer, Integer> parentNodes = new HashMap<Integer, Integer>();
	
	private ArrayList<Integer> adj[] = new ArrayList[50]; //Adjacency matrix
	
	//Add an array of JLabels to highlight path
	
	//4 L corner tiles
	//12 T unmovable treasure tiles
	//6 T movable treasure tiles
	//6 L movable treasure tiles
	//10 L tiles
	//12 I tiles

	public Board(Tile[][] board) {
		super();
		this.board = board;
	}

	public Board() {
		super();
		
		//Initialize array lists in the adjacency matrix
		for(int i = 1; i < adj.length; i++) {
            adj[i] = new ArrayList<Integer>();
        }
	}

	public Tile[][] getBoard() {
		return board;
	}

	public void setBoard(Tile[][] board) {
		this.board = board;
	}
	
	public JLabel[][] getHighlight() {
		return highlight;
	}

	public void setHighlight(JLabel[][] highlight) {
		this.highlight = highlight;
	}
	
	public boolean[] getVis() {
		return vis;
	}

	public void setVis(boolean[] vis) {
		this.vis = vis;
	}

	public HashMap<Integer, Integer> getParentNodes() {
		return parentNodes;
	}

	public void setParentNodes(HashMap<Integer, Integer> parentNodes) {
		this.parentNodes = parentNodes;
	}

	//This method randomly fills the board with tiles
	public void fillBoard() {
		
		//to fill board randomly

		
		//Fill unmovable tiles
		board[1][1] = new Tile("L", Assets.permenantTiles[0], 1);
		board[1][3] = new Tile("T", "book", Assets.permenantTiles[1], 0);
		board[1][5] = new Tile("T", "bag", Assets.permenantTiles[2], 0);
		board[1][7] = new Tile("L", Assets.permenantTiles[3], 2);
		
		board[3][1] = new Tile("T", "painting", Assets.permenantTiles[4], 3);
		board[3][3] = new Tile("T", "crown", Assets.permenantTiles[5], 3);
		board[3][5] = new Tile("T", "keys", Assets.permenantTiles[6], 0);
		board[3][7] = new Tile("T", "skull", Assets.permenantTiles[7], 1);
		
		board[5][1] = new Tile("T", "ring", Assets.permenantTiles[8], 3);
		board[5][3] = new Tile("T", "bottle", Assets.permenantTiles[9], 2);
		board[5][5] = new Tile("T", "green", Assets.permenantTiles[10], 1);
		board[5][7] = new Tile("T", "sword", Assets.permenantTiles[11], 1);
		
		board[7][1] = new Tile("L", Assets.permenantTiles[12], 0);
		board[7][3] = new Tile("T", "candles", Assets.permenantTiles[13], 2);
		board[7][5] = new Tile("T", "monster", Assets.permenantTiles[14], 2);
		board[7][7] = new Tile("L", Assets.permenantTiles[15], 3);
		
		//Fill the board with random tiles
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				
				//Fill the spots that have no permanent tiles
				if(board[row][col] == null) {
					
					//Generate a number from 0 to tileDeck.size() and add the
					//tile at that index to the board
					int cardIndex = 0;
					
					if(Tile.tileDeck.size() > 1) {
						cardIndex = r.nextInt(Tile.tileDeck.size());
					}
					
					board[row][col] = Tile.tileDeck.remove(cardIndex);
					
				}
				
				//Set the node number of the tile
				board[row][col].setNodeNum((row - 1) * 7 + col);
				
				//Set the bounds of all tiles on the board
				board[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				
				//Set the bounds of the highlight labels
				highlight[row][col] = new JLabel();
//				highlight[row][col] = new JLabel(Assets.tileHighlight);
//				highlight[row][col].setIcon(null);
				highlight[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				
			}
		}
		
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if(board[row][col] == null) {
					board[row][col] = new Tile();
				}
			}
		}
		
	}
	
	//* move player if player is on the column or row that is pushed
	public void pushColDown(int col) {
		for(int row = 8; row > 0; row--) {

			board[row][col].setType(board[row - 1][col].getType());
			board[row][col].setHasTreasure(board[row - 1][col].isHasTreasure());
			board[row][col].setTreasure(board[row - 1][col].getTreasure());
			board[row][col].setImages(board[row - 1][col].getImages());
			board[row][col].setRotation(board[row - 1][col].getRotation());


			if(board[row][col] != null)
				board[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
			
		}
	}
	
	public void pushColUp(int col) {
		for(int row = 0; row < 8; row++){
			
			board[row][col].setType(board[row + 1][col].getType());
			board[row][col].setHasTreasure(board[row + 1][col].isHasTreasure());
			board[row][col].setTreasure(board[row + 1][col].getTreasure());
			board[row][col].setImages(board[row + 1][col].getImages());
			board[row][col].setRotation(board[row + 1][col].getRotation());
			
			//board[row][col] = board[row+1][col];
			
			if(board[row][col] != null)
				board[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
		}
	}
	
	public void pushRowRight(int row) {
		for(int col = 8; col > 0; col-- ){
			
			board[row][col].setType(board[row][col - 1].getType());
			board[row][col].setHasTreasure(board[row][col - 1].isHasTreasure());
			board[row][col].setTreasure(board[row][col - 1].getTreasure());
			board[row][col].setImages(board[row][col - 1].getImages());
			board[row][col].setRotation(board[row][col - 1].getRotation());
			
			//board[row][col] = board[row][col-1];
			
			if(board[row][col] != null)
				board[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
		}
		
	}
	
	public void pushRowLeft(int row) {
		for(int col = 0; col < 8; col++ ){
			
			board[row][col].setType(board[row][col + 1].getType());
			board[row][col].setHasTreasure(board[row][col + 1].isHasTreasure());
			board[row][col].setTreasure(board[row][col + 1].getTreasure());
			board[row][col].setImages(board[row][col + 1].getImages());
			board[row][col].setRotation(board[row][col + 1].getRotation());
			
			//board[row][col] = board[row][col+1];
			
			if(board[row][col] != null)
				board[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
		}
	}
	
	//This method will be used to find pieces connected to the piece that the player is on
	public void pathfind(int row, int col) {
		
		//Build the adjacency matrix
		buildAdjacencyMatrix();
		
		int nodeNum = (row - 1) * 7 + col; //start point
		//row = nodeNum / 7 + 1
		//col = nodeNum % 7
		
		//Reset the parent nodes map
		parentNodes.clear();
		
		//Reset the queue and visited array
		Q.clear();
		for(int i = 0; i < 50; i++)
			vis[i] = false;

        Q.add(nodeNum);
        vis[nodeNum] = true;
        
        //Run BFS to check for all pathways
        while(!Q.isEmpty()) {
            int cur = Q.poll();
            for(int v : adj[cur]) {
            	if(!vis[v]) {
            		parentNodes.put(v, cur); //parentNodes.put(unvisitedNode, currentNode)
            		Q.add(v);
            		vis[v] = true;
            	}	
            }
        }
        
        highlightTiles();
		
	}
	
	//This method builds the adjacency matrix to be used for path finding
	private void buildAdjacencyMatrix() {
		
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				board[row][col].setNodeNum((row - 1) * 7 + col);
			}
		}
		
		for(int i = 1; i < adj.length; i++) {
            adj[i].clear();
        }
		
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				
				System.out.println("row = " + row);
	    		System.out.println("col = " + col);
				
				//Check if piece to the right is connected
				if(board[row][col].getOpenings()[1] && board[row][col + 1] != null && board[row][col + 1].getOpenings()[3] && col != 7) {
					adj[board[row][col].getNodeNum()].add(board[row][col + 1].getNodeNum());
					adj[board[row][col + 1].getNodeNum()].add(board[row][col].getNodeNum()); //Undirected graph
				}
				
				//Check if piece below is connected
				if(board[row][col].getOpenings()[2] && board[row + 1][col] != null && board[row + 1][col].getOpenings()[0] && row != 7) {
					adj[board[row][col].getNodeNum()].add(board[row + 1][col].getNodeNum());
					adj[board[row + 1][col].getNodeNum()].add(board[row][col].getNodeNum()); //Undirected graph
				}
				
			}
		}
		
	}
	
	//This method highlights tiles
	public void highlightTiles() {
		
        //Highlight available tiles
		for(int i = 1; i < adj.length; i++) {
    		int row = i / 7 + 1;
    		int col = i % 7;
    		
    		//Error check since col number will be set to 0 if col is divisible by 7
    		//Row number will also be 1 higher than it should be
    		if(col == 0) {
    			col = 7;
    			row -= 1;
    		}
    		
    		//Error check since row number will be set to 8 on node 49
    		if(row == 8) {
    			row = 7;
    		}
    		
    		System.out.println("rowHighlight = " + row);
    		System.out.println("colHighlight = " + col);
    		
			if(vis[i]) {
        		highlight[row][col].setIcon(Assets.tileHighlightYellow);
            } else {
            	//highlight[rowHighlight][colHighlight].setIcon(null);
            	highlight[row][col].setIcon(Assets.tileHighlightBlue);
            }
        }
		
	}
	
	//This method removes the highlights from tiles
	public void removeHighlight() {
		
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				highlight[row][col].setIcon(null);
			}
		}
		
	}
	
	//This method returns an array list of the nodes that you must travel through to reach an end node
	public ArrayList<Position> findShortestPath(int startRow, int startCol, int endRow, int endCol) {
		
		ArrayList<Position> shortestPath = new ArrayList<Position>();
		
		int startNode = (startRow - 1) * 7 + startCol;
		int endNode = (endRow - 1) * 7 + endCol;
		
		int currentNode = endNode;
		
		while(currentNode != startNode) {
			
			//Convert node number to row and column
    		int row = currentNode / 7 + 1;
    		int col = currentNode % 7;
    		
    		//Error check since col number will be set to 0 if col is divisible by 7
    		//Row number will also be 1 higher than it should be
    		if(col == 0) {
    			col = 7;
    			row -= 1;
    		}
    		
    		//Error check since row number will be set to 8 on node 49
    		if(row == 8) {
    			row = 7;
    		}
			
			shortestPath.add(new Position(row, col));
			currentNode = parentNodes.get(currentNode);
			
		}
		
		//Reverse the array list because nodes are entered in reverse order
		Collections.reverse(shortestPath);
		
		return shortestPath;
		
	}
	
}
