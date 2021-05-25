package com.programm.projects.td.game;

import com.programm.projects.td.core.GameContext;
import com.programm.projects.td.core.ISubsystem;
import com.programm.projects.td.core.events.IEventHandler;
import com.programm.projects.td.core.ex.TDException;
import com.programm.projects.td.core.ex.TDFatalException;
import com.programm.projects.td.core.ex.TDRuntimeException;
import com.programm.projects.td.core.settings.Settings;
import com.programm.projects.td.core.settings.WindowSettings;
import com.programm.projects.td.core.systems.goh.IGoh;
import com.programm.projects.td.core.systems.renderer.IRenderer;
import com.programm.projects.td.core.systems.renderer.WindowCloseEvent;
import com.programm.projects.td.goh.simple.SimpleGoh;
import com.programm.projects.td.renderer.swing.SwingRenderer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Game implements GameContext, Settings{

    private final List<ISubsystem> subsystems = new ArrayList<>();
    private final EventBus eventBus = new EventBus();

    private boolean stopRequest;

    //SETTINGS
    private WindowSettings windowSettings;

    //SUBSYSTEMS
    private RunLoop runLoop;
    private IGoh goh;
    private IRenderer renderer;

    public void init(String... args) throws TDException {
        log.info("Initializing Game with args: {}", Arrays.toString(args));

        //TODO: Interpret args (-d means debug mode enabled, etc. ...)


        //TODO: Set implementation through automated detection or settings file instead of hard coding
        subsystems.add(goh = new SimpleGoh());
        subsystems.add(renderer = new SwingRenderer(eventBus));

        //TODO: Set input vars for run loop through settings file instead of hard coding
        subsystems.add(runLoop = new RunLoop(this, true, 60, true));


        //Init Engine
        eventBus.listenFor(WindowCloseEvent.class, this::onWindowClose);


        //Init subsystems
        this.windowSettings = renderer.init();

    }

    public void start() throws TDException {
        log.info("Starting Engine...");
        try {
            for (ISubsystem sys : subsystems) {
                log.info("# Starting Subsystem: [{}]", sys.getClass().getSimpleName());
                sys.startup(eventBus);
            }
        }
        catch (TDFatalException e){
            throw new TDFatalException("Exception while starting the Game!", e);
        }
        log.info("Engine Started!");
    }

    public void stop() throws TDException{
        log.info("Stopping Engine...");
        try {
            for(int i=subsystems.size()-1;i>=0;i--){
                ISubsystem sys = subsystems.get(i);
                log.debug("# Stopping Subsystem: [{}]", sys.getClass().getSimpleName());
                sys.shutdown();
            }
        }
        catch (TDFatalException e){
            throw new TDFatalException("Exception while stopping the Game!", e);
        }

        log.info("Engine Stopped!");
    }

    void onWindowClose(WindowCloseEvent e){
        //Must go over stop request as the UI Thread may call this method and it should be handled on the engine thread!
        stopRequest = true;
    }

    void update(){
        if(stopRequest){
            try {
                stop();
            }
            catch (TDException e){
                throw new TDRuntimeException(e);
            }
            return;
        }

        goh.update(this);
    }

    void render(){
        renderer.render(this);
    }

    /*
     * GAME CONTEXT
     */

    @Override
    public Settings settings() {
        return this;
    }

    @Override
    public IGoh goh() {
        return goh;
    }

    @Override
    public IEventHandler events() {
        return eventBus;
    }

    /*
     * SETTINGS
     */

    @Override
    public WindowSettings window() {
        return windowSettings;
    }
}
