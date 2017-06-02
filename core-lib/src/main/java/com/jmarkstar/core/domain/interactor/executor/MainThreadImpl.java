package com.jmarkstar.core.domain.interactor.executor;

import android.os.Handler;
import android.os.Looper;
import javax.inject.Inject;

public class MainThreadImpl implements MainThread {

  private Handler mHandler;

  @Inject
  public MainThreadImpl() {
    this.mHandler = new Handler(Looper.getMainLooper());
  }

  @Override
  public void post(Runnable runnable) {
    mHandler.post(runnable);
  }
}