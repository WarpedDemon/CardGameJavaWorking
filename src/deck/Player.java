package deck;

import java.util.LinkedList;

public class Player {
	
	public int getPlayerScore() {
		return PlayerScore;
	}

	public void setPlayerScore(int playerScore) {
		PlayerScore = playerScore;
	}

	public ID getID() {
		return ID;
	}

	public void setID(ID iD) {
		ID = iD;
	}

	public int getListPosition() {
		return ListPosition;
	}

	public void setListPosition(int listPosition) {
		ListPosition = listPosition;
	}

	//class variables
	private Game game;
	private int PlayerNumber, PlayerPoints, PlayerBid, ListPosition, PlayerScore;
	private String Name;
	private ID ID;
	
	public Player(ID id, Game game, int lp) {
		this.game = game;
		this.ID = id;
		this.PlayerNumber = 0;
		this.PlayerPoints = 0;
		this.Name = "Username";
		this.PlayerBid = 0;
		this.ListPosition = lp;
		this.PlayerScore = 10;
	}
	
	public int getPlayerBid() {
		return PlayerBid;
	}

	public void setPlayerBid(int playerBid) {
		PlayerBid = playerBid;
	}

	LinkedList<Card> hand = new LinkedList<Card>();
	
	public LinkedList<Card> getHand() {
		return hand;
	}

	public void setHand(LinkedList<Card> hand) {
		this.hand = hand;
	}
	
	public int getPlayerNumber() {
		return PlayerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		PlayerNumber = playerNumber;
	}

	public int getPlayerPoints() {
		return PlayerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		PlayerPoints = playerPoints;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public void showBidPanel() {
		if(this.ID == ID.Player) {
			//player
			game.getDisplay().getBidPanel().setVisible(true);
			game.getDisplay().getBidPanel().revalidate();
			game.getDisplay().getBidPanel().repaint();
		}else {
			//ai
			game.getGameState().getRoundHandler().GetAiBids();
		}
	}
	
	public void hideBidPanel() {
		game.getDisplay().getBidPanel().setVisible(false);
		game.getDisplay().getBidPanel().revalidate();
		game.getDisplay().getBidPanel().repaint();
	}
	
	public void ToString()
	{
		String user = "PlayerNumber: " + Integer.toString(PlayerNumber) + ", PlayerPoints: "+ Integer.toString(PlayerPoints) + this.PlayerPoints + ", Name: " + this.Name;
		String temp ="";
		
		int index = 0;
		System.out.println(hand);
		for (Card card : this.hand)
		{
			index++;
			System.out.println("["+ index +"] Card Found: " + card.getName() + ", " + card.getRank() + ", " + card.getSuit());
		}
		return;
	}
}
