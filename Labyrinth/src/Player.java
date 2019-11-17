import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	
	private Random r = new Random();
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	private int row;
	private int col;
	
	public Player(ImageIcon image, int row, int col) {
		this.row = row;
		this.col = col;
		setIcon(image);
		drawHand();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	//This method places 5 cards in the player's hand
	private void drawHand() {
		for(int i = 0; i < 5; i++) {
			
			int cardIndex = 0;
			if(Card.deck.size() > 1) {
				cardIndex = r.nextInt(Card.deck.size());
			}
			
			hand.add(Card.deck.remove(cardIndex));
		}
	}
	
}
