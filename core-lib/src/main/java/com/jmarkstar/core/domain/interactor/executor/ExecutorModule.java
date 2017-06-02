package com.jmarkstar.core.domain.interactor.executor;

import com.jmarkstar.core.util.Constants;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 2/06/2017.
 */
@Module
public final class ExecutorModule {

    @Provides
    @Singleton
    BlockingQueue<Runnable> provideBlockingQueue(){
        return new LinkedBlockingQueue<Runnable>();
    }

    @Provides
    @Singleton
    ThreadPoolExecutor provideThreadPoolExecutor(BlockingQueue<Runnable> workQueue){
        int corePoolSize = Constants.CORE_POOL_SIZE;
        int maxPoolSize = Constants.MAX_POOL_SIZE;
        int keepAliveTime = Constants.KEEP_ALIVE_TIME;
        TimeUnit timeUnit = Constants.TIME_UNIT;
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
    }

    @Provides
    @Singleton
    Executor provideExecutor(ThreadPoolExecutor threadPoolExecutor) {
        return new ThreadExecutor(threadPoolExecutor);
    }

    @Provides
    @Singleton
    MainThread provideMainThread() {
        return new MainThreadImpl();
    }
}
