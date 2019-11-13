import java.awt.Image;

import javax.swing.ImageIcon;

//This class is used to set up image icons to be used for tiles, cards, and players
public class Assets {
	
	//4 L corner tiles
	//12 T unmovable treasure tiles
	//6 T movable treasure tiles
	//6 L movable treasure tiles
	//10 L tiles
	//12 I tiles
	
	// If there is tile in the picture name then it means the tile is permenant
	// 0 = down 1 = left 2 = up 3 = right
	
	//TEMP=====================
	public static ImageIcon blueTile = tileImageSetup("Images/blueTile.png");
	public static ImageIcon redTile = tileImageSetup("Images/redTile.png");
	public static ImageIcon yellowTile = tileImageSetup("Images/yellowTile.png");
	public static ImageIcon greenTile = tileImageSetup("Images/greenTile.png");
	public static ImageIcon whiteTile = tileImageSetup("Images/whiteTile.png");
	
	public static ImageIcon p1 = tileImageSetup("Images/translucentGreen.png");
	public static ImageIcon p2= tileImageSetup("Images/redTile.png");
	public static ImageIcon p3 = tileImageSetup("Images/redTile.png");
	public static ImageIcon p4 = tileImageSetup("Images/redTile.png");
	//TEMP====================
	
	//Corner tiles
	public static ImageIcon p1Corner;
	public static ImageIcon p2Corner;
	public static ImageIcon p3Corner;
	public static ImageIcon p4Corner;
	
	//Unmovable treasure tiles
	public static ImageIcon treasureTile1;
	public static ImageIcon treasureTile2;
	public static ImageIcon treasureTile3;
	public static ImageIcon treasureTile4;
	public static ImageIcon treasureTile5;
	public static ImageIcon treasureTile6;
	public static ImageIcon treasureTile7;
	public static ImageIcon treasureTile8;
	public static ImageIcon treasureTile9;
	public static ImageIcon treasureTile10;
	public static ImageIcon treasureTile11;
	public static ImageIcon treasureTile12;

