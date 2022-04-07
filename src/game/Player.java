package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean right,left;
	public int x,y;
	public int width = 40;
	public int height = 5 ;
	
	public Player(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void tick() {
		if(right) {
			x++;
		}else if(left) {
			x--;
		}
		
		if(x+width > Game.width) {
			x =Game.width - width;
		}else if(x<0) {
			x =0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

}
