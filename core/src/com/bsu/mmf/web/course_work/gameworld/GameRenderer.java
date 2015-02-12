package com.bsu.mmf.web.course_work.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.gameobjects.BackgroundScroll;
import com.bsu.mmf.web.course_work.gameobjects.ScrollHandler;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;

/**
 * Created by Anton on 06.02.2015.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private Squirrel squirrel;
    private ScrollHandler scroller;
    private BackgroundScroll frontBg, backBg;

    private TextureRegion bg;
    private Animation squirrelAnimation;
    private Animation iceAnimation;
    private TextureRegion squirrel1, squirrel2, squirrel3;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, MainConst.GEMEWIDTH , MainConst.GEMEHEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        squirrel = myWorld.getSquirrel();
        scroller = myWorld.getScroller();
        frontBg = scroller.getFrontBg();
        backBg = scroller.getBackBg();

    }

    private void initAssets() {
        bg = AssetLoader.bg;
        squirrelAnimation = AssetLoader.squirrelAnimation;
        iceAnimation = AssetLoader.iceAnimation;
       // squirrel1 = AssetLoader.squirrel1;   // пока что не нужно
       // squirrel2 = AssetLoader.squirrel2;
       // squirrel3 = AssetLoader.squirrel3;
     }

    public void render(float runTime) {

        batcher.begin();
        batcher.disableBlending();

        batcher.draw(bg, frontBg.getX(), frontBg.getY(),frontBg.getWidth(), frontBg.getHeight());
        batcher.draw(bg, backBg.getX(), backBg.getY(), backBg.getWidth(), backBg.getHeight());

        // нужна прозрачность, поэтому включаем ее
        batcher.enableBlending();
        // Отрисуем на координатах. Получим Animation объект из AssetLoader
        // Передадим runTime переменную чтобы получить текущий кадр.
        batcher.draw(iceAnimation.getKeyFrame(runTime),
                squirrel.getX() - 50, squirrel.getY() - 50, 200, 160);       // заменить

        batcher.draw(squirrelAnimation.getKeyFrame(runTime),
                squirrel.getX(), squirrel.getY(), squirrel.getWidth(), squirrel.getHeight());

        batcher.end();


       // нужно для проверки пересечения...отрисока
       // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.RED);
       // shapeRenderer.circle(squirrel.getBoundingCircle().x, squirrel.getBoundingCircle().y, squirrel.getBoundingCircle().radius);
       // shapeRenderer.end();

    }

}
