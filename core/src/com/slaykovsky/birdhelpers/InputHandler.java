package com.slaykovsky.birdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.slaykovsky.gameobjects.Bird;
import com.slaykovsky.gameworld.GameWorld;

/**
 * Created by slaykale on 09/12/15.
 */
public class InputHandler implements InputProcessor {
    private GameWorld gameWorld;
    private Bird bird;

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.bird = this.gameWorld.getBird();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (this.gameWorld.isReady()) {
            this.gameWorld.start();
        }

        Gdx.input.vibrate(20);
        this.bird.onTap();

        if (this.gameWorld.isGameOver() || this.gameWorld.isHighScore()) {
            this.gameWorld.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
