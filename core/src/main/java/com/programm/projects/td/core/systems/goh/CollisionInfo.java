package com.programm.projects.td.core.systems.goh;

import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.math.Vector2f;

public interface CollisionInfo {

    boolean collides();

    GameObject other();

    Vector2f inset();

}
