package mathl4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    double[][] matrix = {
            {1,2,-1},
            {1,0,3},
            {7,8,4}
    };
    double[][] matrix2 = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
    };
    double[] vector = {0,0,0};

    @Test
    public void testSwapLines() throws Exception {
        double[][] trueResult = {
                {1,0,3},
                {7,8,4},
                {1,2,-1}
        },
                arg = Matrix.copy(matrix);
        Matrix.swapLines(arg,0,2);
        Matrix.swapLines(arg,1,0);
        assertArrayEquals(trueResult,arg);
    }

    @Test
    public void testSwapColumns() throws Exception {
        double[][] trueResult = {
                {2,-1,1},
                {0,3,1},
                {8,4,7}
        },
                arg = Matrix.copy(matrix);
        Matrix.swapColumns(arg,0,2);
        Matrix.swapColumns(arg,1,0);
        assertArrayEquals(trueResult,arg);
    }

    @Test
    public void testMulMatrices() throws Exception {
        double[][] trueResult = {
                {2,4,6},
                {22,26,30},
                {67,86,105}
        };
        assertArrayEquals(trueResult, Matrix.mulMatrices(matrix,matrix2));
    }

    @Test
    public void testMulMatrixVector() throws Exception {
        double[] trueResult = {0,0,0};
        assertArrayEquals(trueResult,Matrix.mulMatrixVector(matrix,vector), 0.0001);
    }

    @Test
    public void testTransposition() throws Exception {
        double[][] trueResult = {
                {1,1,7},
                {2,0,8},
                {-1,3,4}
        };
        assertArrayEquals(trueResult, Matrix.transposition(matrix));
    }

    @Test
    public void testCopyMatrix() throws Exception {
        assertArrayEquals(matrix, Matrix.copy(matrix));
    }

    @Test
    public void testCopyVector() throws Exception {
        assertArrayEquals(vector,Matrix.copy(vector),0.0001);
    }

    @Test
    public void testToTriangleMatrixWithoutMainElement() throws Exception {
        double[][] trueResult = {
                {1,2,-1},
                {0,-2,4},
                {0,0,-1}
        },
                arg = Matrix.copy(matrix);
        Matrix.toTriangleMatrix(arg, vector, false);
        assertArrayEquals(trueResult, arg);
    }

    @Test
    public void testToTriangleMatrixWithMainElement() throws Exception {
        double[][] trueResult = {
                {7,8,4},
                {0,-2,4},
                {0,0,-1}
        },
                arg = Matrix.copy(matrix);
        Matrix.toTriangleMatrix(arg, vector, true);
        assertArrayEquals(trueResult, arg);
        /*
        {1,2,-1},
        {1,0,3},
        {7,8,4}
        */
    }

    @Before
    public void setUp() throws Exception {
    }
}