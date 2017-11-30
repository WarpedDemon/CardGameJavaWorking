package deck;



import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//un used copyed code from other project ready for shaping !
import java.util.LinkedList;

import javax.swing.JFrame;

import deck.display.StatsPane;


public class MouseHandler implements MouseListener {
	
	private float mx;
	private float my;
	private Game game;
	 
	public MouseHandler(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		Player myPlayer = game.getGameState().getPlayers().getFirst();
		for(int i = 0; i < myPlayer.getHand().size(); i++) {
			Card card = myPlayer.getHand().get(i);
			if(card.getBounds().intersects(this.getBounds())) {
				System.out.println("Card clicked!: " + card.getName());
				
				onCardClicked(card);
				
			}
		}
		
		Rectangle buttonBounds = new Rectangle(35, 350, 100, 50 );
		if(buttonBounds.intersects(this.getBounds())) {
			game.getGameState().getRoundHandler().StartNewGame(game.getGameState().getPlayers().getFirst(), game.getGameState().getPlayers().getLast());
		}
		System.out.println(mx + ", "+ my);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {   
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void winner() {
		Player winner = null;
		Player loser = null;
		//System.out.println("u r bad and your function is shit");
		//this should be a function
		String trump = null;
		//String trumpWinner = null;
		//trumps
		//0 none
		//1 Spades
		//2 Hearts
		//3 Diamonds
		//4 Clubs
		
		//definer / converter for trump
		
		trump = game.getGameState().getRoundHandler().getCurrentTrump();
		
		//sets winner
		//check for the same
			
		Player player = game.getGameState().getPlayers().getFirst();
		Player AI = game.getGameState().getPlayers().getLast();
		
		Card playerCard = null;
		Card AICard = null;
		
		if(game.getGameState().getGameHand().getFirst().getPlayer() == player) {
			playerCard = game.getGameState().getGameHand().getFirst().getCard();
			AICard = game.getGameState().getGameHand().getLast().getCard();
		} else {
			playerCard = game.getGameState().getGameHand().getLast().getCard();
			AICard = game.getGameState().getGameHand().getFirst().getCard();
		}
		
		
		if(suitValue(playerCard.getSuit()) < suitValue(AICard.getSuit())) {
			//Ai will win only if the player has the trump suit.
			if(playerCard.getSuit() == trump) {
				//Player wins - Has trump card, AI does not.
				game.getGameState().getRoundHandler().setWinReason("Has the correct trump card!");
				winner = player;
				loser = AI;
			} else {
				//AI wins - Better suit value.
				game.getGameState().getRoundHandler().setWinReason("Has a better suit value!");
				winner = AI;
				loser = player;
			}
		}
		
		if(suitValue(playerCard.getSuit()) > suitValue(AICard.getSuit())) {
			if(AICard.getSuit() == trump) {
				//AI wins - has trump, player does not.
				game.getGameState().getRoundHandler().setWinReason("Has the correct trump card!");
				winner = AI;
				loser = player;
			} else {
				//Player wins - better suit value.
				game.getGameState().getRoundHandler().setWinReason("Has a better suit value!");
				winner = player;
				loser = AI;
			}
		}
		
		if(suitValue(playerCard.getSuit()) == suitValue(AICard.getSuit())) {
			//If same suit value
			if(AICard.getRank() > playerCard.getRank()) {
				//AI wins - better card value.
				game.getGameState().getRoundHandler().setWinReason("Has a better card value!");
				winner = AI;
				loser = player;
			} else {
				//Player wins - better card value.
				game.getGameState().getRoundHandler().setWinReason("Has a better card value!");
				winner = player;
				loser = AI;
			}
		}
		
		
		//progress made
		//game.getGameState().renderGameStats(game.g, winner);
		game.getGameState().getRoundHandler().setHasWinner(true);
		game.getGameState().getRoundHandler().setWinner(winner);
		winner.setPlayerScore(winner.getPlayerScore() + winner.getPlayerBid());
		loser.setPlayerScore(loser.getPlayerScore() - loser.getPlayerBid());
		System.out.println("Winner Has Been Declared!");
	}
	
	private int suitValue(String suit) {
		if(suit == "Clubs") 
			return 1;
		if(suit == "Hearts")
			return 3;
		if(suit == "Diamonds")
			return 2;
		if(suit == "Spades") 
			return 4;
		
		return 0;
	}
	
	
	public void onCardClicked(Card card) {
		if(game.getGameState().getRoundHandler().isReadyToShowCard()) {
			

			//add card to game hand
			game.getGameState().getGameHand().add(new GameHandSlot(game, card, (int) card.getxPos(), (int) card.getyPos(), game.getGameState().getPlayers().getFirst()));
			game.getGameState().getGameHand().add(new GameHandSlot(game, game.getGameState().getPlayers().getLast().getHand().getFirst(), (int) game.getGameState().getPlayers().getLast().getHand().getFirst().getxPos(), (int)  game.getGameState().getPlayers().getLast().getHand().getFirst().getyPos(), game.getGameState().getPlayers().getLast()));	
			
			for(int i = 0; i < game.getGameState().getGameHand().size(); i++) {
				game.getGameState().getGameHand().get(i).getCard().setBobbing(false);
				game.getGameState().getGameHand().get(i).getCard().beginAnim();
			}
			
			game.getGameState().getPlayers().getFirst().getHand().remove(card);
			game.getGameState().getPlayers().getLast().getHand().remove(game.getGameState().getPlayers().getLast().getHand().getFirst());
			
			
			winner();

		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) (this.mx - 5), (int) (this.my - 5), 10, 10);
	}
}
