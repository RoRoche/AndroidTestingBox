package com.guddy.android_testing_box.bdd;

public class Then extends AbstractBddCanvas {
    public Then(final Block poBlock) {
        super(poBlock);
    }

    public static void then(final String psDescription, final Then poThen) throws Exception {
        poThen.run();
    }
}
