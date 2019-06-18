/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl11;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class MathL11 {

    /**
     * @param args the command line arguments
     */
    static Double a = -5.;
    static Double b = 5.;
    static Double eps = 0.00001;
    static Double step = 1.5;
    static int limIter = 1000000;
    
    private static Double f(Double x){
        return -(x*x*x*x) + 20;
    }
    
    private static Double firstDif(Double x){
        return -4*(x*x*x);
    }
    
    private static Double secondDif(Double x){
        return -12*(x*x);
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        List<Double> roots = new ArrayList<>();
        for(Double beg = a, end = a + step; end < b + step/100; beg += step, end += step){
            if(f(beg)*f(end) < 0)
                roots.add(calcRoot(beg, end));
        }
        writeToFile(roots, "d:\\output.dat");
    }
    
    private static Double calcRoot(Double a, Double b){
        Double x1,
                x2;
        int iter = 0;
        if(firstDif(a) > 0 && secondDif(a) > 0){
            x1 = a;
            x2 = b;
        }
        else{
            x1 = b;
            x2 = a;
        }
        do{
            x1 = x1 - (f(x1)*(x2-x1))/(f(x2)-f(x1));
            x2 = x2 - f(x2)/firstDif(x2);
            iter++;
            if(iter > limIter)
                break;
        }while(Math.abs(x1-x2) > eps);
        Double x = (x1+x2)/2;
        if(x < a || x > b || iter > limIter || x == Double.NaN)
            x = null;
        return x;
    }
    
    private static void writeToFile(List<Double> roots, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        for(int i = 0; i < roots.size(); i++){
            if(roots.get(i) != null)
                text += roots.get(i).toString() + " ";
        }
        
        out.print(text);
        out.close();
    }
}
