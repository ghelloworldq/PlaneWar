import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Missile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	int x, y;
	Direction dir;
	
	private boolean live = true;
	private PlaneWarFrame plf;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] missileImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	static {
		missileImages = new Image[] {
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileL.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileLU.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileU.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileRU.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileR.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileRD.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileD.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/missileLD.gif"))
		};
		
		imgs.put("L", missileImages[0]);
		imgs.put("LU", missileImages[1]);
		imgs.put("U", missileImages[2]);
		imgs.put("RU", missileImages[3]);
		imgs.put("R", missileImages[4]);
		imgs.put("RD", missileImages[5]);
		imgs.put("D", missileImages[6]);
		imgs.put("LD", missileImages[7]);
		
	}
	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Missile(int x, int y, Direction dir, PlaneWarFrame plf) {
		this(x, y, dir);
		
		this.plf = plf;
	}
	public void draw(Graphics g) {
		if(!live) {
			plf.missiles.remove(this);
			return;
		}
		switch(dir) {
		
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
		}
		
		move();
	}
	private void move() {
		
		
		switch(dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		
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
	public boolean hitEnemy(EnemyPlane e) {
		if(this.live && this.getRect().intersects(e.getRect()) && e.isLive()) {
			
				e.setLive(false);
			    plf.score+=20;
			
			this.live = false;
			//Explode e = new Explode(x, y, tc);
			//tc.explodes.add(e);
			
		}
		return false;
	}
	public boolean hitEnemys(List<EnemyPlane> enemyplanes) {
		for(int i=0; i<enemyplanes.size(); i++) {
			if(hitEnemy(enemyplanes.get(i))) {
				return true;
			}
		}
		return false;
	}
	public boolean hitBoss(Boss bo) {
		if(this.live && this.getRect().intersects(bo.getRect()) && bo.isLive()) {
			
			bo.setLife(bo.getLife()-10);
			if(bo.getLife() <= 0) bo.setLive(false);
			    plf.score+=20;
			
			this.live = false;
			//Explode e = new Explode(x, y, tc);
			//tc.explodes.add(e);
			
		}
		return false;
	}
	/*public boolean hitEnemys2(List<EnemyPlane2> enemyplanes2) {
		for(int i=0; i<enemyplanes2.size(); i++) {
			if(hitEnemy(enemyplanes2.get(i))) {
				return true;
			}
		}
		return false;
	}*/
}
