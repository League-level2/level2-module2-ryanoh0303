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
		rocket.update();
		checkCollision();
		purgeObjects();

		
		
		}
		//there is an error here!
	
	
	void draw(Graphics g) {
		rocket.draw(g);
		
		for(int i=0; i<aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for(int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).draw(g);
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
		for(int y=0; y<aliens.size(); y++) {
			if(rocket.collisionBox.intersects(aliens.get(y).collisionBox)){
				System.out.println("NOt active");
				rocket.isActive=false;
				
		
			}
		}
			
				//there is an error here!!!
			
		}
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien(); 
		
	}
}
