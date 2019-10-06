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
	Rocketship rocket;
	ArrayList<Projectile> projectiles= new ArrayList<Projectile>();
	ArrayList<Alien> aliens= new ArrayList<Alien>();
	Random random= new Random();
	

	
	
	public ObjectManager(Rocketship rocket) {
		this.rocket=rocket;
		
	}
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
	}
	void addAlien(){
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
		System.out.println("asdf");
		
	}
	void update() {
		for(int i=0; i<aliens.size(); i++) {
			aliens.get(i).update();
			if(LeagueInvaders.HEIGHT<=0 || LeagueInvaders.HEIGHT>=800) {
				aliens.get(i).isActive=false;
		      }
		}
		for(int j=0; j<projectiles.size(); j++) {
				projectiles.get(j).update();
				if(LeagueInvaders.HEIGHT<=0 || LeagueInvaders.HEIGHT>=800) {
					projectiles.get(j).isActive=false;
			      }
				
		}
		if(rocket.isActive=true) {  
		checkCollision();
		purgeObjects();
		
		
		}
		//there is an error here!
	}
	
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
		for(int i=0; i<aliens.size()-1; i++) {
			if(aliens.get(i).isActive=false) {
				System.out.println("alsjdfljS");
				aliens.remove(i);
			}
		}
		for (int i = 0; i <projectiles.size()-1;i++) {
			if(projectiles.get(i).isActive=false) {
				System.out.println("sdfasdf");
				projectiles.remove(i);
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
				}
				else if(rocket.collisionBox.intersects(aliens.get(i).collisionBox)){
					rocket.isActive=false;
			aliens.get(i).isActive=false;
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
