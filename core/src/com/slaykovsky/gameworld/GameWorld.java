package com.slaykovsky.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.slaykovsky.birdhelpers.AssetLoader;
import com.slaykovsky.birdhelpers.ScrollHandler;
import com.slaykovsky.gameobjects.Bird;


/**
 * Created by slaykale on 09/12/15.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scrollHandler;
    private Rectangle ground;

    private int score;
    private int midPointY;

    private enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    private GameState gameState;

    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        this.bird = new Bird(33, midPointY - 5, 17, 12);
        this.scrollHandler = new ScrollHandler(this, this.midPointY + 66);
        this.ground = new Rectangle(0, this.midPointY + 66, 136, 11);
        this.score = 0;
        this.gameState = GameState.READY;
    }

    public void update(float delta) {
        switch (this.gameState) {
            case READY:
                this.updateReady(delta);
                break;
            case RUNNING:
                this.updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
    }

    private void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        this.bird.update(delta);
        this.scrollHandler.update(delta);

        if (this.scrollHandler.collides(this.bird) && bird.isAlive()) {
            this.scrollHandler.stop();
            this.bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(this.bird.getCircle(), this.ground)) {
            this.scrollHandler.stop();
            this.bird.die();
            this.bird.decelerate();
            this.gameState = GameState.GAMEOVER;

            if (this.score > AssetLoader.preferences.getInteger("highScore")) {
                AssetLoader.setHighScore(this.score);
                this.gameState = GameState.HIGHSCORE;
            }
        }
    }


    public Bird getBird() {
        return this.bird;
    }

    public ScrollHandler getScrollHandler() {
        return this.scrollHandler;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int increment) {
        this.score += increment;
    }

    public boolean isReady() {
        return this.gameState.equals(GameState.READY);
    }

    public void start() {
        this.gameState = GameState.RUNNING;
    }

    public void restart() {
        this.gameState = GameState.READY;
        this.score = 0;
        this.bird.onRestart(this.midPointY - 5);
        this.scrollHandler.onRestart();
    }

    public boolean isGameOver() {
        return this.gameState.equals(GameState.GAMEOVER);
    }

    public boolean isHighScore() {
        return this.gameState.equals(GameState.HIGHSCORE);
    }
}
