
package org.apache.curator.framework.recipes.locks;

import java.util.concurrent.Executor;

public class RevocationSpec {
    private final Runnable runnable;
    private final Executor executor;

    public RevocationSpec(Executor executor, Runnable runnable) {
        this.runnable = runnable;
        this.executor = executor;
    }

    Runnable getRunnable() {
        return this.runnable;
    }

    Executor getExecutor() {
        return this.executor;
    }
}
