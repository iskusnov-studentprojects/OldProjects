/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replacer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Work
 */
public class Replacer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        writeToFile(changeText(loadText("d:\\text.txt")),"d:\\text.txt");
    }
    
    private static String loadText(String path) throws FileNotFoundException, IOException{
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path),"windows-1251"));
        String res = "",str;
        while((str = file.readLine()) != null)
            res += str + "\n";
        return res.toLowerCase();
    }
    
    private static List<String[]> loadTable(String path) throws FileNotFoundException, IOException{
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path),"windows-1251"));
        List<String[]> list = new ArrayList<>();
        String str;
        while((str = file.readLine()) != null)
            list.add(str.split(" "));
        return list;
    }
    
    private static String changeSymbol(String c, List<String[]> list){
        for(int i = 0; i < list.size(); i++)
            if(c.equals(list.get(i)[0]))
                return list.get(i)[1] + " ";
        return c;
    }
    
    private static String changeText(String text) throws IOException{
        String res = "";
        List<String[]> list = loadTable("d:\\tmp.dat");
        for(int i = 0; i < text.length(); i++)
            res+= changeSymbol(text.charAt(i) + "", list);
        return res;
    }
    
    private static void writeToFile(String text, String path) throws FileNotFoundException, IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        out.write(text);
        out.close();
    }
}
