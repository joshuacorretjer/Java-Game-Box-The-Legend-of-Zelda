package Game.Zelda.World;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.MMBaseMovingEntity;
import Game.Zelda.Entities.Dynamic.MMLink;
import Game.Zelda.Entities.MMBaseEntity;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    ArrayList<MMBaseEntity> blocksOnMap;
    ArrayList<MMBaseMovingEntity> enemiesOnMap;
    Handler handler;
    public MMLink link;
    public int xOffset =0,yOffset = 0;

    public Map(Handler handler) {
        this.handler=handler;
        this.blocksOnMap = new ArrayList<>();
        this.enemiesOnMap = new ArrayList<>();
    }

    public void addBlock(MMBaseEntity block){
        blocksOnMap.add(block);
    }

    public void addEnemy(MMBaseMovingEntity entity){
        enemiesOnMap.add(entity);
    }

    public void drawMap(Graphics2D g2) {
        for (MMBaseEntity block:blocksOnMap) {
            g2.drawImage(block.sprite, block.x , block.y , block.width, block.height , null);
        }
        for (MMBaseMovingEntity entity:enemiesOnMap) {
            if (entity instanceof MMLink){
                entity.render(g2);
            }else {
                g2.drawImage(entity.sprite, entity.x , entity.y , entity.width , entity.height , null);
            }
        }
//        for (int i = 0; i< handler.getZeldaGameState().health;i++) {//Draws the current remaining lives of Pac-Man
//        g2.drawImage(Images.zeldaLinkFrames[0],(4 * (ZeldaGameState.stageWidth/8)) + ZeldaGameState.xOffset-8 ,(4 * (ZeldaGameState.stageHeight/12)) + ZeldaGameState.yOffset+ ((16*ZeldaGameState.worldScale))  , 30, 30 , null);
//        }
    }


    public ArrayList<MMBaseEntity> getBlocksOnMap() {
        return blocksOnMap;
    }

    public ArrayList<MMBaseMovingEntity> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public void reset() {
    }
}
