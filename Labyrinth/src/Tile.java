import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton {
	
	public static ArrayList<Tile> tileDeck = new ArrayList<Tile>();
	public static final int TILE_SIZE = 60;
	
	private static Random r = new Random();
	
	private String type; //I, T, or L tile
	private boolean hasTreasure;
	private String treasure;
	private int rotation;
	private boolean[] openings = new boolean[4];
	
	//opening array index position
	//  0
	//3   1
	//  2
	
	private ImageIcon[] images;
	private int nodeNum; //determined by position on the board (1-49) = (row - 1) * 7 + col
	
	//Constructor for movable tile without treasure
	public Tile(String type, ImageIcon images[]) {
		super();
		this.type = type;
		this.hasTreasure = false;
		this.treasure = "";
		this.images = images;
		setRotation(0);
	}
	
	public Tile(String type, ImageIcon images[], int rotation) {
		super();
		this.type = type;
		this.hasTreasure = false;
		this.treasure = "";
		this.images = images;
		setRotation(rotation);
	}
	
	//Constructor for movable tile with treasure
	public Tile(String type, String treasure, ImageIcon[] images) {
		super();
		this.type = type;
		this.hasTreasure = true;
		this.treasure = treasure;
		this.images = images;
		setRotation(0);
	}
	
	//Constructor for movable tile with treasure
	public Tile(String type, String treasure, ImageIcon[] images, int rotation) {
		super();
		this.type = type;
		this.hasTreasure = true;
		this.treasure = treasure;
		this.images = images;
		setRotation(rotation);
	}
	
	//Constructor for immovable tile without treasure
	public Tile(String type, ImageIcon image, int rotation) {
		super();
		this.type = type;
		this.hasTreasure = false;
		this.treasure = "";
		setIcon(image);
		setRotation(rotation);
	}
	
	//Constructor for immovable tile with treasure
	public Tile(String type, String treasure, ImageIcon image, int rotation) {
		super();
		this.type = type;
		this.hasTreasure = false;
		this.treasure = treasure;
		setIcon(image);
		setRotation(rotation);
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

	public boolean isHasTreasure() {
		return hasTreasure;
	}

	public void setHasTreasure(boolean hasTreausre) {
		this.hasTreasure = hasTreausre;
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

	//toString method
	@Override
	public String toString() {
		return "Tile [type=" + type + ", hasTreausre=" + hasTreasure + ", treasure=" + treasure + "]";
	}
	
	//This method sets up the tile deck
	public static void setupTileDeck() {
		
		//4 L corner tiles
		//12 T unmovable treasure tiles
		//6 T movable treasure tiles - bat, dragon, ghost bottle, ghost waving, lady pig, sorceress
		//6 L movable treasure tiles - lizard, moth, owl, rat, scarab, spider
		//10 L tiles
		//12 I tiles
		
		tileDeck.add(new Tile("T", "bat", Assets.tileBat, r.nextInt(4)));
		tileDeck.add(new Tile("T", "dragon", Assets.tileDragon, r.nextInt(4)));
		tileDeck.add(new Tile("T", "ghostBottle", Assets.tileGhostBottle, r.nextInt(4)));
		tileDeck.add(new Tile("T", "ghostWaving", Assets.tileGhostWaving, r.nextInt(4)));
		tileDeck.add(new Tile("T", "ladyPig", Assets.tileLadyPig, r.nextInt(4)));
		tileDeck.add(new Tile("T", "sorceress", Assets.tileSorceress, r.nextInt(4)));
		
		tileDeck.add(new Tile("L", "lizard", Assets.tileLizard, r.nextInt(4)));
		tileDeck.add(new Tile("L", "moth", Assets.tileMoth, r.nextInt(4)));
		tileDeck.add(new Tile("L", "owl", Assets.tileOwl, r.nextInt(4)));
		tileDeck.add(new Tile("L", "rat", Assets.tileRat, r.nextInt(4)));
		tileDeck.add(new Tile("L", "scarab", Assets.tileScarab, r.nextInt(4)));
		tileDeck.add(new Tile("L", "spider", Assets.tileSpider, r.nextInt(4)));
		
		for(int i = 0; i < 10; i++) {
			tileDeck.add(new Tile("L", Assets.tileL, r.nextInt(4)));	
		}
		
		for(int i = 0; i < 12; i++) {
			tileDeck.add(new Tile("I", Assets.tileI, r.nextInt(4)));	
		}
		
		
	}
	
}
