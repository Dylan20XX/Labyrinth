import java.awt.Image;
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
	
	//Constructor for tile with treasure
	public Tile(String type, String treasure, ImageIcon image) {
		super();
		this.type = type;
		this.hasTreausre = true;
		this.treasure = treasure;
		this.rotation = 0;
		setIcon(image);
	}
	
	//Constructor for tile without treasure
	public Tile(String type, ImageIcon image) {
		super();
		this.type = type;
		this.hasTreausre = false;
		this.treasure = "";
		this.rotation = 0;
		setIcon(image);
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

	public void setRotation(int rotation) {
		this.rotation = rotation;
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
		
		tileDeck.add(new Tile("T", "bat", Assets.blueTile));
		tileDeck.add(new Tile("T", "dragon", Assets.blueTile));
		tileDeck.add(new Tile("T", "ghostBottle", Assets.blueTile));
		tileDeck.add(new Tile("T", "ghostWaving", Assets.blueTile));
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
