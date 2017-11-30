package deck;
//trumps based on card id
//1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1
//number = hand size
// 1 = none
// 2 = Spades
// 3 = Hearts
// 4 = Clubs
// 5 = Diamonds
//repeat

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import deck.display.Display;
import deck.gfx.Assets;
import deck.gfx.ImageLoader;
import deck.states.GameState;
import deck.states.State;

public class Game implements Runnable {
	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public float getDt() {
		return dt;
	}

	public void setDt(float dt) {
		this.dt = dt;
	}

	private Thread thread;
	private boolean running;
	
	private Display display;
	
	private BufferStrategy bs;
	public Graphics g;
	
	private GameState gameState;
	
	private double backgroundWidth = 300.00, backgroundHeight = 300.00;
	private int width = 1920, height = 1080;
	private int rows = (int) Math.ceil(height/backgroundHeight);
	private int cols = (int) Math.ceil(width/backgroundWidth);
	
	private float dt;
	private float startTime;
	
	private BufferedImage background;
	
	public Game() {
		ImageLoader loader = new ImageLoader();
		background = loader.loadImage("/textures/tableBack.png");
		
		
		
		Assets.init();
		
	}
	
	private void init() {
		
		display = new Display(this, "My Card Game", width, height);
		
		display.getCanvas().addMouseListener(new MouseHandler(this));
	
		gameState = new GameState(this);
		State.setState(gameState);
		gameState.init();
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	private void tick() {
		
		if(State.getState() != null) {
			State.getState().tick();
			
		}
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Begin Drawing...
		//----------------
		
		//Fix Display errors hopefully.
		//display.getBidButton().setVisible(true);
		//display.getBidLabel().setVisible(true);
		
		//Clear screen.
		g.clearRect(0,0,width,height);
		int xPos = 0;
		int yPos = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				g.drawImage(background, xPos, yPos, null);
				xPos += backgroundWidth;
			}
			xPos = 0;
			yPos += backgroundHeight;
		}
		//g.drawImage(background, 0, 0, width, height, null);
		if(State.getState() != null) {
			State.getState().render(g);
			
		}		//End Drawing...
		
		
		//---------------
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		

		int fps = 60;
		double timePerTick = 1000000000 / fps; // Amount of time to be able to run updates over.
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		startTime = System.nanoTime();

		while(running) { 
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			dt = (now - startTime) / 1000000000;
			//System.out.println(dt);
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				//If timer has been running for one second.
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		//If the game is already running, return.
		if(running) 
			return;
		running = true;
		//Create a new thread and add an instance of the game to it.
		thread = new Thread(this);
		
		//Start the thread (runs the run function).
		thread.start();
		
	}
	
	public synchronized void stop() {
		if(!running) 
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}