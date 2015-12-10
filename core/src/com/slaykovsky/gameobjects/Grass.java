package com.slaykovsky.gameobjects;

/**
 * Created by slaykale on 10/12/15.
 */
public class Grass extends Scrollable {
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void onRestart(float x, float scrollSpeed) {
        this.position.x = x;
        this.velocity.x = scrollSpeed;
    }
}
