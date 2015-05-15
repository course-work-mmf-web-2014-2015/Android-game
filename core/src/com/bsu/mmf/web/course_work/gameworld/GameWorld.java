package com.bsu.mmf.web.course_work.gameworld;


import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.gameobjects.Acorn;
import com.bsu.mmf.web.course_work.gameobjects.Caretaker;
import com.bsu.mmf.web.course_work.gameobjects.ScrollHandler;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;
import com.bsu.mmf.web.course_work.handlers.GameAccelerometerHandler;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;

import java.util.Collections;

/**
 * Created by Anton on 06.02.2015.
 */
public class GameWorld {

    private Squirrel squirrel;
    private Acorn acorns;
    private ScrollHandler scroller;
    private GameAccelerometerHandler accelerometer ;
    private Caretaker caretaker;
    private boolean isAlive = true;
    private boolean isAliveCheked = true;
    public boolean inPause = false;

    private int score = 0;
    private int score2 = 0;
    private int scoreSum = 0;

    public GameWorld() {
        squirrel = new Squirrel(Constants.POSITION_X_SQUIRREL, Constants.POSITION_Y_SQUIRREL,
                Constants.WIDTH_SQUIRREL,  Constants.HEIGHT_SQUIRREL);

        scroller = new ScrollHandler(this);
        acorns = new Acorn(0, 0);

        accelerometer = new GameAccelerometerHandler(this);
        caretaker = new Caretaker(this);
    }

    public void update(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        squirrel.update(delta);
        scroller.update(delta);
        accelerometer.update();

        float temp = scroller.getIcicles4().getX() ;
        float posXtemp = temp - scroller.getIcicles4().getWidth()/2 - 15 ;

        if (posXtemp < 0){
            posXtemp = temp + scroller.getIcicles4().getWidth() + 15 ;

        }

        acorns.setPosition(posXtemp  , scroller.getIcicles4().getY() - 150);


        if (isAlive && scroller.collides(squirrel)) {
            // Clean up on game over
            scroller.stop();
            squirrel.die();
            isAlive = false;

            if (Constants.SOUND){
                AssetLoader.crashSound.play();
            }

            scoreSum = score + score2*10 ;

            AssetLoader.listScore.add(scoreSum);
            Collections.sort(AssetLoader.listScore);
            Collections.reverse(AssetLoader.listScore);

            for (int i = 0; i<5 ; i++){
                AssetLoader.prefs.putInteger("" + i , AssetLoader.listScore.get(i));
            }
            AssetLoader.prefs.flush();


            //inPause = true; // TODO: добавить sleep
        }

        try {
            caretaker.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (acorns.collides(squirrel)) {
            addScore2(1);
            if (Constants.SOUND){
                AssetLoader.acornSound.play();
            }
        }
    }

    public Squirrel getSquirrel() {
        return squirrel;
    }

    public ScrollHandler getScrollHandler() {
        return scroller;
    }

    public Acorn getAcorn() {
        return acorns;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isAliveCheked() {
        return isAliveCheked;
    }

    public void setIsAliveCheked(boolean isAliveCheked) {
         this.isAliveCheked = isAliveCheked;
    }

    public boolean isPause() {
        return inPause;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public int getScore2() {
        return score2;
    }

    public int getScoreSum() {
        scoreSum = score + score2*10 ;
        return scoreSum;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void addScore2(int increment) {
        score2 += increment;
    }

    public void restart(){
        squirrel.onRestart();
        scroller.onRestart();
        isAlive = true;
        score = 0;
        score2 = 0;
        scoreSum = 0;
        inPause = false;
        isAliveCheked = true;
    }
}
