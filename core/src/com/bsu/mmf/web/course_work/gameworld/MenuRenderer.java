package com.bsu.mmf.web.course_work.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bsu.mmf.web.course_work.Constants;
import com.bsu.mmf.web.course_work.helpers.AssetLoader;

/**
 * Created by DemonStore on 29.04.2015.
 */
public class MenuRenderer {
    private OrthographicCamera cam;
    private MenuWorld world;

    private SpriteBatch batcher;
    private TextureRegion bgMenu;
    private BitmapFont font, shadow;

    private float gameWidth;
    private float gameHeight;
    private float gameWidthCoefficient;

    private void initAssets() {
        bgMenu = AssetLoader.bgMenu;
        font = AssetLoader.font;
        shadow = AssetLoader.shadow;
    }

    public MenuRenderer(MenuWorld world, int gameHeight, float gameWidthCoefficient) {
        this.world = world;
        this.gameWidth = Constants.GAME_WIDTH;
        this.gameHeight = gameHeight;
        this.gameWidthCoefficient = gameWidthCoefficient;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth , gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        initAssets();
    }

    public void render(float delta) {
        // рисуем меню
        batcher.begin();
        batcher.disableBlending();
        batcher.draw(bgMenu, 0, 0, gameWidth, bgMenu.getRegionHeight());
        batcher.enableBlending();

        shadow.setScale(.35f, -.35f);
        font.setScale(.35f, -.35f);

        float soundOnTextWidth = font.getBounds("sound: on").width;
        float soundOffTextWidth = font.getBounds("sound: off").width;
        if (world.isEnabledSound()) {
            shadow.draw(batcher, "sound: on",  gameWidth/2 - soundOnTextWidth/2 , gameHeight/2 + 140 + 50);
            font.draw(batcher, "sound: on",  gameWidth/2 - soundOnTextWidth/2 - 1 , gameHeight/2 + 140 + 50);
        } else {
            shadow.draw(batcher, "sound: off",  gameWidth/2 - soundOffTextWidth/2, gameHeight/2 + 140 + 50);
            font.draw(batcher, "sound: off",  gameWidth/2 - soundOffTextWidth/2 - 1 , gameHeight/2 + 140 + 50);
        }

        float acceleromOnTextWidth = font.getBounds("accelerometer: on").width;
        float acceleromOffTextWidth = font.getBounds("accelerometer: off").width;
        if (world.isEnabledAccelerom()) {
            shadow.draw(batcher, "accelerometer: on" , gameWidth/2 - acceleromOnTextWidth/2  , gameHeight/2 +140 + 110);
            font.draw(batcher, "accelerometer: on" , gameWidth/2 - acceleromOnTextWidth/2 - 1 , gameHeight/2 +140 + 110);
        } else {
            shadow.draw(batcher, "accelerometer: off" , gameWidth/2 - acceleromOffTextWidth/2, gameHeight/2 +140 + 110);
            font.draw(batcher, "accelerometer: off" , gameWidth/2 - acceleromOffTextWidth/2 - 1 , gameHeight/2 +140 + 110);
        }

        float exitTextWidth = font.getBounds("exit").width;
        if (world.isDownExit()){
            shadow.draw(batcher, "exit",  gameWidth/2 - exitTextWidth/2 - 1  , gameHeight/2 + 140 + 170);
        }
        else{
            shadow.draw(batcher, "exit",  gameWidth/2 - exitTextWidth/2 - 1  , gameHeight/2 + 140 + 170);
            font.draw(batcher, "exit",  gameWidth/2 - exitTextWidth/2, gameHeight/2 + 140 + 170);
        }

        shadow.setScale(.20f, -.20f);
        font.setScale(.20f, -.20f);

        float captionTextWidth = font.getBounds("Sqwirrel v1.0 (c) 2015").width;
        shadow.draw(batcher, "Sqwirrel v1.0 (c) 2015",  gameWidth/2 - captionTextWidth/2 , gameHeight - 20);
        font.draw(batcher, "Sqwirrel v1.0 (c) 2015",  gameWidth/2 - captionTextWidth/2 - 1 , gameHeight - 20);

        shadow.setScale(.50f, -.50f);
        font.setScale(.50f, -.50f);


        float startTextWidth = font.getBounds("start").width;
        if (world.isDownBtn()){
            shadow.draw(batcher, "start",  gameWidth/2 - startTextWidth/2, gameHeight/2 + 50);
        }
        else{
            shadow.draw(batcher, "start",  gameWidth/2 - startTextWidth/2, gameHeight/2 + 50);
            font.draw(batcher, "start",  gameWidth/2 - startTextWidth/2 - 1, gameHeight /2 + 50);
        }

        shadow.setScale(.29f, -.29f);
        font.setScale(.29f, -.29f);

        batcher.end();
    }
}