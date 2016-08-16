package me.jtcho.kfib;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Defines a Generator template class that behaves similarly to
 * Python generators.
 *
 * @author mherrmann, jtcho
 */
public abstract class Generator<T> implements Iterable<T> {

  private static ThreadGroup THREAD_GROUP;

  Thread producerThread;
  private boolean hasFinished;
  private final Condition itemAvailableOrHasFinished = new Condition();
  private final Condition itemRequested = new Condition();
  private T nextItem;
  private boolean nextItemAvailable;
  private RuntimeException exceptionRaisedByProducer;

  /**
   * Gets an iterator for the generator.
   *
   * @return the iterator object
   */
  @Override
  public Iterator<T> iterator() {

    return new Iterator<T>() {

      @Override
      public boolean hasNext() {
        return waitForNext();
      }

      @Override
      public T next() {
        if (! waitForNext())
          throw new NoSuchElementException();
        nextItemAvailable = false;
        return nextItem;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

      /**
       * Causes this iterator to wait for the next item in the generator.
       *
       * @return whether there is a next item or not
       */
      private boolean waitForNext() {
        if (nextItemAvailable)
          return true;
        if (hasFinished)
          return false;
        if (producerThread == null)
          startProducer();
        itemRequested.set();
        try {
          itemAvailableOrHasFinished.await();
        } catch (InterruptedException e) {
          hasFinished = true;
        }

        if (exceptionRaisedByProducer != null)
          throw exceptionRaisedByProducer;

        return ! hasFinished;
      }
    };

  }

  /**
   * Abstract method to be run by the Generator daemon.
   *
   * @throws InterruptedException if method interrupted
   */
  protected abstract void run() throws InterruptedException;

  /**
   * Yield the next item to be iterated by the Generator.
   * Invoke this method in the implementation of {@link #run() run()}.
   *
   * @param element the element to be yielded
   * @throws InterruptedException if execution was interrupted
   */
  protected void yield(T element) throws InterruptedException {
    nextItem = element;
    nextItemAvailable = true;
    itemAvailableOrHasFinished.set();
    itemRequested.await();
  }

  /**
   * Initializes the producer thread for the Generator.
   */
  private void startProducer() {
    if (producerThread != null) {
      throw new IllegalStateException("Producer thread was not null at time of start.");
    }

    if (THREAD_GROUP == null) {
      THREAD_GROUP = new ThreadGroup("generator-operations");
    }

    producerThread = new Thread(THREAD_GROUP, () -> {
      try {
        itemRequested.await();
        Generator.this.run();
      } catch (InterruptedException e) {
        // Do nothing. Let everything else clean up.
      } catch (RuntimeException e) {
        exceptionRaisedByProducer = e;
      }
      hasFinished = true;
      itemAvailableOrHasFinished.set();
    });

    producerThread.setDaemon(true);
    producerThread.start();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void finalize() throws Throwable {
    producerThread.interrupt();
    producerThread.join();
    super.finalize();
  }

  /**
   * Describes a condition that operates functionally like a lock.
   *
   * @author jocho
   */
  private class Condition {

    private boolean isSet;

    /**
     * Marks the condition as set. Notifies a monitor watching
     * this object and awakens it.
     */
    public synchronized void set() {
      isSet = true;
      notify();
    }

    /**
     * Waits until this condition is set. When evaluation is complete,
     * resets the state of the condition.
     *
     * @throws InterruptedException
     */
    public synchronized void await() throws InterruptedException {
      try {
        if (! isSet) {
          wait();
        }
      } finally {
        isSet = false;
      }
    }

  }

}
