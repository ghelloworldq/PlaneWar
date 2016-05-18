import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;






public class EnemyPlane extends Plane{
	public static final int X_SPEED = 1;
	public static final int Y_SPEED = 1;
	public static final int WIDTH = 35;
    public static final int HEIGHT = 24;
	private Direction dir = Direction.D;
	private boolean live = true;
	private Random r = new Random();
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	EnemyPlane(int x, int y, Direction dir, PlaneWarFrame plf) {
		super(x, y, dir, plf);
		
	}
	Toolkit tk = Toolkit.getDefaultToolkit(); 
	public void draw(Graphics g) {
		if(!live) {
			 plf.enemyplanes.remove(this);
		     return;
		 }
		if (x < 0)  x = 0;
		if (x+EnemyPlane.WIDTH > PlaneWarFrame.GAME_WIDTH)  x = PlaneWarFrame.GAME_WIDTH-EnemyPlane.WIDTH;
		if (y > PlaneWarFrame.GAME_HEIGHT)  live = false;
		
		Image img = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource(
				"images/enemy1.png"));
		g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		move();
		
	}
	void move(){
		y += Y_SPEED ;
		if(r.nextInt(40) > 37) this.fire();
		
	}
	public EnemyMissile fire() {
		
		int x = this.x + EnemyPlane.WIDTH/2 - EnemyMissile.WIDTH/2;
		int y = this.y + EnemyPlane.HEIGHT/2 - EnemyMissile.HEIGHT/2;
		EnemyMissile m = new EnemyMissile(x, y, Direction.D, plf);
		plf.enemymissiles.add(m);
		return m;
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}
