package com.bsu.mmf.web.course_work.gameobjects;

/**
 * Created by Anton on 08.02.2015.
 */
public class BackgroundScroll extends Scrollable {


    public BackgroundScroll(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

    public void onRestart(float y, float scrollSpeed) {
        position.y = y;
        velocity.y = scrollSpeed;
    }

}
