package com.jmarkstar.core.domain.interactor.executor;

import com.jmarkstar.core.domain.interactor.Action;

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
  public void execute(final Action interactor) {
    if (null == interactor) {
      throw new IllegalArgumentException("Action to execute can't be null");
    }
    mThreadPoolExecutor.submit(new Runnable() {
      @Override public void run() {
        interactor.run();
      }
    });
  }
}