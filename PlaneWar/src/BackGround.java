

	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Toolkit;
public class BackGround {


	
		Toolkit tk = Toolkit.getDefaultToolkit();
		public static final int X_SPEED = 2;
		public static final int Y_SPEED = 2;
		private int x=0, y=0;

		public void drawBackGround(Graphics g) {
				Image img1 = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource("images/BackGround.gif"));
				Image img2 = tk.getImage(PlaneWarFrame.class.getClassLoader().getResource("images/BackGround.gif"));
				g.drawImage(img1, x, y, 400, 500, null);
				g.drawImage(img2, x, y-500, 400, 500, null);
				y += 2;
				if(y==500){
					x = 0;
					y = 0;
				}
		}
	}


