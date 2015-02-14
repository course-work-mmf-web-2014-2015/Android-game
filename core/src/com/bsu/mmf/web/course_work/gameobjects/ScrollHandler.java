package com.bsu.mmf.web.course_work.gameobjects;

import com.bsu.mmf.web.course_work.MainConst;

/**
 * Created by Anton on 08.02.2015.
 */
public class ScrollHandler {

    private BackgroundScroll frontBg, backBg;
    private Icicles icicles1,icicles2,icicles3,icicles4;
    private int distansOfIcicles;

    public ScrollHandler() {
        int scrolSpeed = MainConst.SCROLL_SPEED;
        int width = (int) MainConst.GEMEWIDTH;
        int heightBg = (int) MainConst.HEIGHTBACKGROUND;
        distansOfIcicles = (int) MainConst.DISTANCEICICLES ;
        int widthIcicles = (int) MainConst.WIDTHICICLES;
        int heightIcicles = (int) MainConst.HEIGHTICICLES;

        frontBg = new BackgroundScroll( 0, 0, width, heightBg, scrolSpeed);
        backBg = new BackgroundScroll(0 , frontBg.getTailY(), width, heightBg, scrolSpeed);

        icicles1 = new Icicles(320, 500, widthIcicles, heightIcicles, scrolSpeed);
        icicles2 = new Icicles(420, icicles1.getTailY() + distansOfIcicles, widthIcicles, heightIcicles, scrolSpeed);
        icicles3 = new Icicles(20, icicles2.getTailY() + distansOfIcicles, widthIcicles, heightIcicles, scrolSpeed);
        icicles4 = new Icicles(220, icicles3.getTailY() + distansOfIcicles, widthIcicles, heightIcicles, scrolSpeed);
    }

    public void update(float delta) {

        // обновляем все объекты
        frontBg.update(delta);
        backBg.update(delta);
        icicles1.update(delta);
        icicles2.update(delta);
        icicles3.update(delta);
        icicles4.update(delta);

        // проверяем кто из объектов за границей
        // и соответственно сбрасываем параметры этого объекта
        if (icicles1.isScrolledLeft()) {
            icicles1.reset(icicles4.getTailY() + distansOfIcicles);
        } else if (icicles2.isScrolledLeft()) {
            icicles2.reset(icicles1.getTailY() + distansOfIcicles);

        } else if (icicles3.isScrolledLeft()) {
            icicles3.reset(icicles2.getTailY() + distansOfIcicles);
        }
        else if (icicles4.isScrolledLeft()) {
            icicles4.reset(icicles3.getTailY() + distansOfIcicles);
        }


        if (frontBg.isScrolledLeft()) {
            frontBg.reset(backBg.getTailY());

        } else if (backBg.isScrolledLeft()) {
            backBg.reset(frontBg.getTailY());

        }


    }

    public void stop() {
        frontBg.stop();
        backBg.stop();
        icicles1.stop();
        icicles2.stop();
        icicles3.stop();
        icicles4.stop();}

    // вернуть True если какая-нибудь из труб коснулась птицы
    public boolean collides(Squirrel squirrel) {
        return (icicles1.collides(squirrel) || icicles2.collides(squirrel) ||
                icicles3.collides(squirrel) || icicles4.collides(squirrel));
    }



    // методы доступа к переменным класса
    public BackgroundScroll getFrontBg() {
        return frontBg;
    }

    public Icicles getIcicles1() {
        return icicles1;
    }

    public Icicles getIcicles2() {
        return icicles2;
    }

    public Icicles getIcicles3() {
        return icicles3;
    }

    public Icicles getIcicles4() {
        return icicles4;
    }

    public BackgroundScroll getBackBg() {
        return backBg;
    }


}
