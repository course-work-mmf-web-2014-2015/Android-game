package com.bsu.mmf.web.course_work;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;
import com.bsu.mmf.web.course_work.screens.GameScreen;
import com.bsu.mmf.web.course_work.screens.MenuScreen;

public class OurGame extends Game {

    public GameScreen game;
    public MenuScreen menu;
	
	@Override
	public void create () {
        AssetLoader.load();  // подгружаем ресурсы

        float gameWidth = Constants.GAME_WIDTH;     // просчёт относительных сторон
        float gameHeight = Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / gameWidth);

        game = new GameScreen(this);
        menu = new MenuScreen(this);

        setScreen(menu);
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
