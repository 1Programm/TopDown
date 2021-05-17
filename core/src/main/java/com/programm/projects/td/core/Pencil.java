package com.programm.projects.td.core;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Pencil {

    /* ------------ */
    /* DRAW METHODS */
    /* ------------ */

    void setColor(Color color);

    void drawLine(float x1, float y1, float x2, float y2);

    void drawRect(float x, float y, float width, float height);

    void fillRect(float x, float y, float width, float height);

    void drawImage(float x, float y, float width, float height, BufferedImage image);


    /* -------------------------------------- */
    /* TRANSFORMATION METHODS:                */
    /* (RETURNS A MODIFIED VERSION OF PENCIL) */
    /* -------------------------------------- */

    Pencil offset(float x, float y);

    Pencil rotated(float rotation);

    Pencil rotatedAround(float rotation, float x, float y);


}
