package com.jmarkstar.core.domain.interactor;

/**
 * Common interface to every interactor declared in the application. This interface represents a
 * execution unit for different use cases.
 * <p>
 * By convention every interactor implementation will return the result using a Callback. That
 * callback should be executed over the UI thread.
 * <p>
 * Created by Octa
 */
public interface Interactor {

  void run();
}