package com.jmarkstar.core.domain.interactor.executor;

import com.jmarkstar.core.domain.interactor.Interactor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import javax.inject.Inject;

/**
 * Executor implementation based on {@link ThreadPoolExecutor}. ThreadPoolExecutorConfig:
 * <p>
 * Core pool size: 10.
 * Max pool size: 15.
 * Keep alive time: 120.
 * Time unit: seconds.
 * Work queue: {@link LinkedBlockingQueue}.
 * <p>
 */
public class ThreadExecutor implements Executor {

  private ThreadPoolExecutor mThreadPoolExecutor;

  @Inject public ThreadExecutor(ThreadPoolExecutor threadPoolExecutor) {
    this.mThreadPoolExecutor = threadPoolExecutor;
  }

  @Override
  public void execute(final Interactor interactor) {
    if (null == interactor) {
      throw new IllegalArgumentException("Interactor to execute can't be null");
    }
    mThreadPoolExecutor.submit(new Runnable() {
      @Override public void run() {
        interactor.run();
      }
    });
  }
}