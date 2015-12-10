package com.slaykovsky.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.slaykovsky.com.slaykovsky.birdhelpers.InputHandler;
import com.slaykovsky.gameworld.GameRenderer;
import com.slaykovsky.gameworld.GameWorld;

/**
 * Created by slaykale on 09/12/15.
 */
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;

    private float runTime = 0;

    public GameScreen() {
        float gameScreenWidth = Gdx.graphics.getWidth();
        float gameScreenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = gameScreenHeight / (gameScreenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);

        this.gameWorld = new GameWorld(midPointY);

        this.gameRenderer = new GameRenderer(this.gameWorld, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(this.gameWorld.getBird()));
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "Show called");
    }

    @Override
    public void render(float delta) {
        this.runTime += delta;
        this.gameWorld.update(delta);
        this.gameRenderer.render(this.runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "Resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "Pause called.");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "Resume called.");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "Hide called.");
    }

    @Override
    public void dispose() {
    }
}
