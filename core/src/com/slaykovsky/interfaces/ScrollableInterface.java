package com.slaykovsky.interfaces;

/**
 * Created by slaykale on 15/12/15.
 */
public interface ScrollableInterface {
    void update(float delta);
    void reset(float x);
    void stop();
    boolean isScrolledLeft();
    float getTailX();
    float getX();
    float getY();
    int getWidth();
    int getHeight();
}
