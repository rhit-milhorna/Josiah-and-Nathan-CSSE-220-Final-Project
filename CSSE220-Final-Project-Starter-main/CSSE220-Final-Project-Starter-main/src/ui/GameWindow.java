package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameModel;
/**
 * GameWindow owns the frame
 **/
public class GameWindow {
	
	private final JFrame frame;
	private final GameModel model;
	
	public GameWindow(GameModel model) {
		this.model = model;
		this.frame = new JFrame("CSSE220 Final Project");
		
		JPanel cards = new JPanel(new CardLayout());
		StartPanel startpanel = new StartPanel();
		GameComponent game = new GameComponent(this.model);
		cards.add(startpanel, "Start");
		cards.add(game, "Game");
		this.frame.setContentPane(cards);
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "Start");
		
		startpanel.start.addActionListener(e -> {
			game.startTimer();
			cl.show(cards, "Game");
			game.requestFocus();
		});

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(new GameComponent(this.model));
		this.frame.setSize(1920, 1080);
		this.frame.setLocationRelativeTo(null);
	}

	public void show() {
		this.frame.setVisible(true);
		}
}
