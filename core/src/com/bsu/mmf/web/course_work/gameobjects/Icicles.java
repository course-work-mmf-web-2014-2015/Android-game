package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.bsu.mmf.web.course_work.Constants;

import java.util.Random;

/**
 * Created by Anton on 14.02.2015.
 */
public class Icicles extends Scrollable {

    private Random r;
    private static float posSq = Constants.POSITION_X_SQUIRREL;
    private Rectangle boundingRectangle;

    public Icicles(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        boundingRectangle = new Rectangle();
    }

    @Override
    public void reset(float newY) {
        super.reset(newY);

        int tempX = r.nextInt(5) + 1;
        switch (tempX){
            case 1: position.x = posSq - 200; break;
            case 2: position.x = posSq - 100; break;
            case 3: position.x = posSq; break;
            case 4: position.x = posSq + 100; break;
            case 5: position.x = posSq + 200; break;
        }


    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    @Override
    public void update(float delta) {
         super.update(delta);

        // Метод set() позволяет выставить координаты верзнего лего угла - x, y
        // вместе с width и height прямоугольника
        boundingRectangle.set(position.x, position.y, width, height/3);


    }


    public boolean collides(Squirrel squirrel) {                              // проверка на пересечение
        if (position.y < squirrel.getY() + squirrel.getHeight()) {
            return (Intersector.overlaps(squirrel.getBoundingCircle(), boundingRectangle));
        }
        return false;
    }


    public void onRestart(float y, float scrollSpeed) {
        velocity.y = scrollSpeed;
        reset(y);
    }

}