	//Movable treasure tiles
	public static ImageIcon treasureTileBat[] = new ImageIcon [4];
	public static ImageIcon treasureTileDragon[] = new ImageIcon [4];
	public static ImageIcon tileL[] = new ImageIcon [4];
	public static ImageIcon tileI[] = new ImageIcon [4];
	public static ImageIcon tileDragon[] = new ImageIcon [4];
	public static ImageIcon tileGhostWaving[] = new ImageIcon [4];
	public static ImageIcon tileGhostBottle[] = new ImageIcon [4];
	public static ImageIcon permenantTiles[] = new ImageIcon [16];
	public static ImageIcon treasureCards[] = new ImageIcon [24];
	
	
	public static void imageInitializer(){
		//Have an array for each tile that is moving and there are 13 movable tiles in total
		
		treasureTileBat[0]= tileImageSetup("Images/Bat0.png");
		treasureTileBat[1]= tileImageSetup("Images/Bat1.png");
		treasureTileBat[2]= tileImageSetup("Images/Bat2.png");
		treasureTileBat[3]= tileImageSetup("Images/Bat3.png");
		
		tileL[0]= tileImageSetup("Images/L0.png");
		tileL[1]= tileImageSetup("Images/L1.png");
		tileL[2]= tileImageSetup("Images/L2.png");
		tileL[3]= tileImageSetup("Images/L3.png");
		
		tileI[0]= tileImageSetup("Images/I0.png");
		tileI[1]= tileImageSetup("Images/I1.png");
		tileI[2]= tileImageSetup("Images/I2.png");
		tileI[3]= tileImageSetup("Images/I3.png");
		
		tileDragon[0]= tileImageSetup("Images/Dragon0.png");
		tileDragon[1]= tileImageSetup("Images/Dragon1.png");
		tileDragon[2]= tileImageSetup("Images/Dragon2.png");
		tileDragon[3]= tileImageSetup("Images/Dragon3.png");
		
		tileGhostBottle[0]= tileImageSetup("Images/GhostBottle0.png");
		tileGhostBottle[1]= tileImageSetup("Images/GhostBottle1.png");
		tileGhostBottle[2]= tileImageSetup("Images/GhostBottle2.png");
		tileGhostBottle[3]= tileImageSetup("Images/GhostBottle3.png");
		
		treasureTileDragon[0] = tileImageSetup("Images/Dragon0.png");
		treasureTileDragon[1] = tileImageSetup("Images/Dragon1.png");
		treasureTileDragon[2] = tileImageSetup("Images/Dragon2.png");
		treasureTileDragon[3] = tileImageSetup("Images/Dragon3.png");
		
		tileGhostWaving[0] = tileImageSetup("Images/GhostWaving0.png");
		tileGhostWaving[1] = tileImageSetup("Images/GhostWaving1.png");
		tileGhostWaving[2] = tileImageSetup("Images/GhostWaving2.png");
		tileGhostWaving[3] = tileImageSetup("Images/GhostWaving3.png");
		
		permenantTiles[0] = tileImageSetup("Images/RedCornerTile.png");
		permenantTiles[1] = tileImageSetup("Images/BookTile.png");
		permenantTiles[2] = tileImageSetup("Images/BagTile.png");
		permenantTiles[3] = tileImageSetup("Images/YellowTileCorner.png");
		permenantTiles[4] = tileImageSetup("Images/PaintingTile.png");
		permenantTiles[5] = tileImageSetup("Images/Crown.png");
		permenantTiles[6] = tileImageSetup("Images/KeysTile.png");
		permenantTiles[7] = tileImageSetup("Images/SkullTile.png");
		permenantTiles[8] = tileImageSetup("Images/RingTile.png");
		permenantTiles[9] = tileImageSetup("Images/BottleTile.png");
		permenantTiles[10] = tileImageSetup("Images/GreenTile.png");
		permenantTiles[11] = tileImageSetup("Images/SwordTile.png");
		permenantTiles[12] = tileImageSetup("Images/GreenCornerTile.png");
		permenantTiles[13] = tileImageSetup("Images/CandlesTile.png");
		permenantTiles[14] = tileImageSetup("Images/MonsterTile.png");
		permenantTiles[15] = tileImageSetup("Images/BlueCornerTile.png");
		
		treasureCards[0] = tileImageSetup("Images/CardSkull.png");
		treasureCards[1] = tileImageSetup("Images/CardMoth.png");
		treasureCards[2] = tileImageSetup("Images/CardBottle.png");
		treasureCards[3] = tileImageSetup("Images/CardCandles.png");
		treasureCards[4] = tileImageSetup("Images/CardGhostWaving.png");
		treasureCards[5] = tileImageSetup("Images/CardGhostBottle.png");
		treasureCards[6] = tileImageSetup("Images/CardPainting.png");
		treasureCards[7] = tileImageSetup("Images/CardSorceress.png");
		treasureCards[8] = tileImageSetup("Images/CardSpider.png");
		treasureCards[9] = tileImageSetup("Images/CardBat.png");
		treasureCards[10] = tileImageSetup("Images/CardRat.png");
		treasureCards[11] = tileImageSetup("Images/CardBag.png");
		treasureCards[12] = tileImageSetup("Images/CardSword.png");
		treasureCards[13] = tileImageSetup("Images/CardMonster.png");
		treasureCards[14] = tileImageSetup("Images/CardKeys.png");
		treasureCards[15] = tileImageSetup("Images/CardGreen.png");
		treasureCards[16] = tileImageSetup("Images/CardScarab.png");
		treasureCards[17] = tileImageSetup("Images/CardLadyPig.png");
		treasureCards[18] = tileImageSetup("Images/CardLizard.png");
		treasureCards[19] = tileImageSetup("Images/CardOwl.png");
		treasureCards[20] = tileImageSetup("Images/CardBook.png");
		treasureCards[21] = tileImageSetup("Images/CardRing.png");
		treasureCards[22] = tileImageSetup("Images/CardDragon.png");
		treasureCards[23] = tileImageSetup("Images/CardCrown.png");
		
	}
	
	//This method returns a scaled tile image (60x60)
	private static ImageIcon tileImageSetup(String imagePath) {
		return new ImageIcon(new ImageIcon(imagePath).
				getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH));
	}
	
	//This method returns a scaled player image (30x30)
	private static ImageIcon playerImageSetup(String imagePath) {
		return new ImageIcon(new ImageIcon(imagePath).
				getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH));
	}
	
	
}
