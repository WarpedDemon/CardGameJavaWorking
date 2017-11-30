package deck;

import java.util.LinkedList;

import deck.gfx.Assets;

//Dealer must give each player's hand a unique card from the deck.
public class Dealer {
	private Game game;
	private deck deck;
	private LinkedList<Player> playerList;
	
	//HandSize
	
	private int INITIAL_HAND_SIZE = 1;
	
	public Dealer(Game game){
		this.game = game;
		this.deck = new deck(game);
		this.playerList = game.getGameState().getPlayers();

	}
	
	public void ReturnDeck() {
		//returns deck.deck -> cards

	}
	
	public void PlayerHand(Player player) {
		LinkedList<String> set = FormRandomHand();
		System.out.println("Card amount: " + set.size());
		int handPos = 0;

		LinkedList<Card> newHand = player.getHand();
		
		for(String number : set) {
			Card cardtoAdd = deck.getDeck().get(Integer.parseInt(number)).getCard();
			cardtoAdd.setOwnerId(player.getID());
			newHand.add(cardtoAdd);
		}
			
		
		player.setHand(newHand);
		
		
		
		System.out.println("Card remaining in deck: " + deck.getDeck().size());
		
		for(int i = 0; i < player.getHand().size(); i++) {
			int xPos= 0;
			int width = 73;
			if(player.getHand().size() % 2 == 0) {
				//even hand size.
				int midpoint = player.getHand().size() / 2;
				if(i <= midpoint) {
					xPos = (int) (game.getWidth() / 2 - (midpoint-i) * 75);
				} else {
					xPos = (int) (game.getWidth() / 2 + (i-midpoint) * 75);
				}
			} else {
				//odd
				int midpoint = (int) (Math.ceil(player.getHand().size() / 2));
				if(i < midpoint) {
					xPos = (int) (game.getWidth() / 2 - (width/2) - (midpoint - i) *75);
				}
				if(i == midpoint) {
					xPos = (int) (game.getWidth() / 2 - (73/2));
				}
				if(i > midpoint) {
					xPos = (int) (game.getWidth() / 2 - (width/2) + (i-midpoint)*75);
				}
			}
			//int xPos = (int) (3);
			
			player.getHand().get(i).setxPos(xPos);
		}
		//System.out.println(player.getHand().toString());

	}
	
	public LinkedList<String> FormRandomHand() {
		//int[] numberSet = new int[10];
		LinkedList<String> numberSet = new LinkedList<String>();
		
		int random;

		for(int i = 0; i < INITIAL_HAND_SIZE; i++) {
			random = (int) (Math.random() * 52);
			System.out.println(random);
			while(InCurrentList(numberSet, random)) {
				random = (int) (Math.random() * 52);
				System.out.println("Random: " + random);
			}
			//System.out.println(i);
			numberSet.add(Integer.toString( random ));
		}
		
		return numberSet;
	}
	
	public boolean InCurrentList(LinkedList<String> set, int number) {
		for(int i = 0; i < set.size(); i++) {
			
			System.out.println("Testing: " + set.get(i) + ", " + Integer.toString(number));
			if(Integer.parseInt(set.get(i)) == number) {
				System.out.println("EXCEPTION");
				return true;
			}
		}
		
		return false;
	}
}
