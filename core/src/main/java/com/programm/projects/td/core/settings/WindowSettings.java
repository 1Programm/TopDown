package com.programm.projects.td.core.settings;

public interface WindowSettings {

    void title(String title);
    String title();

    void xpos(int xpos);
    int xpos();

    void ypos(int ypos);
    int ypos();

    void centerPosition();

    void width(int width);
    int width();

    void height(int height);
    int height();

    void size(int width, int height);

}
