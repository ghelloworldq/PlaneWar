import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;


public class Boss extends Plane {
	public static final int X_SPEED = 2;
	public static final int Y_SPEED = 1;
	//private Direction dir = Direction.STOP;
	private Random r = new Random();
	private boolean live = true;
	private int step = r.nextInt(12) + 3;
	public int life = 100;
	private int timeboss = 0; 
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public static final int WIDTH = 100;
    public static final int HEIGHT = 76;
    //private PlaneWarFrame plf;
    
    Boss(int x, int y, Direction dir,PlaneWarFrame plf) {
		super(x, y, dir,plf);
	}
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    public void draw(Graphics g) {
		if(!live) {
			 
		     return;
		 }
		Image img = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource(
				"images/boss.png"));
		g.drawImage(img, x, y,WIDTH ,HEIGHT , null);
		
		move();
	}
    void move(){
    	 
    	 switch(dir){
    	 case L:
 			x -= X_SPEED;
 			break;
 		case LU:
 			x -= X_SPEED;
 			y -= Y_SPEED;
 			break;
 		case U:
 			
 			y -= Y_SPEED;
 			break;
 		case RU:
 			x += X_SPEED;
 			y -= Y_SPEED;
 			break;
 		case R:
 			x += X_SPEED;
 			break;
 		case RD:
 			x += X_SPEED;
 			y += Y_SPEED;
 			break;
 		case D:
 			
 			y += Y_SPEED;
 			break;
 		case LD:
 			x -= X_SPEED;
 			y += Y_SPEED;
 			break;
 		case STOP:
 			break;
			
			}
    	 if(timeboss<=600){
    		 y += Y_SPEED;
    		 timeboss +=1;
    	 } 
    	 else y -=Y_SPEED;
    	if (x < 0)  x = 0;
 		if (x+Boss.WIDTH > PlaneWarFrame.GAME_WIDTH)  x = PlaneWarFrame.GAME_WIDTH-Boss.WIDTH;
 		if (y+Boss.HEIGHT > PlaneWarFrame.GAME_HEIGHT) y = PlaneWarFrame.GAME_HEIGHT - Boss.HEIGHT;
 		 
    	
 		Direction[] dirs = Direction.values();
		if(step == 0) {
			step = r.nextInt(12) + 3;
			int rn = r.nextInt(dirs.length);
			dir = dirs[rn];
		}			
		step --;
		if(r.nextInt(40) > 33) this.fire(Direction.D);
		if(r.nextInt(40) > 38) this.superFire();
		
	}
	public BossMissile fire(Direction dir) {
			
			int x = this.x + Boss.WIDTH/2 - BossMissile.WIDTH/2;
			int y = this.y + Boss.HEIGHT/2 - BossMissile.HEIGHT/2;
			BossMissile m = new BossMissile(x, y, dir, plf);
			plf.bossmissiles.add(m);
			return m;
		}
	private void superFire() {
		Direction[] dirs = Direction.values();
		for(int i=0; i<8; i++) {
			fire(dirs[i]);
		}
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}
