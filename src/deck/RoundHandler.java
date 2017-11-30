package deck;

import java.util.LinkedList;

import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import deck.display.StatsPane;
import deck.display.Display;

public class RoundHandler {
	//trumps
	//0 none
	//1 Spades
	//2 Hearts
	//3 Diamonds
	//4 Clubs
	

	
	public String getWinReason() {
		return winReason;
	}

	public void setWinReason(String winReason) {
		this.winReason = winReason;
	}

	public boolean isHasWinner() {
		return hasWinner;
	}

	public void setHasWinner(boolean hasWinner) {
		this.hasWinner = hasWinner;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public boolean isReadyToShowCard() {
		return readyToShowCard;
	}

	public void setReadyToShowCard(boolean readyToShowCard) {
		this.readyToShowCard = readyToShowCard;
	}

	public static int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		currentRound = currentRound;
	}

	public int getTrump() {
		return trump;
	}

	public void setTrump(int trump) {
		this.trump = trump;
	}

	public static String getCurrentTrump() {
		return currentTrump;
	}

	public static void setCurrentTrump(String currentTrump) {
		RoundHandler.currentTrump = currentTrump;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}

	private Game game;
	public static int currentRound = 1;
	private int trump;
	public static String currentTrump = "None";
	private Player playerTurn;
	
	private boolean readyToShowCard = false;
	
	private boolean hasWinner = false;
	private Player winner;
	private String winReason = "none";
	
	//these should not be used 
	//these are jeryrigged vars
	//they force things to work
	// do not use
	//private Player player1;
	//private int player1Bid;
	//private Player player2;
	//private int player2Bid;

	public RoundHandler(Game game) {
		this.game = game;
	}
	
	public String nextTrump(String trump){
		if(trump == "None") {
			return "Clubs";
		}else if(trump == "Clubs") {
			return "Diamonds";
		}else if(trump == "Diamonds") {
			return "Hearts";
		}else if(trump == "Hearts") {
			return "Spades";
		}else if(trump == "Spades") {
			return "Clubs";
		}else {
			return null;
		}
	}
	
	public void StartNewGame(Player player, Player AI) {
		game.getGameState().setGameHand(new LinkedList<GameHandSlot>());
		this.hasWinner = false;
		this.currentRound += 1;
		this.currentTrump = nextTrump(this.currentTrump); //TO-DO: Randomly select trump function.

		this.playerTurn = game.getGameState().getPlayers().getFirst();
		
		for(int i = 0; i < game.getGameState().getPlayers().size() ;i++) {
			game.getGameState().getDealer().PlayerHand(game.getGameState().getPlayers().get(i));
		}
		
		StatsPane statsPane = new StatsPane(game.getDisplay().getFrame());
		statsPane.showStatsPanel();
		
		GetPlayerBids();
	}
	
	public void GetPlayerBids() {
		if(playerTurn.getID() == ID.AI) { GetAiBids(); return; }
		System.out.println("Waiting for player " + playerTurn.getID() + " to act...");
		playerTurn.showBidPanel();
		
	}
	
	public void GetAiBids() {
		playerTurn.setPlayerBid(3);
		System.out.println("AI HAS BID " + playerTurn.getPlayerBid());
		game.getDisplay().HandlerRoundPlayer();
	}
	
	public int currentRoundSize(int currentRound){
		int handSize = currentRound;
		return handSize;
	}
	
	public int currentTrump(int handSize) {
		//make hand size == currentRound
		
		currentRoundSize(currentRound);
		
		if (handSize == 1) {
			trump = 0;
		}else if(handSize == 2) {
			trump = 1;
		}else if(handSize == 3) {
			trump = 2;
		}else if(handSize == 4) {
			trump = 3;
		}else if(handSize == 5) {
			trump = 4;
		}else if(handSize == 6) {
			trump = 0;
		}else if(handSize == 7) {
			trump = 1; 
		}else if(handSize == 8) {
			trump = 2;
		}else if(handSize == 9) {
			trump = 3;
		}else if(handSize == 10) {
			trump = 4;
		}
		
		return trump;
	}
}
