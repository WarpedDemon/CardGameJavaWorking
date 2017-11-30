package deck.states;
import java.awt.Graphics;

import deck.Dealer;
import deck.Game;
import deck.Player;



public abstract class State {
	//Bundles common things for states.
	
	protected Player player;
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	protected Dealer dealer;
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	public static State getState() {
		return currentState; 
	}
	
	//CLASS
	protected Game game;

	public State(Game game) {
		this.game =  game;
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
