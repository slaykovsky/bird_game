package com.slaykovsky.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by slaykale on 10/12/15.
 */
public class Pipe extends Scrollable {

    private Random random;

    private Rectangle skullDown, skullUp, barDown, barUp;

    private static final int VERTICAL_GAP = 45;
    private static final int SKULL_WIDTH = 24;
    private static final int SKULL_HEIGHT = 11;

    private float groundY;
    private boolean scored;

    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        this.random = new Random();
        this.skullDown = new Rectangle();
        this.skullUp = new Rectangle();
        this.barDown = new Rectangle();
        this.barUp = new Rectangle();

        this.groundY = groundY;
        this.scored = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        this.barUp.set(this.position.x, this.position.y, this.width, this.height);
        this.barDown.set(this.position.x, this.position.y + this.height + VERTICAL_GAP,
                this.width, this.groundY - (this.position.y + this.height + VERTICAL_GAP));

        this.skullUp.set(this.position.x - (SKULL_WIDTH - this.width) / 2,
                this.position.y + this.height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        this.skullDown.set(this.position.x - (SKULL_WIDTH - this.width) / 2, this.barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);
    }

    @Override
    public void reset(float x) {
        super.reset(x);
        this.scored = false;
        this.height = random.nextInt(90) + 15;
    }

    public Rectangle getSkullDown() {
        return this.skullDown;
    }

    public Rectangle getSkullUp() {
        return this.skullUp;
    }

    public Rectangle getBarDown() {
        return this.barDown;
    }

    public Rectangle getBarUp() {
        return this.barUp;
    }

    public boolean collides(Bird bird) {
        if (this.position.x < bird.getWidth() + bird.getX()) {
            return (Intersector.overlaps(bird.getCircle(), this.barUp) ||
                    Intersector.overlaps(bird.getCircle(), this.barDown) ||
                    Intersector.overlaps(bird.getCircle(), this.skullUp) ||
                    Intersector.overlaps(bird.getCircle(), this.skullDown));
        }
        return false;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
