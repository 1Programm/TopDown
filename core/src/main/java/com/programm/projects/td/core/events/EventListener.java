package com.programm.projects.td.core.events;

public interface EventListener<T extends IEvent> {

    void onEvent(T event);

}
