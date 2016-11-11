package com.guddy.android_testing_box;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;

public class Sum {
    public final int a;
    public final int b;
    private final LazyInitializer<Integer> mSum;

    public Sum(int a, int b) {
        this.a = a;
        this.b = b;
        mSum = new LazyInitializer<Integer>() {
            @Override
            protected Integer initialize() throws ConcurrentException {
                return Sum.this.a + Sum.this.b;
            }
        };
    }

    public int getSum() throws ConcurrentException {
        return mSum.get();
    }
}
