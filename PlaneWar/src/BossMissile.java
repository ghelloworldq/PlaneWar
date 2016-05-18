import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;


public class BossMissile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 25;
	public static final int HEIGHT = 25;
	int x, y;
	Direction dir;
	
	private boolean live = true;
	
	private PlaneWarFrame plf;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] missileImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	static {
		missileImages = new Image[] {
				tk.getImage(Missile.class.getClassLoader().getResource("images/bossmissileL.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/bossmissileR.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/bossmissileRD.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/bossmissileD.gif")),
				tk.getImage(Missile.class.getClassLoader().getResource("images/bossmissileLD.gif"))
		};
		
		imgs.put("L", missileImages[0]);
		imgs.put("R", missileImages[1]);
		imgs.put("RD", missileImages[2]);
		imgs.put("D", missileImages[3]);
		imgs.put("LD", missileImages[4]);
		
	}
	public BossMissile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public BossMissile(int x, int y, Direction dir, PlaneWarFrame plf) {
		this(x, y, dir);
		
		this.plf = plf;
	}
	public void draw(Graphics g) {
			
			Image img = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource(
					"images/bossmissile.gif"));
			g.drawImage(img, x, y,WIDTH , HEIGHT, null);
			if(!live) {
				plf.bossmissiles.remove(this);
				return;
			}
			switch(dir) {
			
			case L:
				g.drawImage(imgs.get("L"), x, y, null);
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
	void move(){
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
	public void setLive(boolean live) {
		this.live = live;
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
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}
