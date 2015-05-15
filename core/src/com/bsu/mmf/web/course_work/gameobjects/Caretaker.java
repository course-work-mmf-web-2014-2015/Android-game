package com.bsu.mmf.web.course_work.gameobjects;

import com.bsu.mmf.web.course_work.gameworld.GameWorld;

/**
 * Created by Anton on 14.05.2015.
 */
public class Caretaker {

    private GameWorld gameWorld;

    public Caretaker(GameWorld word){
        gameWorld = word;
    }

    public void update () throws InterruptedException {

        if (!gameWorld.isAlive()&&!gameWorld.inPause && !gameWorld.isAliveCheked()){
            Thread.sleep(500);
            gameWorld.inPause = true;
        }

    }


}
