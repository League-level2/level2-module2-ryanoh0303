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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
		
	}
}
