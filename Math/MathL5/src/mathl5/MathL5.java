/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl5;

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
public class MathL5 {

    static Double eps;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        Double[][] A = readFromFile("d:\\input.dat");
        pair<Double[][], Double[][]> ans = countB(A);
        writeToFile(ans.a, ans.b, countDiscrepancy(A, ans.a, ans.b), "d:\\output.dat");
    }
    
    public static pair<Double[][], Double[][]> countB(Double[][] A) throws Exception{
        Double[][] B = Matrix.copy(A), Ukl, D = Matrix.createMatix(A.length);
        Double max = Double.MIN_VALUE;
        int k = 0, l = 0;
        for(int i = 0; i < B.length; i++){
            for(int j = 0; j < B[i].length; j++){
                if(i != j && Math.abs(B[i][j]) > max){
                    max = Math.abs(B[i][j]);
                    k = i;
                    l = j;
                }
            }
        }
        while(Math.abs(B[k][l]) > eps){
            Ukl = countUkl(B);
            D = Matrix.mulMatrices(D, Ukl);
            B = Matrix.mulMatrices(Matrix.transposition(Ukl), Matrix.mulMatrices(B, Ukl));
            max = Double.MIN_VALUE;
            for(int i = 0; i < B.length; i++)
                for(int j = 0; j < B[i].length; j++)
                    if(i != j && Math.abs(B[i][j]) > max){
                        max = Math.abs(B[i][j]);
                        k = i;
                        l = j;
                    }
        }
        /*Double[][] B = Matrix.copy(A),
                D = Matrix.createMatix(A.length);
        Double alfa = 0., beta = 0.;
        int k = 0, l = 0;
        Double max = Double.MIN_VALUE;
        for(int i = 0; i < A.length; i++)
            for(int j = 0; j < A[i].length; j++)
                if(i != j && Math.abs(B[i][j]) > max){
                    max = Math.abs(B[i][j]);
                    k = i;
                    l = j;
                }
        while(Math.abs(B[k][l]) > eps){
            if(B[k][k].equals(B[l][l]))
                alfa = beta = Math.sqrt(1./2.);
            else{
                alfa = Math.sqrt((1.+1./Math.sqrt(1.+((2*B[k][l])/(B[k][k]-B[l][l]))*(B[k][l]/(B[k][k]-B[l][l]))))/2.);
                beta = sign(B[k][l]/(B[k][k]-B[l][l]))*Math.sqrt((1.-1./Math.sqrt(1.+((2*B[k][l])/(B[k][k]-B[l][l]))*(B[k][l]/(B[k][k]-B[l][l]))))/2.);
            }
            for(int i = 0; i < B.length; i++){
                B[i][k] = B[i][k]*alfa + B[i][l]*beta;
                B[i][l] = -1.*B[i][k]*beta + B[i][l]*alfa;
            }
            for(int i = 0; i < B.length; i++){
                B[k][i] = B[k][i]*alfa + B[l][i]*beta;
                B[l][i] = -1*B[k][i]*beta+B[l][i]*alfa;
            }
            max = Double.MIN_VALUE;
            for(int i = 0; i < B.length; i++)
                for(int j = 0; j < B[i].length; j++)
                    if(i != j && Math.abs(B[i][j]) > max){
                        max = Math.abs(B[i][j]);
                        k = i;
                        l = j;
                    }
        }*/
        return new pair<Double[][], Double[][]>(B,D);
    }
    
    private static int sign(Double value){
        if(value >= 0)
            return 1;
        else
            return -1;
    }
    
    private static Double[][] countUkl(Double[][] A){
        Double[][] Ukl = new Double[A.length][];
        Double alfa = 0., beta = 0.;
        int k = 0, l = 0;
        Double max = Double.MIN_VALUE;
        for(int i = 0; i < A.length; i++)
            for(int j = 0; j < A[i].length; j++)
                if(i != j && Math.abs(A[i][j]) > max){
                    max = Math.abs(A[i][j]);
                    k = i;
                    l = j;
                }
        if(A[k][k].equals(A[l][l]))
            alfa = beta = Math.sqrt(1./2.);
        else{
            alfa = Math.sqrt((1.+1./Math.sqrt(1.+((2*A[k][l])/(A[k][k]-A[l][l]))*(A[k][l]/(A[k][k]-A[l][l]))))/2.);
            beta = sign(A[k][l]/(A[k][k]-A[l][l]))*Math.sqrt((1.-1./Math.sqrt(1.+((2*A[k][l])/(A[k][k]-A[l][l]))*(A[k][l]/(A[k][k]-A[l][l]))))/2.);
        }
        for(int i = 0; i < Ukl.length; i++){
            Ukl[i] = new Double[A[i].length];
            for(int j = 0; j < A[i].length; j++){
                if(i == j)
                    Ukl[i][j] = 1.;
                else
                    Ukl[i][j] = 0.;
            }
        }
        Ukl[k][k] = Ukl[l][l] = alfa;
        Ukl[k][l] = -1*beta;
        Ukl[l][k] = beta;
        return Ukl;
    }
    
    private static Double[][] readFromFile(String path) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        ArrayList<Double[]> A = new ArrayList<Double[]>();
        Double Lambda0 = 0.;
        String str;
        boolean b = true;
        while((str = file.readLine()) != null){
            if("%".equals(str)){
                str = file.readLine();
                eps = Double.valueOf(str);
                continue;
            }
            String[] strs = str.split(" ");
            A.add(new Double[strs.length]);
            for(int i = 0; i < strs.length; i++)
                A.get(A.size() - 1)[i] = Double.valueOf(strs[i]);;
        }
        return A.toArray(new Double[A.size()][]);
    }
    
    private static void writeToFile(Double[][] lambdas, Double[][] vectors, Double[] disc, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        for(int i = 0; i < lambdas.length; i++){
            text += ((Integer)(i + 1)).toString() + " собственное число: " + lambdas[i][i].toString() + "\n";
            text += "Собственный вектор:\n";
            for(int j = 0; j < lambdas.length; j++){
                text += vectors[j][i].toString() + " ";
            }
            text += "\nНевязка: " + disc[i].toString() + "\n";
        }
        
        out.print(text);
        out.close();
    }
    
    private static Double[] countDiscrepancy(Double[][] A, Double[][] lambdas, Double[][] vectors) throws Exception{
        Double[] R = new Double[A.length];
        for(int i = 0; i < R.length; i++){
            Double[] vec1 = new Double[A.length], vec2 = new Double[A.length];
            for(int j = 0; j < vec1.length; j++){
                vec1[j] = vectors[j][i];
                vec2[j] = vectors[j][i]*lambdas[i][i];
            }
            Double[] v = Matrix.mulMatricVector(A, vec1);
            v[0] -= vec2[0];
            Double max = Math.abs(v[0]);
            for(int j = 1; j < R.length; j++){
                v[j] -= vec2[j];
                if(Math.abs(v[j]) > max)
                    max = Math.abs(v[j]);
            }
            R[i] = max;
        }
        return R;
    }
    
}
