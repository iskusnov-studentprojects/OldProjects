package operations;

import graphicalobjects.Point3D;

/**
 * Created by Sergey on 21.09.2016.
 */
public class AffineTranformation {
    private static double[] matrixMultiply(double[] m1, double[][] m2){
        int n = m1.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++)
                result[i] += m1[j] * m2[j][i];
        }
        return result;
    }

    public static void rotationByX(Point3D point3D, double angle){
        double[] pointMatrix = {point3D.getX(), point3D.getY(), point3D.getZ(), point3D.getH()}; // Формируем матрицу на основе исходного объекта
        double[][] transformMatrix = { // Формируем матрицу преобразования0
                        {1, 0, 0, 0},
                        {0, Math.cos(angle), Math.sin(angle), 0},
                        {0, -Math.sin(angle), Math.cos(angle), 0},
                        {0, 0, 0, 1}
                };
        double[] res = matrixMultiply(pointMatrix, transformMatrix);
        point3D.setCoordinate(res[0],res[1],res[2]);
        point3D.setH(res[3]);
    }

    public static void rotationByY(Point3D point3D, double angle){
        double[] pointMatrix = {point3D.getX(), point3D.getY(), point3D.getZ(), point3D.getH()}; // Формируем матрицу на основе исходного объекта
        double[][] transformMatrix = { // Формируем матрицу преобразования
                        {Math.cos(angle), 0, Math.sin(angle), 0},
                        {0, 1, 0, 0},
                        {-Math.sin(angle), 0, Math.cos(angle), 0},
                        {0, 0, 0, 1}
                };
        double[] res = matrixMultiply(pointMatrix, transformMatrix);
        point3D.setCoordinate(res[0],res[1],res[2]);
        point3D.setH(res[3]);
    }

    public static void rotationByZ(Point3D point3D, double angle){
        double[] pointMatrix = {point3D.getX(), point3D.getY(), point3D.getZ(), point3D.getH()}; // Формируем матрицу на основе исходного объекта
        double[][] transformMatrix = { // Формируем матрицу преобразования
                        {Math.cos(angle), Math.sin(angle), 0, 0},
                        {-Math.sin(angle), Math.cos(angle), 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                };
        double[]res = matrixMultiply(pointMatrix, transformMatrix);
        point3D.setCoordinate(res[0],res[1],res[2]);
        point3D.setH(res[3]);
    }

    public static void zoom(Point3D point3D, double byX, double byY, double byZ){
        double[] pointMatrix = {point3D.getX(), point3D.getY(), point3D.getZ(), point3D.getH()}; // Формируем матрицу на основе исходного объекта
        double[][] transformMatrix = { // Формируем матрицу преобразования
                        {byX, 0, 0, 0},
                        {0, byY, 0, 0},
                        {0, 0, byZ, 0},
                        {0, 0, 0, 1}
                };
        double[] res = matrixMultiply(pointMatrix, transformMatrix);
        point3D.setCoordinate(res[0],res[1],res[2]);
        point3D.setH(res[3]);
    }

    public static void reflection(Point3D point3D, boolean byYZ, boolean byXZ, boolean byXY){
        double[] pointMatrix = {point3D.getX(), point3D.getY(), point3D.getZ(), point3D.getH()}; // Формируем матрицу на основе исходного объекта
        double[][] transformMatrix = { // Формируем матрицу преобразования
                        {byYZ?-1:1, 0, 0, 0},
                        {0, byXZ?-1:1, 0, 0},
                        {0, 0, byXY?-1:1, 0},
                        {0, 0, 0, 1}
                };
        // Умножаем матрицы
        double[] res = matrixMultiply(pointMatrix, transformMatrix);
        point3D.setCoordinate(res[0],res[1],res[2]);
        point3D.setH(res[3]);
    }

    public static void movement(Point3D point3D, double byX, double byY, double byZ){
        double[] pointMatrix = {point3D.getX(), point3D.getY(), point3D.getZ(), point3D.getH()}; // Формируем матрицу на основе исходного объекта
        double[][] transformMatrix = { // Формируем матрицу преобразования
                        {1, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 1, 0},
                        {byX, byY, byZ, 1}
                };
        double[] res = matrixMultiply(pointMatrix, transformMatrix);
        point3D.setCoordinate(res[0],res[1],res[2]);
        point3D.setH(res[3]);
    }
}
