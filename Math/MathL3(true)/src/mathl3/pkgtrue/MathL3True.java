/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl3.pkgtrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergey
 */
public class MathL3True {

    private static Double eps;
    private static Integer iterByJacobi;
    private static Integer iterBySeidel;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Double[] Xj = null, Xs = null;
        pair<Double[][],Double[]> tmp;
        try{
            tmp = readFromFile("d:\\input.dat");
            pair<Double[][],Double[]> p = transformSLAU(tmp.a, tmp.b);
            Xj = solveSLAUbyJacobi(p.a, p.b);
            Xs = solveSLAUbySeidel(p.a, p.b);
            writeToFile(Xj, Xs, countDiscrepancy(tmp.a, tmp.b, Xj), countDiscrepancy(tmp.a, tmp.b, Xs), "d:\\output.dat");
        }
        catch(Exception ex){
            Logger.getLogger(MathL3True.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    private static Double[] solveSLAUbyJacobi(Double[][] B, Double[] C) throws Exception{
        iterByJacobi = 0;
        Double[] X,
                nX = new Double[C.length];
        initiationX(nX);
        do{
            X = nX;
            nX = new Double[X.length];
            for(int j = 0; j < nX.length; j++)
                nX[j] = countX(X, B[j], C[j]);
            iterByJacobi++;
            if(iterByJacobi > Integer.MAX_VALUE - 10)
                throw new Exception("Метод Якоби не сходится");
        }while(!convergence(nX, X));
        return nX;
    }
    
    private static Double[] solveSLAUbySeidel(Double[][] B, Double[] C) throws Exception{
        iterBySeidel = 0;
        Double[] X = new Double[C.length],
                oldX = new Double[C.length];
        initiationX(X);
        do{
            System.arraycopy(X, 0, oldX, 0, X.length);
            for(int j = 0; j < X.length; j++)
                X[j] = countX(X, B[j], C[j]);
            iterBySeidel++;
            if(iterBySeidel > Integer.MAX_VALUE - 10)
                throw new Exception("Метод Зеделя не сходится");
        }while(!convergence(X, oldX));
        return X;
    }
    
    private static Double countX(Double[] X, Double[] line, Double b){
        Double summ = 0.;
        for(int i = 0; i < line.length; i++)
            summ += line[i]*X[i];
        return b + summ;
    }
    
    private static void initiationX(Double[] X){
        for(int i = 0; i < X.length; i++)
            X[i] = 0.;
    }
    
    private static boolean convergence(Double[] oldX, Double[] newX){
        Double[] V = new Double[oldX.length];
        for(int i = 0; i < V.length; i++)
            V[i] = newX[i]-oldX[i];
        return normOfVector(V) < eps;
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
    
    private static Double normOfVector(Double[] vector){
        Double norm = 0.;
        for (Double v : vector) {
            norm += Math.abs(v);
        }
        return norm;
    }
    
    private static Double normOfMatrix(Double[][] A){
        Double norm = Double.MIN_VALUE;
        for (Double[] Ai : A) {
            Double summ = normOfVector(Ai);
            if(summ > norm)
                norm = summ;
        }
        return norm;
    }
    
    private static Double[] countDiscrepancy(Double[][] A, Double[] B, Double[] X){
        Double[] R = new Double[X.length];
        for(int i = 0; i < R.length; i++){
            Double summ = 0.;
            for(int j = 0; j < A[i].length; j++)
                summ += A[i][j]*X[j];
            R[i] = B[i] - summ;
        }
        return R;
    }
    
    static pair readFromFile(String path) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        ArrayList<Double[]> A = new ArrayList<>();
        ArrayList<Double> B = new ArrayList<>();
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
                    A.get(A.size() - 1)[i] = Double.valueOf(strs[i]);
            }
            else
                B.add(Double.valueOf(str));
        }
        pair<Double[][], Double[]> res = new pair<>();
        res.a = A.toArray(new Double[A.size()][]);
        res.b = B.toArray(new Double[B.size()]);
        file.close();
        return res;
    }
    
    static void writeToFile(Double[] Xj, Double[] Xs, Double[] discXj, Double[] discXs, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        text+="Метод Якоби:\n";
        for(int i = 0; i < Xj.length; i++)
            text+=Xj[i].toString() + "\n";
        text+="Колличество итераций: ";
        text+= iterByJacobi.toString() + "\n";
        text+="Невязки:\n";
        for(int i = 0; i < discXj.length; i++)
            text+=discXj[i].toString() + " ";
        text+="\nМетод Зейделя:\n";
        for(int i = 0; i < Xs.length; i++)
            text+=Xs[i].toString() + "\n";
        text+="Колличество итераций: ";
        text+= iterBySeidel.toString() + "\n";
        text+="Невязки:\n";
        for(int i = 0; i < discXs.length; i++)
            text+=discXs[i].toString() + " ";
        
        out.print(text);
        out.close();
    }
}
