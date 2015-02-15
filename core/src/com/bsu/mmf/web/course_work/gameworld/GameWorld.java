package com.bsu.mmf.web.course_work.gameworld;


import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.gameobjects.ScrollHandler;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

/**
 * Created by Anton on 06.02.2015.
 */
public class GameWorld {

    private Squirrel squirrel;
    private ScrollHandler scroller;
    private boolean isAlive = true;

    public GameWorld() {
        squirrel = new Squirrel(MainConst.POSITIONXSQUIRREL, MainConst.POSITIONYSQUIRREL,
                MainConst.WIDTHSQUIRREL ,  MainConst.HEIGHTSQUIRREL );

        scroller = new ScrollHandler();

    }

    public void update(float delta) {
        // Добавим лимит для нашей delta, так что если игра начнет тормозить
        // при обновлении, мы не нарушим нашу логику определения колизии

        if (delta > .15f) {
            delta = .15f;
        }


        squirrel.update(delta);
        scroller.update(delta);

        if (isAlive && scroller.collides(squirrel)) {
            // Clean up on game over
            scroller.stop();
            squirrel.die();
            isAlive = false;
        }

    }

    public Squirrel getSquirrel() {

        return squirrel;

    }

    public ScrollHandler getScroller() {

        return scroller;

    }

    public void restart(){
        squirrel.onRestart();
        scroller.onRestart();
        isAlive = true;
    }

}
