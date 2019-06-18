/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl3;

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
public class MathL3 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Double[] A = null,
                B = null,
                C = null;
        Double[][] res = readFromFile("d:\\input.dat");
        A = res[0];
        B = res[1];
        C = res[2];
        Double[] X = solveSLAU(A,B,C,res[3]);
    }
    
    private static Double[] solveSLAU(Double[] A, Double[] B, Double[] C, Double[] dop){
        Double[] X = new Double[dop.length],
                P = new Double[B.length],
                Q = new Double[dop.length];
        P[0] = C[0]/-B[0];
        Q[0] = -dop[0]/-B[0];
        for(int i = 1; i < P.length; i++){
            P[i] = C[i]/(-B[i] - A[i]*P[i-1]);
            Q[i] = (A[i]*Q[i-1] - dop[i])/(-B[i] - A[i]*P[i-1]);
        }
        X[X.length - 1] = Q[X.length - 1];
        for(int i = X.length - 2; i >=0; i--){
            X[i] = P[i]*X[i+1]+Q[i];
        }
        return X;
    }
    
    static Double[][] readFromFile(String path) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        ArrayList<Double> A = new ArrayList<>(), 
                B = new ArrayList<>(),
                C = new ArrayList<>(),
                dop = new ArrayList<>();
        String str;
        boolean b = true;
        int i = 0;
        while((str = file.readLine()) != null){
            if("#".equals(str)){
                b = false;
                continue;
            }
            if(b){
                String[] strs = str.split(" ");
                if(i == 0){
                    A.add(0.);
                    B.add(Double.valueOf(strs[i]));
                    C.add(Double.valueOf(strs[i+1]));
                    i++;
                    continue;
                }
                if(i == strs.length-1){
                    A.add(Double.valueOf(strs[i-1]));
                    B.add(Double.valueOf(strs[i]));
                    C.add(0.);
                    i++;
                    continue;
                }
                A.add(Double.valueOf(strs[i-1]));
                B.add(Double.valueOf(strs[i]));
                C.add(Double.valueOf(strs[i+1]));
                i++;
            }
            else
                dop.add(Double.valueOf(str));
        }
        Double[][] res = new Double[4][];
        res[0] = A.toArray(new Double[A.size()]);
        res[1] = B.toArray(new Double[B.size()]);
        res[2] = C.toArray(new Double[C.size()]);
        res[3] = dop.toArray(new Double[dop.size()]);
        file.close();
        return res;
    }
    
    static void writeToFile(Double[][] A, Double[] B, Double[] X, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "A:\n";
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length; j++)
                text+=A[i][j].toString() + " ";
            text+="\n";
        }
        text+="B:\n";
        for(int i = 0; i < B.length; i++)
            text+=B[i].toString() + "\n";
        text+="X:\n";
        for(int i = 0; i < X.length; i++)
            text+=X[i].toString() + "\n";
        out.print(text);
        out.close();
    }
}
