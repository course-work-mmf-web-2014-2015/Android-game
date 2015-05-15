package com.bsu.mmf.web.course_work.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bsu.mmf.web.course_work.Constants;
import java.util.ArrayList;

/**
 * Created by Anton on 07.02.2015.
 * Класс для загрузки и доступа к различным ресурсам
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg;

    public static Animation squirrelAnimation;
    public static Animation iceAnimation;
    public static TextureRegion squirrel1, squirrel2, squirrel3;
    public static TextureRegion ice1, ice2;
    public static TextureRegion icicles;
    public static TextureRegion stars;
    public static TextureRegion acorn;

    public static TextureRegion squirrelBum;
    public static TextureRegion pause;
    public static BitmapFont font, shadow;

    public static Texture textureForMenu;
    public static TextureRegion bgMenu;
    public static TextureRegion button1, button2;
    public static TextureRegion buttonHome, buttonRest;

    public static Texture textureForGameMenu;
    public static TextureRegion bgGameMenu;
    public static TextureRegion buttonGameMenu, buttonGameMenu2;

    public static Preferences prefs;             // переменная сохранения счёта в файл
    public static ArrayList<Integer> listScore;

    public static Sound crashSound,acornSound;
    public static Music mp3Music;


    public static void load() {

        acornSound = Gdx.audio.newSound(Gdx.files.internal("sound/addAcornSound.mp3"));
        crashSound = Gdx.audio.newSound(Gdx.files.internal("sound/crashSound.mp3"));
        mp3Music = Gdx.audio.newMusic(Gdx.files.internal("sound/mainSound.mp3"));

        texture = new Texture(Gdx.files.internal("img/ice-wallpaper2.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 540, 1280);
        bg.flip(false, true);

        squirrel1 = new TextureRegion(texture, 600, 0, 100, 100);
        squirrel1.flip(false, true);

        squirrel2 = new TextureRegion(texture, 700, 0, 100, 100);
        squirrel2.flip(false, true);

        squirrel3 = new TextureRegion(texture, 800, 0, 100, 100);
        squirrel3.flip(false, true);

        icicles = new TextureRegion(texture, 600, 500, 107, 92);
        icicles.flip(false, true);

        stars = new TextureRegion(texture, 800, 300, 25, 25);
        stars.flip(false, true);

        squirrelBum = new TextureRegion(texture, 600, 600, 160, 112);
        squirrelBum.flip(false, true);

        acorn = new TextureRegion(texture, 800, 600, 40, 40);
        acorn.flip(false, true);

        pause = new TextureRegion(texture, 600, 740, 20, 24);
        pause.flip(false, true);

        ice1 = new TextureRegion(texture, 600, 100, 200, 162);
        ice2 = new TextureRegion(texture, 600, 300, 200, 146);


        TextureRegion[] squirrels = { squirrel1, squirrel2, squirrel3 };
        squirrelAnimation = new Animation(Constants.ANIMATION_SQUIRREL, squirrels);
        squirrelAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] ice = { ice1, ice2 };
        iceAnimation = new Animation(Constants.ANIMATION_SQUIRREL, ice);
        iceAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);


        font = new BitmapFont(Gdx.files.internal("fnt/text.fnt"));
        font.setScale(.29f, -.29f);
        shadow = new BitmapFont(Gdx.files.internal("fnt/shadow.fnt"));
        shadow.setScale(.29f, -.29f);


        // menu

        textureForMenu = new Texture(Gdx.files.internal("img/menu2.png"));
        textureForMenu.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bgMenu = new TextureRegion(textureForMenu, 0, 0, 540, 1200);
        bgMenu.flip(false, true);

        button1 = new TextureRegion(textureForMenu, 600, 0, 138, 136);
        button1.flip(false, true);

        button2 = new TextureRegion(textureForMenu, 800, 0, 138, 136);
        button2.flip(false, true);

        buttonHome =  new TextureRegion(textureForMenu, 800, 150, 100, 100);
        buttonHome.flip(false, true);

        buttonRest =  new TextureRegion(textureForMenu, 600, 150, 100, 100);
        buttonRest.flip(false, true);



        // for GameMenu

        textureForGameMenu = new Texture(Gdx.files.internal("img/game-menu.png"));
        textureForGameMenu.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bgGameMenu = new TextureRegion(textureForGameMenu, 0, 0, 300, 300);
        bgGameMenu.flip(false, true);

        buttonGameMenu = new TextureRegion(textureForMenu, 600, 0, 138, 136);
        buttonGameMenu.flip(false, true);

        buttonGameMenu2 = new TextureRegion(textureForMenu, 800, 0, 138, 136);
        buttonGameMenu2.flip(false, true);



        // подгрузка лучшего счёта из файла SqwirrelScoreList в prefs, а оттуда в ArrayList
        prefs = Gdx.app.getPreferences("SqwirrelScoreList");
        listScore =  new ArrayList<Integer>();

        for (int i = 0; i<5 ; i++){
            listScore.add(prefs.getInteger("" + i));
        }

    }

    public static void dispose() {

        // выгружаем лучший счёт в файл
        for (int i = 0; i<5 ; i++){
            prefs.putInteger("" + i , listScore.get(i));
        }
        prefs.flush();

        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        texture.dispose();
        textureForMenu.dispose();
        textureForGameMenu.dispose();
        font.dispose();
        shadow.dispose();

        crashSound.dispose();
        mp3Music.dispose();
        acornSound.dispose();
    }

}
