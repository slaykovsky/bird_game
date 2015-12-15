package com.slaykovsky.bird;

import com.badlogic.gdx.Game;
import com.slaykovsky.birdhelpers.AssetLoader;
import com.slaykovsky.screen.GameScreen;

public class BirdGame extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
