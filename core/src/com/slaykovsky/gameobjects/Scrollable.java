package com.slaykovsky.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by slaykale on 10/12/15.
 */
public class Scrollable {
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        this.isScrolledLeft = false;
    }

    public void update(float delta) {
        this.position.add(this.velocity.cpy().scl(delta));

        if (this.position.x + this.width < 0) {
            this.isScrolledLeft = true;
        }
    }

    public void reset(float x) {
        this.position.x = x;
        this.isScrolledLeft = false;
    }

    public void stop() {
        this.velocity.x = 0;
    }

    public boolean isScrolledLeft() {
        return this.isScrolledLeft;
    }

    public float getTailX() {
        return this.position.x + this.width;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
