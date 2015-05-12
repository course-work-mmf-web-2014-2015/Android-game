package com.bsu.mmf.web.course_work.handlers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameworld.MenuWorld;

/**
 * Created by DemonStore on 20.04.2015.
 */
public class MenuInputHandler implements InputProcessor {
    private OurGame game;
    private MenuWorld world;

    private float gameWidth;
    private float gameHeight;
    private float gameWidthCoefficient;

    public MenuInputHandler(OurGame game, MenuWorld world, float gameHeight, float gameWidthCoefficient) {
        this.game = game;
        this.world = world;

        this.gameWidth = (int) Constants.GAME_WIDTH;
        this.gameHeight = gameHeight;
        this.gameWidthCoefficient = gameWidthCoefficient;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if ((screenX/ gameWidthCoefficient > gameWidth / 2 - 140/2 )&&
                (screenX/ gameWidthCoefficient < gameWidth / 2 + 140/2 ))
            if ((screenY / gameWidthCoefficient > gameHeight/2 )&&
                    (screenY / gameWidthCoefficient < gameHeight/2 + 140))
                world.setDownBtn(true);

        if ((screenX/ gameWidthCoefficient > gameWidth/2 - 50 )&&
                (screenX/ gameWidthCoefficient < gameWidth/2 + 50 ))
            if ((screenY / gameWidthCoefficient > gameHeight/2 + 140 + 50 - 5 )&&
                    (screenY / gameWidthCoefficient < gameHeight/2 + 140 + 50 + 25))
                world.setEnabledSound(!world.isEnabledSound());

        if ((screenX/ gameWidthCoefficient > gameWidth/2 - 100 )&&
                (screenX/ gameWidthCoefficient < gameWidth/2 + 100 ))
            if ((screenY / gameWidthCoefficient > gameHeight/2 + 140 + 110 - 5 )&&
                    (screenY / gameWidthCoefficient < gameHeight/2 + 140 + 110 + 25))
                world.setEnabledAccelerom(!world.isEnabledAccelerom());

        if ((screenX/ gameWidthCoefficient > gameWidth/2 - 40 )&&
                (screenX/ gameWidthCoefficient < gameWidth/2 + 40 ))
            if ((screenY / gameWidthCoefficient > gameHeight/2 + 140+ 170 - 5 )&&
                    (screenY / gameWidthCoefficient < gameHeight/2 + 140 + 170 + 25))
                world.setDownExit(true);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            return false;
        }

        if(world.isDownBtn()){
            world.setDownBtn(false);
            Constants.SOUND = world.isEnabledSound();
            Constants.ACCELEROMETER = world.isEnabledAccelerom();
            //dispose();
            game.setScreen(game.game);
        }

        if(world.isDownExit()){
            Gdx.app.exit();
            //dispose();
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
