import java.util.Random;

public class Board {
	
	private Tile[][] board = new Tile[9][9];
	private Random r = new Random();
	
	private int x; //col
	private int y; //row
	
	
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
	}

	public Tile[][] getBoard() {
		return board;
	}

	public void setBoard(Tile[][] board) {
		this.board = board;
	}
	
	public void fillBoard() {
		
		//to fill board randomly
		//generate number from 0 to tileDeck.size() and add the
		//tile at that index to the board
		
		//*add functionality to fill unmovable tiles
		
		//Fill the board with random tiles
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
//				if(row % 2 == 1) {
//					if(col % 2 == 1) {
//						board[col][row] = new Tile("I", Assets.blueTile);
//					} else {
//						board[col][row] = new Tile("L", Assets.greenTile);
//					}
//				} else {
//					if(col % 2 == 1) {
//						board[col][row] = new Tile("L", Assets.greenTile);
//					} else {
//						board[col][row] = new Tile("I", Assets.blueTile);
//					}
//				}
				//System.out.println(Tile.tileDeck.size());
				int cardIndex = 0;
				if(Tile.tileDeck.size() > 1) {
					cardIndex = r.nextInt(Tile.tileDeck.size());
				}
				board[col][row] = Tile.tileDeck.remove(cardIndex);
				
				board[col][row].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
			}
		}
		
	}
	
	public void pushColDown(int col) {
		for(int row = 8; row > 0; row--) {
			board[row][col] = board[row - 1][col];
		}
	}
	
	public void pushColUp() {
		
	}
	
	public void pushRowRight() {
		
	}
	
	public void pushRowDown() {
		
	}
	
	//This method will be used to find
	public void pathfind() {
		
	}
	
}
