package com.bsu.mmf.web.course_work.gameworld;


import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

/**
 * Created by Anton on 06.02.2015.
 */
public class GameWorld {

    private Squirrel squirrel;

    public GameWorld(int midPointX) {
        squirrel = new Squirrel(midPointX - 5, 40, 17, 12);    // вынести в константы
    }

    public void update(float delta) {
        squirrel.update(delta);
    }

    public Squirrel getSquirrel() {
        return squirrel;

    }


}
