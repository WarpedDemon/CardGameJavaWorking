package deck.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPane {
	private JPanel statsPanel;
	private JLabel roundLabel;
	private JLabel trumpLabel;
	private JFrame frame;
	
	public StatsPane(JFrame frame) {
		this.frame = frame;
		
		this.roundLabel = new JLabel("The current round is: 0");
		this.trumpLabel = new JLabel("There is no trump...");
		this.statsPanel = new JPanel();
		
		roundLabel.setBounds(10, 10, 50, 10 );
		trumpLabel.setBounds(10, 30, 50, 10);
		
		statsPanel.setBounds(100, 50, 100, 100);
		
		statsPanel.add(roundLabel);
		statsPanel.add(trumpLabel);
		
		roundLabel.setVisible(true);
		trumpLabel.setVisible(true);
		statsPanel.setVisible(false);
		frame.getContentPane().add(statsPanel, BorderLayout.CENTER);

	}
	
	public void showStatsPanel() {
		
		statsPanel.setVisible(true);
		statsPanel.repaint();
		statsPanel.revalidate();
		
		System.out.println("Revalidated");
		
	}
	
}
