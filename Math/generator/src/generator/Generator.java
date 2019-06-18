/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Work
 */
public class Generator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("d:\\input.dat");
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        for(Double i = 1.; i <= 7.1; i+=1.){
            for(Double j = 1.; j <= 7.1; j+=1.)
                text+=((Double)(Math.pow(1./(i+j-1.), 7.))).toString() + " ";
            text+="\n";
        }
        text+="#\n";
        for(int i = 0; i < 7; i++){
            text += "1\n";
        }
        out.print(text);
        out.close();
    }
}
