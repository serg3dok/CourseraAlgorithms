package week01;

/**
 * Created by sergeyokinchuk on 9/8/16.
 */
public class Percolation {

    int[][] matrix;
    int[] line;
    int n;

    public Percolation(int n)               // create n-by-n grid, with all sites blocked
    {
        this.n = n;
        int parentUp = 0;
        int parentDown = n * n;

        matrix = new int[n][n];
        line = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
                if (j == 0) line[i] = parentUp;
                if (j == n-1) line[i] = parentDown;
            }
        }
    }

    public void open(int i, int j)          // open site (row i, column j) if it is not open already
    {
        matrix[i][j] = 1;
        line[i * n + j] = ;
    }

    public boolean isOpen(int i, int j)     // is site (row i, column j) open?
    {

        return matrix[i][j] == 1;
    }

    public boolean isFull(int i, int j)     // is site (row i, column j) full?
    {

        return true;
    }

    public boolean percolates()             // does the system percolate?
    {

       return true;
    }

    public static void main(String[] args)  // test client (optional)
    {

    }
}
