package me.jtcho.kfib;

import java.math.BigInteger;

/**
 * Demonstration of algorithm for enumeration of binary string with no K-runs of ones.
 *
 * Copyright ©2016 JT Cho.
 * All rights reserved. Contact JT for licensing or use.
 * http://blog.jtcho.me/enumerating-binary-strings-without-k-runs-of-ones
 *
 * @author jtcho
 */
public class BinaryStringsWithoutKRuns {

  /**
   * Count the total number of binary strings of length N such that there are no adjacent ones.
   *
   * You'll observe that the output generated for consecutive values of N produces the Fibonacci sequence.
   * Hence, the output can be simulated in O(log n) time by computing the corresponding Fibonacci number
   * using matrix multiplication.
   *
   * It is also worth noting that the form shown below is analogous to the Fibonacci matrix recurrence.
   * [C_i1 C_i0]' = [ 1 1 ] . [C_{i-1}0 C_{i-1}1]'
   *                [ 0 1 ]
   *
   * @param N the length of the bit string
   * @return the total number of strings satisfying the constraint
   */
  public static int countNumberBinaryStringsNoAdjacentOnes(int N) {
    int c[][] = new int[N+1][2];
    c[1][0] = c[1][1] = 1;

    for (int i = 2; i <= N; i++) {
      c[i][0] = c[i-1][0] + c[i-1][1];
      c[i][1] = c[i-1][0];
    }

    return c[N][0] + c[N][1];
  }

/**
 * Count the total number of binary strings of length N such that there is no run of three consecutive ones
 * in the strings.
 *
 * @param N the length of the binary string
 * @return the total number of strings satisfying the constraint
 */
  public static int countNumberBinaryStringsNo3Run(int N) {

    int c[][] = new int[N+1][3];

    c[1] = new int[] {1, 1, 0};

    for (int i = 2; i <= N; i++) {
      c[i][0] = c[i-1][0] + c[i-1][1] + c[i-1][2];  //??00/??10 - whatever ends in 0's and whatever ends in 1's
      c[i][1] = c[i-1][0];                          //??01 – whatever ends in 0's...
      c[i][2] = c[i-1][1];                          //?011 – whatever ends in 1's and is still valid.
    }

    return c[N][0] + c[N][1] + c[N][2];
  }

  /**
   * Count the total number of binary strings of length N such that there is no run of K consecutive ones
   * in the strings.
   *
   * @param N the length of the binary string
   * @param K the parameter for consecutive one runs to exclude
   * @return the total number of strings satisfying the constraint
   */
  public static int countNumberBinaryStringsNoKRun(int N, int K) {
    int c[][] = new int[N+1][K];

    // Initial conditions.
    c[1][0] = 1;
    c[1][1] = 1;

    for (int i = 2; i <= N; i++) {
      c[i][0] = sum(c, i-1, 0);
      for (int j = 1; j < K; j++) {
        c[i][j] = c[i-1][j-1];
      }
    }

    return sum(c, N, 0);
  }

  /**
   * Computes the sum of matrix row entries starting at a particular column offset.
   *
   * @param c the matrix
   * @param x the row index
   * @param yStart the column index to start
   * @return the sum
   */
  private static int sum(int c[][], int x, int yStart) {
    int total = 0;
    for (int i = yStart; i < c[0].length; i++) {
      total += c[x][i];
    }
    return total;
  }

  /**
   * Iterative method for computing a particular element from the r-Fibonacci sequence.
   *
   * @param n the element number to get from the sequence
   * @param r the r parameter
   * @return the nth element of the r-Fibonacci sequence
   */
  public static int rFib(int n, int r) {
    if (r > n) {
      return 0;
    }

    int c[] = new int[n+1];
    c[r-1] = 1;
    for (int i = r; i <= n; i++) {
      for (int j = 1; j <= r; j++) {
        c[i] += c[i-j];
      }
    }

    return c[n];
  }

  public static void main(String[] args) throws Exception {
    KFibonacciGenerator generator;
    int i;
    int K = 3;
    System.out.format("\nNumber of Binary Strings of Length N With No Runs of Length %d:\n", K);
    generator = new KFibonacciGenerator(K, K, 10);
    i = 1;
    for (BigInteger value : generator) {
      System.out.format("N=%d\t %d\n", i, countNumberBinaryStringsNoKRun(i, K));
      System.out.format("%d-Fib(n=%d): %d\n", K, i, value);
      i++;
      Thread.sleep(250);
    }
  }

}
