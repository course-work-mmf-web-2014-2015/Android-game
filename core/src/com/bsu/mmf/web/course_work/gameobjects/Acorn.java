package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Anton on 16.02.2015.
 */

public class Acorn {
    private static final float CIRCLE_RADIUS_ACORN = 20;
    private static final float WIDTH_ACORN  = 40;
    private static final float HEIGHT_ACORN  = 40;

    private Vector2 position;

    private Circle acornBoundingCircle;

    private boolean inScreen = true;

    public Acorn(float x, float y) {
        position = new Vector2(x, y);

        acornBoundingCircle = new Circle();
        acornBoundingCircle.set(position.x + CIRCLE_RADIUS_ACORN + 4, position.y + CIRCLE_RADIUS_ACORN + 4, CIRCLE_RADIUS_ACORN);

    }

    public boolean inScreen() {
        return inScreen;

    }

    public boolean collides(Squirrel squirrel) {                              // проверка на пересечение
        if ( position.y < squirrel.getY() + squirrel.getHeight()) {
            if (inScreen && Intersector.overlaps(squirrel.getBoundingCircle(), acornBoundingCircle)){
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
        position.x = x;
        position.y = y;
        acornBoundingCircle.set(position.x + CIRCLE_RADIUS_ACORN + 2, position.y + CIRCLE_RADIUS_ACORN + 2, CIRCLE_RADIUS_ACORN);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return WIDTH_ACORN;
    }

    public float getHeight() {
        return HEIGHT_ACORN;
    }

    public Circle getBoundingCircle() {
        return acornBoundingCircle;
    }
}
