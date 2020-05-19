package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.PacMan.World.MapBuilder;
import Game.Zelda.Entities.MMBaseEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.World.Map;
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

    public MMMovingPad(int x, int y, int width, int height, BufferedImage sprite, Handler handler) {
        super(x, y,sprite, handler);
        this.width = MapBuilder.pixelMultiplier;
        this.height = MapBuilder.pixelMultiplier;
        bounds = new Rectangle(x ,y ,MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier);
        Map map = new Map(handler);

    }

    @Override
    public void tick() {
        if(handler.getState() instanceof ZeldaMMGameState && handler.getKeyManager().shift){
//            ((ZeldaMMGameState)handler.getState()).map.link.x+=width;
//            ((ZeldaMMGameState)handler.getState()).map.link.y = linkedY;
    	if(sprite==Images.movingTiles.get(0)) {
    		((ZeldaMMGameState)handler.getState()).map.link.x+=5;
		}else if(sprite==Images.movingTiles.get(1)){
			((ZeldaMMGameState)handler.getState()).map.link.y+=5;
		}else if(sprite==Images.movingTiles.get(2)) {
			((ZeldaMMGameState)handler.getState()).map.link.x-=5;
		}else if(sprite==Images.movingTiles.get(3)) {
			((ZeldaMMGameState)handler.getState()).map.link.y-=5;
		}
//        	movingStep();
        }
        
    }
    
    public void movingStep() {
    	Map map = new Map(handler);
    	for(MMBaseEntity tileR: map.getBlocksOnMap()) {
    		if(tileR instanceof MMMovingPad && map.link.interactBounds.intersects(tileR.bounds)) {
    			if(tileR.sprite.equals(Images.movingTiles.get(0))) {
    				map.link.move(Direction.RIGHT);
    			}else if(tileR.sprite.equals(Images.movingTiles.get(1))) {
    				map.link.move(Direction.DOWN);
    			}else if(tileR.sprite.equals(Images.movingTiles.get(2))) {
    				map.link.move(Direction.LEFT);
    			}else if(tileR.sprite.equals(Images.movingTiles.get(3))) {
    				map.link.move(Direction.UP);
    			}
    			movingStep();
    		}
    	}
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x ,y,MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier,null);
    }
}
