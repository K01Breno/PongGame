package game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x,y;
	public int width = 40;
	public int height = 5;
	
	public Enemy(double x,double y) {
		this.x=x;
		this.y=y;
	}

	public void tick() {
		x+=(Game.ball.x - x- 6) * 0.075;
		
		if(x+width > Game.width) {
			x =Game.width - width;
		}else if(x<0) {
			x =0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
}
