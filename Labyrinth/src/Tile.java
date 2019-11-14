import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton {
	
	public static ArrayList<Tile> tileDeck = new ArrayList<Tile>();
	public static Tile defaultTile = new Tile("I", Assets.blueTile);
	public static final int TILE_SIZE = 60;
	
	private String type; //I, T, or L tile
	private boolean hasTreausre;
	private String treasure;
	private int rotation;
	private boolean[] openings = new boolean[4];
	
	//opening array index position
	//  0
	//3   1
	//  2
	
	private ImageIcon[] images;
	private int nodeNum; //determined by position on the board (1-49)
	
	//Constructor for tile immovable tile with treasure
	public Tile(String type, String treasure, ImageIcon image) {
		super();
		this.type = type;
		this.hasTreausre = true;
		this.treasure = treasure;
		setRotation(0);
		setIcon(image);
	}
	
	//Constructor 
	public Tile(String type, String treasure, ImageIcon[] images) {
		super();
		this.type = type;
		this.hasTreausre = true;
		this.treasure = treasure;
		this.images = images;
		setRotation(0);
	}
	
	//Constructor for tile without treasure
	public Tile(String type, ImageIcon image) {
		super();
		this.type = type;
		this.hasTreausre = false;
		this.treasure = "";
		setIcon(image);
		setRotation(0);
	}
	
	//Getters and setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasTreausre() {
		return hasTreausre;
	}

	public void setHasTreausre(boolean hasTreausre) {
		this.hasTreausre = hasTreausre;
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
		if(images != null) {
			setIcon(images[rotation]);
			System.out.println("tile"); //test
		}
			
		if(type.equals("T")) {
			
			for(int i = 0; i < 4; i++) {
				if(!(i == rotation))
					openings[i] = true;
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

	//toString method
	@Override
	public String toString() {
		return "Tile [type=" + type + ", hasTreausre=" + hasTreausre + ", treasure=" + treasure + "]";
	}
	
	//This method sets up the tile deck
	public static void setupTileDeck() {
		//4 L corner tiles
		//12 T unmovable treasure tiles
		//6 T movable treasure tiles - bat, dragon, ghost bottle, ghost waving, lady pig, sorceress
		//6 L movable treasure tiles - lizard, moth, owl, rat, scarab, spider
		//10 L tiles
		//12 I tiles
		
//		Tile batTile = new Tile("T", "bat", Assets.blueTile);
//		Tile dragonTile = new Tile("T", "dragon", Assets.blueTile);
//		Tile ghostBottleTile = new Tile("T", "ghostBottle", Assets.blueTile);
//		Tile ghostWavingTile = new Tile("T", "ghostWaving", Assets.blueTile);
//		Tile ladyPigTile = new Tile("T", "ladyPig", Assets.blueTile);
//		Tile sorceressTile = new Tile("T", "sorceress", Assets.blueTile);
//		
//		Tile lizardTile = new Tile("T", "lizard", Assets.blueTile);
//		Tile mothTile = new Tile("T", "moth", Assets.blueTile);
//		Tile owlTile = new Tile("T", "owl", Assets.blueTile);
//		Tile ratTile = new Tile("T", "rat", Assets.blueTile);
//		Tile scarabTile = new Tile("T", "scarab", Assets.blueTile);
//		Tile spiderTile = new Tile("T", "spider", Assets.blueTile);
//		
//		for(int i = 0; i < 10; i++) {
//			Tile tile = new Tile("L", Assets.blueTile);	
//		}
//		
//		for(int i = 0; i < 12; i++) {
//			Tile tile = new Tile("I", Assets.blueTile);	
//		}
		
		tileDeck.add(new Tile("T", "bat", Assets.treasureTileBat));
		tileDeck.add(new Tile("T", "dragon", Assets.treasureTileDragon));
		tileDeck.add(new Tile("T", "ghostBottle", Assets.tileGhostBottle));
		tileDeck.add(new Tile("T", "ghostWaving", Assets.tileGhostWaving));
		tileDeck.add(new Tile("T", "ladyPig", Assets.blueTile));
		tileDeck.add(new Tile("T", "sorceress", Assets.blueTile));
		
		tileDeck.add(new Tile("T", "lizard", Assets.blueTile));
		tileDeck.add(new Tile("T", "moth", Assets.blueTile));
		tileDeck.add(new Tile("T", "owl", Assets.blueTile));
		tileDeck.add(new Tile("T", "rat", Assets.blueTile));
		tileDeck.add(new Tile("T", "scarab", Assets.blueTile));
		tileDeck.add(new Tile("T", "spider", Assets.blueTile));
		
		for(int i = 0; i < 10; i++) {
			tileDeck.add(new Tile("L", Assets.blueTile));	
		}
		
		for(int i = 0; i < 12; i++) {
			tileDeck.add(new Tile("I", Assets.blueTile));	
		}
		
		//Currently filling spots for tiles that are unmovable tiles
		for(int i = 0; i < 16; i++) {
			tileDeck.add(new Tile("I", Assets.blueTile));	
		}
		
		
	}
	
}
