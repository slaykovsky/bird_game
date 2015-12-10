package com.slaykovsky.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.slaykovsky.com.slaykovsky.birdhelpers.AssetLoader;

/**
 * Created by slaykale on 09/12/15.
 */
public class Bird {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    private boolean isAlive;

    private Circle circle;

    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 460);
        this.circle = new Circle();
        this.isAlive = true;
    }

    public void update(float delta) {
        this.velocity.add(this.acceleration.cpy().scl(delta));

        if (this.velocity.y > 200) {
            this.velocity.y = 200;
        }

        if (this.position.y < -13) {
            this.position.y = -13;
            this.velocity.y = 0;
        }

        this.position.add(this.velocity.cpy().scl(delta));
        this.circle.set(this.position.x + 9, this.position.y + 6, 6.5f);

        if (this.velocity.y < 0) {
            this.rotation -= 600 * delta;

            if (this.rotation < -20) {
                this.rotation = -20;
            }
        }

        if (this.isFalling() || !this.isAlive) {
            this.rotation += 480 * delta;

            if (this.rotation > 90) {
                this.rotation = 90;
            }
        }
    }

    public boolean isFalling() {
        return this.velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return this.velocity.y > 70 || !this.isAlive;
    }

    public void onClick() {
        if (this.isAlive) {
            AssetLoader.flap.play();
            this.velocity.y -= 140;
        }
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

    public float getRotation() {
        return this.rotation;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public void die() {
        this.isAlive = false;
        this.velocity.y = 0;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void decelerate() {
        this.acceleration.y = 0;
    }
}
