package com.programm.projects.td.game;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.IBehavior;
import com.programm.projects.td.core.ex.TDException;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
public class Main {

    public static void main(String[] args) throws TDException {
        Game game = new Game();
        game.init(args);

        GameObject o = new GameObject(100, 100, 64, 64);
        o.addRenderable((p, context, obj) -> {
            p.setColor(Color.BLACK);
            p.fillRect(obj.getPosition().getX(), obj.getPosition().getY(), obj.getSize().getX(), obj.getSize().getY());
        });
        o.addBehavior((object, context) -> {
            object.getPosition().add(1.1f, 0);
        });

        game.goh().addObject(o);

        game.settings().window().title("TEST");
        game.settings().window().size(600, 500);

        game.start();
    }

}
