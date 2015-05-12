package com.bsu.mmf.web.course_work.gameobjects;

import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.gameworld.GameWorld;

/**
 * Created by Anton on 08.02.2015.
 */
public class ScrollHandler {

    private GameWorld gameWorld;
    private BackgroundScroll frontBg, backBg;
    private Icicles icicles1,icicles2,icicles3,icicles4;
    private int distansOfIcicles;
    private int scrolSpeed;

    public ScrollHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        scrolSpeed = Constants.SCROLL_SPEED;
        int width = (int) Constants.GAME_WIDTH;
        int heightBg = (int) Constants.HEIGHT_BACKGROUND;
        distansOfIcicles = (int) Constants.DISTANCE_ICICLES;
        int widthIcicles = (int) Constants.WIDTH_ICICLES;
        int heightIcicles = (int) Constants.HEIGHT_ICICLES;

        frontBg = new BackgroundScroll( 0, 0, width, heightBg, scrolSpeed);
        backBg = new BackgroundScroll(0 , frontBg.getTailY(), width, heightBg, scrolSpeed);

        icicles1 = new Icicles(320, 1000, widthIcicles, heightIcicles, scrolSpeed);
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
        if (icicles1.isScrolled()) {
            icicles1.reset(icicles4.getTailY() + distansOfIcicles);
            addScore(1);
        } else if (icicles2.isScrolled()) {
            icicles2.reset(icicles1.getTailY() + distansOfIcicles);
            addScore(1);

        } else if (icicles3.isScrolled()) {
            icicles3.reset(icicles2.getTailY() + distansOfIcicles);
            addScore(1);
        }
        else if (icicles4.isScrolled()) {
            icicles4.reset(icicles3.getTailY() + distansOfIcicles);
            addScore(1);
        }


        if (frontBg.isScrolled()) {
            frontBg.reset(backBg.getTailY());

        } else if (backBg.isScrolled()) {
            backBg.reset(frontBg.getTailY());

        }


    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);

    }

    public void stop() {
        frontBg.stop();
        backBg.stop();
        icicles1.stop();
        icicles2.stop();
        icicles3.stop();
        icicles4.stop();
    }

    public void play() {
        frontBg.play();
        backBg.play();
        icicles1.play();
        icicles2.play();
        icicles3.play();
        icicles4.play();
    }


    public boolean collides(Squirrel squirrel) {
        return (icicles1.collides(squirrel) || icicles2.collides(squirrel) ||
                icicles3.collides(squirrel) || icicles4.collides(squirrel));
    }



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


    public void onRestart() {
        frontBg.onRestart(0, scrolSpeed);
        backBg.onRestart(frontBg.getTailY(), scrolSpeed);
        icicles1.onRestart(1000, scrolSpeed);
        icicles2.onRestart(icicles1.getTailY() + distansOfIcicles, scrolSpeed);
        icicles3.onRestart(icicles2.getTailY() + distansOfIcicles, scrolSpeed);
        icicles4.onRestart(icicles3.getTailY() + distansOfIcicles, scrolSpeed);
    }


}
