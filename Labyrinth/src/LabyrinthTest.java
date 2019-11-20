
//This is the class is used to run the program
public class LabyrinthTest {
	
	public static void main(String[] args) {
		Assets.imageInitializer();
		Tile.setupTileDeck();
		Card.deckSetup();
		new LabyrinthTitleScreen();
	}
	
}
