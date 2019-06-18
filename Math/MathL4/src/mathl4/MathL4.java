/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class MathL4 {

    static double eps;
    static int limIter = 0;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        Double[][] A = null;
        Double lambda0;
        pair<Double[][], Double> p = readFromFile("D:\\input.dat");
        A = p.a;
        lambda0 = p.b;
        pair<Double, Double[]> p1 = null, p1T = null, p2 = null, pn = null;
        p1 = countLambda1(A);
        p1T = countLambda1(transpose(A));
        if(p1 != null)
            p2 = countLambda2(A, p1.b, p1T.b);
        //pn = countNearestLambda(A, lambda0);
        writeToFile(A, p1, p2, pn, "d:\\output.dat");
    }

    private static Double[] countX(Double[][] A, Double[] oldX){
        Double[] X = new Double[oldX.length];
        for(int i = 0; i < X.length; i++)
            X[i] = 0.;
        for(int i = 0; i < A.length; i++)
            for(int j = 0; j < A[i].length; j++)
                X[i]+=A[i][j]*oldX[j];
        return X;
    }
    
    private static Double countX(Double[] X, Double[] line, Double b){
        Double summ = 0.;
        for(int i = 0; i < line.length; i++)
            summ += line[i]*X[i];
        return b + summ;
    }
    
    private static Double scalarProduct(Double[] vector1, Double[] vector2){
        Double res = 0.;
        for(int i = 0; i < vector1.length; i++)
            res+=vector1[i]*vector2[i];
        return res;
    }
    
    private static Double normOfVector(Double[] vector){
        return Math.sqrt(scalarProduct(vector, vector));
    }
    
    public static pair<Double, Double[]> countLambda1(Double[][] A) throws Exception{
        Double[] oldX = new Double[A.length],
                newX;
        Double oldLambda,
                newLambda;
        int numIter = 0,
                allIter = 0;
        initiationX(oldX);
        newX = countX(A, oldX);
        newLambda = scalarProduct(newX, oldX)/scalarProduct(oldX, oldX);
        do{
            if(numIter > limIter){
                div(newX, normOfVector(newX));
                numIter = 0;
            }
            oldX = newX;
            newX = countX(A, oldX);
            oldLambda = newLambda;
            newLambda = scalarProduct(newX, oldX)/scalarProduct(oldX, oldX);
            numIter++;
            allIter++;
            if(allIter > Integer.MAX_VALUE/100)
                return null;
        }while(Math.abs(newLambda - oldLambda) > eps);
        div(newX, normOfVector(newX));
        return new pair<>(newLambda, newX);
    }
    
    public static pair<Double, Double[]> countLambda2(Double[][] A, Double[] e1, Double[] g1) throws Exception{
        Double[] X = new Double[A.length],
                oldY = new Double[A.length],
                newY;
        Double oldLambda,
                newLambda;
        int numIter = 0,
                allIter = 0;
        initiationX(X);
        for(int i = 0; i < oldY.length; i++)
            oldY[i] = X[i] - scalarProduct(X, g1)/scalarProduct(e1,g1)*e1[i];
        newY = countX(A, oldY);
        newLambda = scalarProduct(newY, oldY)/scalarProduct(oldY, oldY);
        do{
            if(numIter > limIter){
                for(int i = 0; i < oldY.length; i++)
                    newY[i] = newY[i] - scalarProduct(newY, g1)/scalarProduct(e1,g1)*e1[i];
                div(newY, normOfVector(newY));
                numIter = 0;
            }
            oldY = newY;
            newY = countX(A, oldY);
            oldLambda = newLambda;
            newLambda = scalarProduct(newY, oldY)/scalarProduct(oldY, oldY);
            numIter++;
            allIter++;
            if(allIter > Integer.MAX_VALUE/100)
                return null;
        }while(Math.abs(newLambda - oldLambda) > eps);
        for(int i = 0; i < oldY.length; i++)
            newY[i] = newY[i] - scalarProduct(newY, g1)/scalarProduct(e1,g1)*e1[i];
        div(newY, normOfVector(newY));
        return new pair<>(newLambda, newY);
    }
    
    public static pair<Double, Double[]> countNearestLambda(Double[][] A, Double lambda0) throws Exception{
        Double[] oldX = new Double[A.length],
                newX;
        Double oldLambda,
                newLambda;
        int allIter = 0;
        initiationX(oldX);
        pair<Double[][], Double[]> p = transformSLAU(transformMatrix(A, lambda0), null/*Matrix.copy(oldX)*/);
        newX = solveSLAU(p.a, p.b);
        //newX = solveSLAU(transformMatrix(A, lambda0), Matrix.copy(oldX));
        newLambda = lambda0 + scalarProduct(oldX, oldX)/scalarProduct(newX, oldX);
        do{
            oldX = newX;
            oldLambda = newLambda;
            p = transformSLAU(transformMatrix(A, lambda0), null/*Matrix.copy(oldX)*/);
            newX = solveSLAU(p.a, p.b);
            //newX = solveSLAU(transformMatrix(A, oldLambda), Matrix.copy(oldX));
            newLambda = newLambda + scalarProduct(oldX, oldX)/scalarProduct(newX, oldX);
            allIter++;
            if(allIter > Integer.MAX_VALUE/100)
                return null;
        }while(Math.abs(newLambda - oldLambda) > eps);
        div(newX, normOfVector(newX));
        return new pair<>(newLambda, newX);
    }
    
    private static void initiationX(Double[] X){
        for(int i = 0; i < X.length; i++)
            X[i] = 1.;
    }
    
    private static Double[][] transpose(Double[][] A){
        Double[][] At = new Double[A.length][];
        for(int i = 0; i < A.length; i++){
            At[i] = new Double[A[i].length];
            for(int j = 0; j < A[i].length; j++)
                At[i][j] = A[j][i];
        }
        return At;
    }
    
    private static void div(Double[] vector, Double value){
        for(int i = 0; i < vector.length; i++)
            vector[i]/=value;
    }
    
    private static Double[][] transformMatrix(Double[][] A, Double lambda0){
        Double[][] res = new Double[A.length][];
        for(int i = 0; i < res.length; i++){
            res[i] = new Double[A[i].length];
            for(int j = 0; j < res[i].length; j++){
                res[i][j] = A[i][j];
                if(j == i)
                    res[i][j] -= lambda0;
            }
        }
        return res;
    }
    
    /*private static Double countX(Double[] line, Double[] X, int k, Double b){
        Double x = 0.;
        for(int i = k+1; i < line.length; i++)
            x += line[i]*X[i];
        x=(b-x)/line[k];
        return x;
    }
    
    public static Double[] solveSLAU(Double[][] A, Double[] B) throws IOException, Exception{
        Matrix.toTriangleMatrix(A, B, true);
        Double[] X = new Double[B.length];
        for(int i = A.length - 1; i >=0; i--)
            X[i] = countX(A[i], X, i, B[i]);
        return X;
    }*/
    
    private static Double[] solveSLAU(Double[][] B, Double[] C) throws Exception{
        int iterBySeidel = 0;
        Double[] X = new Double[C.length],
                oldX = new Double[C.length];
        initiationX(X);
        do{
            System.arraycopy(X, 0, oldX, 0, X.length);
            for(int j = 0; j < X.length; j++)
                X[j] = countX(X, B[j], C[j]);
            iterBySeidel++;
            if(iterBySeidel > Integer.MAX_VALUE/100)
                throw new Exception("Метод Зеделя не сходится");
        }while(!convergence(X, oldX));
        return X;
    }
    
    private static pair transformSLAU(Double[][] A, Double[] B) throws Exception{
        Double[][] nA = new Double[A.length][];
        Double[] nB = new Double[B.length];
        for(int i = 0; i < A.length; i++){
            if(A[i][i].equals(0.))
                throw new Exception("На диагонали нуль");
            nA[i] = new Double[A[i].length];
            nB[i] = B[i]/A[i][i];
            for(int j = 0; j < A[i].length; j++){
                if(i == j)
                    nA[i][j] = 0.;
                else
                    nA[i][j] = -A[i][j]/A[i][i];
            }
        }
        return new pair<>(nA, nB);
    }
    
    private static boolean convergence(Double[] oldX, Double[] newX){
        Double[] V = new Double[oldX.length];
        for(int i = 0; i < V.length; i++)
            V[i] = newX[i]-oldX[i];
        return normOfVector(V) < eps;
    }
    
    private static pair readFromFile(String path) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        ArrayList<Double[]> A = new ArrayList<>();
        Double Lambda0 = 0.;
        String str;
        boolean b = true;
        while((str = file.readLine()) != null){
            if("#".equals(str)){
                b = false;
                continue;
            }
            if("%".equals(str)){
                str = file.readLine();
                eps = Double.valueOf(str);
                continue;
            }
            if(b){
                String[] strs = str.split(" ");
                A.add(new Double[strs.length]);
                for(int i = 0; i < strs.length; i++)
                    A.get(A.size() - 1)[i] = Double.valueOf(strs[i]);;
            }
            else
                Lambda0 = Double.valueOf(str);
        }
        pair<Double[][], Double> res = new pair<>();
        res.a = A.toArray(new Double[A.size()][]);
        res.b = Lambda0;
        file.close();
        return res;
    }

    static void writeToFile(Double[][] A, pair<Double, Double[]> eigenvalue1, pair<Double, Double[]> eigenvalue2, pair<Double, Double[]> eigenvaluen, String path) throws IOException, Exception{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        if(eigenvalue1 != null){
            text+="Максимальное по модулю собственное число: ";
            text+=eigenvalue1.a.toString() + "\n";
            text+="Собственный вектор:\n";
            for(int i = 0; i < eigenvalue1.b.length; i++)
                text+=eigenvalue1.b[i].toString() + " ";
            text+="\nНевязка: " + countDiscrepancy(A, eigenvalue1.a, eigenvalue1.b).toString() + "\n";
        }
        else
            text+="Невозможно вычислить максимальное по модулю собственное число\n";
        
        text+="\n";
        if(eigenvalue2 != null){
            text+="Второе максимальное по модулю собственное число: ";
            text+=eigenvalue2.a.toString() + "\n";
            text+="Собственный вектор:\n";
            for(int i = 0; i < eigenvalue1.b.length; i++)
                text+=eigenvalue2.b[i].toString() + " ";
            text+="\nНевязка: " + countDiscrepancy(A, eigenvalue2.a, eigenvalue2.b).toString();
        }
        else
            text+="Невозможно вычислить второе максимальное по модулю собственное число\n";
        
        text+="\n";
        /*if(eigenvaluen != null){
            text+="Ближайшее к заданному собственное число: ";
            text+=eigenvaluen.a.toString() + "\n";
            text+="Собственный вектор:\n";
            for(int i = 0; i < eigenvaluen.b.length; i++)
                text+=eigenvaluen.b[i].toString() + " ";
            text+="\n";
        }
        else
            text+="Невозможно Ближайшее к заданному собственное число\n";
        */
        out.print(text);
        out.close();
    }
    
    private static Double countDiscrepancy(Double[][] A, Double lambda, Double[] vector) throws Exception{
        Double[] v1 = null;
        double[] v2 = Matrix.copy((double[]) null/*vector*/);
        //v1 = Matrix.mulMatrixVector(A, vector);
        Double max = Double.MIN_VALUE;
        for(int i = 0; i < vector.length; i++){
            v2[i]*=lambda;
            if(Math.abs(v2[i] - v1[i]) > max)
                max = v2[i] - v1[i];
        }
        return max;
    }
}
