package com.programm.projects.td.core.systems.goh;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.ISubsystem;
import com.programm.projects.td.core.systems.renderer.Pencil;

public interface IGoh extends ISubsystem {

    void update(GameContext context);
    void render(GameContext context, Pencil pencil);

    void addObject(GameObject object);
    void removeObject(GameObject object);

    CollisionInfo testCollision(GameObject object);

}