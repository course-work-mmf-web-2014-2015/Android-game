package com.bsu.mmf.web.course_work.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;

/**
 * Created by Anton on 12.02.2015.
 */
public class MenuScreen  implements Screen, InputProcessor {

    private OurGame game;
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private boolean downBtn;
    private TextureRegion bgMenu, button1, button2;

    private float gameWidthK;

    public MenuScreen(OurGame game) {
        this.game = game;

        // задаём камеру
        cam = new OrthographicCamera();
        cam.setToOrtho(true, MainConst.GEMEWIDTH , MainConst.GEMEHEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);



        bgMenu = AssetLoader.bgMenu;
        button1 = AssetLoader.button1;
        button2 = AssetLoader.button2;

        downBtn = false;

        gameWidthK = Gdx.graphics.getWidth() /  MainConst.GEMEWIDTH ;
        MainConst.GEMEWIDTHK = gameWidthK;
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        // рисуем меню
        batcher.begin();
        batcher.disableBlending();

        batcher.draw(bgMenu, 0, 0,  MainConst.GEMEWIDTH, bgMenu.getRegionHeight());

        batcher.enableBlending();

        if (downBtn)
             batcher.draw(button2,  MainConst.GEMEWIDTH/2 - button2.getRegionWidth()/2,  MainConst.GEMEHEIGHT/2 ,
                     button2.getRegionWidth(), button2.getRegionHeight());
        else
            batcher.draw(button1,  MainConst.GEMEWIDTH/2 - button1.getRegionWidth()/2,  MainConst.GEMEHEIGHT/2 ,
                    button1.getRegionWidth(), button1.getRegionHeight());

        batcher.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);

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
        if ((screenX/ gameWidthK > MainConst.GEMEWIDTH / 2 - button1.getRegionWidth()/2 )&&
                (screenX/ gameWidthK < MainConst.GEMEWIDTH / 2 + button1.getRegionWidth()/2 ))
            if ((screenY / gameWidthK > MainConst.GEMEHEIGHT/2 )&&
                    (screenY / gameWidthK < MainConst.GEMEHEIGHT/2 + button1.getRegionHeight()))
                  downBtn = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;

        if(downBtn){
            dispose();
            game.setScreen(game.game);
            downBtn = false;
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
