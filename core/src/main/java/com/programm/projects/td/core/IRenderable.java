package com.programm.projects.td.core;

import com.programm.projects.td.core.systems.renderer.Pencil;

public interface IRenderable {

    void render(GameContext context, Pencil p, GameObject obj);

}
