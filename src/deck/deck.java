package deck;

import java.util.LinkedList;

public class deck {
	LinkedList<CardSlot> deck = new LinkedList<CardSlot>();
	
	private Game game;
	
	public LinkedList<CardSlot> getDeck() {
		return deck;
	}

	//public static Object deck;
	public deck(Game game) {
		this.game = game;
		loadDeck();
	}
	
	private void loadDeck() {
		for(int i = 1; i < 53; i++) {
			int stage = 0;
			if(i < 13) {
				stage = 0;
			} else if(13 <= i && i < 26) {
				stage = 1;
			} else if(26 <= i && i <= 39) {
				stage = 2;
			} else if(i > 39) {
				stage = 3;
			}
			//#################################
			CardSlot newCardSlot = null;
			Card newCard = null;
			
			if(stage == 3) {
				newCard = new Card(game, i, "Spades", Integer.toString(Math.max(i-39, 1)));
			}else if(stage == 2) {
				newCard = new Card(game, i, "Hearts", Integer.toString(Math.max(i-26, 1)));			
			}else if(stage == 1) {
				newCard = new Card(game,  i, "Diamonds", Integer.toString(Math.max(i-13, 1)));	
			}else if(stage == 0) {
				newCard = new Card(game, i, "Clubs", Integer.toString(i));				
			}
			newCardSlot = new CardSlot(game, newCard, i-1);
			deck.add(newCardSlot);
			
			
		}
	}
	
	public String ToString() {
		String temp ="";
		int index = 0;
		for (CardSlot cardSlot : deck)
		{
			//temp += cardSlot.ToString();
			if (index < 51)
			{
				temp += ", ";
			}
			index++;
		}
		return temp;
	}

	public void setDeck(LinkedList<Card> deck) {
		deck = deck;
	}

}