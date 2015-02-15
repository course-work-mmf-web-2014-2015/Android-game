package com.bsu.mmf.web.course_work.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

/**
 * Created by Anton on 07.02.2015.
 */
public class InputHandler implements InputProcessor {

    private Squirrel mysquirrel;
    private OurGame game;

    public InputHandler(Squirrel squirrel , OurGame game) {
        this.game = game;
        mysquirrel = squirrel;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK ){
            game.game.world.restart();
            game.game.dispose();
            game.setScreen(game.menu);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mysquirrel.xtouchDown = screenX ;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mysquirrel.xtouchUp = screenX ;
        mysquirrel.swype();
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
