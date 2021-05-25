package com.programm.projects.td.core;

import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.ex.TDFatalException;

public interface ISubsystem {

    void startup(IEventHandler events) throws TDFatalException;
    void shutdown() throws TDFatalException;

}
