package com.programm.projects.td.core.systems.renderer;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.ISubsystem;
import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.settings.WindowSettings;

public interface IRenderer extends ISubsystem {

    /**
     * Initializes the renderer.
     * {@link IRenderer#init()} should be called before the {@link ISubsystem#startup(IEventHandler)} method is called!
     * It will provide a responsive link to manipulate the window and its properties.
     * @return the "link" which can be used to manipulate the windows properties.
     */
    WindowSettings init();

    void render(GameContext context);

}
