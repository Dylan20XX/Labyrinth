
public class Board {
	
	private Tile[][] board = new Tile[9][9];

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
		
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				if(row % 2 == 1) {
					if(col % 2 == 1) {
						board[col][row] = new Tile("I", Assets.blueTile);
					} else {
						board[col][row] = new Tile("L", Assets.greenTile);
					}
				} else {
					if(col % 2 == 1) {
						board[col][row] = new Tile("L", Assets.greenTile);
					} else {
						board[col][row] = new Tile("I", Assets.blueTile);
					}
				}
				
				board[col][row].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
			}
		}
		
	}
	
}
