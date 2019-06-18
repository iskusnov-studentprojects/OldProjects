/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transponation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Work
 */
public class Transponation {

    /**
     * @param args the command line arguments
     */
    static char[] ABC = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int index = 0;
        String text = loadText("d:\\text.txt");
        int len = (int)(Math.sqrt(text.length()));
        List<String[]> list = new ArrayList<>();
        changeTable(formTable(text, len), new String[len], list, 0, len);
        writeToFile(list, "d:\\output.dat");
    }
    
    public static String[] formTable(String text, int len){
        String changetext = "";
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                changetext += text.charAt(len*j + i);
            }
        }
        String[] strs = new String[len];
        for(int i = 0; i < len; i++)
            strs[i] = changetext.substring(i*len, i*len + len);
        return strs;
    }
    
    public static String restoreText(String[] table){
        String str = "";
        if(table == null)
            return str;
        for(int i = 0; i < table.length; i++)
            for(int j = 0; j < table.length; j++){
                if(table[i] == null)
                    return str;
                str += table[j].charAt(i);
            }
        return str;
    }
    
    private static String loadText(String path) throws IOException{
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path),"windows-1251"));
        String res = "",str;
        while((str = file.readLine()) != null)
            res += str;
        return res.toLowerCase();
    }
    
    private static void writeToFile(List<String[]> list, String path) throws FileNotFoundException, IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        
        String text = "";
        for(String[] i: list)
            text += restoreText(i) + "\n";
        
        out.write(text);
        out.close();
    }
    
    private static String[] changeTable(String[] table, String[] nTable, List<String[]> list, int i, int len) throws IOException{
        if(i >= len){
            if(PlainText.checkText(restoreText(nTable), PlainText.loadBigrams("d:\\bigrams.txt"))){
                return nTable;
            }
            else
                return null;
        }
        for(int k = 0; k < len; k++){
            if(nTable[k] == null){
                nTable[k] = table[i];
                if(changeTable(table, nTable, list, i+1, len) != null){
                    list.add(copyTable(nTable));
                }
                nTable[k] = null;
            }
        }
        return null;
    }
    
    private static String[] copyTable(String[] table){
        String[] res = new String[table.length];
        for(int i = 0; i < table.length; i++)
            res[i] = table[i];
        return res;
    }
}
