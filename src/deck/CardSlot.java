package deck;

public class CardSlot {
	private Card card;
	private boolean taken;
	private int position;
	private Game game;
	
	public CardSlot(Game game, Card card, int position) {
		this.card = card;
		this.taken = false;
		this.position = position;
		this.game = game;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}