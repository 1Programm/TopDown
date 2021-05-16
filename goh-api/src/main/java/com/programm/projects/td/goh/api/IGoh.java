package com.programm.projects.td.goh.api;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.ISubsystem;

public interface IGoh extends ISubsystem {

    void update(GameContext context);

    void addObject(GameObject object);
    void removeObject(GameObject object);

}
