package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.Sword;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {


    private final int animSpeed = 120;
    int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
    public boolean movingMap = false;
    public boolean attacki = false;
    Direction movingTo;
    Animation UpAttackAnim;
    Animation DownAttackAnim;
    Animation LeftAttackAnim;
    Animation RightAttackAnim;
    public double counter = 0.5*60;
    public double count=0.0;
    public int newWidth = 0;
    public boolean flag = false;
    public boolean exp = false;


    public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite, handler);
        speed = 4;
        health = 6;
        BufferedImage[] animList = new BufferedImage[2];
        animList[0] = sprite[4];
        animList[1] = sprite[5];

        animation = new Animation(animSpeed,animList);
        
        BufferedImage[] upattack = new BufferedImage[5];
        upattack[0] = Images.attack[0];
        upattack[1] = Images.attack[1];
        upattack[2] = Images.attack[2];
        upattack[3] = Images.attack[1];
        upattack[4] = Images.attack[0];
        
        UpAttackAnim = new Animation(70,upattack);
        
        BufferedImage[] downattack = new BufferedImage[5];
        downattack[0] = Images.attack[3];
        downattack[1] = Images.attack[4];
        downattack[2] = Images.attack[5];
        downattack[3] = Images.attack[4];
        downattack[4] = Images.attack[3];
        
        DownAttackAnim = new Animation(70,downattack);
        
        BufferedImage[] leftattack = new BufferedImage[5];
        leftattack[0] = Images.attack[6];
        leftattack[1] = Images.attack[7];
        leftattack[2] = Images.attack[8];
        leftattack[3] = Images.attack[7];
        leftattack[4] = Images.attack[6];
        
        LeftAttackAnim = new Animation(70,leftattack);
        
        BufferedImage[] rightattack = new BufferedImage[5];
        rightattack[0] = Images.attack[6];
        rightattack[1] = Images.attack[7];
        rightattack[2] = Images.attack[8];
        rightattack[3] = Images.attack[7];
        rightattack[4] = Images.attack[6];
        
        RightAttackAnim = new Animation(70,rightattack);
        
        
        
    }

    @Override
    public void tick() {
        if (movingMap){
            switch (movingTo) {
                case RIGHT:
                    handler.getZeldaGameState().cameraOffsetX++;
                    newMapX++;
                    if (xExtraCounter>0){
                        x+=2;
                        xExtraCounter--;
                        animation.tick();

                    }else{
                        x--;
                    }
                    break;
                case LEFT:
                    handler.getZeldaGameState().cameraOffsetX--;
                    newMapX--;
                    if (xExtraCounter>0){
                        x-=2;
                        xExtraCounter--;
                        animation.tick();

                    }else{
                        x++;
                    }
                    break;
                case UP:
                    handler.getZeldaGameState().cameraOffsetY--;
                    newMapY++;
                    if (yExtraCounter>0){
                        y-=2;
                        yExtraCounter--;
                        animation.tick();

                    }else{
                        y++;
                    }
                    break;
                case DOWN:
                    handler.getZeldaGameState().cameraOffsetY++;
                    newMapY--;
                    if (yExtraCounter>0){
                        y+=2;
                        yExtraCounter--;
                        animation.tick();
                    }else{
                        y--;
                    }
                    break;
            }
            bounds = new Rectangle(x,y,width,height);
            changeIntersectingBounds();
            if (newMapX == 0 && newMapY == 0){
                movingMap = false;
                movingTo = null;
                newMapX = 0;
                newMapY = 0;
            }
        }else {
            if (handler.getKeyManager().up) {
                if (direction != UP) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = sprites[4];
                    animList[1] = sprites[5];
                    animation = new Animation(animSpeed, animList);
                    direction = UP;
                    sprite = sprites[4];
                }
                animation.tick();
                move(direction);

            } else if (handler.getKeyManager().down) {
                if (direction != DOWN) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = sprites[0];
                    animList[1] = sprites[1];
                    animation = new Animation(animSpeed, animList);
                    direction = DOWN;
                    sprite = sprites[0];
                }
                animation.tick();
                move(direction);
            } else if (handler.getKeyManager().left) {
                if (direction != Direction.LEFT) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = Images.flipHorizontal(sprites[2]);
                    animList[1] = Images.flipHorizontal(sprites[3]);
                    animation = new Animation(animSpeed, animList);
                    direction = Direction.LEFT;
                    sprite = Images.flipHorizontal(sprites[3]);
                }
                animation.tick();
                move(direction);
            } else if (handler.getKeyManager().right) {
                if (direction != Direction.RIGHT) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = (sprites[2]);
                    animList[1] = (sprites[3]);
                    animation = new Animation(animSpeed, animList);
                    direction = Direction.RIGHT;
                    sprite = (sprites[3]);
                }
                animation.tick();
                move(direction);
            }
            
            else {
                moving = false;
            }
        }
     if(handler.getKeyManager().enter) {
    	 attacki = true;
     }
     if(attacki) {
    	 switch(direction) {
    	 case UP:
    		 UpAttackAnim.tick();
        	 if(UpAttackAnim.end) {
        		 attacki=false;
        		 UpAttackAnim.reset();
        		 }
        	 break;
    	 case DOWN:
    		 DownAttackAnim.tick();
        	 if(DownAttackAnim.end) {
        		 attacki=false;
        		 DownAttackAnim.reset();
        		 }
        	 break;
    	 case LEFT:
    		 LeftAttackAnim.tick();
        	 if(LeftAttackAnim.end) {
        		 attacki=false;
        		 LeftAttackAnim.reset();
        		 }
        	 break;
    	 case RIGHT:
    		 RightAttackAnim.tick();
        	 if(RightAttackAnim.end) {
        		 attacki=false;
        		 RightAttackAnim.reset();
        		 }
        	 break;
    	 }
     }
     
     
    }

    @Override
    public void render(Graphics g) {
//        for (int i = 0; i< health;i++) {//Draws the current remaining lives of Pac-Man
//        g.drawImage(Images.zeldaLinkFrames[0],(2 * (ZeldaGameState.stageWidth/10)) + ZeldaGameState.xOffset+400 ,(4 * (ZeldaGameState.stageHeight/12)), 30, 30 , null);
//        }
        if (moving) {
            g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
        } else {
            if (movingMap){
                g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
            }
            if(attacki==true) {
            	if(direction == Direction.UP) {
            		if(UpAttackAnim.getCurrentFrame()==Images.attack[0]){
            		g.drawImage(UpAttackAnim.getCurrentFrame(), x, y, width, height+2, null);
            		}
            		if(UpAttackAnim.getCurrentFrame()==Images.attack[1]){
            		g.drawImage(UpAttackAnim.getCurrentFrame(), x, y, width, height+10, null);
            		}
            		if(UpAttackAnim.getCurrentFrame()==Images.attack[2]){
            		g.drawImage(UpAttackAnim.getCurrentFrame(), x, y, width, height+20, null);
            		}
            	}else if(direction == Direction.DOWN) {
            		if(DownAttackAnim.getCurrentFrame()==Images.attack[3]){
            		g.drawImage(DownAttackAnim.getCurrentFrame(), x, y, width, height+2, null);
            		}
            		if(DownAttackAnim.getCurrentFrame()==Images.attack[4]){
            		g.drawImage(DownAttackAnim.getCurrentFrame(), x, y, width, height+10, null);
            		}
            		if(DownAttackAnim.getCurrentFrame()==Images.attack[5]){
            		g.drawImage(DownAttackAnim.getCurrentFrame(), x, y, width, height+20, null);
            		}
//            		g.drawImage(DownAttackAnim.getCurrentFrame(),x , y,width , (int) (height+count) , null);
            	}else if(direction == Direction.LEFT) {
            		
            		if(LeftAttackAnim.getCurrentFrame()==Images.attack[6]){
            		g.drawImage(LeftAttackAnim.getCurrentFrame(), x+width, y, -(width+2), height , null);
            		}
            		if(LeftAttackAnim.getCurrentFrame()==Images.attack[7]){
            		g.drawImage(LeftAttackAnim.getCurrentFrame(), x+width, y, -(width+10), height , null);
            		}
            		if(LeftAttackAnim.getCurrentFrame()==Images.attack[8]){
            		g.drawImage(LeftAttackAnim.getCurrentFrame(), x+width, y, -(width+20), height , null);
            		}
//            		g.drawImage(LeftAttackAnim.getCurrentFrame(),x , y,width, height , null);
            	}else if(direction == Direction.RIGHT) {
            		if(RightAttackAnim.getCurrentFrame()==Images.attack[6]){
            		g.drawImage(RightAttackAnim.getCurrentFrame(), x, y, width+2, height, null);
            		}
            		if(RightAttackAnim.getCurrentFrame()==Images.attack[7]){
            		g.drawImage(RightAttackAnim.getCurrentFrame(), x, y, width+10, height, null);
            		}
            		if(RightAttackAnim.getCurrentFrame()==Images.attack[8]){
            		g.drawImage(RightAttackAnim.getCurrentFrame(), x, y, width+20, height, null);
            		}
//            		g.drawImage(RightAttackAnim.getCurrentFrame(),x , y,(int) (width+count) , height , null);
            	}
        		
            }else {
            	g.drawImage(sprite, x , y, width , height , null);
            }
            
        }
    }

    @Override
    public void move(Direction direction) {
        moving = true;
        changeIntersectingBounds();
        //chack for collisions
        if (ZeldaGameState.inCave){
            for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
                if ((objects instanceof DungeonDoor) && objects.bounds.intersects(bounds) && direction == ((DungeonDoor) objects).direction) {
                    if (((DungeonDoor) objects).name.equals("caveStartLeave")) {
                        ZeldaGameState.inCave = false;
                        x = ((DungeonDoor) objects).nLX;
                        y = ((DungeonDoor) objects).nLY;
                        direction = DOWN;
                    }
                } else if (!(objects instanceof DungeonDoor) && objects.bounds.intersects(interactBounds)) {
                    //dont move
                    return;
                }
            }
            for(BaseEntity swoRD : handler.getZeldaGameState().interactable) {
            	if(swoRD.bounds.intersects(bounds)) {
            		ZeldaGameState.haveSword = true;
            	}
        	}
//            if(handler.getZeldaGameState().sword.bounds.intersects(bounds)) {
//            	
//            }
        }
        else{
            for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
                if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
                    if (!(objects instanceof DungeonDoor)) {
                        movingMap = true;
                        movingTo = ((SectionDoor) objects).direction;
                        switch (((SectionDoor) objects).direction) {
                            case RIGHT:
                                newMapX = -(((handler.getZeldaGameState().mapWidth) + 1) * worldScale);
                                newMapY = 0;
                                handler.getZeldaGameState().mapX++;
                                xExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                            case LEFT:
                                newMapX = (((handler.getZeldaGameState().mapWidth) + 1) * worldScale);
                                newMapY = 0;
                                handler.getZeldaGameState().mapX--;
                                xExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                            case UP:
                                newMapX = 0;
                                newMapY = -(((handler.getZeldaGameState().mapHeight) + 1) * worldScale);
                                handler.getZeldaGameState().mapY--;
                                yExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                            case DOWN:
                                newMapX = 0;
                                newMapY = (((handler.getZeldaGameState().mapHeight) + 1) * worldScale);
                                handler.getZeldaGameState().mapY++;
                                yExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                        }
                        return;
                    }
                    else {
                        if (((DungeonDoor) objects).name.equals("caveStartEnter")) {
                            ZeldaGameState.inCave = true;
                            x = ((DungeonDoor) objects).nLX;
                            y = ((DungeonDoor) objects).nLY;
                            direction = UP;
                        }
                    }
                }
                else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
                    //dont move
                    return;
                }
            }

        }
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

                break;
        }
        bounds.x = x;
        bounds.y = y;
        changeIntersectingBounds();

    }
}
