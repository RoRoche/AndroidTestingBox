package com.guddy.android_testing_box.bdd;

public abstract class AbstractBddCanvas {
    protected final Block block;

    protected AbstractBddCanvas(final Block poBlock) {
        block = poBlock;
    }

    public void run() throws Exception {
        block.run();
    }
}
