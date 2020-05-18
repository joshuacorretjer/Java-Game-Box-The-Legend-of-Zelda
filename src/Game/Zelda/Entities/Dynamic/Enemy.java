package Game.Zelda.Entities.Dynamic;

import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Enemy extends BaseMovingEntity{
	
	public Animation Bouncingenemy;

	public Enemy(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		// TODO Auto-generated constructor stub
		
		Bouncingenemy= new Animation(120, Images.bouncyEnemyFrames);
		
		
		
		 Random rand = new Random(); 
		 
		 int mix=rand.nextInt(4);
		 
		 if(mix==0) {
			 direction=Direction.RIGHT;
		 }else if(mix==1) {
			 direction=Direction.LEFT;
		 }else if(mix==2) {
			 direction=Direction.UP;
		 }else if(mix==3) {
			 direction=Direction.DOWN;
		 }
		
		
		  }
	
	
	  @Override
	    public void render(Graphics g) {
	     Bouncingenemy.tick();
	            g.drawImage(Bouncingenemy.getCurrentFrame(),x , y, width , height  , null);
	            
	            switch (direction) {
	            case RIGHT:
	                x += speed;
	                break;
	            case LEFT:
	                x -= speed;
	                break;
	            case UP:
	                y -= speed;
	                break;
	            case DOWN:
	                y += speed;

	                break;}
	  
	  
	  }

}
