import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;




public class PlaneWarFrame extends Frame {
	 public static final int GAME_WIDTH = 400;
	 public static final int GAME_HEIGHT = 500;
	 private Image offScreanImage = null;
	 BackGround b = new BackGround();
	 MyPlane myPlane = new MyPlane(180, 460, null, this);
	 Blood bl = new Blood(200,32,Direction.D);
	 Boss bo = new Boss(150,-76,Direction.STOP,this);
	 public int score = 0;
	// BloodBar bb = new BloodBar();
	// EnemyPlane enemyplane = new EnemyPlane(50,50,Direction.STOP,this);
	 List<Missile> missiles = new ArrayList<Missile>();
	// List<Blood> bloods = new ArrayList<Blood>();
	 List<BossMissile> bossmissiles = new ArrayList<BossMissile>();
	 List<EnemyMissile> enemymissiles = new ArrayList<EnemyMissile>();
	 //List<EnemyPlane2> enemyplanes2 = new ArrayList<EnemyPlane2>();
	 List<EnemyPlane> enemyplanes = new ArrayList<EnemyPlane>();
	 public void paint(Graphics g) {
		 b.drawBackGround(g);
		 myPlane.draw(g);
		 myPlane.eat(bl);
		 myPlane.collidesWithEnemyPlane(enemyplanes);
		 myPlane.collidesWithBoss(bo);
		// bb.draw(g);
		// enemyplane.draw(g);
		 g.drawString("missiles count:" + missiles.size(), 5, 50);
		 g.drawString("planes    count:" + enemyplanes.size(), 5, 70);
		 g.drawString("myPlane      life:" + myPlane.getLife(), 5, 90);
		 g.drawString("score              :"+score, 5,110);
		 
		 for(int i=0; i<missiles.size(); i++) {
				Missile m = missiles.get(i);
				m.hitEnemys(enemyplanes);
				//m.hitEnemys2(enemyplanes2);
				m.hitBoss(bo);
				m.draw(g);
				
				
			}
		 for(int i=0; i<enemymissiles.size(); i++) {
			 EnemyMissile m = enemymissiles.get(i);
			 m.hitMyPlane(myPlane);
				m.draw(g);
				
				
			}
		 for(int i=0; i<bossmissiles.size(); i++) {
			 BossMissile m = bossmissiles.get(i);
			 m.hitMyPlane(myPlane);
			 m.draw(g);
				
				
			}
		 for(int i=0; i<enemyplanes.size(); i++) {
			 EnemyPlane e = enemyplanes.get(i);
				
				e.draw(g);
				
			}
		
		 /*for(int i=0; i<enemyplanes2.size(); i++) {
			 EnemyPlane2 e = enemyplanes2.get(i);
				
				e.draw(g);
				
			}*/
			 /*if(enemyplanes.size()<4&&enemyplanes2.size()<5){
				 
			 	 for(int i=0;i<=4;i++){
                	 enemyplanes2.add(new EnemyPlane2(60*(i+1),30,Direction.D,this));
                	 
                	 
                 }
				
           }*/
			 	 
		    if(enemyplanes.size()<4&&score<120){
				 
			 	 for(int i=0;i<=4;i++){
                	 enemyplanes.add(new EnemyPlane(70*(i+1),30,Direction.D,this));
                	 
                	 
                 }
				
           }
		   if(myPlane.life<50) {
			  
			   bl.draw(g);
			   
		   }
		   if(score>=120){
			   bo.draw(g);
			   g.drawString("Boss         life:" + bo.getLife(), 5, 120);
		   }
		   if(bo.getLife()==0){
			   Font f=new Font("ËÎÌו",Font.BOLD+Font.ITALIC,40);
			   g.setFont(f);
			   
			   g.drawString("YOU WIN !", 120, 250);
		   }
		   if(myPlane.getLife()==0){
			   Font f=new Font("ËÎÌו",Font.BOLD+Font.ITALIC,40);
			   g.setFont(f);
			   
			   g.drawString("YOU LOSE !", 120, 250);
		   }
		}
	 
	    public void update(Graphics g) {
			if(offScreanImage == null){
				offScreanImage = this.createImage(GAME_WIDTH, GAME_HEIGHT) ;
			}
			Graphics goffScrean = offScreanImage.getGraphics();
			Color c = goffScrean.getColor();
			goffScrean.setColor(Color.white);
			goffScrean.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
			paint(goffScrean);
			g.drawImage(offScreanImage, 0, 0, null);
		}

		public static void main(String[] args) {
			PlaneWarFrame tc = new PlaneWarFrame();
	        tc.lauchFrame();
	        
		}
		
	    public void lauchFrame(){
	    	 this.setLocation(220,220);
	    	 this.setSize(GAME_WIDTH,GAME_HEIGHT);
	    	 this.setTitle("PlaneWar");
	    	 this.setBackground(Color.green);
	    	 this.addWindowListener(new WindowAdapter(){

				
				public void windowClosing(WindowEvent e) {
					
					System.exit(0);
					
				}});
	    	 setVisible(true);
	    	 this.addKeyListener(new KeyMonitor());
	    	 this.setResizable(false);
	    	 new Thread(new PaintThread()).start();
            /* for(int i=0;i<=5;i++){
            	 enemyplanes.add(new EnemyPlane(60*(i+1),30,Direction.D,this));
            	 
            	 
             }*/
            
	    }
	    private class PaintThread implements Runnable{
	    	

			
			public void run() {
				while(true){
		    		repaint();
		    		try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
		    	}
				
			}
	    	
	    	
	    	
	    } 
	    public class KeyMonitor extends KeyAdapter{

			
			
			public void keyReleased(KeyEvent e) {
				
				myPlane.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				myPlane.keyPressed(e);
			}
	    	 
	    	
	    	
	    }
	   /* public class BloodBar {
	    	
	    	
	    	public void draw(Graphics g) {
	    		Color c = g.getColor();
	    		g.setColor(Color.RED);
	    		g.drawRect(300, 10, 50, 10);
	    		int w = 50* myPlane.life/100 ;
	    		g.fillRect(300, 10, w, 10);
	    		g.setColor(c);
	    	}
	    }*/

}
