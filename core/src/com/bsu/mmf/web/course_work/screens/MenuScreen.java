package com.bsu.mmf.web.course_work.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private boolean downSound;
    private boolean downAccelerom;
    private boolean downExit;
    private TextureRegion bgMenu;
    public static BitmapFont font, shadow;

    private float gameWidthK;

    public MenuScreen(OurGame game) {
        this.game = game;

        // задаём камеру
        cam = new OrthographicCamera();
        cam.setToOrtho(true, MainConst.GEMEWIDTH , MainConst.GEMEHEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        bgMenu = AssetLoader.bgMenu;
        font = AssetLoader.font;
        shadow = AssetLoader.shadow;

        downBtn = false;
        downSound = true;
        downAccelerom = true;
        downExit = false;

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

        shadow.setScale(.35f, -.35f);
        font.setScale(.35f, -.35f);

        shadow.draw(batcher, "sound",  MainConst.GEMEWIDTH/2 - 40 , MainConst.GEMEHEIGHT/2 + 140 + 50);
        if (downSound)
        font.draw(batcher, "sound",  MainConst.GEMEWIDTH/2 - 40-1 , MainConst.GEMEHEIGHT/2 + 140 + 50);
        shadow.draw(batcher, "accelerometer" , MainConst.GEMEWIDTH/2 - 90  , MainConst.GEMEHEIGHT/2 +140 + 110);
        if (downAccelerom)
        font.draw(batcher, "accelerometer" , MainConst.GEMEWIDTH/2 - 90-1 , MainConst.GEMEHEIGHT/2 +140 + 110);

        if (downExit){
            shadow.draw(batcher, "exit",  MainConst.GEMEWIDTH/2 - 30-1  , MainConst.GEMEHEIGHT/2 + 140 + 170);
        }
        else{
            shadow.draw(batcher, "exit",  MainConst.GEMEWIDTH/2 - 30-1  , MainConst.GEMEHEIGHT/2 + 140 + 170);
            font.draw(batcher, "exit",  MainConst.GEMEWIDTH/2 - 30  , MainConst.GEMEHEIGHT/2 + 140 + 170);
        }

        shadow.setScale(.20f, -.20f);
        font.setScale(.20f, -.20f);

        shadow.draw(batcher, "Sqwirrel v1.0 (c) 2015",  MainConst.GEMEWIDTH/2 - 80 , MainConst.GEMEHEIGHT - 20);
        font.draw(batcher, "Sqwirrel v1.0 (c) 2015",  MainConst.GEMEWIDTH/2 - 80-1 , MainConst.GEMEHEIGHT - 20);

        shadow.setScale(.50f, -.50f);
        font.setScale(.50f, -.50f);


        if (downBtn){
            shadow.draw(batcher, "Start",  MainConst.GEMEWIDTH/2 - 50 , MainConst.GEMEHEIGHT/2 + 50);
        }
        else{
            shadow.draw(batcher, "Start",  MainConst.GEMEWIDTH/2 - 50 , MainConst.GEMEHEIGHT/2 + 50);
            font.draw(batcher, "Start",  MainConst.GEMEWIDTH/2 - 50-1 , MainConst.GEMEHEIGHT /2 + 50);
        }

        shadow.setScale(.29f, -.29f);
        font.setScale(.29f, -.29f);

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
        if ((screenX/ gameWidthK > MainConst.GEMEWIDTH / 2 - 140/2 )&&
                (screenX/ gameWidthK < MainConst.GEMEWIDTH / 2 + 140/2 ))
            if ((screenY / gameWidthK > MainConst.GEMEHEIGHT/2 )&&
                    (screenY / gameWidthK < MainConst.GEMEHEIGHT/2 + 140))
                  downBtn = true;

        if ((screenX/ gameWidthK > MainConst.GEMEWIDTH/2 - 50 )&&
                (screenX/ gameWidthK < MainConst.GEMEWIDTH/2 + 50 ))
            if ((screenY / gameWidthK > MainConst.GEMEHEIGHT/2 + 140 + 50 - 5 )&&
                    (screenY / gameWidthK < MainConst.GEMEHEIGHT/2 + 140 + 50 + 25))
                if (downSound) downSound = false;
                else downSound = true;

        if ((screenX/ gameWidthK > MainConst.GEMEWIDTH/2 - 100 )&&
                (screenX/ gameWidthK < MainConst.GEMEWIDTH/2 + 100 ))
            if ((screenY / gameWidthK > MainConst.GEMEHEIGHT/2 + 140 + 110 - 5 )&&
                    (screenY / gameWidthK < MainConst.GEMEHEIGHT/2 + 140 + 110 + 25))
                if (downAccelerom) downAccelerom = false;
                else downAccelerom = true;

        if ((screenX/ gameWidthK > MainConst.GEMEWIDTH/2 - 40 )&&
                (screenX/ gameWidthK < MainConst.GEMEWIDTH/2 + 40 ))
            if ((screenY / gameWidthK > MainConst.GEMEHEIGHT/2 + 140+ 170 - 5 )&&
                    (screenY / gameWidthK < MainConst.GEMEHEIGHT/2 + 140 + 170 + 25))
                      downExit = true;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;

        if(downBtn){
            downBtn = false;
            MainConst.SOUND = downSound;
            MainConst.ACCELEROMETER = downAccelerom;
            dispose();
            game.setScreen(game.game);
        }

        if(downExit){
            Gdx.app.exit();
            dispose();
        }

        return true;
    }

    public boolean getDownSound(){
        return downSound;
    }

    public boolean getDownAccelerometer(){
        return downAccelerom;
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
