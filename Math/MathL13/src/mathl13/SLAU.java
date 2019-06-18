/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl13;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Work
 */
public class SLAU {

    static Double[][] C;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static Double[] SLAU(Double[][] A, Double[] B) throws IOException, Exception{
        Double[] X = null;
        pair<Double[][],Double[]> p;
        X = solveSLAU(Matrix.copy(A), Matrix.copy(B));
        return X;
    }
    
    private static Double normOfMatrix(Double[][] A){
        Double norm = Double.MIN_VALUE;
        for (Double[] Ai : A) {
            Double summ = 0.;
            for (Double a : Ai) {
                summ += Math.abs(a);
            }
            if(summ > norm)
                norm = summ;
        }
        return norm;
    }
    
    public static Double[] solveSLAU(Double[][] A, Double[] B) throws IOException, Exception{
        Matrix.toTriangleMatrix(A, B, true);
        Double[] X = new Double[B.length];
        for(int i = A.length - 1; i >=0; i--)
            X[i] = countX(A[i], X, i, B[i]);
        return X;
    }
    
    private static Double countX(Double[] line, Double[] X, int k, Double b){
        Double x = 0.;
        for(int i = k+1; i < line.length; i++)
            x += line[i]*X[i];
        x=(b-x)/line[k];
        return x;
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
    
    private static Double determenant(Double[][] A) throws Exception{
        Double res=1.;
        int swaps = Matrix.toTriangleMatrix(A, null, true);
        for(int i = 0; i < A.length; i++)
            res*=A[i][i];
        for(int i = 0; i < swaps; i++)
            res*=-1;
        return res;
    }
    
    private static Double[][] inversMatrix(Double[][] A) throws Exception{
        Double[][] res = new Double[A.length][];
        Double[] B = new Double[A.length], X;
        for(int i = 0; i < A.length; i++)
            res[i] = new Double[A[i].length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++){
                if(j == i){
                    B[i] = 1.;
                }
                else
                    B[j] = 0.;
            }
            X = solveSLAU(Matrix.copy(A), Matrix.copy(B));
            for(int j = 0; j < A.length; j++){
                res[j][i] = X[j];
            }
        } 
        return res;
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
            if(b){
                String[] strs = str.split(" ");
                A.add(new Double[strs.length]);
                for(int i = 0; i < strs.length; i++){
                    A.get(A.size() - 1)[i] = Double.valueOf(strs[i]);
                }
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
    
    static void writeToFile(Double[][] A, Double[] X, Double[] disc, Double[][] invM, Double det, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        text+="X:\n";
        for(int i = 0; i < X.length; i++)
            text+=X[i].toString() + "\n";
        text+="Невязки:\n";
        for(int i = 0; i < disc.length; i++)
            text+=disc[i].toString() + " ";
        text+="\nОбратная матрица:\n";
        for(int i = 0; i < invM.length; i++){
            for(int j = 0; j < invM[i].length; j++)
                text+=invM[i][j].toString() + " ";
            text+="\n";
        }
        text+="Оперделитель:\n";
        text+=det.toString() + "\n";
        text+="Число обусловленности:\n";
        text+=((Double)(normOfMatrix(A)*normOfMatrix(invM))).toString();
        out.print(text);
        out.close();
    }
}
