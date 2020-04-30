package Game.Zelda.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.BaseEntity;
import Main.Handler;
import Resources.Images;

public class Sword extends BaseEntity{

	public Sword(int x, int y, BufferedImage[] sword, Handler handler) {
		super(x, y, Images.sword[0], handler);
		// TODO Auto-generated constructor stub
        bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
        

    }
	
	

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,(x* (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y* (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset-20,width,-height,null);
    }
}