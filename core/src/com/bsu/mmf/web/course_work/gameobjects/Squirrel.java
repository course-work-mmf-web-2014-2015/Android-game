package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.bsu.mmf.web.course_work.MainConst;

/**
 * Created by Anton on 06.02.2015.
 */
public class Squirrel {

    private Vector2 position;    // позиция белки...position.x — определяет позицию по оси X и так же Y
    private Vector2 velocity;    // скорость...velocity.y отвечает за скорость по оси Y и ...

    private Circle boundingCircle; //описанная окружность...для проверки пересечения

    private float width;           //ширина
    private float height;          //высота белки
    private float circleRadius = MainConst.CIRCLERADIUSSQUIRREL;

    public Squirrel(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        boundingCircle = new Circle();
    }


    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 6, position.y + 6, circleRadius);     // круг для просмотра пересечения...отрисовывается за объектом
        velocity.x = 0;  // по другому нужно...это потом переделается

    }

    public void swypeLeft() {
       //реализация свайпа
        velocity.x = - MainConst.SWYPE;
    }

    public void swypeRight() {
        //реализация свайпа
        velocity.x = MainConst.SWYPE;
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