/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencycryptanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Work
 */
public class FrequencyCryptanalysis {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String text = loadText("d:\\text.txt"),
                tmp = changeText(text);
        pair<char[], double[]> theorstat = loadFreqTable("d:\\statistictable.txt");
        pair<String[], double[]> realstat = new pair(allSymbs(tmp), countFreq(tmp, allSymbs(tmp)));
        pair<char[], String[]> symbs = associate(theorstat, realstat);
        String res = restoreText(text, symbs);
        writeToFile(res, "d:\\output.dat");
    }
    
    private static void writeToFile(String text, String path) throws FileNotFoundException, IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
            out.write(text);
        }
    }
    
    private static pair<char[], double[]> loadFreqTable(String path) throws IOException{
        ArrayList<Character> symbs;
        ArrayList<Double> freq;
        try (BufferedReader file = new BufferedReader(new FileReader(path))) {
            symbs = new ArrayList<>();
            freq = new ArrayList<>();
            String str;
            while((str = file.readLine()) != null){
                String[] strs = str.split(" ");
                symbs.add(strs[0].charAt(0));
                freq.add(Double.valueOf(strs[1]));
            }
        }
        char[] a = new char[symbs.size()];
        for(int i = 0; i < a.length; i++)
            a[i] = symbs.get(i);
        double[] b = new double[freq.size()];
        for(int i = 0; i < b.length; i++)
            b[i] = freq.get(i);
        return new pair(a,b);
    }
    
    private static String loadText(String path) throws FileNotFoundException, IOException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        String res = "",str;
        while((str = file.readLine()) != null)
            res += str + "\n";
        return res;
    }
    
    private static String[] allSymbs(String text){
        String[] symbs = text.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String i: symbs)
            if(!exists(list,i) && i.compareTo("") != 0)
                list.add(i);
        String[] res = new String[list.size()];
        for(int i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }
    
    private static String changeText(String text){
        String res = "";
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) >= '0' && text.charAt(i) <= '9')
                res += text.charAt(i);
            else
                res += " ";
        }
        return res;
    }
    
    private static double[] countFreq(String text, String[] symbs){
        String[] textsym = text.split(" ");
        int[] nums = new int[symbs.length];
        int allsym = 0;
        for(int i = 0; i < nums.length; i++)
            nums[i] = 0;
        for(String i: textsym)
            if(i.compareTo("") != 0){
                nums[indexOf(symbs, i)]++;
                allsym++;
            }
        double[] res = new double[nums.length];
        for(int i = 0; i < res.length; i++)
            res[i] = ((double)nums[i])/((double)allsym);
        return res;
    }
    
    private static boolean exists(ArrayList<String> list, String str){
        return list.stream().anyMatch((i) -> (str.compareTo(i) == 0));
    }
    
    private static int indexOf(String[] mass, String str){
        for(int i = 0; i < mass.length; i++)
            if(mass[i].compareTo(str) == 0)
                return i;
        return -1;
    }
    
    private static pair<char[], String[]> associate(pair<char[], double[]> theorstat, pair<String[], double[]> realstat){
        sortC(theorstat);
        sortS(realstat);
        pair<char[], String[]> res = new pair<>();
        res.a = new char[theorstat.a.length];
        res.b = new String[theorstat.a.length];
        for(int i = 0; i < res.a.length; i++){
            res.a[i] = theorstat.a[i];
            res.b[i] = realstat.a[i];
        }
        return res;
    }
    
    private static void sortC(pair<char[], double[]> value){
        for(int i = 0; i < value.a.length - 1; i++)
            for(int j = 0; j <  value.a.length - i - 1; j++)
                if(value.b[j] > value.b[j+1]){
                    char tmpc = value.a[j];
                    double tmpd = value.b[j];
                    value.a[j] = value.a[j+1];
                    value.b[j] = value.b[j+1];
                    value.a[j+1] = tmpc;
                    value.b[j+1] = tmpd;
                }
    }
    
    private static void sortS(pair<String[], double[]> value){
        for(int i = 0; i < value.a.length - 1; i++)
            for(int j = 0; j <  value.a.length - i - 1; j++)
                if(value.b[j] > value.b[j+1]){
                    String tmpc = value.a[j];
                    double tmpd = value.b[j];
                    value.a[j] = value.a[j+1];
                    value.b[j] = value.b[j+1];
                    value.a[j+1] = tmpc;
                    value.b[j+1] = tmpd;
                }
    }
    
    private static String restoreText(String text, pair<char[], String[]> symbs){
        String res = "";
        for(int i = 0; i < text.length();){
            if(text.charAt(i) >= '0' &&  text.charAt(i) <= '9'){
                res += findSymbol(text, symbs, i);
                while(text.charAt(i) >= '0' &&  text.charAt(i) <= '9') i++;
            }
            else{
                if(text.charAt(i) == ' '){
                    int num = 0;
                    while(text.charAt(i) == ' '){
                        num++;
                        i++;
                    }
                    if(num > 1)
                        res+=" ";
                }
                else{
                    res += text.charAt(i);
                    i++;
                }
            }
        }
        return res;
    }
    
    private static String findSymbol(String text, pair<char[], String[]> symbs, int index){
        String str = "";
        while(text.charAt(index) >= '0' &&  text.charAt(index) <= '9'){
            str += text.charAt(index);
            index++;
        }
        int i;
        for(i = 0; i < symbs.a.length; i++)
            if(str.compareTo(symbs.b[i]) == 0)
                break;
        if(i < symbs.a.length){
            str = "";
            str += symbs.a[i];
        }
        return str;
    }
}
