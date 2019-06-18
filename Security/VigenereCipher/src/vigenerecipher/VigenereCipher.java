/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerecipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Sergey
 */
public class VigenereCipher {

    /**
     * @param args the command line arguments
     */
    static int lenABC = 33;
    static char[] ABC = {'а','б','в','г','д','е','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я','_'};
    static double crit = 0.053;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String text = loadText("d:\\text.txt");
        int len;
        ArrayList<Integer> list = new ArrayList<>();
        do{
            len = countLengthPass(text, list);
            list.add(len);
        }while(len < 0 || !checkLength(text, len));
        
        char[][] table = formTable(text, len);
        ArrayList<pair<char[], int[]>> freqs = new ArrayList<>();
        double[] indeces = new double[len];
        for(int i = 0; i < len; i++){
            freqs.add(createABCTable());
            countFreq(table[i], freqs.get(i));
            indeces[i] = countIndexC(table[i], freqs.get(i).b);
        }
        int[] shifts = countShifts(table, freqs);
        List<String> passes = countPass(len, shifts);
        String res;
        int i = 0;
        do{
            res = decodeText(text, passes.get(i));
            i++;
        }while(!PlainText.checkText(res, PlainText.loadBigrams("d:\\bigrams.txt")));
        writeToFile(res,"d:\\output.dat");
    }
    
    public static int countLengthPass(String text, List<Integer> except){
        for(int len = 3; len < 20; len++){
            for(int i = 0; i < text.length() - len*2; i++){
                String str = text.substring(i, i + len),
                        subtext = text;
                ArrayList<Integer> list = new ArrayList<>();
                while(subtext.contains(str)){
                    list.add(subtext.indexOf(str) + len);
                    subtext = subtext.substring(subtext.indexOf(str) + len);
                }
                int nod = 0;
                int[] mass = new int[list.size()];
                for(int j = 0; j < mass.length; j++)
                    mass[j] = list.get(j);
                for(int j=0;j<mass.length;j++) { //Нахождение НОД всех чисел массива
                    if(j+1<mass.length) {
                        for(int k=j+1;k<=j+1;k++) {
                            nod = gcd(mass[j], mass[k]);
                        }
                        mass[j+1] = nod;
                    }
                }
                if(nod > 2 && list.size() > 1 && !except.contains(nod))
                    return nod;
            }
        }
        return -1;
    }
    
    public static Integer gcd(Integer a, Integer b){
        while (b !=0) {
            Integer tmp = a%b;
            a = b;
            b = tmp;
        }
        return a;
    }
    
    public static String loadText(String path) throws IOException{
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(path),"windows-1251"));
        String res = "",str;
        while((str = file.readLine()) != null)
            res += str + "\n";
        return res.toLowerCase();
    }
    
    public static char[][] formTable(String text, int len){
        char[][] table = new char[len][];
        ArrayList<ArrayList<Character>> lists = new ArrayList<>();
        for(int i = 0; i < len; i++)
            lists.add(new ArrayList<>());
        for(int i = 0; i < text.length(); i++)
            lists.get(i%len).add(text.charAt(i));
        for(int i = 0; i < len; i++){
            table[i] = new char[lists.get(i).size()];
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = lists.get(i).get(j).charValue();
            }
        }
        return table;
    }
    
    public static pair<char[], int[]> createABCTable(){
        pair<char[], int[]> table = new pair<>(new char[lenABC], new int[lenABC]);
        for(int i = 0; i < lenABC; i++){
            table.a[i] = ABC[i];
            table.b[i] = 0;
        }
        return table;
    }
    
    public static void countFreq(char[] mass, pair<char[], int[]> freq){
        for(int i = 0; i < mass.length; i++){
            for(int j = 0; j < freq.a.length; j++)
                if(freq.a[j] == mass[i]){
                    freq.b[j]++;
                    break;
                }
        }
    }
    
    public static double countIndexC(char[] mass, int[] freq){
        int sum = 0;
        for(int i = 0; i < freq.length; i++)
            sum += freq[i]*(freq[i]-1);
        return ((double)sum)/((double)mass.length*(mass.length - 1));
    }
    
    public static boolean checkLength(String text, int len){
        char[][] table = formTable(text, len);
        ArrayList<pair<char[], int[]>> freqs = new ArrayList<>();
        for(int i = 0; i < len; i++){
            freqs.add(createABCTable());
            countFreq(table[i], freqs.get(i));
            if(countIndexC(table[i], freqs.get(i).b) < crit)
                return false;
        }
        return true;
    }
    
    public static double indexM(char[] col1, char[] col2, pair<char[], int[]> freq1, pair<char[], int[]> freq2){
        int sum = 0;
        for(int j = 0; j < lenABC; j++)
            sum += freq1.b[j]*freq2.b[j];
        double IM = ((double)sum)/((double)col1.length*col2.length);
        return IM;
    }
    
    public static pair<char[], int[]> changeFreq(pair<char[], int[]> freq, int shift){
        pair<char[], int[]> newfreq = createABCTable();
        for(int i = 0; i < lenABC; i++)
            newfreq.b[i] = freq.b[(lenABC - shift + i)%lenABC];
        return newfreq;
    }
    
    public static int[] countShifts(char[][] table, List<pair<char[], int[]>> freqs){
        int[] shifts = new int[table.length];
        for(int i = 1; i < table.length; i++){
            for(int j = 0; j < lenABC; j++)
                if(indexM(table[0], table[i], freqs.get(0), changeFreq(freqs.get(i), j)) > crit){
                    shifts[i] = j;
                    break;
                }
        }
        return shifts;
    }
    
    public static List<String> countPass(int len, int[] shifts) throws IOException{
        List<String> list = new ArrayList<>();
        for(int i = 0; i < lenABC; i++){
            String word = "";
            for(int j = 0; j < len; j++)
                word += changeSymbol(ABC[0], -shifts[j] + i);
            if(PlainText.checkText(word, PlainText.loadBigrams("d:\\bigrams.txt")))
                list.add(word);
        }
        return list;
    }
    
    public static String decodeText(String text, String pass){
        String res = "";
        for(int i = 0; i < text.length(); i++){
            res += changeSymbol(text.charAt(i), -indexOf(pass.charAt(i%pass.length())));
        }
        return res;
    }
    
    private static boolean inABC(char c){
        for(char i: ABC)
            if(i == c)
                return true;
        return false;
    }
    
    private static char changeSymbol(char c, int shift){
        shift %= lenABC;
        if(shift < 0)
            shift += lenABC;
        if(indexOf(c) == -1)
            return c;
        return ABC[(indexOf(c) + shift)%lenABC];
    }
    
    private static int indexOf(char c){
        for(int i = 0; i < ABC.length; i++)
            if(c == ABC[i])
                return i;
        return -1;
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
