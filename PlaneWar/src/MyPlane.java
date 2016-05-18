import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;






public class MyPlane extends Plane {
	    private Direction dir = Direction.STOP;
	    
	    public static final int xSpeed = 6;
	    public static final int ySpeed = 6;
	    private Boolean bl = false,bu = false, br = false,bd = false;
	    public static final int WIDTH = 35;
		public static final int HEIGHT = 24;
		private boolean live = true;
		public int life = 100;
		public int getLife() {
			return life;
		}
		public void setLife(int life) {
			this.life = life;
		}
		public boolean isLive() {
			return live;
		}
		public void setLive(boolean live) {
			this.live = live;
		}
		
	MyPlane(int x, int y, Direction dir,PlaneWarFrame plf) {
		super(x, y, dir,plf);
	}
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public void draw(Graphics g) {
		if(!live) {
			 
		     return;
		 }
		Image img = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource(
				"images/myplane.png"));
		g.drawImage(img, x, y, 35, 24, null);
		move();
	}
	void move(){
		switch(dir){
		case L:
			x -= xSpeed;
			break;
		case LU:
			x -= xSpeed;
			y -= ySpeed;
			break;
		case U:
			
			y -= ySpeed;
			break;
		case RU:
			x += xSpeed;
			y -= ySpeed;
			break;
		case R:
			x += xSpeed;
			break;
		case RD:
			x += xSpeed;
			y += ySpeed;
			break;
		case D:
			
			y += ySpeed;
			break;
		case LD:
			x -= xSpeed;
			y += ySpeed;
			break;
		case STOP:
			break;
		}
		if(x < 0) x = 0;
		if(y < 35) y = 35;
		if(x + MyPlane.WIDTH > PlaneWarFrame.GAME_WIDTH) x = PlaneWarFrame.GAME_WIDTH - MyPlane.WIDTH;
		if(y + MyPlane.HEIGHT > PlaneWarFrame.GAME_HEIGHT) y = PlaneWarFrame.GAME_HEIGHT - MyPlane.HEIGHT;
		
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_A:
			superFire();
			break;
		case(KeyEvent.VK_LEFT) :
			bl = true;
		break;
		case(KeyEvent.VK_UP) :
			bu = true;
		break;
		case(KeyEvent.VK_RIGHT) :
			br = true;
		break;
		case(KeyEvent.VK_DOWN) :
			bd = true;
		break;
		}
		locateDirection();
		
		
	}
	void locateDirection(){
		if(bl && !bu && !br && !bd) dir = Direction.L;
		else if(bl && bu && !br && !bd) dir = Direction.LU;
		else if(!bl && bu && !br && !bd) dir = Direction.U;
		else if(!bl && bu && br && !bd) dir = Direction.RU;
		else if(!bl && !bu && br && !bd) dir = Direction.R;
		else if(!bl && !bu && br && bd) dir = Direction.RD;
		else if(!bl && !bu && !br && bd) dir = Direction.D;
		else if(bl && !bu && !br && bd) dir = Direction.LD;
		else if(!bl && !bu && !br && !bd) dir = Direction.STOP;
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case(KeyEvent.VK_LEFT) :
			bl = false;
		break;
		case(KeyEvent.VK_UP) :
			bu = false;
		break;
		case(KeyEvent.VK_RIGHT) :
			br = false;
		break;
		case(KeyEvent.VK_DOWN) :
			bd = false;
		break;
		}
		locateDirection();
		
	}
	public Missile fire() {
		if(!live) return null;
		int x = this.x + MyPlane.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + MyPlane.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, Direction.U, plf);
		plf.missiles.add(m);
		return m;
	}
	public Missile fire(Direction dir) {
		if(!live) return null;
		int x = this.x + MyPlane.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + MyPlane.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, dir, this.plf);
		plf.missiles.add(m);
		return m;
	}
	private void superFire() {
		Direction[] dirs = Direction.values();
		for(int i=0; i<8; i++) {
			fire(dirs[i]);
		}
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	public boolean eat(Blood bl) {
		if(this.live && bl.isLive() && this.getRect().intersects(bl.getRect())) {
			this.life = 100;
			bl.setLive(false);
			return true;
		}
		return false;
	}
	public boolean collidesWithEnemyPlane(java.util.List<EnemyPlane> enemyplanes) {
		for(int i=0; i<plf.enemyplanes.size(); i++) {
			EnemyPlane e = plf.enemyplanes.get(i);
			
				if(this.live && e.isLive() && this.getRect().intersects(e.getRect())) {
					this.setLife(0);
					this.setLive(false);
					e.setLive(false);
					
				}
			}
		return true;
		}
	public boolean collidesWithBoss(Boss bs) {
		if(this.live && bs.isLive() && this.getRect().intersects(bs.getRect())) {
			this.life = 0;
			this.setLive(false);
			//bs.setLive(false);
			return true;
		}
		return false;
	}

}
