package com.programm.projects.td.core;

import com.programm.projects.td.core.systems.renderer.Pencil;
import com.programm.projects.td.core.utils.PriorityList;
import com.programm.projects.td.math.Vector1f;
import com.programm.projects.td.math.Vector2f;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameObject {

    private final Vector2f position;
    private final Vector2f size;
    private final Vector1f rotation;

    private final PriorityList<IBehavior> behaviorList = new PriorityList<>();
    private final List<IRenderable> renderableList = new ArrayList<>();

    //Marker for GOH to know when to remove an object from the list
    private boolean dead;

    public GameObject(float x, float y, float w, float h){
        this.position = new Vector2f(x, y);
        this.size = new Vector2f(w, h);
        this.rotation = new Vector1f();
    }

    public void update(GameContext context) {
        for(int i=0;i<behaviorList.size();i++){
            behaviorList.get(i).update(context, this);
        }
    }

    public void render(GameContext context, Pencil p) {
        for(int i=0;i<renderableList.size();i++){
            renderableList.get(i).render(context, p, this);
        }
    }

    public void die(){
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public GameObject addBehavior(IBehavior behavior){
        behaviorList.add(behavior, behavior.priority());
        return this;
    }

    public GameObject addRenderable(IRenderable renderable){
        renderableList.add(renderable);
        return this;
    }
}
