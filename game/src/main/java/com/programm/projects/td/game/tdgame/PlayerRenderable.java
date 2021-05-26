package com.programm.projects.td.game.tdgame;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.IRenderable;
import com.programm.projects.td.core.systems.renderer.Pencil;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public class PlayerRenderable implements IRenderable {

    private final Color fillColor;
    private final Color boundsColor;

    @Override
    public void render(GameContext context, Pencil p, GameObject obj) {
        float x = obj.getPosition().getX();
        float y = obj.getPosition().getY();
        float w = obj.getSize().getX();
        float h = obj.getSize().getY();

        if(fillColor != null){
            p.setColor(fillColor);
            p.fillRect(x, y, w, h);
        }

        if(boundsColor != null){
            p.setColor(boundsColor);
            p.drawRect(x, y, w, h);
        }
    }
}
