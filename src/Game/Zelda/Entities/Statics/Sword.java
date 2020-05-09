package Game.Zelda.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.BaseEntity;
import Main.Handler;
import Resources.Images;

public class Sword extends BaseEntity{
	public int boundx=0;
	public int boundy=0;
	public Rectangle interactBounds;

	public Sword(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, Images.sword[0], handler);
		// TODO Auto-generated constructor stub
		interactBounds = (Rectangle) bounds.clone();
        

    }
	
	

    public Rectangle getInteractBounds() {
		return interactBounds;
	}



	@Override
    public void render(Graphics g) {
    	
    	g.drawImage(sprite,(4 * (ZeldaGameState.stageWidth/8)) + ZeldaGameState.xOffset-8 ,(4 * (ZeldaGameState.stageHeight/12)) + ZeldaGameState.yOffset+ ((16*ZeldaGameState.worldScale))+30,width,height,null);
    }
}