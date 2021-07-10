package com.programm.projects.td.game;

import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.ex.TDException;
import com.programm.projects.td.game.tdgame.PlayerBehavior;
import com.programm.projects.td.game.tdgame.PlayerRenderable;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
public class Main {

    public static void main(String[] args) throws TDException {
        Game game = new Game();
        game.init(args);


        game.start();

        GameObject o = new GameObject(100, 100, 50, 50);
        o.addRenderable(new PlayerRenderable(Color.BLACK, Color.RED));
        o.addBehavior(new PlayerBehavior());

        game.goh().addObject(o);

        game.settings().window().title("TEST");
        game.settings().window().size(250, 250);
        game.settings().window().centerPosition();

    }

}
