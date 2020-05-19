package Game.Zelda.Entities.Dynamic;

import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import Game.Zelda.Entities.Statics.SectionDoor;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Enemy extends BaseMovingEntity{
	
	public Animation Bouncingenemy;

	public Enemy(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		// TODO Auto-generated constructor stub
		
		Bouncingenemy= new Animation(120, Images.bouncyEnemyFrames);

	}
@Override
public void tick(){ 
 Random rando = new Random(); 
 int random= rando.nextInt(4);
 switch(random) {
 case 0:  
	 if(direction != UP) {
     BufferedImage[] animList = new BufferedImage[2];
     animList[0] =sprites[0];
     animList[1] = sprites[1];
     animation = new Animation(120, animList);
     direction = UP;
     sprite = sprites[1];
 }
 animation.tick();
 move(direction);
	 
 case 1:
	  if (direction != DOWN) {
          BufferedImage[] animList = new BufferedImage[2];
          animList[0] = sprites[0];
          animList[1] = sprites[1];
          animation = new Animation(120, animList);
          direction = DOWN;
          sprite = sprites[1];
      }
      animation.tick();
      move(direction);
 case 2:
	 if (direction != Direction.LEFT) {
         BufferedImage[] animList = new BufferedImage[2];
         animList[0] =sprites[0];
         animList[1] = sprites[1];
         animation = new Animation(120, animList);
         direction = Direction.LEFT;
         sprite = sprites[1];
     }
     animation.tick();
     move(direction);
 case 3:
	 if (direction != Direction.RIGHT) {
         BufferedImage[] animList = new BufferedImage[2];
         animList[0] = (sprites[0]);
         animList[1] = (sprites[1]);
         animation = new Animation(120, animList);
         direction = Direction.RIGHT;
         sprite = sprites[1];
     }
     animation.tick();
     move(direction);
 
 
 
 }
         switch (direction) {
         case RIGHT:
             x += speed;
             animation.tick();
             move(direction);
             break;
         case LEFT:
             x -= speed;
             animation.tick();
             move(direction);
             break;
         case UP:
             y -= speed;
             animation.tick();
             move(direction);
             break;
         case DOWN:
             y += speed;
             animation.tick();
             move(direction);
             break;}
         
         
	}
	
	  @Override
	    public void render(Graphics g) {

		  		Bouncingenemy.tick();
	            g.drawImage(Bouncingenemy.getCurrentFrame(),handler.getZeldaGameState().xOffset+(handler.getZeldaGameState().stageWidth/2)-80 , handler.getZeldaGameState().yOffset + (handler.getZeldaGameState().stageHeight/2)+11, width , height  , null);
	            
		  }
	           
	               
	            
	  
	  }