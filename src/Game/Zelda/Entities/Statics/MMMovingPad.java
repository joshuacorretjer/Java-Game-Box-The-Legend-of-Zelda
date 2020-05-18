package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.Zelda.Entities.MMBaseEntity;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class MMMovingPad extends MMBaseEntity {

    public int linkedX,linkedY;
    public boolean mov = false;

    public MMMovingPad(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y,sprite, handler);
        
        bounds = new Rectangle(x ,y ,width,height);

    }

    @Override
    public void tick() {
        if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
//            ((ZeldaMMGameState)handler.getState()).map.link.x = linkedX;
//            ((ZeldaMMGameState)handler.getState()).map.link.y = linkedY;
        	mov=true;
        }
        if (mov) {
        	recursiveMove(((ZeldaMMGameState)handler.getState()).map.link.x,((ZeldaMMGameState)handler.getState()).map.link.y);
        	
            return;
        }
        
    }
    
    public void recursiveMove(int x, int y) {
    	if(((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && sprite==Images.movingTiles.get(0)) {
    		recursiveMove(x+(width+10),y);
    	}else {
    		mov=false;
    	}
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x ,y,width,height,null);
    }
}
