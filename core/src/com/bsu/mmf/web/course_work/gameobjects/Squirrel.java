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
    private int tempX ;
    private float tempSwype = MainConst.SWYPE ;

    public int xtouchDown = 0 ;
    public int xtouchUp = 0 ;

    private int positionInBg;
    private float positionInBgMassX[]  ;

    public Squirrel(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        boundingCircle = new Circle();

        positionInBg = 3;
        tempX = positionInBg;

        positionInBgMassX = new float[6];
        positionInBgMassX[3] = MainConst.POSITIONXSQUIRREL ;
        positionInBgMassX[2] = positionInBgMassX[3] - 100 ;
        positionInBgMassX[1] = positionInBgMassX[2] - 100 ;
        positionInBgMassX[4] = positionInBgMassX[3] + 100 ;
        positionInBgMassX[5] = positionInBgMassX[4] + 100 ;
    }


    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + 6, position.y + 6, circleRadius);     // круг для просмотра пересечения...отрисовывается за объектом

        if (velocity.x != 0){
            float tempPosition = positionInBgMassX[positionInBg] ;

            if (  positionInBg > tempX )                        // проверка на выход за границы и
                if (position.x >= tempPosition ) {
                    velocity.x = 0;
                    position.x = tempPosition;
                }

            if (  positionInBg < tempX )
                if (position.x <= tempPosition ) {
                    velocity.x = 0;
                    position.x = tempPosition;
                }


        }


    }

    public void swype() {                     //реализация свайпа


        if (xtouchDown!= 0 && xtouchUp!= 0)
            if (xtouchUp > xtouchDown){
                if (positionInBg != 5){
                    velocity.x = tempSwype;
                    tempX = positionInBg;
                    positionInBg++;
                }
            }
            else if (xtouchUp < xtouchDown)
                if (positionInBg != 1){
                    velocity.x = -tempSwype;
                    tempX = positionInBg;
                    positionInBg--;
                }

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
