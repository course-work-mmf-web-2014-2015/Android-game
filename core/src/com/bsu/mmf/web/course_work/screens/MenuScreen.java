package com.bsu.mmf.web.course_work.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.OurGame;
import com.bsu.mmf.web.course_work.gameworld.MenuRenderer;
import com.bsu.mmf.web.course_work.gameworld.MenuWorld;
import com.bsu.mmf.web.course_work.handlers.MenuInputHandler;

/**
 * Created by Anton on 12.02.2015.
 */
public class MenuScreen  implements Screen {

    private OurGame game;
    private MenuRenderer renderer;
    private MenuWorld world;

    private float gameWidth;
    private float gameHeight;
    private float gameWidthCoefficient;

    public MenuScreen(OurGame game) {
        this.gameWidth = Constants.GAME_WIDTH;
        this.gameHeight = (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / gameWidth));
        this.gameWidthCoefficient = Gdx.graphics.getWidth() /  gameWidth ;

        this.game = game;
        this.world = new MenuWorld();
        this.renderer = new MenuRenderer(world, (int) gameHeight, gameWidthCoefficient);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new MenuInputHandler(game, world, gameHeight, gameWidthCoefficient));
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
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

}
