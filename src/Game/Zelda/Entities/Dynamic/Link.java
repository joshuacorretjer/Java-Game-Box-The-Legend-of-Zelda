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
    Animation RattackAni;
    Animation DattackAni;
    public int counter = 10*60;


    public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite, handler);
        speed = 4;
        health = 6;
        BufferedImage[] animList = new BufferedImage[2];
        animList[0] = sprite[4];
        animList[1] = sprite[5];

        animation = new Animation(animSpeed,animList);
        
        BufferedImage[] rattac = new BufferedImage[3];
        rattac[0] = Images.attack[3];
        rattac[1] = Images.attack[4];
        rattac[2] = Images.attack[5];
        
        RattackAni = new Animation(animSpeed,rattac);
        
        BufferedImage[] dattac = new BufferedImage[3];
        dattac[0] = Images.attack[0];
        dattac[1] = Images.attack[1];
        dattac[2] = Images.attack[2];
        
        DattackAni = new Animation(animSpeed,dattac);
        
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
//            }else if(handler.getKeyManager().enter) {
//            	attacki=true;
//                if(attacki==true) {
//                	BufferedImage[] atta = new BufferedImage[3];
//                	atta[0] = Images.attack[3];
//                	atta[1] = Images.attack[4];
//                	atta[2] = Images.attack[5];
//                attackAni = new Animation(animSpeed,atta);
//            }
//                attackAni.tick();
//                attacki=false;
            }
            
            else {
                moving = false;
            }
        }
     if(handler.getKeyManager().enter) {
    	attacki=true;
        if(attacki==true && direction == Direction.RIGHT) {
        	if(counter>0) {
        		attacki=true;
        		RattackAni.tick();
        		counter--;
        	}else{
        		attacki=false;
        		counter = 6*60;
        	}
        
        }else if(attacki==true && direction == Direction.DOWN) {
        	if(counter>0) {
        		attacki=true;
        		DattackAni.tick();
        		counter--;
        	}else{
        		attacki=false;
        		counter = 6*60;
        	}
        }
     }else {
    	 attacki=false;
     }
     
     
    }

    @Override
    public void render(Graphics g) {
        if (moving) {
            g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
        } else {
            if (movingMap){
                g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
            }
            if(attacki==true && direction == Direction.RIGHT) {
//            	if(counter>0) {
//            		g.drawImage(attackAni.getCurrentFrame(),x , y, width , height  , null);
//            		counter--;
//            	}else{
//            		attacki=false;
//            		counter = 1*60;
//            	}
        		g.drawImage(RattackAni.getCurrentFrame(),x , y,50 , height , null);
            }else if(attacki==true && direction == Direction.DOWN) {
        		g.drawImage(DattackAni.getCurrentFrame(),x , y,50 , height , null);
            
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
