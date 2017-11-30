package deck.display;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import deck.Game;
import deck.ID;

public class Display {
	private Game game;
	public static JFrame frame;
	private Canvas canvas;
	
	private JPanel bidPanel;
	private JTextField bidField;
	private JLabel bidLabel;
	private JButton bidButton;
	
	private JPanel statsPanel;
	private JLabel roundLabel;
	private JLabel trumpLabel;

	private String title;
	private int width, height;
	
	public Display(Game game, String title, int width, int height) {
		this.game = game;
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay() {
		//Initializing the Jframe, setting its size, location etc...
		frame = new JFrame(title);
		bidPanel = new JPanel();
		bidField = new JTextField("00");
		bidLabel = new JLabel("Bid Amount: ");
		bidButton = new JButton("Bid now!");
		
		bidField.setColumns(1);
		bidField.revalidate();
		bidPanel.add(bidLabel);
		bidPanel.add(bidField);
		bidPanel.add(bidButton);
		
		/*
		this.roundLabel = new JLabel("The current round is: 0");
		this.trumpLabel = new JLabel("There is no trump...");
		this.statsPanel = new JPanel();
		
		
		roundLabel.setBounds(10, 50, 50, 10 );
		trumpLabel.setBounds(10, 30, 50, 10);
		
		statsPanel.setBounds(20, 75, 250, 150);
		
		statsPanel.add(roundLabel);
		statsPanel.add(trumpLabel);
	
		*/
		//makes the button button
		
	    bidButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//RoundHandler.playerBid = bidField.getText();
	    		//System.out.println("Player1's Bid = " + bidField.getText());
	    		HandlerRoundPlayer();
	    		
	    	}
	    });
	    
	    
		
		bidPanel.setBounds(20, 20, 250, 40);
		bidButton.setBounds(100, 100, 100, 100 );
		bidField.setBounds(100, 100, 150, 100 );
		
		frame.setSize(width, height);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Initializing Canvas, setting it's size.
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		//Add the canvas to the main JFrame, the 'pack' it.
		frame.add(bidPanel);
		//frame.add(statsPanel);
		frame.add(canvas);
		
		frame.pack();
		
		bidPanel.setVisible(false);
		bidField.setVisible(true);
		bidButton.setVisible(true);
		bidLabel.setVisible(true);
		
	}
	
	public void HandlerRoundPlayer() {
		game.getGameState().getRoundHandler().getPlayerTurn().hideBidPanel();
		int pos = game.getGameState().getRoundHandler().getPlayerTurn().getListPosition() + 1;
		System.out.println("List Pos: " + game.getGameState().getRoundHandler().getPlayerTurn().getListPosition() + " Length : " + (game.getGameState().getPlayers().size() - 1));
		if(game.getGameState().getRoundHandler().getPlayerTurn().getID() == ID.Player) { 
			game.getGameState().getRoundHandler().getPlayerTurn().setPlayerBid(Integer.parseInt(bidField.getText()));
			System.out.println("PLAYER HAS BID " + game.getGameState().getRoundHandler().getPlayerTurn().getPlayerBid());
		}
		if(pos <= game.getGameState().getPlayers().size() - 1) {
		
			game.getGameState().getRoundHandler().setPlayerTurn(game.getGameState().getPlayers().get(pos));
			game.getGameState().getRoundHandler().GetPlayerBids();
		
		} else {
			//No more players left, gone through all and AI also. Move to next part of round
			//Wait for user to click a card.
			//force enity ai to bid randomly in rounds range
			game.getGameState().getRoundHandler().setReadyToShowCard(true);
		}
	}
	
	public JPanel getBidPanel() {
		return bidPanel;
	}

	public void setBidPanel(JPanel bidPanel) {
		this.bidPanel = bidPanel;
	}

	public JTextField getBidField() {
		return bidField;
	}

	public void setBidField(JTextField bidField) {
		this.bidField = bidField;
	}

	public JLabel getBidLabel() {
		return bidLabel;
	}

	public void setBidLabel(JLabel bidLabel) {
		this.bidLabel = bidLabel;
	}

	public JButton getBidButton() {
		
		return bidButton;
	}

	public void setBidButton(JButton bidButton) {
		this.bidButton = bidButton;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
