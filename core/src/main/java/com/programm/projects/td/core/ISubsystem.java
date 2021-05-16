package com.programm.projects.td.core;

import com.programm.projects.td.core.ex.TDFatalException;

public interface ISubsystem {

    void startup() throws TDFatalException;
    void shutdown() throws TDFatalException;

}
