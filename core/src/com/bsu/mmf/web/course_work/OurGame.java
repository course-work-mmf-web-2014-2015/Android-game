package com.bsu.mmf.web.course_work;


import com.badlogic.gdx.Game;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;
import com.bsu.mmf.web.course_work.screens.GameScreen;

public class OurGame extends Game {

	
	@Override
	public void create () {

        AssetLoader.load();
        setScreen(new GameScreen());

	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
