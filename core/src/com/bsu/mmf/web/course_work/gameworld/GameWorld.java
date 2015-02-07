package com.bsu.mmf.web.course_work.gameworld;


import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

/**
 * Created by Anton on 06.02.2015.
 */
public class GameWorld {

    private Squirrel squirrel;

    public GameWorld() {
        squirrel = new Squirrel(MainConst.POSITIONXSQUIRREL, MainConst.POSITIONYSQUIRREL,
                MainConst.WIDTHSQUIRREL ,  MainConst.HEIGHTSQUIRREL );
    }

    public void update(float delta) {

        squirrel.update(delta);

    }

    public Squirrel getSquirrel() {

        return squirrel;

    }


}