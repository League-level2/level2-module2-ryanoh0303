package Invaders;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class PowerfulAliens extends GameObject {

	


		public static BufferedImage image;
		public static boolean needImage = true;
		public static boolean gotImage = false;	
		int speed=2;
		public PowerfulAliens(int x, int y, int width, int height) {
			super(x, y, width, height);
			if (needImage) {
			    loadImage ("poweful.png");
			}
			
		}
		void update(Rocketship rocket) {
			if(x<rocket.x) {
				x+=speed;
			}
			if(x>rocket.x) {
				x-=speed;
			}
			if(y<rocket.y) {
				y+=speed;
			}
			if(y>rocket.y) {
				y-=speed;
			}
			
			super.update();
			
		}
		void draw(Graphics g) {
		    g.setColor(Color.YELLOW);
	        g.fillRect(x, y, width, height);
	        if (gotImage) {
	        	g.drawImage(image, x, y, width, height, null);
	        } else {
	        	g.setColor(Color.YELLOW);
	        	g.fillRect(x, y, width, height);
	        }
		}

		void loadImage(String imageFile) {
		    if (needImage) {
		        try {
		            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		            gotImage = true;
		        } catch (Exception e) {
		            
		        }
		        needImage = false;
		    }
		}
	}


