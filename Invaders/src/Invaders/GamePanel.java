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
	Timer alienSpawn;
	Timer drinkSpawn;
	Timer speedSpawn;
	Timer Opspwan;
	Timer bombSpawn;
	int currentState=MENU;
	Font titleFont;
	Font titleFont2;
	Rocketship rocketship=new Rocketship(250,700,50,50);
	ObjectManager objectmanager;
	int spawna= 3000;
	int spawnb= 6000;
	int drinkspawn=7000;
	int speedspawn=5000;

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

public GamePanel(){
	objectmanager=new ObjectManager(rocketship,this);
	 titleFont = new Font("Arial", Font.PLAIN, 40);
	 titleFont2= new Font("Arial", Font.PLAIN, 20);
	 frameDraw = new Timer(1000/60,this);
	 frameDraw.start();
	 if (needImage) {
		    loadImage ("background.png");
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
	if(rocketship.isActive==false) {
		System.out.println("GAME ENDED");
		currentState=END;
				
	}
	
}
void updateEndState() {    
	
}
void startGame() {
	alienSpawn=new Timer(spawna, objectmanager);
	alienSpawn.start();
	drinkSpawn= new Timer(drinkspawn,objectmanager);
	drinkSpawn.start();
	speedSpawn= new Timer(speedspawn,objectmanager);
	speedSpawn.start();
	Opspwan= new Timer(spawnb,objectmanager);
	Opspwan.start();


}
void drawMenuState(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	g.drawString("LEAGUE INVADERS2" ,65,100);
	
	g.setFont(titleFont2);
	g.drawString("Press ENTER to Start!" , 150,500);
  
	g.drawString("Soda Can: Makes you bigger" ,120,300);
	g.drawString("Sword: Makes you faster and smaller" ,120,250);
	g.drawString("PowerfulAliens:Shoots at you" ,120,200);
}
void drawGameState(Graphics g) {
	g.setColor(Color.BLACK);
	
    g.fillRect(0, 0, WIDTH, HEIGHT);
    
   
    g.setColor(Color.BLACK);

 
    if (gotImage) {
		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		 g.setFont(titleFont2);        
		 g.drawString("Bomb left: "+ objectmanager.amount, 360 ,50); 
	} else {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	}
    objectmanager.update();
	objectmanager.draw(g);
	
}
void drawEndState(Graphics g) {
	alienSpawn.stop();
	g.setColor(Color.RED);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setColor(Color.YELLOW);
	g.setFont(titleFont);
	g.drawString("GAME OVER",115,100);
	g.setFont(titleFont2);
	g.drawString("You killed " +objectmanager.getScore()+ " enemy" , 160,400);
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
	        rocketship= new Rocketship(250,700,50,50);
	        objectmanager= new ObjectManager(rocketship, this);
	    } 
	    else if(currentState==MENU){
	    	currentState = GAME;
	        startGame();
	    }
	    else {
	    	currentState++;
	    }
	   
	}  
	if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
		objectmanager.amount=objectmanager.amount-1;
		System.out.println("Bomb");
		objectmanager.killAll();
	}
	if (e.getKeyCode()==KeyEvent.VK_UP) {
	    System.out.println("UP");
	    rocketship.up();
	}
	if (e.getKeyCode()==KeyEvent.VK_DOWN) {
	    System.out.println("DOWN");
	    rocketship.down();
	}
	if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
	    System.out.println("RIGHT");
	    rocketship.right();
	}
	if (e.getKeyCode()==KeyEvent.VK_LEFT) {
	    System.out.println("LEFT");
	    rocketship.left();
	}
	if(e.getKeyCode()==KeyEvent.VK_SPACE){
		objectmanager.addProjectile(rocketship.getProjectile());
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



