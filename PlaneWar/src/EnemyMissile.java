import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;



public class EnemyMissile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	int x, y;
	Direction dir;
	
	private boolean live = true;
	private PlaneWarFrame plf;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public void draw(Graphics g) {
		
		Image img = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource(
				"images/enemymissile.gif"));
		g.drawImage(img, x, y,WIDTH , HEIGHT, null);
		if(!live) {
			plf.enemymissiles.remove(this);
			return;
		}
		move();
	}
	public EnemyMissile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public EnemyMissile(int x, int y, Direction dir, PlaneWarFrame plf) {
		this(x, y, dir);
		
		this.plf = plf;
	}
	void move(){
		y += YSPEED ;
		if(x < 0 || y < 0 || x > PlaneWarFrame.GAME_WIDTH || y > PlaneWarFrame.GAME_HEIGHT) {
			live = false;
		}	
		
	}
	public boolean isLive() {
		return live;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	public boolean hitMyPlane( MyPlane e) {
		if(this.live && this.getRect().intersects(e.getRect()) && e.isLive()) {
			
			e.setLife(e.getLife()-20);
			if(e.getLife() <= 0) e.setLive(false);
			
			
			this.live = false;
			//Explode e = new Explode(x, y, tc);
			//tc.explodes.add(e);
			
		}
		return false;
	}
}
