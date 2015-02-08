package com.bsu.mmf.web.course_work.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bsu.mmf.web.course_work.MainConst;

/**
 * Created by Anton on 07.02.2015.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg;

    public static Animation squirrelAnimation;
    public static TextureRegion squirrel1, squirrel2, squirrel3;

    public static void load() {

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


        TextureRegion[] squirrels = { squirrel1, squirrel2, squirrel3 };
        squirrelAnimation = new Animation(MainConst.ANIMATIONSQUIRREL, squirrels);
        squirrelAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);



    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        texture.dispose();
    }

}
