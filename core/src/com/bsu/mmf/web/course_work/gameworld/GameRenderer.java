package com.bsu.mmf.web.course_work.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.gameobjects.Acorn;
import com.bsu.mmf.web.course_work.gameobjects.BackgroundScroll;
import com.bsu.mmf.web.course_work.gameobjects.Icicles;
import com.bsu.mmf.web.course_work.gameobjects.ScrollHandler;
import com.bsu.mmf.web.course_work.gameobjects.Squirrel;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;

/**
 * Created by Anton on 06.02.2015.
 * Отрисовываем игровые объекты
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private Squirrel squirrel;
    private Acorn acorns;
    private ScrollHandler scroller;
    private BackgroundScroll frontBg, backBg;
    private Icicles icicles1, icicles2, icicles3, icicles4;

    private TextureRegion bg;
    private TextureRegion icicles;
    private Animation squirrelAnimation;
    private Animation iceAnimation;
    private TextureRegion squirrel1;
    private TextureRegion stars;
    private TextureRegion pause;
    private TextureRegion squirrelBum;
    private TextureRegion acorn;
    private TextureRegion bgGameMenu;
    private TextureRegion buttonGameMenu,buttonGameMenu2;
    private TextureRegion buttonHome, buttonRest;

    private BitmapFont font, shadow;

    private int gameWidth;
    private int gameHeight;
    private int widthIcicles;
    private int heightIcicles;
    private int widthIse;
    private int heightIse;

    public GameRenderer(GameWorld world, int gameHeight) {
        this.gameWidth = (int) Constants.GAME_WIDTH;
        this.gameHeight = gameHeight;
        this.world = world;
        // задаём камеру
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Constants.GAME_WIDTH , gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();

        widthIcicles = (int) Constants.WIDTH_ICICLES;
        heightIcicles = (int) Constants.HEIGHT_ICICLES;
        widthIse = (int) Constants.WIDTH_ISE;
        heightIse = (int) Constants.HEIGHT_ISE;

    }

    private void initGameObjects() {
        squirrel = world.getSquirrel();
        scroller = world.getScroller();
        frontBg = scroller.getFrontBg();
        backBg = scroller.getBackBg();
        icicles1 = scroller.getIcicles1();
        icicles2 = scroller.getIcicles2();
        icicles3 = scroller.getIcicles3();
        icicles4 = scroller.getIcicles4();
        acorns = world.getAcorn();

    }

    private void initAssets() {
        bg = AssetLoader.bg;
        icicles = AssetLoader.icicles;
        squirrelAnimation = AssetLoader.squirrelAnimation;
        iceAnimation = AssetLoader.iceAnimation;
        stars = AssetLoader.stars;
        squirrel1 = AssetLoader.squirrel1;
        font = AssetLoader.font;
        shadow = AssetLoader.shadow;
        squirrelBum = AssetLoader.squirrelBum;
        acorn = AssetLoader.acorn;
        pause = AssetLoader.pause;
        bgGameMenu = AssetLoader.bgGameMenu;
        buttonGameMenu = AssetLoader.buttonGameMenu;
        buttonGameMenu2 = AssetLoader.buttonGameMenu2;
        buttonHome = AssetLoader.buttonHome;
        buttonRest = AssetLoader.buttonRest;
     }

    public void render(float runTime) {
        batcher.begin();

        drawBackgroundScroll();
        drawIciclesAndAcorns();
        drawSquirrel(runTime);
        drawScore();
        drawMenu();

        batcher.end();
    }


    private void drawSquirrel(float runTime) {
        // Отрисуем на координатах. Получим Animation объект из AssetLoader
        // Передадим runTime переменную чтобы получить текущий кадр.
        if (!world.isAlive()){
            batcher.draw(squirrelBum, squirrel.getX() - squirrel.getWidth()/4, squirrel.getY(),
                    squirrelBum.getRegionWidth(), squirrelBum.getRegionHeight());
            world.setIsAliveCheked(false);

        } else {

            if (!world.isPause()){
                batcher.draw(iceAnimation.getKeyFrame(runTime),
                        squirrel.getX() - squirrel.getWidth()/2, squirrel.getY() - squirrel.getWidth()/2, widthIse, heightIse);

                batcher.draw(squirrelAnimation.getKeyFrame(runTime),
                        squirrel.getX(), squirrel.getY(), squirrel.getWidth(), squirrel.getHeight());
            }
            else
                batcher.draw(squirrel1,
                        squirrel.getX(), squirrel.getY(), squirrel.getWidth(), squirrel.getHeight());


        }
    }

    private void drawBackgroundScroll() {
        batcher.disableBlending(); // без прозрачности грузит быстрее
        batcher.draw(bg, frontBg.getX(), frontBg.getY(),frontBg.getWidth(), frontBg.getHeight());
        batcher.draw(bg, backBg.getX(), backBg.getY(), backBg.getWidth(), backBg.getHeight());
        // нужна прозрачность, поэтому включаем ее
        batcher.enableBlending();
    }

    private void drawIciclesAndAcorns() {
        batcher.draw(icicles, icicles1.getX(), icicles1.getY(),widthIcicles, heightIcicles);
        batcher.draw(icicles, icicles2.getX(), icicles2.getY(),widthIcicles, heightIcicles);
        batcher.draw(icicles, icicles3.getX(), icicles3.getY(),widthIcicles, heightIcicles);
        batcher.draw(icicles, icicles4.getX(), icicles4.getY(),widthIcicles, heightIcicles);

        if (acorns.inScreen()){
            batcher.draw(acorn, acorns.getX(), acorns.getY(), acorn.getRegionWidth(), acorn.getRegionHeight());
        }
    }

    private void drawScore(){
        shadow.draw(batcher, "" + world.getScoreSum(),  gameWidth - 29 - 10 , 6);
        font.draw(batcher, "" + world.getScoreSum(), gameWidth - 30 -10 , 6);

        shadow.draw(batcher, "" + world.getScore2(),  gameWidth - 59 - stars.getRegionWidth() - 10 , 6);
        font.draw(batcher, "" + world.getScore2(), gameWidth - 60 - stars.getRegionWidth() - 10 , 6);

        batcher.draw(stars, gameWidth - 60 - 10 , 0 ,stars.getRegionWidth(), stars.getRegionHeight());
        batcher.draw(acorn, gameWidth - 90 - stars.getRegionWidth() - 10 ,  2 ,stars.getRegionWidth(), stars.getRegionHeight());

        batcher.draw(pause, gameWidth - 34 , gameHeight - 40 ,pause.getRegionWidth(), pause.getRegionHeight());
    }


    private void drawMenu(){
        if (world.isPause()){
            batcher.draw(bgGameMenu,120 , 200 ,bgGameMenu.getRegionWidth(), bgGameMenu.getRegionHeight());
            shadow.setScale(.40f, -.40f);
            font.setScale(.40f, -.40f);
            shadow.draw(batcher,"MENU",  220 + 10 , 200 + 20 );
            font.draw(batcher,"MENU" ,  220 + 10-1, 200 + 20 );
            shadow.setScale(.29f, -.29f);
            font.setScale(.29f, -.29f);

            if (world.isAlive()) {
                batcher.draw(buttonGameMenu, 120 + (300 - buttonGameMenu.getRegionWidth()) / 2, 500 - buttonGameMenu.getRegionWidth() - 100,
                        buttonGameMenu.getRegionWidth(), buttonGameMenu.getRegionWidth());
            }
            else{
                batcher.draw(buttonGameMenu2, 120 + (300 - buttonGameMenu.getRegionWidth()) / 2, 500 - buttonGameMenu.getRegionWidth() - 100,
                        buttonGameMenu.getRegionWidth(), buttonGameMenu.getRegionWidth());
            }

            batcher.draw(buttonHome,120 + (300 - buttonGameMenu.getRegionWidth())/2  -138/2 , 500-buttonGameMenu.getRegionWidth() - 50 ,
                    buttonHome.getRegionWidth()-50, buttonHome.getRegionWidth()-50);
            batcher.draw(buttonRest,120 + (300 - buttonGameMenu.getRegionWidth())/2+138+10, 500-buttonGameMenu.getRegionWidth() - 50,
                    buttonRest.getRegionWidth()-50, buttonRest.getRegionWidth()-50);

            batcher.draw(stars,120 + 20 + 140  ,200 + bgGameMenu.getRegionHeight() - 50-6,
                    stars.getRegionWidth(), stars.getRegionHeight());

            batcher.draw(acorn, 120 + 40,  200 + bgGameMenu.getRegionHeight() - 50-4 ,
                    stars.getRegionWidth(), stars.getRegionHeight());


            shadow.draw(batcher, "" + world.getScoreSum(),  120 + 20 + 160 +stars.getRegionWidth() , 200 + bgGameMenu.getRegionHeight() - 50);
            font.draw(batcher, "" + world.getScoreSum(), 120 + 20 + 160-1 +stars.getRegionWidth(), 200 + bgGameMenu.getRegionHeight() - 50);
            shadow.draw(batcher, "" + world.getScore2(),  120 + 40 +acorn.getRegionWidth(), 200 + bgGameMenu.getRegionHeight() - 50);
            font.draw(batcher, "" + world.getScore2(),  120 + 40-1+acorn.getRegionWidth(), 200 + bgGameMenu.getRegionHeight() - 50);


            shadow.setScale(.40f, -.40f);
            font.setScale(.40f, -.40f);

            batcher.draw(bgGameMenu,120 , 200+ bgGameMenu.getRegionHeight() + 30 ,bgGameMenu.getRegionWidth(),
                    bgGameMenu.getRegionHeight());
            shadow.draw(batcher,"TOP 5 SCORE",  180 + 10 , 200 + bgGameMenu.getRegionHeight() + 50 );
            font.draw(batcher,"TOP 5 SCORE" ,  180 + 10-1, 200 + bgGameMenu.getRegionHeight() + 50 );

            shadow.draw(batcher,"YOUR: " + world.getScoreSum(),  200 + 10 , 200 + bgGameMenu.getRegionHeight()*2  );
            font.draw(batcher,"YOUR: " + world.getScoreSum(),  200 + 10-1, 200 + bgGameMenu.getRegionHeight()*2  );

            if (AssetLoader.listScore.get(0) == world.getScoreSum()){
                shadow.setScale(.70f, -.70f);
                font.setScale(.70f, -.70f);

                shadow.draw(batcher,"NEW RECORD!", 120  ,  100 );
                font.draw(batcher,"NEW RECORD!" , 120 ,  100 );

                shadow.setScale(.40f, -.40f);
                font.setScale(.40f, -.40f);

            }

            for (int i = 0; i<5 ; i++){
                int temp = AssetLoader.listScore.get(i);

                shadow.draw(batcher,i+1 + ". - " + temp,  200 + 30 , 200 + bgGameMenu.getRegionHeight() + 120 + i*30);
                font.draw(batcher,i+1 + ". - " + temp,  200 + 30-1, 200 + bgGameMenu.getRegionHeight() + 120 + i*30);

            }

            shadow.setScale(.29f, -.29f);
            font.setScale(.29f, -.29f);
        }
    }
}