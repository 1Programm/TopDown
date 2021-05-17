package com.programm.projects.td.math;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transform2f {

    private final Vector2f position;
    private final Vector2f size;
    private float rotation;

    public Transform2f(){
        this.position = new Vector2f();
        this.size = new Vector2f();
        this.rotation = 0;
    }

    public Transform2f(Vector2f position, Vector2f size, float rotation) {
        this.position = Checks.instanceOrNew(position, Vector2f::new);
        this.size = Checks.instanceOrNew(size, Vector2f::new);;
        this.rotation = rotation;
    }

    public Transform2f setPosition(float x, float y){
        this.position.set(x, y);
        return this;
    }

    public Transform2f setSize(float width, float height){
        this.size.set(width, height);
        return this;
    }

    @Override
    public Transform2f clone() {
        Vector2f position = this.position.clone();
        Vector2f size = this.size.clone();
        float rotation = this.rotation;

        return new Transform2f(position, size, rotation);
    }
}
