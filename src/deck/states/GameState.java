package deck.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import deck.Card;
import deck.Dealer;
import deck.Game;
import deck.GameHandSlot;
import deck.ID;
import deck.Player;
import deck.RoundHandler;
import deck.gfx.Assets;

public class GameState extends State {

	public LinkedList<GameHandSlot> getGameHand() {
		return GameHand;
	}

	public void setGameHand(LinkedList<GameHandSlot> gameHand) {
		GameHand = gameHand;
	}

	LinkedList<GameHandSlot> GameHand = new LinkedList<GameHandSlot>();
	
	public RoundHandler getRoundHandler() {
		return roundHandler;
	}

	public void setRoundHandler(RoundHandler roundHandler) {
		this.roundHandler = roundHandler;
	}

	LinkedList<Player> PlayerList = new LinkedList<Player>();
	
	private Game game;
	
	private Player AI;
	public Player getAI() {
		return AI;
	}

	public void setAI(Player aI) {
		AI = aI;
	}

	private RoundHandler roundHandler;
	
	private float testTick = 0;
	
	public GameState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		this.game = game;
		
	}
	
	public void init() {
		this.player = new Player(ID.Player, game, 0);
		this.dealer = new Dealer(game);
		this.AI = new Player(ID.AI, game, 1);
		
		PlayerList.add(player);
		PlayerList.add(AI);
		System.out.println(ToString());
		player.ToString();
	
		roundHandler = new RoundHandler(game);
		roundHandler.StartNewGame(player, AI);
	}	
	//Animations
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		testTick += 0.05;
		for(int i = 0; i < player.getHand().size(); i++) {
			if(player.getHand().get(i).isBobbing()) {
				player.getHand().get(i).setyPos((float) ((game.getHeight()-200)-64 + 32 * Math.sin(testTick + (i))));
			}
			if(player.getHand().get(i).isAnimating()) {
				player.getHand().get(i).animate();
			}
		}
		
		for(int i = 0; i < AI.getHand().size(); i++) {
			if(AI.getHand().get(i).isBobbing()) {
				AI.getHand().get(i).setyPos((float) ((200) + 32 * Math.sin(testTick + (i))));
			}
			if(AI.getHand().get(i).isAnimating()) {
				AI.getHand().get(i).animate();
			}
		}
		
		
		for(int i = 0; i < GameHand.size(); i++) {
			Card card = GameHand.get(i).getCard();
			if(card.isAnimating()) {
				card.animate();
			}
		}
	}
	
	//draws hand
	// RETARDED JAMES COMMENT !!!sdaSGFDAS
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for(int i = 0; i < player.getHand().size(); i++) {
			g.drawImage(Assets.textureList[player.getHand().get(i).getRank()], (int) player.getHand().get(i).getxPos(), (int) (player.getHand().get(i).getyPos()), null);
		}
		
		for(int i = 0; i < AI.getHand().size(); i++) {
			g.drawImage(Assets.textureList[AI.getHand().get(i).getRank()], (int) AI.getHand().get(i).getxPos(), (int) (AI.getHand().get(i).getyPos()), null);
		}
		
		
		for(int i = 0; i < GameHand.size(); i++) {
			g.drawImage(Assets.textureList[GameHand.get(i).getCard().getRank()], (int) GameHand.get(i).getCard().getxPos(), (int) (GameHand.get(i).getCard().getyPos()), null);
		}
		
		renderGameStats(g,0);
		
		if(getRoundHandler().isHasWinner()) {
			winner(g, getRoundHandler().getWinner());
		}
	}
	
	public LinkedList<Player> getPlayers() {
		//System.out.println("WRD" + this.player);
		return PlayerList;
	}
	
	public int aiBid() {
		int imax = RoundHandler.currentRound;
		int i = (int) Math.random()*imax + 0;
		//ai logic use enity handler
		return i;
	}
	
	public void winner(Graphics g, Player winner) {
		
		//render winner on screen. There is always a winner.
		//there is a biased to the default drump card Spades Hearts Diamonds Clubs 
		
		g.setColor(new Color(240, 240, 240, 255));
		
		g.fillRect(20, 255, 250, 160);
		
		g.setColor(Color.black);
		//player turn
		g.drawString("The winner is: " + winner.getID(), 35, 280);
		g.drawString("Win Reason: " + roundHandler.getWinReason(), 35, 300);
		
		g.setColor(new Color(0,0,0,255));
		g.fillRect(35,  350, 100, 50);
		g.setColor(new Color(255,255,255,255));
		g.drawString("Continue", 60,  380);
		
	}
	
	public void renderGameStats(Graphics g, int winner){
		
		g.setColor(new Color(240, 240, 240, 255));
		
		g.fillRect(20, 75, 250, 160);
		
		g.setColor(Color.black);
		//player turn
		g.drawString("Its currently your turn!", 35, 90);
		//round
		g.drawString("The round is: " + RoundHandler.getCurrentRound(), 35, 110);
		//current score
	//	g.drawString("Your score is: " + player.getPlayerPoints(), 100, (game.getHeight()/2)+40);
		//current trump
		g.drawString("The current trump: " + RoundHandler.getCurrentTrump(), 35, 130);
		
		int drawPoint = 150;
		//score and bid for players
		for(int i = 0; i < PlayerList.size(); i++) {
			//set bids
			int playerBid = PlayerList.get(i).getPlayerBid();
			int aiBid = aiBid();
			
			if(PlayerList.get(i).getID() != ID.AI) {
				g.drawString("Player " + (i + 1) + "'s Bid: " + game.getGameState().getPlayers().getFirst().getPlayerBid(), 35, drawPoint + (20*i+10) );
				g.drawString("Player " + (i + 1) + "'s Score: " + game.getGameState().getPlayers().getFirst().getPlayerScore(), 35, drawPoint + (20*i+25) );
			} else {
				g.drawString("AI  's Bid: " + game.getGameState().getPlayers().getLast().getPlayerBid(), 35, drawPoint + (20*i+30) );
				g.drawString("AI  's Score: " + game.getGameState().getPlayers().getLast().getPlayerScore(), 35, drawPoint + (20*i+45) );
			}
			
			if(winner == 1) {
				g.drawString("Player Wins!", 35, drawPoint + (20*i+80));
			}if (winner == 2) {
				g.drawString("Ai Wins!", 35, drawPoint + (20*i+80));
			}else {
				//hhahahah nope
			}
		};
		
		//READY TO CLICK CARD STRING//
		if(roundHandler.isReadyToShowCard()) {
			g.setColor(Color.white);
			g.drawString("Click a card to begin!", 100, game.getHeight()/2);
		}
		
		//g.drawString("Your current bid: " + , 100, (game.getHeight()/2)+70);
		
//		g.drawString("Your enemies bid: " + , 100, (game.getHeight()/2)+85);
	}
	
	public String ToString()
	{
		String temp ="";
		int index = 0;
		
		for (Player Player : PlayerList)
		{
			System.out.println(Player.getName());
		}
		return temp;
	}
	
}
