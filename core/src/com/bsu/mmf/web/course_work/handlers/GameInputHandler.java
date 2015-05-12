package com.bsu.mmf.web.course_work.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;

/**
 * Created by Anton on 07.02.2015.
 */
public class GameInputHandler implements InputProcessor {

    private GameWorld myWorld;
    private Squirrel mysquirrel;
    private OurGame game;
    private int gameWidth;
    private int gameHeight;
    private float gameWidthCoefficient;


    public GameInputHandler(GameWorld myWorld, OurGame game,float gameHeight ,float gameWidthCoefficient) {
        this.game = game;
        this.myWorld = myWorld;
        mysquirrel = myWorld.getSquirrel();
        myWorld.inPause = false;

        this.gameWidth = (int) Constants.GAME_WIDTH;
        this.gameHeight = (int) gameHeight;

        this.gameWidthCoefficient = gameWidthCoefficient;
    }

    public void playAll() {
        myWorld.getScrollHandler().play();
        myWorld.inPause = false;
        mysquirrel.xtouchDown = 0;
        mysquirrel.xtouchUp = 0;

    }

    public void stopAll() {
        myWorld.inPause = true;
        myWorld.getScrollHandler().stop();
    }

    public void backAll() {
        myWorld.inPause = false;
        mysquirrel.xtouchDown = 0;
        mysquirrel.xtouchUp = 0;
        myWorld.restart();
        game.game.dispose();
        game.setScreen(game.menu);
    }

    public void restartAll() {
        myWorld.inPause = false;
        mysquirrel.xtouchDown = 0;
        mysquirrel.xtouchUp = 0;
        myWorld.restart();
    }

    public void inPauseOn(int screenX, int screenY) {
        if ((screenX/ gameWidthCoefficient > gameWidth /2 - 100)&&(screenX/ gameWidthCoefficient < gameWidth /2 + 100)
                && (screenY/ gameWidthCoefficient > gameHeight /2 - 200)&&(screenY/ gameWidthCoefficient < gameHeight /2-100 )) {
            if (myWorld.isAlive()) {
                playAll();
            }
        }

        if ((screenX/ gameWidthCoefficient > gameWidth /2 - 100-138)&&(screenX/ gameWidthCoefficient < gameWidth /2 + 100-138)
                && (screenY/ gameWidthCoefficient > gameHeight /2 - 200)&&(screenY/ gameWidthCoefficient < gameHeight /2-100 )) {
            backAll();
        }

        if ((screenX/ gameWidthCoefficient > gameWidth /2 - 100+138)&&(screenX/ gameWidthCoefficient < gameWidth /2 + 100+138)
                && (screenY/ gameWidthCoefficient > gameHeight /2 - 200)&&(screenY/ gameWidthCoefficient < gameHeight /2-100 )) {
            restartAll();
        }
    }

    public boolean keyPause(int screenX, int screenY) {
        return !myWorld.inPause && (screenX / gameWidthCoefficient > gameWidth - 100) && (screenY / gameWidthCoefficient > gameHeight - 100);
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK ){
            stopAll();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (myWorld.inPause) {
                inPauseOn(screenX,screenY);
            }
            else if (keyPause(screenX,  screenY)) {
                stopAll();
            }
            else if (!myWorld.inPause) {
                mysquirrel.xtouchDown = screenX;
            }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isAlive()) {
            if (!myWorld.inPause) {
                mysquirrel.xtouchUp = screenX ;
                mysquirrel.swype();
            }
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