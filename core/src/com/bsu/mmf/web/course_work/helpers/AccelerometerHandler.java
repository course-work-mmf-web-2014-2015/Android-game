package com.bsu.mmf.web.course_work.helpers;

import com.badlogic.gdx.Gdx;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

import sun.rmi.runtime.Log;


/**
 * Created by Anton on 02.04.2015.
 */
public class AccelerometerHandler {

    private Squirrel squirrel;
    float accelX ;
    float accelXTemp,accelXTemp2  ;

    public AccelerometerHandler(Squirrel squirrel) {
        this.squirrel = squirrel;
        accelX = 3;
        accelXTemp2 = 3;
        accelXTemp = 3;
    }

    public void update() {

        accelXTemp = accelX;
        accelX = Gdx.input.getAccelerometerX();

        if (Math.abs(accelX - accelXTemp ) < 1) accelX = accelXTemp;


         if (accelX > 4 && accelXTemp < 4  && accelXTemp > -4) {
             squirrel.swypeAccelerom(-1);
         }

        else
             if (accelX < -4  && accelXTemp > -4  && accelXTemp < 4) {
                  squirrel.swypeAccelerom(1);
             }


    }


}
