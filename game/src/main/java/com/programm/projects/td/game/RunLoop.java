package com.programm.projects.td.game;

import com.programm.projects.td.core.ISubsystem;
import com.programm.projects.td.core.events.IEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RunLoop implements ISubsystem {

    private static final String THREAD_NAME = "TD Engine";

    private final Thread engineThread = new Thread(this::run, THREAD_NAME);;
    private final Game game;
    private final boolean onNewThread;
    private final float fps;
    private final boolean printFps;

    private boolean running;

    @Override
    public void startup(IEventHandler events){
        if(running) return;
        running = true;

        if(onNewThread){
            log.debug("Running on new Thread!");

            engineThread.start();
        }
        else {
            log.debug("Running in same Thread!");
            run();
        }
    }

    @Override
    public void shutdown(){
        if(!running) return;
        running = false;
    }

    private void run(){
        long lastTime = System.nanoTime();
        double ns = fps / 1000000000;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) * ns;
            lastTime = now;

            boolean updated = false;

            while(delta >= 1){
                game.update();
                if(!running) return;
                if(printFps) updates++;
                delta--;

                updated = true;
            }

            if(updated){
                game.render();
            }

            if(printFps) {
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    log.trace("FPS: " + frames + " - TICKS: " + updates);
                    frames = 0;
                    updates = 0;
                }
            }
        }
    }

}
