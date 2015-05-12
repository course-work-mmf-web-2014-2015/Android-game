package com.bsu.mmf.web.course_work.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameworld.GameRenderer;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;
import com.bsu.mmf.web.course_work.handlers.GameInputHandler;

/**
 * Created by Anton on 06.02.2015.
 * В GameScreen делегируем обновление и отрисовку классам GameWorld и GameRenderer
 */
public class GameScreen implements Screen {

    private OurGame game;
    public GameWorld world;
    private GameRenderer renderer;

    private float gameWidth;
    private float gameHeight;
    private float gameWidthCoefficient;

    private float runTime = 0;

    public GameScreen(OurGame game) {
        gameWidth = Constants.GAME_WIDTH;     // просчёт относительных сторон
        gameHeight = Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / gameWidth);
        gameWidthCoefficient = Gdx.graphics.getWidth() /  Constants.GAME_WIDTH ;

        this.game = game;
        this.world = new GameWorld();
        this.renderer = new GameRenderer(world, (int) gameHeight);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GameInputHandler(world, game, gameHeight, gameWidthCoefficient));
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
        hide();
    }
}
