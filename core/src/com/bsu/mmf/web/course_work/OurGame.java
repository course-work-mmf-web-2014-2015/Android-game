package com.bsu.mmf.web.course_work;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;
import com.bsu.mmf.web.course_work.screens.GameScreen;
import com.bsu.mmf.web.course_work.screens.MenuScreen;
import com.bsu.mmf.web.course_work.screens.SetLvlScreen;

public class OurGame extends Game {

    public GameScreen game;
    public MenuScreen menu;
    public SetLvlScreen setLvl;
	
	@Override
	public void create () {

        AssetLoader.load();

        float gameWidth = MainConst.GEMEWIDTH;
        float gameHeight = Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / gameWidth);
        MainConst.GEMEHEIGHT = gameHeight;

        game = new GameScreen(this);
        menu = new MenuScreen(this);
        setLvl = new SetLvlScreen(this);

        setScreen(menu);

	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
