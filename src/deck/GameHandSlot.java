package deck;

public class GameHandSlot {
	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	private Card card;
	private Player player;
	private int positionX, positionY;
	private Game game;
	
	public GameHandSlot(Game game, Card card, int positionX, int positionY, Player player) {
		this.card = card;
		this.player = player;
		this.positionY = positionY;
		this.positionX = positionX;
		this.game = game;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int position) {
		this.positionX = position;
	}
}
