package Invaders;



import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame jframe;
	GamePanel panel;
	static final int WIDTH=500;
	static final int HEIGHT=800;
	

	
	public static void main(String[] args) {
		LeagueInvaders league= new LeagueInvaders();
		league.setup();
		
		
	}
	
	public void setup() {
		jframe.add(panel);
		jframe.setSize(WIDTH,HEIGHT);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(panel);
		jframe.setVisible(true);
		
		
	}
	public LeagueInvaders() {
		jframe= new JFrame("League Invaders");
		panel= new GamePanel();
		
	}
}
