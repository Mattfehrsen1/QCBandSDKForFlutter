package com.baidu.bdhttpdns;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class m {
    private static volatile m a;
    private final Executor b = new ThreadPoolExecutor(5, 25, 20, TimeUnit.SECONDS, new LinkedBlockingDeque(50));

    private m() {
    }

    static m a() {
        if (a == null) {
            synchronized (m.class) {
                if (a == null) {
                    a = new m();
                }
            }
        }
        return a;
    }

    Executor b() {
        return this.b;
    }
}
