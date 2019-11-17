package Invaders;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.Timer;

public class ObjectManager implements ActionListener, KeyListener {
	int amount=4;
	int score=0;
	
	Timer bullet= new Timer(1000, this);
	Rocketship rocket;
	GamePanel game;
	ArrayList<Projectile> projectiles= new ArrayList<Projectile>();
	ArrayList<AnotherProjectile> projectiles2= new ArrayList<AnotherProjectile>();
	ArrayList<Alien> aliens= new ArrayList<Alien>();
	ArrayList<Powerup> drinks= new ArrayList<Powerup>();
	ArrayList<Speed> sword= new ArrayList<Speed>();
	ArrayList<PowerfulAliens> aliens2= new ArrayList<PowerfulAliens>();
	ArrayList<Bomb> bomb= new ArrayList<Bomb>(amount);
	Random random= new Random();

	int getScore() {
		return score;
	}
	
	
	public ObjectManager(Rocketship rocket, GamePanel game) {
		this.rocket=rocket;
		rocket.isActive=true;
		this.game=game;
		bullet.start();
		
	}
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
	}
	void addProjectile2(AnotherProjectile projectile2) {
		projectiles2.add(projectile2);
	}
	void killAll() {	
		if(amount<=0) {
			System.out.println(amount);
			System.out.println("NOT ENOUGH BOMBS");
		}
		else{
			for(int i = 0; i<aliens.size(); i++) {
		
					aliens.get(i).isActive=false;
					
				}
				for(int j=0; j<aliens2.size(); j++) {
					aliens2.get(j).isActive=false;
					aliens2.get(j).update();
				}
				for(int k=0; k<drinks.size(); k++) {
					drinks.get(k).isActive=false;
					drinks.get(k).update();
				}
				
				purgeObjects();

	}
	}
	
	
	void addAlien(){
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
		
	}
	void addAlien2(){
		aliens2.add(new PowerfulAliens(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
		
	}
	void addDrink() {
		drinks.add(new Powerup(random.nextInt(500),0,30,30));
		
	}
	void addSpeed() {
		sword.add(new Speed(random.nextInt(500),0,40,40));
	}
	void update() {
		
		
		for(int i=0; i<aliens.size(); i++) {
			aliens.get(i).isActive=true;
			aliens.get(i).update();
			if(aliens.get(i).height<=0 || aliens.get(i).width>=800) {
				aliens.get(i).isActive=false;
		     }
		
		}
		for(int j=0; j<projectiles.size(); j++) {
				projectiles.get(j).isActive=true;
				projectiles.get(j).update();
				if(projectiles.get(j).height<=0 || projectiles.get(j).height>=800) {
					projectiles.get(j).isActive=false;
			      }
								
		}
		for(int j=0; j<projectiles2.size(); j++) {
			projectiles2.get(j).isActive=true;
			projectiles2.get(j).update();
			if(projectiles2.get(j).height<=0 || projectiles2.get(j).height>=800) {
				projectiles2.get(j).isActive=false;
		     }
							
	    }
		for(int k=0; k<drinks.size(); k++) {
			drinks.get(k).isActive=true;
			drinks.get(k).update();
		}
		     
		for(int k=0; k<sword.size(); k++) {
			sword.get(k).isActive=true;
			sword.get(k).update();
		}
		for(int k=0; k<aliens2.size(); k++) {
			aliens2.get(k).isActive=true;
			aliens2.get(k).update(rocket);
		}
		rocket.update();
		checkCollision();
		purgeObjects();

		
		
		}
		//there is an error here!
	
	
	void draw(Graphics g) {
		rocket.draw(g);
		
		for(int i=0; i<aliens2.size(); i++) {
			aliens2.get(i).draw(g);
		}
		for(int i=0; i<aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for(int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for(int i=0; i<projectiles2.size(); i++) {
			projectiles2.get(i).draw(g);
		}

		for(int i=0; i<drinks.size(); i++) {
			drinks.get(i).draw(g);
		}
		for(int i=0; i<sword.size(); i++) {
			sword.get(i).draw(g);
		}
		for(int i=0; i<bomb.size(); i++) {
			bomb.get(i).draw(g);
		}
		
	}
	void purgeObjects() {
		for(int i=0; i<aliens.size(); i++) {
			if(aliens.get(i).isActive!=true) { 
				
				System.out.println(game.spawna);
				aliens.remove(i);
			}
		}
		for (int c = 0; c <projectiles.size();c++) {
			if(projectiles.get(c).isActive!=true) {
				projectiles.remove(c);
				
			}
		}
		for (int c = 0; c <projectiles2.size();c++) {
			if(projectiles2.get(c).isActive!=true) {
				projectiles2.remove(c);
			}
		}
		for (int d= 0; d <drinks.size();d++) {
			if(drinks.get(d).isActive!=true) {
				drinks.remove(d);
				game.drinkspawn+=400;
				System.out.println(game.drinkspawn);
			}
		}
		for (int d= 0; d <sword.size();d++) {
			if(sword.get(d).isActive!=true) {
				sword.remove(d);
				game.speedspawn+=400;
				System.out.println(game.speedspawn);
			}
		}
		for (int d= 0; d <aliens2.size();d++) {
				if(aliens2.get(d).isActive!=true) {
					aliens2.remove(d);
					
				}
				
			
				
			
		}
		
		
		//there is an error here!!!
	}
	void checkCollision(){
		
		for(int i=0; i<aliens.size(); i++) {
			for(int j=0; j<projectiles.size(); j++) {
				if(projectiles.get(j).collisionBox.intersects(aliens.get(i).collisionBox)) {
					aliens.get(i).isActive=false;
					projectiles.get(j).isActive=false;
					
				
					score=score+1;
				}
				}
			for(int j=0; j<projectiles2.size(); j++) {
				if(rocket.collisionBox.intersects(projectiles2.get(j).collisionBox)) {
					projectiles2.get(j).isActive=false;
					rocket.isActive=false;
					
				}
				}
				
				
				
				
			}
		for(int i=0; i<aliens2.size(); i++) {
			for(int j=0; j<projectiles.size(); j++) {
				if(projectiles.get(j).collisionBox.intersects(aliens2.get(i).collisionBox)) {
					aliens2.get(i).isActive=false;
					projectiles.get(j).isActive=false;
					score=score+1;
			}
			}
		}
		for(int y=0; y<aliens.size(); y++) {
			if(rocket.collisionBox.intersects(aliens.get(y).collisionBox)){
				System.out.println("NOt active");
				rocket.isActive=false;
		
			}
			
			
			
			}
		for(int t =0; t<aliens2.size(); t++) {
			if(rocket.collisionBox.intersects(aliens2.get(t).collisionBox)) {
				aliens2.get(t).isActive=false;
				rocket.isActive=false;
				
			}
		}
		
		for(int k =0; k<drinks.size(); k++) {
			if(rocket.collisionBox.intersects(drinks.get(k).collisionBox)) {
				System.out.println("Bigger");
				drinks.get(k).isActive=false;
				rocket.width+=2;
				rocket.height+=2;
		}
		}
		for(int k =0; k<sword.size(); k++) {
			if(rocket.collisionBox.intersects(sword.get(k).collisionBox)) {

				sword.get(k).isActive=false;
				rocket.speed+=1;
				rocket.width-=3;
				rocket.height-=3;
				if(rocket.width<2) {
					rocket.isActive=false;
				}
				
		}
		}
	}
		
		
			
				//there is an error here!!!
			
		
		
			
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(game.alienSpawn)) {
			game.spawna-=100;
			System.out.println(game.spawna);
			game.alienSpawn=new Timer(game.spawna, this);
			if(game.spawna<=0) {
				game.spawna=3000;
			}
			game.alienSpawn.start();
			addAlien();
		}
		if(e.getSource().equals(game.Opspwan)) {
			System.out.println("Adding OP Alien");
			addAlien2();
		}
		
		if(e.getSource().equals(game.drinkSpawn)) {
			addDrink();
		}
		if(e.getSource().equals(game.speedSpawn)) {
			addSpeed();
		}
		for(int i=0; i<aliens2.size(); i++) {
			if(bullet.equals(e.getSource())) {
				addProjectile2(aliens2.get(i).getProjectile2());
			}
			
		}

	
			

		
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
