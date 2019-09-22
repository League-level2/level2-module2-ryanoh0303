package Invaders;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Projectile> projectiles= new ArrayList<Projectile>();
	ArrayList<Alien> aliens= new ArrayList<Alien>();
	Random random= new Random();
	

	
	
	public ObjectManager(Rocketship rocket) {
		this.rocket=rocket;
		
	}
	void addProjectile(Projectile projectile) {
		
	}
	void addAlien(){
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
		
	}
	void update() {
		for(int i=0; i<aliens.size(); i++) {
			update();
			if(LeagueInvaders.HEIGHT<=0 || LeagueInvaders.HEIGHT>=800) {
				aliens.get(i).isActive=false;
			}
		}
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
		for(int i=0; i<aliens.size(); i++) {
			if(aliens.get(i).isActive=false) {
				aliens.remove(i);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isActive=false) {
				projectiles.remove(i);
			}
		}
		
	}
}
