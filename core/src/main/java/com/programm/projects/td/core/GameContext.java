package com.programm.projects.td.core;

import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.settings.Settings;
import com.programm.projects.td.core.systems.goh.IGoh;

public interface GameContext {

    /*
     *  SYSTEMS AND STATICS
     */

    Settings settings();

    IGoh goh();

    IEventHandler events();


}
