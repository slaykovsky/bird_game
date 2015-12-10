package com.slaykovsky.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.slaykovsky.birdhelpers.AssetLoader;
import com.slaykovsky.birdhelpers.ScrollHandler;
import com.slaykovsky.gameobjects.Bird;
import com.slaykovsky.gameobjects.Grass;
import com.slaykovsky.gameobjects.Pipe;

/**
 * Created by slaykale on 09/12/15.
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private OrthographicCamera orthographicCamera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private int midPointY;
    private int gameHeight;

    private Bird bird;
    private ScrollHandler scrollHandler;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;
    private TextureRegion background;
    private TextureRegion grass;
    private TextureRegion birdMid;
    private TextureRegion birdDown;
    private TextureRegion birdUp;
    private TextureRegion bar;
    private TextureRegion skullDown;
    private TextureRegion skullUp;
    private Animation birdAnimation;

    public GameRenderer(GameWorld gameWorld, int gameHeight, int midPointY) {
        this.gameWorld = gameWorld;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        this.orthographicCamera = new OrthographicCamera();
        this.orthographicCamera.setToOrtho(true, 136, this.gameHeight);

        this.spriteBatch = new SpriteBatch();

        this.spriteBatch.setProjectionMatrix(this.orthographicCamera.combined);

        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(this.orthographicCamera.combined);

        this.initGameObjects();
        this.initAssets();
    }

    private void initGameObjects() {
        this.bird = this.gameWorld.getBird();
        this.scrollHandler = this.gameWorld.getScrollHandler();
        this.frontGrass = this.scrollHandler.getFrontGrass();
        this.backGrass = this.scrollHandler.getBackGrass();
        this.pipe1 = this.scrollHandler.getPipe1();
        this.pipe2 = this.scrollHandler.getPipe2();
        this.pipe3 = this.scrollHandler.getPipe3();
    }

    private void initAssets() {
        this.background = AssetLoader.background;
        this.grass = AssetLoader.grass;
        this.birdMid = AssetLoader.bird;
        this.birdDown = AssetLoader.birdDown;
        this.birdUp = AssetLoader.birdUp;
        this.bar = AssetLoader.bar;
        this.skullDown = AssetLoader.skullDown;
        this.skullUp = AssetLoader.skullUp;
        this.birdAnimation = AssetLoader.birdAnimation;
    }

    private void drawGrass() {
        this.spriteBatch.draw(this.grass, this.frontGrass.getX(), this.frontGrass.getY(),
                this.frontGrass.getWidth(), this.frontGrass.getHeight());
        this.spriteBatch.draw(this.grass, this.backGrass.getX(), this.backGrass.getY(),
                this.backGrass.getWidth(), this.backGrass.getHeight());
    }

    private void drawSkulls() {
        this.spriteBatch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        this.spriteBatch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        this.spriteBatch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        this.spriteBatch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        this.spriteBatch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        this.spriteBatch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        this.spriteBatch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        this.spriteBatch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        this.spriteBatch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        this.spriteBatch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        this.spriteBatch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        this.spriteBatch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Background
        this.shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        this.shapeRenderer.rect(0, 0, 136, this.midPointY + 66);
        // Grass
        this.shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        this.shapeRenderer.rect(0, this.midPointY + 66, 136, 11);
        // Dirt
        this.shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        this.shapeRenderer.rect(0, this.midPointY + 77, 136, 52);

        this.shapeRenderer.end();

        this.spriteBatch.begin();
        // Disable transparency for background
        this.spriteBatch.disableBlending();
        this.spriteBatch.draw(this.background, 0, this.midPointY + 23, 136, 43);
        this.drawGrass();
        this.drawPipes();
        // Enable transparency for foreground
        this.spriteBatch.enableBlending();
        this.drawSkulls();
        // Bird
        if (this.bird.shouldntFlap()) {
            this.spriteBatch.draw(this.birdMid, this.bird.getX(), this.bird.getY(),
                    this.bird.getWidth() / 2.0f, this.bird.getHeight() / 2.0f,
                    this.bird.getWidth(), this.bird.getHeight(),
                    1, 1, this.bird.getRotation());
        } else {
            this.spriteBatch.draw(this.birdAnimation.getKeyFrame(runTime),
                    this.bird.getX(), this.bird.getY(),
                    this.bird.getWidth() / 2.0f, this.bird.getHeight() / 2.0f ,
                    this.bird.getWidth(), this.bird.getHeight(), 1, 1, this.bird.getRotation());
        }

        if (this.gameWorld.isReady()) {
            AssetLoader.shadow.draw(this.spriteBatch, "Touch Me", (136 / 2) - 42, 76);
            AssetLoader.font.draw(this.spriteBatch, "Touch Me", (136 / 2) - 41, 75);
        } else if (this.gameWorld.isGameOver() || this.gameWorld.isHighScore()) {
            if (this.gameWorld.isGameOver()) {
                AssetLoader.shadow.draw(this.spriteBatch, "Game Over", 25, 56);
                AssetLoader.font.draw(this.spriteBatch, "Game Over", 24, 55);

                AssetLoader.shadow.draw(this.spriteBatch, "High Score:", 23, 106);
                AssetLoader.font.draw(this.spriteBatch, "High Score:", 22, 105);

                String highScore = Integer.toString(AssetLoader.getHighScore());

                AssetLoader.shadow.draw(this.spriteBatch, highScore, (136 / 2)
                        - (3 * highScore.length()), 128);
                AssetLoader.font.draw(this.spriteBatch, highScore, (136 / 2)
                        - (3 * highScore.length() - 1), 127);
            } else {
                AssetLoader.shadow.draw(this.spriteBatch, "High Score!", 19, 56);
                AssetLoader.font.draw(this.spriteBatch, "High Score!", 18, 55);
            }

            AssetLoader.shadow.draw(this.spriteBatch, "Try again?", 23, 76);
            AssetLoader.font.draw(this.spriteBatch, "Try again?", 24, 75);

            String score = Integer.toString(this.gameWorld.getScore());
            AssetLoader.shadow.draw(this.spriteBatch, score, (136 / 2) - (3 * score.length()), 12);
            AssetLoader.font.draw(this.spriteBatch, score, (136 / 2) - (3 * score.length()) - 1, 11);
        }

        String score = Integer.toString(this.gameWorld.getScore());
        AssetLoader.shadow.draw(this.spriteBatch, score, (136 / 2) - (3 * score.length()), 12);
        AssetLoader.font.draw(this.spriteBatch, score, (136 / 2) - (3 * score.length()) - 1, 11);

        this.spriteBatch.end();

    }
}
