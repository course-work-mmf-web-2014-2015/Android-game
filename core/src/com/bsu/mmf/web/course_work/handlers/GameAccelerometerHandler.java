package com.bsu.mmf.web.course_work.handlers;

import com.badlogic.gdx.Gdx;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;


/**
 * Created by Anton on 02.04.2015.
 */
public class GameAccelerometerHandler {

    private GameWorld gameWorld;
    private Squirrel squirrel;
    private float accelX ;
    private float accelXTemp,accelXTemp2  ;
    private boolean accelerom ;

    public GameAccelerometerHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        squirrel = gameWorld.getSquirrel();
        accelX = 3;
        accelXTemp2 = 3;
        accelXTemp = 3;
        accelerom =  Constants.ACCELEROMETER;
    }

    public void update() {

        if (!gameWorld.inPause && Constants.ACCELEROMETER) {
            accelXTemp = accelX;
            accelX = Gdx.input.getAccelerometerX();

            if (Math.abs(accelX - accelXTemp) < 1) accelX = accelXTemp;


            if (accelX > 4 && accelXTemp < 4 && accelXTemp > -4) {
                squirrel.swypeAccelerom(-1);
            } else if (accelX < -4 && accelXTemp > -4 && accelXTemp < 4) {
                squirrel.swypeAccelerom(1);
            }

        }
    }


}
