package Game.Zelda.Entities;

import Game.GameStates.Zelda.ZeldaMapMakerState;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;

/**
 * Created by AlexVR on 3/14/2020
 */
public class BaseEntity {
    public int x,y,width,height;
    public BufferedImage sprite;
    public Rectangle bounds;
    public Handler handler;
    public boolean remove;

    public BaseEntity(int x, int y, BufferedImage sprite, Handler handler) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.handler = handler;
        if (sprite!=null) {
            width = sprite.getWidth() * worldScale;
            height = sprite.getHeight() * worldScale;
        }else{
            width = 1;
            height = 1;
        }
        bounds = new Rectangle(x,y,width,height);

    }

    public void tick(){

    }

    public void render(Graphics g) {

    }

	public void set(int i, ArrayList<BaseEntity> interact) {
		// TODO Auto-generated method stub
		
	}

	public void add(ArrayList arrayList) {
		// TODO Auto-generated method stub
		
	}
}
