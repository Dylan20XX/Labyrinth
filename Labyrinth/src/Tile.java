import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel {
	
	//4 L corner tiles
	//12 T unmovable treasure tiles
	//6 T movable treasure tiles
	//6 L movable treasure tiles
	//10 L tiles
	//12 I tiles
	public static ImageIcon blueTile = imageSetup("Images/blueTile.png");
	public static ImageIcon redTile = imageSetup("Images/redTile.png");
	public static ImageIcon yellowTile = imageSetup("Images/yellowTile.png");
	public static ImageIcon greenTile = imageSetup("Images/greenTile.png");
	
	public static Tile defaultTile = new Tile("I", blueTile);
	
	private String type; //I, T, or L tile
	boolean hasTreausre;
	String treasure;
	
	//Constructor for tile with treasure
	public Tile(String type, String treasure, ImageIcon image) {
		super();
		this.type = type;
		this.hasTreausre = true;
		this.treasure = treasure;
		setIcon(image);
	}
	
	//Constructor for tile without treasure
	public Tile(String type, ImageIcon image) {
		super();
		this.type = type;
		this.hasTreausre = false;
		this.treasure = "";
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
