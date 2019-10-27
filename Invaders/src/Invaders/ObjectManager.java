package Invaders;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class ObjectManager implements ActionListener {
	int score=0;
	Rocketship rocket;
	ArrayList<Projectile> projectiles= new ArrayList<Projectile>();
	ArrayList<Alien> aliens= new ArrayList<Alien>();
	ArrayList<Powerup> drinks= new ArrayList<Powerup>();
	ArrayList<Speed> sword= new ArrayList<Speed>();
	ArrayList<PowerfulAliens> aliens2= new ArrayList<PowerfulAliens>();
	Random random= new Random();

	int getScore() {
		return score;
	}
	
	
	public ObjectManager(Rocketship rocket) {
		this.rocket=rocket;
		rocket.isActive=true;
		
	}
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
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
		for(int i=0; i<drinks.size(); i++) {
			drinks.get(i).draw(g);
		}
		for(int i=0; i<sword.size(); i++) {
			sword.get(i).draw(g);
		}
		
	}
	void purgeObjects() {
		for(int i=0; i<aliens.size(); i++) {
			if(aliens.get(i).isActive!=true) { 
				
				aliens.remove(i);
			}
		}
		for (int c = 0; c <projectiles.size();c++) {
			if(projectiles.get(c).isActive!=true) {
				projectiles.remove(c);
			}
		}
		for (int d= 0; d <drinks.size();d++) {
			if(drinks.get(d).isActive!=true) {
				drinks.remove(d);
			}
		}
		for (int d= 0; d <sword.size();d++) {
			if(sword.get(d).isActive!=true) {
				sword.remove(d);
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
		//System.out.println("Checking");
		for(int i=0; i<aliens.size(); i++) {
			for(int j=0; j<projectiles.size(); j++) {
				if(projectiles.get(j).collisionBox.intersects(aliens.get(i).collisionBox)) {
					aliens.get(i).isActive=false;
					projectiles.get(j).isActive=false;
					score=score+1;
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
			if(rocket.collisionBox.intersects(drinks.get(t).collisionBox)) {
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
		// TODO Auto-generated method stub
		addAlien(); 
		addAlien2();
		addDrink();
		addSpeed();
		
	}
}
