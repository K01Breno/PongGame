package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public double x,y;
	public int width = 3;
	public int height = 3;
	
	public double dx,dy;
	public double speed = 1.5;
	
	public Ball(double x,double y) {
		
		this.x=x;
		this.y=y;
		
		int angle = new Random().nextInt(120-45) + 46;
		dx=Math.cos(Math.toRadians(angle));
		dy=Math.sin(Math.toRadians(angle));
		
	}

	public void tick() {
		
		x+=dx*speed;
		y+=dy*speed;
		
		if(x+(dx*speed) + width >= Game.width ) {
			dx*=-1;
		}else if(x+(dx*speed)+width<0) {
			dx*=-1;
		}
		
		if (y >= Game.height) {
			System.out.println("ponto do Inimigo");
			new Game();
			return;
		}else if(y<0) {
			System.out.println("ponto nosso! YAYY");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		
		Rectangle boundPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
		
		Rectangle boundEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
		
		if(bounds.intersects(boundPlayer)) {
			int angle = new Random().nextInt(120-45) + 46;
			dx=Math.cos(Math.toRadians(angle));
			dy=Math.sin(Math.toRadians(angle));
			if(dy>0)
			dy*=-1;
		}else if(bounds.intersects(boundEnemy)) {
			int angle = new Random().nextInt(120-45) + 46;
			dx=Math.cos(Math.toRadians(angle));
			dy=Math.sin(Math.toRadians(angle));
			if(dy<0)
			dy*=-1;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, width, height);
	}
}
