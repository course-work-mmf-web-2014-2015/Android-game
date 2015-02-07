package com.bsu.mmf.web.course_work.helpers;

import com.badlogic.gdx.InputProcessor;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

/**
 * Created by Anton on 07.02.2015.
 */
public class InputHandler implements InputProcessor {

    private Squirrel mysquirrel;

    public InputHandler(Squirrel squirrel) {
        mysquirrel = squirrel;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
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
