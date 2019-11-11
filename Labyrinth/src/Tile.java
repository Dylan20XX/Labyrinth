import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton {
	
	public static Tile defaultTile = new Tile("I", Assets.blueTile);
	public static final int TILE_SIZE = 60;
	
	private String type; //I, T, or L tile
	private boolean hasTreausre;
	private String treasure;
	private int rotation;
	
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

	@Override
	public String toString() {
		return "Tile [type=" + type + ", hasTreausre=" + hasTreausre + ", treasure=" + treasure + "]";
	}
	
	//This method returns a scaled tile image
	private static ImageIcon imageSetup(String imagePath) {
		return new ImageIcon(new ImageIcon(imagePath).
				getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH));
	}
	
}
