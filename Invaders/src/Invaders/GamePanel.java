package Invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU=0;
	final int GAME=1;
	final int END=2;
	Timer frameDraw;
	int currentState=MENU;
	Font titleFont;
	Font titleFont2;
	Rocketship rocketship=new Rocketship(250,700,50,50);
	ObjectManager objectmanager= new ObjectManager(rocketship);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

public GamePanel(){
	 titleFont = new Font("Arial", Font.PLAIN, 40);
	 titleFont2= new Font("Arial", Font.PLAIN, 20);
	 frameDraw = new Timer(1000/60,this);
	 frameDraw.start();
	 if (needImage) {
		    loadImage ("space.png");
		}

}

@Override
public void paintComponent(Graphics g) {
	if(currentState == MENU){
	    drawMenuState(g);
	}else if(currentState == GAME){
	    drawGameState(g);
	}else if(currentState == END){
	    drawEndState(g);
	}
}

void updateMenuState() {}
void updateGameState() {
	objectmanager.update();
}
void updateEndState() {}

void drawMenuState(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	g.drawString("LEAGUE INVADERS" ,65,100);
	
	g.setFont(titleFont2);
	g.drawString("Press ENTER to Start!" , 150,400);
	g.drawString("Press SPACE for instructions" ,120,600);
}
void drawGameState(Graphics g) {
	g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    if (gotImage) {
		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
	} else {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	}
	
	objectmanager.draw(g);
}
void drawEndState(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setColor(Color.YELLOW);
	g.setFont(titleFont);
	g.drawString("GAME OVER",115,100);
	g.setFont(titleFont2);
	g.drawString("You killed enemy" , 160,400);
	g.drawString("Press ENTER to restart" ,130,600);
}

@Override
public void actionPerformed(ActionEvent e) {
	if(currentState == MENU){
	    updateMenuState();
	}else if(currentState == GAME){
	    updateGameState();
	}else if(currentState == END){
	    updateEndState();
	}
	repaint();
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	if (e.getKeyCode()==KeyEvent.VK_ENTER) {
	    if (currentState == END) {
	        currentState = MENU;
	    } else {
	        currentState++;
	    }
	}   
	if (e.getKeyCode()==KeyEvent.VK_UP) {
	    System.out.println("UP");
	}
	if (e.getKeyCode()==KeyEvent.VK_DOWN) {
	    System.out.println("DOWN");
	}
	if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
	    System.out.println("RIGHT");
	}
	if (e.getKeyCode()==KeyEvent.VK_LEFT) {
	    System.out.println("LEFT");
	}
	
	
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
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



