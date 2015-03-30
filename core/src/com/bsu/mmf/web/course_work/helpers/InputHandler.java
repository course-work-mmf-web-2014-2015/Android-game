package com.bsu.mmf.web.course_work.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;

/**
 * Created by Anton on 07.02.2015.
 */
public class InputHandler implements InputProcessor {

    private GameWorld myWorld;
    private Squirrel mysquirrel;
    private OurGame game;
    private int widthGEME;
    private int heightGEME;
    private float gameWidthK;

    public InputHandler(GameWorld myWorld , OurGame game) {
        this.game = game;
        this.myWorld = myWorld;
        mysquirrel = myWorld.getSquirrel();
        myWorld.inPause = false;

        widthGEME = (int) MainConst.GEMEWIDTH;
        heightGEME = (int) MainConst.GEMEHEIGHT;

        gameWidthK = MainConst.GEMEWIDTHK ;
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
        game.game.world.restart();
        game.game.dispose();
        game.setScreen(game.menu);
    }

    public void restartAll() {
        myWorld.inPause = false;
        mysquirrel.xtouchDown = 0;
        mysquirrel.xtouchUp = 0;
        game.game.world.restart();
    }

    public void inPauseOn(int screenX, int screenY) {
        if ((screenX/gameWidthK > widthGEME/2 - 100)&&(screenX/gameWidthK < widthGEME/2 + 100)
                &&(screenY/gameWidthK > heightGEME/2 - 200)&&(screenY/gameWidthK < heightGEME/2-100 )){
            if (myWorld.isAlive()) {
                playAll();
            }

        }

        if ((screenX/gameWidthK > widthGEME/2 - 100-138)&&(screenX/gameWidthK < widthGEME/2 + 100-138)
                &&(screenY/gameWidthK > heightGEME/2 - 200)&&(screenY/gameWidthK < heightGEME/2-100 )){
            backAll();
        }

        if ((screenX/gameWidthK > widthGEME/2 - 100+138)&&(screenX/gameWidthK < widthGEME/2 + 100+138)
                &&(screenY/gameWidthK > heightGEME/2 - 200)&&(screenY/gameWidthK < heightGEME/2-100 )){
            restartAll();
        }
    }

    public boolean keyPause(int screenX, int screenY) {

        if (!myWorld.inPause&&(screenX/gameWidthK > widthGEME - 100)&&(screenY/gameWidthK > heightGEME - 100)){
            return true;
        }
        return false;

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


            if (myWorld.inPause){
                inPauseOn(screenX,screenY);
            }
            else if (keyPause( screenX,  screenY)){
                stopAll();
            }
            else   if (!myWorld.inPause) {
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
