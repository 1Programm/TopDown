package com.programm.projects.td.renderer.swing;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.settings.WindowSettings;
import com.programm.projects.td.core.systems.renderer.IRenderer;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class SwingRenderer implements IRenderer {

    private final Canvas canvas;
    private final SwingWindow window;
    private final SwingPencil pencil = new SwingPencil();

    public SwingRenderer(IEventHandler eventHandler) {
        this.canvas = new Canvas();
        this.window = new SwingWindow(canvas, eventHandler);
    }

    @Override
    public void startup(IEventHandler events) {
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @Override
    public void shutdown() {
        window.dispose();
    }

    @Override
    public WindowSettings init() {
        return window;
    }

    @Override
    public void render(GameContext context) {
        BufferStrategy bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        if(pencil.g == null) {
            pencil.g = (Graphics2D) g;
        }

        context.goh().render(context, pencil);

        g.dispose();
        bs.show();
    }
}
