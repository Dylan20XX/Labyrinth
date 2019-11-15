import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Board {
	
	private Tile[][] board = new Tile[9][9];
	private Random r = new Random();
	
	private int x; //col
	private int y; //row
	
	ArrayList<Integer> adj[] = new ArrayList[50]; //Adjacency matrix
	
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
		
		//Initialize array lists in the adjacency matrix
		for(int i = 1; i <= adj.length; i++) {
            adj[i] = new ArrayList<Integer>();
        }
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
		
		//Fill unmovable tiles
		board[1][1] = new Tile("L", Assets.permenantTiles[0], 1);
		board[1][3] = new Tile("T", Assets.permenantTiles[1], 0);
		board[1][5] = new Tile("T", Assets.permenantTiles[2], 0);
		board[1][7] = new Tile("L", Assets.permenantTiles[3], 2);
		
		board[3][1] = new Tile("T", Assets.permenantTiles[4], 3);
		board[3][3] = new Tile("T", Assets.permenantTiles[5], 3);
		board[3][5] = new Tile("T", Assets.permenantTiles[6], 0);
		board[3][7] = new Tile("T", Assets.permenantTiles[7], 1);
		
		board[5][1] = new Tile("T", Assets.permenantTiles[8], 3);
		board[5][3] = new Tile("T", Assets.permenantTiles[9], 2);
		board[5][5] = new Tile("T", Assets.permenantTiles[10], 1);
		board[5][7] = new Tile("T", Assets.permenantTiles[11], 1);
		
		board[7][1] = new Tile("L", Assets.permenantTiles[12], 0);
		board[7][3] = new Tile("T", Assets.permenantTiles[13], 2);
		board[7][5] = new Tile("T", Assets.permenantTiles[14], 2);
		board[7][7] = new Tile("L", Assets.permenantTiles[15], 3);
		
		//Fill the board with random tiles
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				
				//Fill the spots that have no permanent tiles
				if(board[row][col] == null) {
					int cardIndex = 0;
					if(Tile.tileDeck.size() > 1) {
						cardIndex = r.nextInt(Tile.tileDeck.size());
					}
					board[row][col] = Tile.tileDeck.remove(cardIndex);
					
				}
				
				//Set the bounds of all tiles on the board
				board[row][col].setBounds(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
				
			}
		}
		
	}
	
	//* move player if player is on the column or row that is pushed
	public void pushColDown(int col) {
		for(int row = 8; row > 0; row--) {
			board[row][col] = board[row - 1][col];
		}
	}
	
	public void pushColUp(int col) {
		for(int row = 8; row > 0; row--){
			board[row][col] = board[row+1][col];
		}
	}
	
	public void pushRowRight(int row) {
		for(int col = 8; col > 0; col-- ){
			board[row][col] = board[row][col-1];
		}
		
	}
	
	public void pushRowLeft(int row) {
		for(int col = 0; col < 8; col++ ){
			board[row][col] = board[row][col+1];
		}
	}
	
	public void pushColUp() {
		
	}
	
	public void pushRowRight() {
		
	}
	
	public void pushRowDown() {
		

	}
	
	//This method will be used to find pieces connected to the piece that the player is on
	public void pathfind(int row, int col) {
		
		int nodeNum = (row - 1) * 7 + col; //start point
		//row = nodeNum / 7 + 1
		//col = nodeNum % 7

        int D = 50; //end point set to an impossible number to check for all possible paths
        
        int dis[] = new int[50];
        boolean vis[] = new boolean[50];
        Queue<Integer> Q = new LinkedList<Integer>();

        Q.add(nodeNum);
        vis[nodeNum] = true;
        dis[nodeNum] = 0;
        
        //Run BFS to check for all pathways
        while(!Q.isEmpty()) {
            int cur = Q.poll();
                for(int v : adj[cur]) {
                    if(!vis[v]) {
                       Q.add(v);
                       vis[v] = true;
                       dis[v] = dis[cur]+1;
                    }
                    if(v == D) break;	
                }
            if(vis[D]) break;	
        }
        if(vis[D]) System.out.println(dis[D]);
        else System.out.println(-1);

		
	}
	
	public void buildAdjacencyMatrix() {
		
		for(int i = 1; i <= adj.length; i++) {
            adj[i].clear();
        }
		
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 8; col++) {
				
				//Check if piece to the right is connected
				if(board[row][col].getOpenings()[1] && board[row][col + 1] != null && board[row][col + 1].getOpenings()[3]) {
					adj[board[row][col].getNodeNum()].add(board[row][col + 1].getNodeNum());
					adj[board[row][col + 1].getNodeNum()].add(board[row][col].getNodeNum()); //Undirected graph
				}
				
				//Check if piece below is connected
				if(board[row][col].getOpenings()[2] && board[row + 1][col] != null && board[row + 1][col].getOpenings()[0]) {
					adj[board[row][col].getNodeNum()].add(board[row + 1][col].getNodeNum());
					adj[board[row + 1][col].getNodeNum()].add(board[row][col].getNodeNum()); //Undirected graph
				}
				
			}
		}
		
	}
	
}
