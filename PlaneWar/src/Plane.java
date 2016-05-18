import java.awt.Rectangle;






public class Plane {
     int x,y;
     Direction dir;
     PlaneWarFrame plf = null;
     Plane(int x, int y) {
 		this.x = x;
 		this.y = y;
 	}


     Plane(int x, int y, Direction dir) {
 		this(x, y);
 		this.dir = dir;
 	}
 	
     Plane(int x, int y, Direction dir,PlaneWarFrame plf) {
 		this(x, y,dir);
 		this.plf = plf;
 	}

 	
 	
 }

