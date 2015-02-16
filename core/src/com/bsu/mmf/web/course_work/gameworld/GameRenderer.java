package com.bsu.mmf.web.course_work.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bsu.mmf.web.course_work.MainConst;
import com.bsu.mmf.web.course_work.gameobjects.Acorn;
import com.bsu.mmf.web.course_work.gameobjects.BackgroundScroll;
import com.bsu.mmf.web.course_work.gameobjects.Icicles;
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
    private Acorn acorns;
    private ScrollHandler scroller;
    private BackgroundScroll frontBg, backBg;
    private Icicles icicles1,icicles2,icicles3,icicles4;

    private TextureRegion bg;
    public  TextureRegion icicles;
    private Animation squirrelAnimation;
    private Animation iceAnimation;
    private TextureRegion squirrel1, squirrel2, squirrel3;
    public  TextureRegion stars;
    public  TextureRegion squirrelBum;
    public  TextureRegion acorn;

    public static BitmapFont font, shadow;

    private int widthIcicles;
    private int heightIcicles;
    private int widthIse;
    private int heightIse;
    private int widthGEME;

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

        widthIcicles = (int) MainConst.WIDTHICICLES;
        heightIcicles = (int) MainConst.HEIGHTICICLES;
        widthIse = (int) MainConst.WIDTHISE;
        heightIse = (int) MainConst.HEIGHTISE;
        widthGEME = (int) MainConst.GEMEWIDTH;
    }

    private void initGameObjects() {
        squirrel = myWorld.getSquirrel();
        scroller = myWorld.getScroller();
        frontBg = scroller.getFrontBg();
        backBg = scroller.getBackBg();
        icicles1 = scroller.getIcicles1();
        icicles2 = scroller.getIcicles2();
        icicles3 = scroller.getIcicles3();
        icicles4 = scroller.getIcicles4();
        acorns = myWorld.getAcorn();

    }

    private void initAssets() {
        bg = AssetLoader.bg;
        icicles = AssetLoader.icicles;
        squirrelAnimation = AssetLoader.squirrelAnimation;
        iceAnimation = AssetLoader.iceAnimation;
        stars = AssetLoader.stars;
       // squirrel1 = AssetLoader.squirrel1;   // пока что не нужно
       // squirrel2 = AssetLoader.squirrel2;
       // squirrel3 = AssetLoader.squirrel3;
        font = AssetLoader.font;
        shadow = AssetLoader.shadow;
        squirrelBum = AssetLoader.squirrelBum;
        acorn = AssetLoader.acorn;
     }

    public void render(float runTime) {

        batcher.begin();
        batcher.disableBlending();

        batcher.draw(bg, frontBg.getX(), frontBg.getY(),frontBg.getWidth(), frontBg.getHeight());
        batcher.draw(bg, backBg.getX(), backBg.getY(), backBg.getWidth(), backBg.getHeight());

        // нужна прозрачность, поэтому включаем ее
        batcher.enableBlending();

        batcher.draw(icicles, icicles1.getX(), icicles1.getY(),widthIcicles, heightIcicles);
        batcher.draw(icicles, icicles2.getX(), icicles2.getY(),widthIcicles, heightIcicles);
        batcher.draw(icicles, icicles3.getX(), icicles3.getY(),widthIcicles, heightIcicles);
        batcher.draw(icicles, icicles4.getX(), icicles4.getY(),widthIcicles, heightIcicles);


        if (acorns.inScreen() == true){
            batcher.draw(acorn, acorns.getX(), acorns.getY(),acorn.getRegionWidth(), acorn.getRegionHeight());
        }



        // Отрисуем на координатах. Получим Animation объект из AssetLoader
        // Передадим runTime переменную чтобы получить текущий кадр.




        if (myWorld.isAlive() == false){
            batcher.draw(squirrelBum, squirrel.getX() - squirrel.getWidth()/4, squirrel.getY(),
                    squirrelBum.getRegionWidth(), squirrelBum.getRegionHeight());
        }else {
            batcher.draw(iceAnimation.getKeyFrame(runTime),
                    squirrel.getX() - squirrel.getWidth()/2, squirrel.getY() - squirrel.getWidth()/2, widthIse, heightIse);

            batcher.draw(squirrelAnimation.getKeyFrame(runTime),
                    squirrel.getX(), squirrel.getY(), squirrel.getWidth(), squirrel.getHeight());
        }


        // Сначала отрисовываем тень
        shadow.draw(batcher, "" + myWorld.getScore(),  widthGEME - 29 , 6);
        // Отрисуем сам текст
        font.draw(batcher, "" + myWorld.getScore(), widthGEME - 30, 6);

        shadow.draw(batcher, "" + myWorld.getScore2(),  widthGEME - 59 - stars.getRegionWidth() , 6);
        // Отрисуем сам текст
        font.draw(batcher, "" + myWorld.getScore2(), widthGEME - 60 - stars.getRegionWidth(), 6);

        batcher.draw(stars,widthGEME - 60 , 0 ,stars.getRegionWidth(), stars.getRegionHeight());
        batcher.draw(acorn, widthGEME - 90 - stars.getRegionWidth(),  2 ,stars.getRegionWidth(), stars.getRegionHeight());

        batcher.end();

       // нужно для проверки пересечения...отрисока
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.RED);
        //shapeRenderer.circle(acorns.getBoundingCircle().x, acorns.getBoundingCircle().y, acorns.getBoundingCircle().radius);
        //shapeRenderer.circle(squirrel.getBoundingCircle().x, squirrel.getBoundingCircle().y, squirrel.getBoundingCircle().radius);

        //shapeRenderer.rect(icicles1.getBoundingRectangle().x, icicles1.getBoundingRectangle().y,
        //        icicles1.getBoundingRectangle().width, icicles1.getBoundingRectangle().height);
        //shapeRenderer.rect(icicles2.getBoundingRectangle().x, icicles2.getBoundingRectangle().y,
        //        icicles2.getBoundingRectangle().width, icicles2.getBoundingRectangle().height);
        //shapeRenderer.rect(icicles3.getBoundingRectangle().x, icicles3.getBoundingRectangle().y,
        //        icicles3.getBoundingRectangle().width, icicles3.getBoundingRectangle().height);
        //shapeRenderer.rect(icicles4.getBoundingRectangle().x, icicles4.getBoundingRectangle().y,
        //        icicles4.getBoundingRectangle().width, icicles4.getBoundingRectangle().height);
        //shapeRenderer.end();

    }

}
