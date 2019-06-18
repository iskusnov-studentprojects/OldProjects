/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl9;

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
public class MathL9 {

    /**
     * @param args the command line arguments
     */
    static Double sStep = 0.5;
    static Double sBeg = -5.;
    static Double sEnd = 5.;
    static Double eps = 0.0001;
    
    private static Double function(Double x){
        return x*x;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        writeToFile("d:\\output.dat");
    }
    
    private static Double rectangle(Double beg, Double end, Double step){
        Double res = 0.;
        for(Double i = beg; i < end; i+=step)
            res+=function((i + i + step)/2)*step;
        return res;
    }
    
    private static Double trapeze(Double beg, Double end, Double step){
        Double res = 0.;
        for(Double i = beg + step; i < end + eps; i+=step)
            res+=(function(i) + function(i-step))/2;
        return res*step;
    }
    
    private static Double simpson(Double beg, Double end, Double step){
        Double res = function(beg) + function(end);
        int i = 1;
        for(Double x = beg + step; x < end - eps; x+=step, i++){
            if(i%2 == 1)
                res+=4.*function(x);
            else
                res+=2.*function(x);
        }
        return res*(step/3.);
    }
    
    private static Double autoStepRect(Double beg, Double end, Double step){
        Double Ih = rectangle(beg, end, step), Ih2 = rectangle(beg, end, step/2);
        if(Math.abs(Ih - Ih2) < eps)
            return Ih;
        else
            return autoStepRect(beg, end, step/2);
    }
    
    private static Double autoStepTrap(Double beg, Double end, Double step){
        Double Ih = trapeze(beg, end, step), Ih2 = trapeze(beg, end, step/2);
        if(Math.abs(Ih - Ih2) < eps)
            return Ih;
        else
            return autoStepTrap(beg, end, step/2);
    }
    
    private static Double autoStepSim(Double beg, Double end, Double step){
        Double Ih = simpson(beg, end, step), Ih2 = simpson(beg, end, step/2);
        if(Math.abs(Ih - Ih2) < eps)
            return Ih;
        else
            return autoStepSim(beg, end, step/2);
    }
    
    private static void writeToFile(String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        text += rectangle(sBeg, sEnd, sStep).toString() + " " + autoStepRect(sBeg, sEnd, sStep).toString() + "\n" + 
                trapeze(sBeg, sEnd, sStep).toString() + " " + autoStepTrap(sBeg, sEnd, sStep).toString() + "\n" +
                simpson(sBeg, sEnd, sStep).toString() + " " + autoStepSim(sBeg, sEnd, sStep).toString();
        
        out.print(text);
        out.close();
    }
}
