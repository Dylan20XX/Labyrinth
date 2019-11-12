import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card extends JLabel{
	
	public static ArrayList<Card> deck = new ArrayList<Card>();
	
	//Unmovable Treasures - labeled in order that they appear on the gam board from top right
	public static Card bookCard = new Card("book");
	public static Card bagCard = new Card("bag");
	public static Card paintingCard = new Card("painting");
	public static Card crownCard = new Card("crown");
	public static Card keysCard = new Card("keys");
	public static Card skullCard = new Card("skull");
	public static Card ringCard = new Card("ring");
	public static Card bottleCard = new Card("bottle");
	public static Card greenCard = new Card("green");
	public static Card swordCard = new Card("sword");
	public static Card candlesCard = new Card("candles");
	public static Card monsterCard = new Card("monster");
	
	//Movable Treasures
	public static Card batCard = new Card("bat");
	public static Card dragonCard = new Card("dragon");
	public static Card ghostBottleCard = new Card("ghostBottle");
	public static Card ghostWavingCard = new Card("ghostWaving");
	public static Card ladyPigCard = new Card("ladyPig");
	public static Card lizardCard = new Card("lizard");
	public static Card mothCard = new Card("moth");
	public static Card owlCard = new Card("owl");
	public static Card ratCard = new Card("rat");
	public static Card scarabCard = new Card("scarab");
	public static Card sorceressCard = new Card("sorceress");
	public static Card spiderCard = new Card("spider");
	
	private String treasure;
	
	//Delete this constructor when images are cropped and added to Assets class
	public Card(String treasure) {
		super();
		this.treasure = treasure;
		deck.add(this); //add cards to deck arraylist
	}
	
	//Constructor method
	public Card(String treasure, ImageIcon image) {
		super();
		this.treasure = treasure;
		setIcon(image);
		deck.add(this);
	}
	
	//Getter and setter
	public String getTreasure() {
		return treasure;
	}
	
	public void setTreasure(String treasure) {
		this.treasure = treasure;
	}
	
}
