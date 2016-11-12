package com.guddy.android_testing_box.bdd;

public class Given extends AbstractBddCanvas {

    public Given(final Block poBlock) {
        super(poBlock);
    }

    public static void given(final String psDescription, final Given poGiven) throws Exception {
        poGiven.run();
    }
}
