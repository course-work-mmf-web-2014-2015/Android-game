package com.bsu.mmf.web.course_work.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;

/**
 * Created by Anton on 06.02.2015.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;

    private ShapeRenderer shapeRenderer;

    private Squirrel squirrel;

    public GameRenderer(GameWorld world,  int gameHeight, int midPointX ) {
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, MainConst.GEMEWIDTH , MainConst.GEMEHEIGHT);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
    }

    private void initGameObjects() {
        squirrel = myWorld.getSquirrel();

    }

    public void render() {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(squirrel.getBoundingCircle().x, squirrel.getBoundingCircle().y, squirrel.getBoundingCircle().radius);
        shapeRenderer.end();

    }

}
