/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl13;
/**
 *
 * @author Work
 */
public class Matrix {
    public static void mulLine(Double[][] matrix, int i, Double c) {
        for (int j = 0; j < matrix[i].length; j++) {
            matrix[i][j] *= c;
        }
    }
    
    public static void mulLine(Double[] line, Double c) {
        for (int i = 0; i < line.length; i++) {
            line[i] *= c;
        }
    }

    public static void subLines(Double[] line1, Double[] line2) {
        for (int i = 0; i < line1.length; i++) {
            line1[i] -= line2[i];
        }
    }

    public static void addLines(Double[][] matrix, int k, int p) {
        for (int i = 0; i < matrix[k].length; i++) {
            matrix[k][i] += matrix[p][i];
        }
    }
    
    public static void addLines(Double[] line1, Double[] line2) {
        for (int i = 0; i < line1.length; i++) {
            line1[i] += line2[i];
        }
    }

    public static void swapLines(Double[][] matrix, int k, int p) {
        Double[] tmp = matrix[k];
        matrix[k] = matrix[p];
        matrix[p] = tmp;
    }

    public static void swapColumns(Double[][] matrix, int k, int p) {
        for (int i = 0; i < matrix[k].length; i++) {
            matrix[i][k] += matrix[i][p];
            matrix[i][p] = matrix[i][k] - matrix[i][p];
            matrix[i][k] -= matrix[i][p];
        }
    }

    public static Double[][] mulMatrices(Double[][] matrix1, Double[][] matrix2) throws Exception {
        if (matrix1[0].length != matrix2.length) {
            throw new Exception();
        }
        Double[][] res = new Double[matrix1.length][];
        for (int i = 0; i < res.length; i++) {
            res[i] = new Double[matrix2[0].length];
        }
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++){
                res[i][j] = 0.;
                for (int k = 0; k < matrix2.length; k++) {
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }
    
    private static int maxPosInColumn(Double[][] matrix, int i, int j){
        Double max = Math.abs(matrix[i][j]);
        int p = i;
        for(int _i = i + 1; _i < matrix.length; _i++)
            if(Math.abs(matrix[_i][j]) > max){
                max = Math.abs(Math.abs(matrix[_i][j]));
                p = _i;
            }
        return p;
    }
    
    private static int maxPosInLine(Double[][] matrix, int i, int j){
        Double max = Math.abs(matrix[i][j]);
        int p = j;
        for(int _j = j + 1; _j < matrix[i].length; _j++)
            if(Math.abs(matrix[i][j]) > max){
                max = Math.abs(matrix[i][_j]);
                p = _j;
            }
        return p;
    }
    
    public static void transposition(Double[][] matrix){
        for(int i = 0; i < matrix.length; i++)
            for(int j = i; j < matrix[i].length; j++){
                matrix[j][i] += matrix[i][j];
                matrix[i][j] = matrix[j][i] - matrix[i][j];
                matrix[j][i] -= matrix[i][j];
            }
    }
    
    public static Double[][] copy(Double[][] matrix){
        Double[][] res = new Double[matrix.length][];
        for(int i = 0; i < matrix.length; i++){
            res[i] = new Double[matrix[i].length];
            System.arraycopy(matrix[i], 0, res[i], 0, res[i].length);
        }
        return res;
    }
    
    public static Double[] copy(Double[] vector){
        Double[] res = new Double[vector.length];
        System.arraycopy(vector, 0, res, 0, res.length);
        return res;
    }
    
    public static int toTriangleMatrix(Double[][] matrix, Double[] col, boolean useMainElement) throws Exception{
        int swaps = 0;
        for(int k = 0; k < matrix.length - 1; k++){
            if(useMainElement){
                int p = maxPosInColumn(matrix, k, k);
                if(p!=k){
                    swaps++;
                    swapLines(matrix, k, p);
                    if(col != null){
                        col[p] += col[k];
                        col[k] = col[p] - col[k];
                        col[p] -= col[k];
                    }
                }
            }
            if(Double.compare(matrix[k][k], 0.) == 0)
                throw new Exception("Матрица вырожденная");
            for(int i = k + 1; i < matrix.length; i++){
                if(col != null)
                    col[i] -= matrix[i][k]/matrix[k][k]*col[k];
                for(int j = matrix[i].length-1; j >= k; j--){
                    matrix[i][j] -= (matrix[i][k]/matrix[k][k])*matrix[k][j];
                }
            }
        }
        if(Double.compare(matrix[matrix.length-1][matrix.length-1], 0.) == 0)
                throw new Exception("Матрица вырожденная");
        return swaps;
    }
}
