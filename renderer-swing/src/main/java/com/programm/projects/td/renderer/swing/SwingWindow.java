package com.programm.projects.td.renderer.swing;

import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.settings.WindowSettings;
import com.programm.projects.td.core.systems.renderer.WindowCloseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SwingWindow extends JFrame implements WindowSettings, WindowListener {

    private final Canvas canvas;
    private final Dimension size;
    private final IEventHandler eventHandler;

    public SwingWindow(Canvas canvas, IEventHandler eventHandler) {
        this.canvas = canvas;
        this.size = new Dimension();
        this.eventHandler = eventHandler;

        this.addWindowListener(this);
        this.setResizable(false); //Mby also through settings
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.add(canvas);
        this.pack();
    }

    @Override
    public void title(String title) {
        this.setTitle(title);
    }

    @Override
    public String title() {
        return this.getTitle();
    }

    @Override
    public void width(int width) {
        size.width = width;
        canvas.setPreferredSize(size);
        this.setSize(size);
    }

    @Override
    public int width() {
        return size.width;
    }

    @Override
    public void height(int height) {
        size.height = height;
        canvas.setPreferredSize(size);
        this.setSize(size);
    }

    @Override
    public int height() {
        return size.height;
    }

    @Override
    public void size(int width, int height) {
        size.setSize(width, height);
        canvas.setPreferredSize(size);
        this.setSize(size);
    }

    //WINDOW LISTENER
    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        eventHandler.dispatch(new WindowCloseEvent());
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
