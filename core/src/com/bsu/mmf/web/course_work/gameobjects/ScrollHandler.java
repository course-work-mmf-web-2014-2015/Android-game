package com.bsu.mmf.web.course_work.gameobjects;

import com.bsu.mmf.web.course_work.MainConst;

/**
 * Created by Anton on 08.02.2015.
 */
public class ScrollHandler {

    private BackgroundScroll frontBg, backBg;

    public ScrollHandler() {
        int scrolSpeed = MainConst.SCROLL_SPEED;
        int width = (int) MainConst.GEMEWIDTH;
        int heightBg = (int) MainConst.HEIGHTBACKGROUND;

        frontBg = new BackgroundScroll( 0, 0, width, heightBg, scrolSpeed);
        backBg = new BackgroundScroll(0 , frontBg.getTailY(), width, heightBg, scrolSpeed);
    }

    public void update(float delta) {

        // обновляем все объекты
        frontBg.update(delta);
        backBg.update(delta);

        // проверяем кто из объектов за границей
        // и соответственно сбрасываем параметры этого объекта

        if (frontBg.isScrolledLeft()) {
            frontBg.reset(backBg.getTailY());

        } else if (backBg.isScrolledLeft()) {
            backBg.reset(frontBg.getTailY());

        }


    }

    // методы доступа к переменным класса
    public BackgroundScroll getFrontBg() {
        return frontBg;
    }

    public BackgroundScroll getBackBg() {
        return backBg;
    }


}
