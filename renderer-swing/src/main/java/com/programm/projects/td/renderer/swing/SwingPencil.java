package com.programm.projects.td.renderer.swing;

import com.programm.projects.td.core.Pencil;
import com.programm.projects.td.math.Vector1f;
import com.programm.projects.td.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingPencil implements Pencil {

    private class MutatedSwingPencil implements Pencil {
        private final Vector2f offset;
        private final Vector1f rotation;

        public MutatedSwingPencil(float xoff, float yoff, float rotation){
            this.offset = new Vector2f(xoff, yoff);
            this.rotation = new Vector1f(rotation);
        }

        private void doMutation(){
            float xoff = offset.getX();
            float yoff = offset.getY();

            if(xoff != 0 || yoff != 0){
                SwingPencil.this.g.translate(xoff, yoff);
            }

            float rot = rotation.getVal();
            if(rot != 0){
                SwingPencil.this.g.rotate(rot);
            }
        }

        private void undoMutation(){
            float rot = rotation.getVal();
            if(rot != 0){
                SwingPencil.this.g.rotate(-rot);
            }

            float xoff = offset.getX();
            float yoff = offset.getY();

            if(xoff != 0 || yoff != 0){
                SwingPencil.this.g.translate(-xoff, -yoff);
            }
        }

        @Override
        public void setColor(Color color) {
            SwingPencil.this.setColor(color);
        }

        @Override
        public void drawLine(float x1, float y1, float x2, float y2) {
            doMutation();
            SwingPencil.this.drawLine(x1, y1, x2, y2);
            undoMutation();
        }

        @Override
        public void drawRect(float x, float y, float width, float height) {
            doMutation();
            SwingPencil.this.drawRect(x, y, width, height);
            undoMutation();
        }

        @Override
        public void fillRect(float x, float y, float width, float height) {
            doMutation();
            SwingPencil.this.fillRect(x, y, width, height);
            undoMutation();
        }

        @Override
        public void drawImage(float x, float y, float width, float height, BufferedImage image) {
            doMutation();
            SwingPencil.this.drawImage(x, y, width, height, image);
            undoMutation();
        }

        @Override
        public Pencil offset(float x, float y) {
            this.offset.add(x, y);
            return this;
        }

        @Override
        public Pencil rotated(float rotation) {
            this.rotation.add(rotation);
            return this;
        }
    }

    Graphics2D g;

    @Override
    public void setColor(Color color) {
        g.setColor(color);
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    @Override
    public void drawRect(float x, float y, float width, float height) {
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void fillRect(float x, float y, float width, float height) {
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void drawImage(float x, float y, float width, float height, BufferedImage image) {
        g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
    }

    @Override
    public Pencil offset(float x, float y) {
        return new MutatedSwingPencil(x, y, 0);
    }

    @Override
    public Pencil rotated(float rotation) {
        return new MutatedSwingPencil(0, 0, rotation);
    }

}
