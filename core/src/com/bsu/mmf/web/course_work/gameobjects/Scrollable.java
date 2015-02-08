package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Anton on 08.02.2015.
 */
public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        // Если объект Scrollable более не виден:
        if (position.y + height < 0) {
            isScrolledLeft = true;
        }
    }

    // Reset: Нужно переопределять в дочернем классе, если необходимо описать
    // другое поведение
    public void reset(float newY) {
        position.y = newY;
        isScrolledLeft = false;
    }

    // Методы доступа к переменым класса
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailY() {
        return position.y + height;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
