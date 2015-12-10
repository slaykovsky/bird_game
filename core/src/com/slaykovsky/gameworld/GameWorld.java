package com.slaykovsky.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
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
    private Rectangle ground;

    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);
        this.scrollHandler = new ScrollHandler(midPointY + 66);
        this.ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
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
        }
    }

    public Bird getBird() {
        return this.bird;
    }

    public ScrollHandler getScrollHandler() {
        return this.scrollHandler;
    }
}
