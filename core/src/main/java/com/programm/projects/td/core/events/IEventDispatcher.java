package com.programm.projects.td.core.events;

public interface IEventDispatcher {

    <T extends IEvent> void dispatch(T event);

}
