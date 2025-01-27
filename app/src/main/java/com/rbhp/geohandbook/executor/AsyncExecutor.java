package com.rbhp.geohandbook.executor;

import java.util.concurrent.Executor;

public class AsyncExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
