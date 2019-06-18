/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Sergey
 */
public class MathL2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        Double[][] A = null,
                invA = null;
        Double[] B = null,
                X = null,
                R = null;
        Double det;
        pair<Double[][],Double[]> p;
        p = readFromFile("d:\\input.dat");
        A = p.a;
        B = p.b;
        Double[][] S = null;
        Double[][] D = null;
        pair<Double[][],Double[][]> res;
        res = countS(A);
        S = res.a;
        D = res.b;
        X = solveSLAU(S,D,B);
        R = countDiscrepancy(A, B, X);
        det = determenant(S, D);
        invA = inversMatrix(S, D);
        writeToFile(A, X, R, inversMatrix(S,D), det, "d:\\output.dat");
    }
    
    private static Double[] solveSLAU(Double[][] S, Double[][] D, Double[] B){
        Double[] X = new Double[B.length],
                Y = new Double[B.length],
                Z = new Double[B.length];
        Double summ;
        for(int i = 0; i < X.length; i++){
            summ = 0.;
            for(int j = 0; j < i; j++)
                summ+=Z[j]*S[j][i];
            Z[i]=(B[i]-summ)/S[i][i];
            Y[i]=Z[i]/D[i][i];
        }
        for(int i = X.length - 1; i >= 0; i--){
            summ = 0.;
            for(int j = i + 1; j < X.length; j++)
                summ += S[i][j]*X[j];
            X[i] = (Y[i] - summ)/S[i][i];
        }
        return X;
    }
    
    private static Double[][] inversMatrix(Double[][] S, Double[][] D) throws Exception{
        Double[][] res = new Double[S.length][];
        Double[] B = new Double[S.length], X;
        for(int i = 0; i < S.length; i++)
            res[i] = new Double[S[i].length];
        for(int i = 0; i < S.length; i++){
            for(int j = 0; j < S.length; j++){
                if(j == i){
                    B[i] = 1.;
                }
                else
                    B[j] = 0.;
            }
            X = solveSLAU(S, D, B);
            for(int j = 0; j < S.length; j++){
                res[j][i] = X[j];
            }
        }
        return res;
    }
    
    private static Double determenant(Double[][] S, Double[][] D) throws Exception{
        Double res=1.;
        for(int i = 0; i < S.length; i++)
            res*=S[i][i]*S[i][i]*D[i][i];
        return res;
    }
    
    private static pair<Double[][],Double[][]> countS(Double[][] A){
        Double[][] S = new Double[A.length][];
        Double[][] D = new Double[A.length][];
        for(int i = 0; i < A.length; i++){
            S[i] = new Double[A[i].length];
            D[i] = new Double[A[i].length];
        }
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < i; j++){
                S[i][j] = 0.;
                D[i][j] = 0.;
            }
            Double summ = 0.;
            for(int l = 0; l < i; l++)
                summ+=S[l][i]*S[l][i]*D[l][l];
            D[i][i] = sign(A[i][i]-summ);
            S[i][i] = Math.sqrt(Math.abs(A[i][i]-summ));
            summ = 0.;
            for(int j = i+1; j < A[i].length; j++){
                summ = 0.;
                for(int l = 0; l < i; l++)
                    summ+=S[l][i]*S[l][j]*D[l][l];
                S[i][j]=(A[i][j]-summ)/(S[i][i]*D[i][i]);
                D[i][j]=0.;
            }
        }
        return new pair<>(S,D);
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
    
    private static Double sign(Double value){
        if(value >= 0)
            return 1.;
        else
            return -1.;
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
    
    private static Double normOfMatrix(Double[][] A){
        Double norm = Double.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            Double summ = 0.;
            for(int j = 0; j < A[i].length; j++)
                summ+=Math.abs(A[i][j]);
            if(summ > norm)
                norm = summ;
        }
        return norm;
    }
}
