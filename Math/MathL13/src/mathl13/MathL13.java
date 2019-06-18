/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl13;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class MathL13 {

    /**
     * @param args the command line arguments
     */
    static Double eps = 0.0001;
    static Double[] X0 = {0.5, 0.5, 0.5};
    
    private static Double[] funs(Double[] X){
        Double[] Y = new Double[X.length];
        Y[0] = X[0]*X[0] + X[1]*X[1] + X[2]*X[2] - 1;
        Y[1] = 2*X[0]*X[0] + X[1]*X[1] - 4*X[2];
        Y[2] = 3*X[0]*X[0] - 4*X[1] + X[2]*X[2];
        return Y;
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        writeToFile(solveSystem(), "d:\\output.dat");
    }
    
    private static Double[][] difFk(Double[] X){
        Double[][] B = new Double[X.length][];
        for(int i = 0; i < B.length; i++){
            B[i] = new Double[X.length];
        }
        
        B[0][0] = 2.*X[0];
        B[0][1] = 2.*X[1];
        B[0][2] = 2.*X[2];
        
        B[1][0] = 4*X[0];
        B[1][1] = 2*X[1];
        B[1][2] = -4.;
        
        B[2][0] = 6*X[0];
        B[2][1] = -4.;
        B[2][2] = 2*X[2];
        return B;
    }
    
    private static Double[] solveSystem() throws Exception{
        Double[] newX = X0,
                oldX,
                dX,
                Fx;
        do{
            oldX = newX;
            Fx = funs(oldX);
            for(int i = 0; i < Fx.length; i++)
                Fx[i]*=-1.;
            dX = SLAU.SLAU(difFk(oldX), Fx);
            newX = new Double[dX.length];
            for(int i = 0; i < newX.length; i++)
                newX[i] = oldX[i] + dX[i];
        }while(Math.abs(max(dX)) > eps);
        return newX;
    }
    
    private static Double max(Double[] mass){
        Double max = mass[0];
        for(int i = 0; i < mass.length; i++)
            if(Math.abs(mass[i]) > Math.abs(max))
                max = mass[i];
        return max;
    }
    
    private static void writeToFile(Double[] root, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        for(int i = 0; i < root.length; i++){
            text += root[i].toString() + " ";
        }
        
        out.print(text);
        out.close();
    }
}
