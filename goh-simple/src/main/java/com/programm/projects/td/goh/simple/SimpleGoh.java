package com.programm.projects.td.goh.simple;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.GameObject;
import com.programm.projects.td.core.systems.renderer.Pencil;
import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.systems.goh.CollisionInfo;
import com.programm.projects.td.core.systems.goh.IGoh;
import com.programm.projects.td.math.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class SimpleGoh implements IGoh, CollisionInfo {

    private final List<GameObject> objects = new ArrayList<>();

    @Override
    public void startup(IEventHandler events) {}

    @Override
    public void shutdown() {}

    @Override
    public void update(GameContext context) {
        for(int i=0;i<objects.size();i++){
            objects.get(i).update(context);
        }
    }

    @Override
    public void render(GameContext context, Pencil pencil) {
        for(int i=0;i<objects.size();i++){
            objects.get(i).render(context, pencil);
        }
    }

    @Override
    public void addObject(GameObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    @Override
    public CollisionInfo testCollision(GameObject object) {
        Vector2f mPos = object.getPosition();
        Vector2f mSize = object.getSize();

        collides = false;

        for(int i=0;i<objects.size();i++){
            GameObject other = objects.get(i);

            if(object != other){
                Vector2f oPos = other.getPosition();
                Vector2f oSize = other.getSize();

                float leftInset = (oPos.getX() + oSize.getX()) - mPos.getX();
                float rightInset = (mPos.getX() + mSize.getX()) - oPos.getX();
                float topInset = (oPos.getY() + oSize.getY()) - mPos.getY();
                float bottomInset = (mPos.getY() + mSize.getY()) - oPos.getY();

                if(leftInset > 0 || rightInset > 0 || topInset > 0 || bottomInset > 0){
                    float hInset, vInset;
                    int hPre, vPre;

                    if(leftInset < rightInset){
                        hInset = leftInset;
                        hPre = -1;
                    }
                    else {
                        hInset = rightInset;
                        hPre = 1;
                    }

                    if(topInset < bottomInset){
                        vInset = topInset;
                        vPre = -1;
                    }
                    else {
                        vInset = bottomInset;
                        vPre = 1;
                    }

                    collides = true;
                    this.other = other;

                    if(hInset < vInset){
                        inset.set(hInset * hPre, 0);
                    }
                    else {
                        inset.set(0, vInset * vPre);
                    }

                    break;
                }
            }
        }

        return this;
    }


    //CURRENT COLLISION
    //BASED ON THAT COLLISION WILL BE TESTED AFTER ANOTHER AND NOT ON MULTIPLE THREADS

    private boolean collides;
    private GameObject other;
    private final Vector2f inset = new Vector2f();

    @Override
    public boolean collides() {
        return collides;
    }

    @Override
    public GameObject other() {
        return other;
    }

    @Override
    public Vector2f inset() {
        return inset;
    }
}
