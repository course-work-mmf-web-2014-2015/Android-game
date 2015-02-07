package com.bsu.mmf.web.course_work.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.gameworld.GameRenderer;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;

/**
 * Created by Anton on 06.02.2015.
 * В нашем GameScreen, мы делегируем обновление и отрисовку нашим классам GameWorld и GameRenderer, соответственно.
 *
 Во время создания GameScreen, мы должны создать два новых объекта типа GameWorld и GameRenderer.
 Внутри render метода класса GameScreen, мы должны запросить выполнить обновление и отрисовку у классов GameWorld и GameRenderer соответственно.
 *
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = MainConst.GEMEWIDTH;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        MainConst.GEMEHEIGHT = gameHeight;


        int midPointX = (int) (gameWidth / 2);

        world = new GameWorld();
        renderer = new GameRenderer(world , (int)gameHeight ,midPointX );

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        world.update(delta); // GameWorld updates
        renderer.render(); // GameRenderer renders
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

    }

    @Override
    public void dispose() {

    }
}
