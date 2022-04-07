package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static int width = 160;
	public static int height = 120;
	public static int scale = 3;
	
	public BufferedImage layer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(width*scale,height*scale));
		this.addKeyListener(this);
		player = new Player((width/2)-20,height-5);
		enemy = new Enemy((width/2)-20,0);
		ball = new Ball(width-(width/2)-5,height-(height/2)-50);
	}
	
	public static void main (String args[]) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs =this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g =layer.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g=bs.getDrawGraphics();
		g.drawImage(layer,0,0,width*scale,height*scale,null);
		
		bs.show();
	}

	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT);{
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT);{
			player.left = false;
		}
	}
	
	
	
}
