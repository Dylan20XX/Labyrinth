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
	
	public static void Imageinitializer(){
		treasureTileBat[0]= tileImageSetup("Images/Bat0.png");
		treasureTileBat[1]= tileImageSetup("Images/Bat1.png");
		treasureTileBat[2]= tileImageSetup("Images/Bat2.png");
		treasureTileBat[3]= tileImageSetup("Images/Bat3.png");
		treasureTileDragon[0] = tileImageSetup("Images/Dragon0.png");
		treasureTileDragon[1] = tileImageSetup("Images/Dragon1.png");
		treasureTileDragon[2] = tileImageSetup("Images/Dragon2.png");
		treasureTileDragon[3] = tileImageSetup("Images/Dragon3.png");
		//dgasgvd
		
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
