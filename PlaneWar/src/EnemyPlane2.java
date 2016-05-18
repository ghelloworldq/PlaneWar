import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class EnemyPlane2 extends  EnemyPlane {
	public static final int X_SPEED = 3;
	public static final int Y_SPEED = 3;
	public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
	//private Direction dir = null;
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	EnemyPlane2(int x, int y, Direction dir, PlaneWarFrame plf) {
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
				"images/enemy2.png"));
		g.drawImage(img, x, y,  WIDTH, HEIGHT, null);
		move();
	}
	void move(){
		int oldx,oldy;
		oldx = x;
		oldy = y;
		y += Y_SPEED ;
		if(y-oldy>=4* Y_SPEED ){
			x += X_SPEED;
			y += Y_SPEED;
		}
		
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}
