package com.slaykovsky.interfaces;

import com.slaykovsky.gameobjects.Bird;
import com.slaykovsky.gameobjects.Grass;
import com.slaykovsky.gameobjects.Pipe;

public interface ScrollHandlerInterface {
    int SCROLL_SPEED = -59;
    int PIPE_GAP = 49;

    void update(float delta);
    void stop();
    boolean collides(Bird bird);
    void onRestart();
    Grass getFrontGrass();
    Grass getBackGrass();
    Pipe getPipe1();
    Pipe getPipe2();
    Pipe getPipe3();
}
