package com.guddy.android_testing_box.bdd;

public final class BddCanvas {

    //region Private constructor
    private BddCanvas() {
    }
    //endregion

    //region Static methods
    public static void given(final String psDescription, final Block poGiven) throws Exception {
        poGiven.run();
    }

    public static void when(final String psDescription, final Block poWhen) throws Exception {
        poWhen.run();
    }

    public static void then(final String psDescription, final Block poThen) throws Exception {
        poThen.run();
    }
    //endregion

    //region Runnable interface
    public interface Block {
        void run() throws Exception;
    }
    //endregion
}
