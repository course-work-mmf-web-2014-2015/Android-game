package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.bsu.mmf.web.course_work.MainConst;

/**
 * Created by Anton on 16.02.2015.
 */
public class Acorn {

    private float positionX;
    private float positionY;

    private Circle acornCircle;
    private float circleRadius = MainConst.CIRCLERADIUSACORN;

    private float width = MainConst.WIDTHACORN;
    private float height = MainConst.HEIGHTACORN;

    private boolean inScreen = true;

    public Acorn(float x, float y) {
        positionX = x;
        positionY = y;

        acornCircle = new Circle();
        acornCircle.set(positionX + circleRadius+4, positionY + circleRadius+4 , circleRadius);

    }


    public boolean inScreen() {
        return inScreen;

    }

    public boolean collides(Squirrel squirrel) {                              // проверка на пересечение
        if ( positionY < squirrel.getY() + squirrel.getHeight()) {
            if (inScreen && Intersector.overlaps(squirrel.getBoundingCircle(), acornCircle)){
                inScreen = false;
                return true;
            }
            else{
                return false;
            }

        }

        else {
            inScreen = true;
            return false;
        }
    }

    public void setPosition(float x, float y) {
        positionX = x;
        positionY = y;
        acornCircle.set(positionX + circleRadius+2, positionY + circleRadius+2 , circleRadius);
    }

    public float getX() {
        return positionX;
    }

    public float getY() {
        return positionY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Circle getBoundingCircle() {
        return acornCircle;
    }


}
