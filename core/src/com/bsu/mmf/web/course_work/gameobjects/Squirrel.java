package com.bsu.mmf.web.course_work.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.bsu.mmf.web.course_work.Constants;

/**
 * Created by Anton on 06.02.2015.
 */
public class Squirrel {

    private Vector2 position;    // позиция белки...position.x — определяет позицию по оси X и так же Y
    private Vector2 velocity;    // скорость...velocity.y отвечает за скорость по оси Y и ...

    private Circle boundingCircle; //описанная окружность...для проверки пересечения

    private float width;           //ширина
    private float height;          //высота белки
    private float circleRadius = Constants.CIRCLE_RADIUS_SQUIRREL;
    private int tempX ;
    private float tempSwype = Constants.SWYPE ;

    private boolean isAlive;

    public int xtouchDown = 0 ;
    public int xtouchUp = 0 ;

    private int positionInBg;             // позиция 1 из 5...5 путей
    private float positionInBgMassX[]  ;  // массив каждому пути - свои координаты точки

    public Squirrel(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        boundingCircle = new Circle();

        positionInBg = 3;
        tempX = positionInBg;
        isAlive = true;

        positionInBgMassX = new float[6];
        positionInBgMassX[3] = Constants.POSITION_X_SQUIRREL;
        positionInBgMassX[2] = positionInBgMassX[3] - 100 ;
        positionInBgMassX[1] = positionInBgMassX[2] - 100 ;
        positionInBgMassX[4] = positionInBgMassX[3] + 100 ;
        positionInBgMassX[5] = positionInBgMassX[4] + 100 ;
    }


    public void update(float delta) {


        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + circleRadius+4, position.y + circleRadius+4 , circleRadius);     // круг для просмотра пересечения...отрисовывается за объектом

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

        if (isAlive) {
            if (xtouchDown!= 0 && xtouchUp!= 0 && Math.abs(xtouchUp - xtouchDown ) > 50){   // 50 - минимум растояние, чтоб свайп сработал
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
        }

    }
    public void swypeAccelerom(int x) {
        if (isAlive) {

            if (x == 1) {
                if (positionInBg != 5){
                    velocity.x = tempSwype;
                    tempX = positionInBg;
                    positionInBg++;
                }

            }
            else if (x == -1) {
                if (positionInBg != 1){
                    velocity.x = -tempSwype;
                    tempX = positionInBg;
                    positionInBg--;
                }

            }
        }
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        velocity.x = 0;
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

    public void onRestart() {
        position.y = Constants.POSITION_Y_SQUIRREL;
        position.x = Constants.POSITION_X_SQUIRREL;
        velocity.x = 0;
        isAlive = true;
        positionInBg = 3;
        tempX = positionInBg;
    }

}
