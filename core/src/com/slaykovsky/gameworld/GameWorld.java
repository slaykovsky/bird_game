package com.slaykovsky.gameworld;

import com.slaykovsky.com.slaykovsky.birdhelpers.AssetLoader;
import com.slaykovsky.com.slaykovsky.birdhelpers.ScrollHandler;
import com.slaykovsky.gameobjects.Bird;
import com.slaykovsky.gameobjects.Grass;
import com.slaykovsky.gameobjects.Pipe;

/**
 * Created by slaykale on 09/12/15.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scrollHandler;
    private boolean isAlive = true;


    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);
        this.scrollHandler = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        this.bird.update(delta);
        this.scrollHandler.update(delta);

        if (this.scrollHandler.collides(bird)) {
            this.scrollHandler.stop();
            AssetLoader.dead.play();
            this.isAlive = false;
        }
    }

    public Bird getBird() {
        return this.bird;
    }

    public ScrollHandler getScrollHandler() {
        return this.scrollHandler;
    }
}
