package com.programm.projects.td.core;

public interface IBehavior {

    void update(GameContext context, GameObject obj);
    default int priority() {
        return 1;
    }

}
