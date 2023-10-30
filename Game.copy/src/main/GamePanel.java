package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import Backgrounds.TileManager;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Screen seattings 
	final int originalTileSize = 16; // 16 by 16 tille
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRaw = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 px
	public final int screenHeight = tileSize * maxScreenRaw; //576 px
	
	
	// WORLD SEATINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	
	int FPS = 60;
	
	TileManager begroundM = new TileManager(this);
	keyhandler KeyH = new keyhandler();
	Thread gameThread;
	public CollisionChecker Checker = new CollisionChecker(this);
	public Player player = new Player(this,KeyH);
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
		
		
		
		
	}
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS; 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currenttime;
		
	
		while(gameThread != null) {
			currenttime = System.nanoTime();
			
			delta += (currenttime - lastTime)/ drawInterval;
			
			lastTime = currenttime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				
			}
		}	
	}
	
	public void update() {
		
		player.update();
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 =(Graphics2D)g;
		
		begroundM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();// dispose of this graphics context and release any system resources that it is using.
		
		
		
	}
}
