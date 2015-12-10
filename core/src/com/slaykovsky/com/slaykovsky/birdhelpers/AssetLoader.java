package com.slaykovsky.com.slaykovsky.birdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by slaykale on 09/12/15.
 */
public class AssetLoader {
    public static Sound dead;

    public static Texture texture;
    public static TextureRegion background;
    public static TextureRegion grass;

    public static Animation birdAnimation;
    public static TextureRegion bird;
    public static TextureRegion birdDown;
    public static TextureRegion birdUp;

    public static TextureRegion skullUp;
    public static TextureRegion skullDown;
    public static TextureRegion bar;

    public static void load() {
        dead = Gdx.audio.newSound(Gdx.files.internal("android/assets/dead.wav"));

        texture = new Texture(Gdx.files.internal("android/assets/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background = new TextureRegion(texture, 0, 0, 136, 43);
        background.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = {birdDown, bird, birdDown};
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
    }

    public static void dispose() {
        texture.dispose();
    }

}
