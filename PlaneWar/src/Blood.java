import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;






public class Blood {
	
	 int x,y;
	 private Direction dir = Direction.D;
     public static final int xSpeed = 3;
	 public static final int ySpeed = 3;
	 public static final int WIDTH = 20;
     public static final int HEIGHT = 20;
     private PlaneWarFrame plf;
	 private boolean live = true;
	
	private Random r = new Random();
	 private int step = r.nextInt(12) + 3;
	 public Blood(int x, int y,Direction dir) {
			
			this.x = x;
			this.y = y;
			this.dir= dir;
		}
	 private static Toolkit tk = Toolkit.getDefaultToolkit();
	 public void draw(Graphics g) {
			if(!live) {
						 
					     return;
					 }
			Image img = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource(
					"images/blood.png"));
			g.drawImage(img, x, y,WIDTH , HEIGHT, null);
			
			move();
			
		}
	 void move(){
		 switch(dir){
		 case D:
				y += ySpeed;
				break;
		 case RD:
				y += ySpeed;
				x += xSpeed;
				break;
		 case LD:
				y +=ySpeed;
				x -=xSpeed;
				break;
		 case U:
				y += ySpeed;
				break;
		 case RU:
				y += ySpeed;
				x += xSpeed;
				break;
		 case LU:
				y +=ySpeed;
				x -=xSpeed;
				break;
		 case STOP:
				y += ySpeed;
				break;
			
			}
		 //y += ySpeed;
		 if(x < 0) x = 0;
		 if(y>plf.GAME_HEIGHT) live = false;
		 if (x+ Blood.WIDTH > PlaneWarFrame.GAME_WIDTH)  x = PlaneWarFrame.GAME_WIDTH- Blood.WIDTH;
		 Direction[] dirs = Direction.values();
			if(step == 0) {
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
			}			
			step --;
	 }
	 public Rectangle getRect() {
			return new Rectangle(x, y, WIDTH, HEIGHT);
		}
	 public boolean isLive() {
			return live;
		}
	 public void setLive(boolean live) {
			this.live = live;
		}
}
