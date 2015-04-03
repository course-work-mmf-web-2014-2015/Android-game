package com.bsu.mmf.web.course_work.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameworld.GameRenderer;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;
import com.bsu.mmf.web.course_work.helpers.InputHandler;

/**
 * Created by Anton on 06.02.2015.
 * В нашем GameScreen, мы делегируем обновление и отрисовку нашим классам GameWorld и GameRenderer, соответственно.
 *
 Во время создания GameScreen, мы должны создать два новых объекта типа GameWorld и GameRenderer.
 Внутри render метода класса GameScreen, мы должны запросить выполнить обновление и отрисовку у классов GameWorld и GameRenderer соответственно.
 *
 */
public class GameScreen implements Screen {

    private OurGame game;

    public GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen(OurGame game) {

        this.game = game;

        world = new GameWorld();
        renderer = new GameRenderer(world);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputHandler(world,game ));
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta); // GameWorld updates
        renderer.render(runTime); // GameRenderer renders
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
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        Gdx.input.setCatchBackKey(false);
    }
}
