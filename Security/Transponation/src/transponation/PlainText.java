/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transponation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Sergey
 */
public class PlainText {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String text = loadText("d:\\text.txt");
        if(checkText(text, loadBigrams("d:\\bigrams.txt")))
            System.out.print("Текст является открытым.\n");
        else
            System.out.print("Текст является случайным набором символов.\n");
    }
    
    public static String[] loadBigrams(String path) throws IOException{
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path),"windows-1251"));
        String str = "";
        ArrayList<String> list = new ArrayList<>();
        while((str = file.readLine()) != null)
            list.add(str);
        String[] strings = new String[list.size()];
        for(int i = 0; i < strings.length; i++)
            strings[i] = list.get(i);
        return strings;
    }
    
    public static String loadText(String path) throws IOException{
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path),"windows-1251"));
        String res = "",str;
        while((str = file.readLine()) != null)
            res += str + "\n";
        return res;
    }
    
    public static boolean checkText(String text, String[] bigrams){
        for(String i: bigrams)
            if(text.contains(i))
                return false;
        return true;
    }
}
