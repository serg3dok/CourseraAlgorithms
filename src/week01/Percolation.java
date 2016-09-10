package week01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by sergeyokinchuk on 9/8/16.
 */
public class Percolation {

    private boolean[][] matrix;
    private int[] line;
    private int n;
    private int parentUp;
    private int parentDown;
    private WeightedQuickUnionUF wQUF = null;

    public Percolation(int n)               // create n-by-n grid, with all sites blocked
    {
        this.n = n;
        parentUp = 0;
        parentDown = n * n + 1;
        wQUF = new WeightedQuickUnionUF(n * n + 2);

        matrix = new boolean[n][n];
        //line = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //matrix[i][j] = 0;
                if (j == 0) line[i] = parentUp;
                if (j == n-1) line[i] = parentDown;
            }
        }
    }

    public void open(int i, int j)          // open site (row i, column j) if it is not open already
    {
        if (i < 1 || j < 1 || i > n || j > n ) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }


        if (!isOpen(i, j)) {
            line[convertToOneDimensional(i, j)] = 1;
            matrix[i][j] = true;

            if (isOpen(i-1, j)) {
                wQUF.union(convertToOneDimensional(i, j), convertToOneDimensional(i-1, j));
            }
            else if (isOpen(i, j+1)) {
                wQUF.union(convertToOneDimensional(i, j), convertToOneDimensional(i, j+1));
            }
            else if (isOpen(i+1, j)) {
                wQUF.union(convertToOneDimensional(i, j), convertToOneDimensional(i+1, j));
            }
            else if (isOpen(i, j-1)) {
                wQUF.union(convertToOneDimensional(i, j), convertToOneDimensional(i, j-1));
            }
        }

        if (i == 0) {
            wQUF.union(convertToOneDimensional(i, j), parentUp);
        }
        if (i == n) {
            wQUF.union(convertToOneDimensional(i, j), parentDown);
        }

    }

    public int convertToOneDimensional(int i, int j) {
        if (i < 1 || j < 1 || i > n || j > n ) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return i * n + j;
    }

    public boolean isOpen(int i, int j)     // is site (row i, column j) open?
    {
        if (i < 1 || j < 1 || i > n || j > n ) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }

        return matrix[i][j] == true;
    }

    public boolean isFull(int i, int j)     // is site (row i, column j) full?
    {
        if (i < 1 || j < 1 || i > n || j > n ) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return wQUF.connected(convertToOneDimensional(i, j), 0);

    }

    public boolean percolates()             // does the system percolate?
    {

       return wQUF.connected(0, n * n + 1);
    }


}
