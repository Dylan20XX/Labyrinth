import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	
	private Random r = new Random();
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public Player(ImageIcon image) {
		setIcon(image);
		drawHand();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	//This method places 5 cards in the player's hand
	private void drawHand() {
		for(int i = 0; i < 5; i++) {
			
			int cardIndex = 0;
			if(Tile.tileDeck.size() > 1) {
				cardIndex = r.nextInt(Tile.tileDeck.size());
			}
			
			hand.add(Card.deck.remove(cardIndex));
		}
	}
	
}
