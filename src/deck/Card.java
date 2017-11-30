package deck;

import java.awt.Rectangle;

public class Card {
	public ID getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(ID ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isAnimating() {
		return animating;
	}

	public void setAnimating(boolean animating) {
		this.animating = animating;
	}

	public boolean isBobbing() {
		return bobbing;
	}

	public void setBobbing(boolean bobbing) {
		this.bobbing = bobbing;
	}

	//added x and y position
	private int rank;
	private float xPos, yPos;
	private String name, suit;
	private boolean animating, bobbing;
	private float animTick, animStart;
	private float animDuration = 1.0f;
	private ID ownerId;
	private Game game;
	
	public Card(Game game, int rank, String suit, String name) {
		this.rank = rank;
		this.name = name;
		this.suit = suit;
		this.game = game;
		
		this.bobbing = true;
		
	}
	
	
	
	
	public void beginAnim() {
		setAnimating(true);
		this.animStart = game.getDt();
		
		game.getGameState().getRoundHandler().setReadyToShowCard(false);
		
	}
	
	public void animate() {
			int dist = (game.getHeight() - 200) - game.getHeight()/2 ;
			float pxlsPerFrame = dist / (60);
			float cPxls = 100 / 60;
			if(this.animDuration > game.getDt() - animStart ) {
				if(this.ownerId == ID.Player) {
					this.yPos -= pxlsPerFrame;
					this.xPos -= cPxls;
				} else {
					this.yPos += pxlsPerFrame;
					this.xPos += cPxls;
				}
			} else {
				this.animating = false;
				stopAnim();
			}
			
	
		//System.out.println("Animating");
		
	}
	
	public void stopAnim() {
		
	}
	
	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public String ToString()
	{
		String temp = "Rank: " + Integer.toString(rank) + ", Name: " + this.name + ", Suit: " + this.suit;
		return temp;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle((int) (this.xPos - 5), (int) (this.yPos - 5), 73, 98);
	}
}
