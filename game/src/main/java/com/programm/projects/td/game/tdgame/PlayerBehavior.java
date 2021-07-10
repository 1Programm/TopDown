package com.programm.projects.td.game.tdgame;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.IBehavior;
import com.programm.projects.td.core.settings.WindowSettings;
import com.programm.projects.td.math.Vector2f;

public class PlayerBehavior implements IBehavior {

    private static final float SPEED_X = 0.5f;
    private static final float SPEED_Y = 0.7f;


    private final Vector2f vel = new Vector2f(SPEED_X, SPEED_Y);

    @Override
    public void update(GameContext context, GameObject obj) {
        WindowSettings window = context.settings().window();

        Vector2f pos = obj.getPosition();
        Vector2f size = obj.getSize();

        float nx = pos.getX() + vel.getX();
        float ny = pos.getY() + vel.getY();

        if(nx + size.getX() > window.width()){
            vel.setX(-SPEED_X);
            nx = window.width() - size.getX();
            window.xpos(window.xpos() + 100);
            nx -= 100;
        }
        else if(nx < 0){
            vel.setX(SPEED_X);
            nx = 0;
            window.xpos(window.xpos() - 100);
            nx += 100;
        }

        if(ny + size.getY() > window.height()){
            vel.setY(-SPEED_Y);
            ny = window.height() - size.getY();
            window.ypos(window.ypos() + 100);
            ny -= 100;
        }
        else if(ny < 0){
            vel.setY(SPEED_Y);
            ny = 0;
            window.ypos(window.ypos() - 100);
            ny += 100;
        }

        pos.set(nx, ny);
    }
}
