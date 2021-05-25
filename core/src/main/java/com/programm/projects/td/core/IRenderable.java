package com.programm.projects.td.core;

import com.programm.projects.td.core.systems.renderer.Pencil;

public interface IRenderable {

    void render(Pencil p, GameContext context, GameObject obj);

}
