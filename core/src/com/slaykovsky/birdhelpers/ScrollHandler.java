package com.slaykovsky.birdhelpers;

import com.slaykovsky.gameobjects.Bird;
import com.slaykovsky.gameobjects.Grass;
import com.slaykovsky.gameobjects.Pipe;
import com.slaykovsky.gameworld.GameWorld;

/**
 * Created by slaykale on 10/12/15.
 */
public class ScrollHandler {
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    private GameWorld gameWorld;

    private static final int SCROLL_SPEED = -59;
    private static final int PIPE_GAP = 49;


    public ScrollHandler(GameWorld gameWorld, float y) {
        this.gameWorld = gameWorld;
        this.frontGrass = new Grass(0, y, 143, 11, SCROLL_SPEED);
        this.backGrass = new Grass(this.frontGrass.getTailX(), y, 143, 11, SCROLL_SPEED);

        this.pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, y);
        this.pipe2 = new Pipe(this.pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED, y);
        this.pipe3 = new Pipe(this.pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, y);
    }

    public void update(float delta) {
        this.frontGrass.update(delta);
        this.backGrass.update(delta);
        this.pipe1.update(delta);
        this.pipe2.update(delta);
        this.pipe3.update(delta);

        if (this.pipe1.isScrolledLeft()) {
            this.pipe1.reset(this.pipe3.getTailX() + PIPE_GAP);
        } else if (this.pipe2.isScrolledLeft()) {
            this.pipe2.reset(this.pipe1.getTailX() + PIPE_GAP);
        } else if (this.pipe3.isScrolledLeft()) {
            this.pipe3.reset(this.pipe2.getTailX() + PIPE_GAP);
        }

        if (this.frontGrass.isScrolledLeft()) {
            this.frontGrass.reset(this.backGrass.getTailX());
        } else if (this.backGrass.isScrolledLeft()) {
            this.backGrass.reset(this.frontGrass.getTailX());
        }
    }

    public void stop() {
        this.frontGrass.stop();
        this.backGrass.stop();
        this.pipe1.stop();
        this.pipe2.stop();
        this.pipe3.stop();
    }

    public boolean collides(Bird bird) {
        if (!this.pipe1.isScored() &&
                this.pipe1.getX() + (this.pipe1.getWidth() / 2) < bird.getX() + bird.getWidth()) {
            this.gameWorld.addScore(1);
            this.pipe1.setScored(true);
            AssetLoader.coin.play();
        } else if (!this.pipe2.isScored() &&
                this.pipe2.getX() + (this.pipe2.getWidth() / 2) < bird.getX() + bird.getWidth()) {
            this.gameWorld.addScore(1);
            this.pipe2.setScored(true);
            AssetLoader.coin.play();
        } else if (!this.pipe3.isScored() &&
                this.pipe3.getX() + (this.pipe3.getWidth() / 2) < bird.getX() + bird.getWidth()) {
            this.gameWorld.addScore(1);
            this.pipe3.setScored(true);
            AssetLoader.coin.play();
        }

        return (this.pipe1.collides(bird) ||
                this.pipe2.collides(bird) ||
                this.pipe3.collides(bird));
    }

    public void onRestart() {
        this.frontGrass.onRestart(0, SCROLL_SPEED);
        this.backGrass.onRestart(this.frontGrass.getTailX(), SCROLL_SPEED);
        this.pipe1.onRestart(210, SCROLL_SPEED);
        this.pipe2.onRestart(this.pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
        this.pipe3.onRestart(this.pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
    }

    public Grass getFrontGrass() {
        return this.frontGrass;
    }

    public Grass getBackGrass() {
        return this.backGrass;
    }

    public Pipe getPipe1() {
        return this.pipe1;
    }

    public Pipe getPipe2() {
        return this.pipe2;
    }

    public Pipe getPipe3() {
        return this.pipe3;
    }
}
