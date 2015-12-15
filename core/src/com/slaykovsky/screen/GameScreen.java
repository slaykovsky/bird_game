package com.slaykovsky.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.slaykovsky.birdhelpers.InputHandler;
import com.slaykovsky.gameworld.GameRenderer;
import com.slaykovsky.gameworld.GameWorld;

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

        Gdx.input.setInputProcessor(new InputHandler(this.gameWorld));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.runTime += delta;
        this.gameWorld.update(delta);
        this.gameRenderer.render(this.runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
