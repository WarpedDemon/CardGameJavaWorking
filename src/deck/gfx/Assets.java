package deck.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static Spritesheet cardSpritesheet;
	public static BufferedImage[] textureList = new BufferedImage[53];
	
	public Assets() {

	}

	public static void init() {
		
		cardSpritesheet = new Spritesheet(ImageLoader.loadImage("/textures/cards.png"));
		
		
		for(int i = 1; i < 14; i++) {
			
			textureList[i] = cardSpritesheet.crop((i-1)*73, 0, 73, 98);
		}
		for(int i = 1; i < 14; i++) {
			
			textureList[13+i] = cardSpritesheet.crop((i-1)*73, 98, 73, 98);
		}
		for(int i = 1; i < 14; i++) {
			
			textureList[26+i] = cardSpritesheet.crop((i-1)*73, 98*2, 73, 98);
		}
		for(int i = 1; i < 14; i++) {
			System.out.println(i);
			textureList[39+i] = cardSpritesheet.crop((i-1)*73, 98*3, 73, 98);
		}
	}
}