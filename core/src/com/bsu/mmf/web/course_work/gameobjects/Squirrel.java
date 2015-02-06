package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Anton on 06.02.2015.
 */
public class Squirrel {

    private Vector2 position;    // позиция белки...position.x — определяет позицию по оси X и так же Y
    private Vector2 velocity;    // скорость...velocity.y отвечает за скорость по оси Y и ...

    private Circle boundingCircle; //описанная окружность...для проверки пересечения

    private int width;           //ширина
    private int height;          //высота белки

    public Squirrel(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        boundingCircle = new Circle();
    }


    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 6, position.y + 6, 6.5f);
        velocity.x = 0;

    }

    public void swypeLeft() {
       //реализация свайпа
        velocity.x = -140;          // вынести в константы
    }

    public void swypeRight() {
        //реализация свайпа
        velocity.x = 140;           // вынести в константы
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

}
