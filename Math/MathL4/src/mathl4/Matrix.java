/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl4;
/**
 *
 * @author Work
 */
public class Matrix {

    /**
    * Создание единичной квадратной матрицы
    * @param size - размер матрицы
    * @return - Возвращает двумерный массив типа double
     */
    public static double[][] createMatrix(int size){
        double[][] res = new double[size][];
        for(int i = 0; i < size; i++){
            res[i] = new double[size];
            for(int j = 0; j < size; j++){
                if(i == j)
                    res[i][j] = 1.;
                else
                    res[i][j] = 0.;
            }
        }
        return res;
    }

    /**
    * Поменять местами строки матрицы
    * @param matrix - матрица
    * @param k - индекс первой строки
    * @param p - индекс второй строки
     */
    public static void swapLines(double[][] matrix, int k, int p) {
        double[] tmp = matrix[k];
        matrix[k] = matrix[p];
        matrix[p] = tmp;
    }

    /**
    * Пеменять местами столбцы матрицы
    * @param matrix - матрица
    * @param k - индекс первой строки
    * @param p - индекс второй строки
     */
    public static void swapColumns(double[][] matrix, int k, int p) {
        for (int i = 0; i < matrix[k].length; i++) {
            matrix[i][k] += matrix[i][p];
            matrix[i][p] = matrix[i][k] - matrix[i][p];
            matrix[i][k] -= matrix[i][p];
        }
    }

    /**
    * Умножение матриц
    * @param matrix1 - первый множитель
    * @param matrix2 - второй множитель
    * @return Двумерный массив с результирующей матрицей
     */
    public static double[][] mulMatrices(double[][] matrix1, double[][] matrix2) throws Exception {
        if (matrix1[0].length != matrix2.length) {
            throw new Exception();
        }
        double[][] res = new double[matrix1.length][];
        for (int i = 0; i < res.length; i++) {
            res[i] = new double[matrix2[0].length];
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

    /**
    * Умножение матрицы на вектор (одномерную матрицу-столбец)
    * @param matrix1 - матирица
    * @param vector - вектор
     */
    public static double[] mulMatrixVector(double[][] matrix1, double[] vector) throws Exception {
        if (matrix1[0].length != vector.length) {
            throw new Exception();
        }
        double[] res = new double[matrix1.length];
        for (int i = 0; i < vector.length; i++) {
            res[i] = 0.;
            for (int k = 0; k < vector.length; k++) {
                res[i] += matrix1[i][k] * vector[k];
            }
        }
        return res;
    }

    /**
    * Поиск индекса максимального элемента в столбце
    * @param matrix - матрица
    * @param i - индекс строки, с которой начинается поиск
    * @param j - индекс столбца
    * @return Возврщает индекс строки с максимальным элементом
     */
    private static int maxPosInColumn(double[][] matrix, int i, int j){
        double max = Math.abs(matrix[i][j]);
        int p = i;
        for(int _i = i + 1; _i < matrix.length; _i++)
            if(Math.abs(matrix[_i][j]) > max){
                max = Math.abs(Math.abs(matrix[_i][j]));
                p = _i;
            }
        return p;
    }

    /**
    * Поиск индекса максимального элемента в строке
    * @param matrix - матрица
    * @param i - индекс строки
    * @param j - индекс столбца, с которого начинается поиск
    * @return Возврщает индекс столбца с максимальным элементом
     */
    private static int maxPosInLine(double[][] matrix, int i, int j){
        double max = Math.abs(matrix[i][j]);
        int p = j;
        for(int _j = j + 1; _j < matrix[i].length; _j++)
            if(Math.abs(matrix[i][j]) > max){
                max = Math.abs(matrix[i][_j]);
                p = _j;
            }
        return p;
    }

    /**
    * Транспонировать матрицу
    * @param matrix - матрица
    * @return Возвращает транспонированную матрицу
     */
    public static double[][] transposition(double[][] matrix){
        double[][] res = new double[matrix.length][];
        for(int i = 0; i < matrix.length; i++){
            res[i] = new double[matrix.length];
            for(int j = 0; j < res[i].length; j++)
                res[i][j] = matrix[j][i];
        }
        return res;
    }

    /**
    * Копировать матрицу
    * @param matrix - матрица
    * @return Возвращает копию исходной матрицы
     */
    public static double[][] copy(double[][] matrix){
        double[][] res = new double[matrix.length][];
        for(int i = 0; i < matrix.length; i++){
            res[i] = new double[matrix[i].length];
            System.arraycopy(matrix[i], 0, res[i], 0, res[i].length);
        }
        return res;
    }

    /**
    * Копировать вектор (одномерная матрица)
    * @param vector - матрица
    * @return Возвращает копию исходного вектора
     */
    public static double[] copy(double[] vector){
        double[] res = new double[vector.length];
        System.arraycopy(vector, 0, res, 0, res.length);
        return res;
    }

    /**
    * Привести матрицу к треугольному виду
    * @param matrix исходная матрица
    * @param col дополнение матрицы
    * @param useMainElement параметр, указывающий нужно ли использовать главный элемент при приобразовании
    * @return Возвращает количество перестановок строк
     */
    public static int toTriangleMatrix(double[][] matrix, double[] col, boolean useMainElement) throws Exception{
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
            if(Double.compare(matrix[k][k], 0) == 0)
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
