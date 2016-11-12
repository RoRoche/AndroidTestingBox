package com.guddy.android_testing_box.bdd;

public class When extends AbstractBddCanvas {
    public When(final Block poBlock) {
        super(poBlock);
    }

    public static void when(final String psDescription, final When poWhen) throws Exception {
        poWhen.run();
    }
}
