import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton implements Cloneable{
	
	public static Tile[] tiles = new Tile[30];
	public static ArrayList<Tile> tileDeck = new ArrayList<Tile>();
	public static final int TILE_SIZE = 60;
	
	private static Random r = new Random();
	
	private String type; //I, T, or L tile
	private String treasure;
	private int rotation;
	private int id;
	private boolean[] openings = new boolean[4];
	
	//opening array index position
	//  0
	//3   1
	//  2
	
	private ImageIcon[] images;
	private int nodeNum; //determined by position on the board (1-49) = (row - 1) * 7 + col
	
	//Unmovable tiles
	public static Tile tileP1Start = new Tile("L", Assets.permenantTiles[0], 1, 0);
	public static Tile tileBook = new Tile("T", "book", Assets.permenantTiles[1], 0, 1);
	public static Tile tileBag = new Tile("T", "bag", Assets.permenantTiles[2], 0, 2);
	public static Tile tileP2Start = new Tile("L", Assets.permenantTiles[3], 2, 3);
	
	public static Tile tilePainting = new Tile("T", "painting", Assets.permenantTiles[4], 3, 4);
	public static Tile tileCrown = new Tile("T", "crown", Assets.permenantTiles[5], 3, 5);
	public static Tile tileKeys = new Tile("T", "keys", Assets.permenantTiles[6], 0, 6);
	public static Tile tileSkull = new Tile("T", "skull", Assets.permenantTiles[7], 1, 7);
	
	public static Tile tileRing = new Tile("T", "ring", Assets.permenantTiles[8], 3, 8);
	public static Tile tileBottle = new Tile("T", "bottle", Assets.permenantTiles[9], 2, 9);
	public static Tile tileGreen = new Tile("T", "green", Assets.permenantTiles[10], 1, 10);
	public static Tile tileSword = new Tile("T", "sword", Assets.permenantTiles[11], 1, 11);
	
	public static Tile tileP3Start = new Tile("L", Assets.permenantTiles[12], 0, 12);
	public static Tile tileCandles = new Tile("T", "candles", Assets.permenantTiles[13], 2, 13);
	public static Tile tileMonster = new Tile("T", "monster", Assets.permenantTiles[14], 2, 14);
	public static Tile tileP4Start = new Tile("L", Assets.permenantTiles[15], 3, 15);
	
	//Movable Tiles
	public static Tile tileBat = new Tile("T", "bat", Assets.tileBat, r.nextInt(4), 16);
	public static Tile tileDragon = new Tile("T", "dragon", Assets.tileDragon, r.nextInt(4), 17);
	public static Tile tileGhostBottle = new Tile("T", "ghostBottle", Assets.tileGhostBottle, r.nextInt(4), 18);
	public static Tile tileGhostWaving = new Tile("T", "ghostWaving", Assets.tileGhostWaving, r.nextInt(4), 19);
	public static Tile tileLadyPig = new Tile("T", "ladyPig", Assets.tileLadyPig, r.nextInt(4), 20);
	public static Tile tileSorceress = new Tile("T", "sorceress", Assets.tileSorceress, r.nextInt(4), 21);
	
	public static Tile tileLizard = new Tile("L", "lizard", Assets.tileLizard, r.nextInt(4), 22);
	public static Tile tileMoth = new Tile("L", "moth", Assets.tileMoth, r.nextInt(4), 23);
	public static Tile tileOwl = new Tile("L", "owl", Assets.tileOwl, r.nextInt(4), 24);
	public static Tile tileRat = new Tile("L", "rat", Assets.tileRat, r.nextInt(4), 25);
	public static Tile tileScarab = new Tile("L", "scarab", Assets.tileScarab, r.nextInt(4), 26);
	public static Tile tileSpider = new Tile("L", "spider", Assets.tileSpider, r.nextInt(4), 27);
	
	public static Tile tileL = new Tile("L", Assets.tileL, r.nextInt(4), 28);
	public static Tile tileI = new Tile("I", Assets.tileI, r.nextInt(4), 29);
	
//	public static Tile tileL1 = new Tile("L", Assets.tileL, r.nextInt(4), 28);
//	public static Tile tileL2 = new Tile("L", Assets.tileL, r.nextInt(4), 29);
//	public static Tile tileL3 = new Tile("L", Assets.tileL, r.nextInt(4), 30);
//	public static Tile tileL4 = new Tile("L", Assets.tileL, r.nextInt(4), 31);
//	public static Tile tileL5 = new Tile("L", Assets.tileL, r.nextInt(4), 32);
//	public static Tile tileL6 = new Tile("L", Assets.tileL, r.nextInt(4), 33);
//	public static Tile tileL7 = new Tile("L", Assets.tileL, r.nextInt(4), 34);
//	public static Tile tileL8 = new Tile("L", Assets.tileL, r.nextInt(4), 35);
//	public static Tile tileL9 = new Tile("L", Assets.tileL, r.nextInt(4), 36);
//	public static Tile tileL10 = new Tile("L", Assets.tileL, r.nextInt(4), 37);
//	
//	public static Tile tileI1 = new Tile("I", Assets.tileI, r.nextInt(4), 38);
//	public static Tile tileI2 = new Tile("I", Assets.tileI, r.nextInt(4), 39);	
//	public static Tile tileI3 = new Tile("I", Assets.tileI, r.nextInt(4), 40);	
//	public static Tile tileI4 = new Tile("I", Assets.tileI, r.nextInt(4), 41);	
//	public static Tile tileI5 = new Tile("I", Assets.tileI, r.nextInt(4), 42);	
//	public static Tile tileI6 = new Tile("I", Assets.tileI, r.nextInt(4), 43);	
//	public static Tile tileI7 = new Tile("I", Assets.tileI, r.nextInt(4), 44);	
//	public static Tile tileI8 = new Tile("I", Assets.tileI, r.nextInt(4), 45);	
//	public static Tile tileI9 = new Tile("I", Assets.tileI, r.nextInt(4), 46);	
//	public static Tile tileI10 = new Tile("I", Assets.tileI, r.nextInt(4), 47);	
//	public static Tile tileI11 = new Tile("I", Assets.tileI, r.nextInt(4), 48);	
//	public static Tile tileI12 = new Tile("I", Assets.tileI, r.nextInt(4), 49);	
	
//	public Tile(String type, ImageIcon images[]) {
//		super();
//		this.type = type;
//		this.hasTreasure = false;
//		this.treasure = "";
//		this.images = images;
//		setRotation(0);
//	}
	
	//Constructor for movable tile without treasure
	public Tile(String type, ImageIcon images[], int rotation, int id) {
		super();
		this.type = type;
		this.treasure = "";
		this.images = images;
		this.id = id;
		setRotation(rotation);
		tiles[id] = this;
	}
	
	//Constructor for movable tile with treasure
//	public Tile(String type, String treasure, ImageIcon[] images) {
//		super();
//		this.type = type;
//		this.hasTreasure = true;
//		this.treasure = treasure;
//		this.images = images;
//		setRotation(0);
//	}
	
	//Constructor for movable tile with treasure
	public Tile(String type, String treasure, ImageIcon[] images, int rotation, int id) {
		super();
		this.type = type;
		this.treasure = treasure;
		this.images = images;
		this.id = id;
		setRotation(rotation);
		tiles[id] = this;
	}
	
	//Constructor for immovable tile without treasure
	public Tile(String type, ImageIcon image, int rotation, int id) {
		super();
		this.type = type;
		this.treasure = "";
		this.id = id;
		setIcon(image);
		setRotation(rotation);
		tiles[id] = this;
	}
	
	//Constructor for immovable tile with treasure
	public Tile(String type, String treasure, ImageIcon image, int rotation, int id) {
		super();
		this.type = type;
		this.treasure = treasure;
		this.id = id;
		setIcon(image);
		setRotation(rotation);
		tiles[id] = this;
	}
	
	public Tile() {
		super();
	}
	
	//Getters and setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTreasure() {
		return treasure;
	}

	public void setTreasure(String treasure) {
		this.treasure = treasure;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	//Change openings in the rotation setter
	public void setRotation(int rotation) {
		this.rotation = rotation;
		
		//If the tile is movable set its image icon
		if(images != null) 
			setIcon(images[rotation]);
			
		if(type.equals("T")) {
			
			for(int i = 0; i < 4; i++) {
				if(!(i == rotation))
					openings[i] = true;
				else
					openings[i] = false;
			}
			
		} else if(type.equals("L")) {
			
			if(rotation == 3) {
				for(int i = 0; i < 4; i++) {
					if(i == 3 || i == 0)
						openings[i] = true;
					else
						openings[i] = false;
				}
			} else {
				for(int i = 0; i < 4; i++) {
					if(i == rotation || i == rotation + 1)
						openings[i] = true;
					else
						openings[i] = false;
				}
			}
			
		} else if(type.equals("I")) {
			
			if(rotation == 0 || rotation == 2) {
				openings[0] = true;
				openings[1] = false;
				openings[2] = true;
				openings[3] = false;
			} else if(rotation == 1 || rotation == 3) {
				openings[0] = false;
				openings[1] = true;
				openings[2] = false;
				openings[3] = true;
			}
			
		}
		
	}
	
	public boolean[] getOpenings() {
		return openings;
	}

	public void setOpenings(boolean[] openings) {
		this.openings = openings;
	}

	public ImageIcon[] getImages() {
		return images;
	}

	public void setImages(ImageIcon[] images) {
		this.images = images;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	
	public static ArrayList<Tile> getTileDeck() {
		return tileDeck;
	}

	public static void setTileDeck(ArrayList<Tile> tileDeck) {
		Tile.tileDeck = tileDeck;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Tile [type=" + type + ", treasure=" + treasure + ", rotation=" + rotation + ", id=" + id + ", openings="
				+ Arrays.toString(openings) + ", nodeNum=" + nodeNum + "]";
	}

	//This method sets up the tile deck
	public static void setupTileDeck() {
		
		//4 L corner tiles
		//12 T unmovable treasure tiles
		//6 T movable treasure tiles - bat, dragon, ghost bottle, ghost waving, lady pig, sorceress
		//6 L movable treasure tiles - lizard, moth, owl, rat, scarab, spider
		//10 L tiles
		//12 I tiles
		
		for(int id = 16; id < 28; id++) {
			Tile tile = new Tile();
			
			tile.setType(tiles[id].getType());
			tile.setTreasure(tiles[id].getTreasure());
			tile.setImages(tiles[id].getImages());
			tile.setRotation(tiles[id].getRotation());
			tile.setId(tiles[id].getId());

			tileDeck.add(tile);
			
//			tileDeck.add(new Tile(tiles[id].type, tiles[id].treasure, 
//					tiles[id].images, tiles[id].rotation, tiles[id].id));	
		}
		
		for(int i = 0; i < 10; i++) {
			
			Tile tile = new Tile();
			
			tile.setType(tiles[28].getType());
			tile.setTreasure(tiles[28].getTreasure());
			tile.setImages(tiles[28].getImages());
			tile.setRotation(r.nextInt(4));
			tile.setId(tiles[28].getId());
			
			tileDeck.add(tile);
			
		}
		
		for(int i = 0; i < 12; i++) {
			
			Tile tile = new Tile();
			
			tile.setType(tiles[29].getType());
			tile.setTreasure(tiles[29].getTreasure());
			tile.setImages(tiles[29].getImages());
			tile.setRotation(r.nextInt(4));
			tile.setId(tiles[29].getId());
			
			tileDeck.add(tile);
//			tileDeck.add(new Tile("I", Assets.tileI, r.nextInt(4), 29));	
		}
		
//		try {
//			tileDeck.add((Tile) tileBat.clone());
//			tileDeck.add(tileDragon.clone());
//			tileDeck.add(tileGhostBottle.clone());
//			tileDeck.add(tileGhostWaving.clone());
//			tileDeck.add(tileLadyPig.clone());
//			tileDeck.add(tileSorceress.clone());
//			
//			tileDeck.add(tileLizard.clone());
//			tileDeck.add(tileMoth.clone());
//			tileDeck.add(tileOwl.clone());
//			tileDeck.add(tileRat.clone());
//			tileDeck.add(tileScarab.clone());
//			tileDeck.add(tileSpider.clone());
//			
//			for(int i = 0; i < 10; i++) {
//				tileDeck.add(tileL.clone());	
//			}
//			
//			for(int i = 0; i < 12; i++) {
//				tileDeck.add(tileI.clone());	
//			}
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
}
