package me.jtcho.kfib;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates elements of the K-Fibonacci sequence ad infinitum.
 *
 * Copyright ©2016 JT Cho.
 * All rights reserved. Contact JT for licensing or use.
 * http://blog.jtcho.me/enumerating-binary-strings-without-k-runs-of-ones
 *
 * The K-Fibonacci sequence is defined as follows:
 * U_n = U_n-1 + U_n-2 + ... + U_n-K
 *
 * @author jtcho
 */
public class KFibonacciGenerator extends Generator<BigInteger> {

  private static int GENERATOR_LIMIT;
  private int numSeen;
  private List<BigInteger> lastRInSeq;

  public KFibonacciGenerator(int K) {
    this(K, 0, -1);
  }

  public KFibonacciGenerator(int K, int offset) {
    this(K, offset, -1);
  }

  /**
   * Initializes the generator.
   *
   * @param K the K parameter for the generator 
   * @param offset the offset in the sequence at which to start generating elements 
   * @param limit  the total number of elements to generate or -1 for infinite generation
   */
  public KFibonacciGenerator(int K, int offset, int limit) {
    lastRInSeq = new ArrayList<>(K);
    for (int i = 0; i < K-1; i++)
      lastRInSeq.add(BigInteger.ZERO);
    lastRInSeq.add(BigInteger.ONE);

    numSeen = 0;

    if (offset < 0) {
      throw new IllegalArgumentException("Given offset " + offset + " must be greater than 0.");
    }

    for (int i = 0; i < offset+1; i++) {
      getNextInSequence();
    }

    GENERATOR_LIMIT = limit+offset+1;
  }

  private BigInteger getNextInSequence() {
    lastRInSeq.add(lastRInSeq.stream().reduce(BigInteger.ZERO, BigInteger::add));
    numSeen++;
    return lastRInSeq.remove(0);
  }

  /**
   * Generates the elements of the r-Fibonacci sequence.
   * {@inheritDoc}
  */
  @SuppressWarnings({"InfiniteLoopStatement"})
  @Override
  protected void run() throws InterruptedException {
    if (GENERATOR_LIMIT == -1) {
      while(true) {
        yield(getNextInSequence());
      }
    }
    else {
      while (numSeen < GENERATOR_LIMIT) {
        yield(getNextInSequence());
      }
    }
  }
}
