/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl8;

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
public class MathL8 {

    static Double step = 0.5;
    static Double beg = -5.;
    static Double end = 5.;
    static Double eps = 0.00001;
    
    private static Double function(Double x){
        return Math.exp(x);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        writeData("d:\\input.dat");
        Double[][] tmp = readFromFile("d:\\input.dat");
        Double[] X = new Double[tmp.length],
                Y = new Double[tmp.length];
        for(int i = 0; i < tmp.length; i++){
            X[i] = tmp[i][0];
            Y[i] = tmp[i][1];
        }
        Double[] dif1 = differential1(X, Y),
                dif2 = differential2(X, Y, dif1),
                dif3 = differential3(X, Y, dif2);
        writeToFile(X, dif1, dif2, dif3, "d:\\output.dat");
    }
    
    private static Double[] differential1(Double[] X, Double[] Y) throws Exception{
        Double[] res = new Double[X.length];
        Double h = X[1] - X[0];
        if(h < eps)
            throw new Exception("Интервал = 0");
        res[0] = (-3.*Y[0] + 4.*Y[1] - Y[2])/(2.*h);
        for(int i = 1; i < res.length - 1; i++)
            res[i] = (Y[i+1] - Y[i-1])/(2.*h);
        res[res.length-1] = (Y[res.length-1] - Y[res.length-2])/h;
        return res;
    }
    
    private static Double[] differential2(Double[] X, Double[] Y, Double[] dif1) throws Exception{
        Double[] res = new Double[X.length];
        Double h = X[1] - X[0];
        if(h < eps)
            throw new Exception("Интервал = 0");
        res[0] = (2.*Y[0] - 5.*Y[1] + 4.*Y[2] - Y[3])/(h*h);
        for(int i = 1; i < res.length - 1; i++)
            res[i] = (Y[i-1] - 2.*Y[i] + Y[i+1])/(h*h);
        res[res.length-1] = (dif1[res.length-1] - dif1[res.length-2])/h;
        return res;
    }
    
    private static Double[] differential3(Double[] X, Double[] Y, Double[] dif2) throws Exception{
        Double[] res = new Double[X.length];
        Double h = X[1] - X[0];
        if(h < eps)
            throw new Exception("Интервал = 0");
        res[0] = (-3.*dif2[0] + 4.*dif2[1] - dif2[2])/(2.*h);
        res[1] = (dif2[1] - dif2[0])/h;
        for(int i = 2; i < res.length - 1; i++)
            res[i] = (Y[i+1] - 3.*Y[i] + 3.*Y[i-1] - Y[i-2])/(h*h*h);
        res[res.length-1] = (dif2[res.length-1] - dif2[res.length-2])/h;
        return res;
    }
    
    private static Double[][] readFromFile(String path) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        ArrayList<Double[]> A = new ArrayList<>();
        String str;
        while((str = file.readLine()) != null){
            String[] strs = str.split(" ");
            A.add(new Double[strs.length]);
            for(int i = 0; i < strs.length; i++)
                A.get(A.size() - 1)[i] = Double.valueOf(strs[i]);;
        }
        return A.toArray(new Double[A.size()][]);
    }
    
    private static void writeToFile(Double[] X, Double[] firstDif, Double[] secondDif, Double[] thirdDif, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        for(int i = 0; i < firstDif.length; i++){
            text += X[i].toString() + " " + 
                    firstDif[i].toString() + " " + 
                    secondDif[i].toString() + " " + 
                    thirdDif[i].toString() + "\n";
        }
        
        out.print(text);
        out.close();
    }
    
    private static void writeData(String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        for(Double i = beg; i < end+0.001; i+=step){
            text += i.toString() + " " + function(i).toString() + "\n";
        }
        
        out.print(text);
        out.close();
    }
    
}
