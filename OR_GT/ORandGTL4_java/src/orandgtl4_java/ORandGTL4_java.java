package orandgtl4_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Work
 */
public class ORandGTL4_java {

    static Double h = 0.01;
    static Double eps = 0.00001;
    static List<Double[]> points = new ArrayList<>();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        Double[][] matrix = readFromFile("d:\\input.dat"), copy = new Double[matrix.length][];
        Double[] koshi = solveKoshi(matrix),
                B = new Double[koshi.length];
        for(int i = 0; i < B.length; i++)
            B[i] = 0.;
        B[B.length - 1] = 1.;
        for(int i = 0; i < copy.length; i++){
            copy[i] = new Double[matrix[i].length];
            for(int j = 0; j < copy[i].length; j++){
                if(i < matrix.length - 1)
                    copy[i][j] = matrix[i][j];
                else{
                    copy[i][j] = 1.;
                }
            }
        }
        
        
        writeToFile(koshi, SLAU.SLAU(copy, B), "d:\\output.dat");
        XYSeries[] graphics = new XYSeries[koshi.length];
        XYSeriesCollection dataset = new XYSeriesCollection();
        for(int i = 0; i < graphics.length; i++){
            graphics[i] = new XYSeries(((Integer)i).toString());
            for(int j = 0; j < points.size(); j++)
                graphics[i].add(j, points.get(j)[i]);
            dataset.addSeries(graphics[i]);
        }
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Вероятности", 
                "Ось t", "Ось P(t)",
                dataset, 
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );
        
        JFrame frame = new JFrame("MinimalStaticChart");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(640,480);
        frame.show();
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
    
    public static void writeToFile(Double[] koshi, Double[] gaus, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        String text = "";
        
        text += "Результат решения задачи Коши:\n";
        for(int i = 0; i < koshi.length; i++)
            text += koshi[i].toString() + "\n";
        
        text += "Результат решения СЛАУ:\n";
        for(int i = 0; i < gaus.length; i++)
            text += gaus[i].toString() + "\n";
        out.print(text);
        out.close();
    }
    
    private static Double[] solveKoshi(Double[][] matrix){
        Double step = h;
        Double[] res = new Double[matrix.length], oldRes = null;
        res[0] = 1.;
        for(int i = 1; i < res.length; i++)
            res[i] = 0.;
        do{
            oldRes = res;
            points.add(oldRes);
            res = mulMatrixVector(matrix, res);
            res = mulVectorConstant(res, h);
            res = addVectors(oldRes, res);
            step += h;
        }while(Math.abs(normOfVector(oldRes) - normOfVector(res)) > eps);
        return res;
    }
    
    public static Double[][] createMatrix(int n, int m){
        Double[][] res = new Double[n][];
        for(int i = 0; i < n; i++){
            res[i] = new Double[m];
            for(int j = 0; j < m; j++){
                if(i == j)
                    res[i][j] = 1.;
                else
                    res[i][j] = 0.;
            }
        }
        return res;
    }
    
    public static Double[] mulMatrixVector(Double[][] matrix, Double[] vector){
        Double[] res = new Double[matrix.length];
        for(int i = 0; i < res.length; i++){
            Double sum = 0.;
            for(int j = 0; j < vector.length; j++)
                sum += matrix[i][j]*vector[j];
            res[i] = sum;
        }
        return res;
    }
    
    public static Double[][] mulMatrixConstant(Double[][] matrix, Double c){
        Double[][] res = createMatrix(matrix.length, matrix[0].length);
        for(int i = 0; i < res.length; i++)
            for(int j = 0; j < res[0].length; j++)
                res[i][j] = matrix[i][j]*c;
        return res;
    }
    
    public static Double[] mulVectorConstant(Double[] vector, Double c){
        Double[] res = new Double[vector.length];
        for(int i = 0; i < vector.length; i++)
            res[i] = vector[i]*c;
        return res;
    }
    
    public static Double[] addVectors(Double[] vector1, Double[] vector2){
        Double[] res = new Double[vector1.length];
        for(int i = 0; i < res.length; i++)
            res[i] = vector1[i] + vector2[i];
        return res;
    }
    
    public static Double[][] addMatrices(Double[][] matrix1, Double[][] matrix2){
        Double[][] res = new Double[matrix1.length][];
        for(int i = 0; i < res.length; i++){
            res[i] = new Double[matrix1.length];
            for(int j = 0; j < matrix1[0].length; j++)
                res[i][j] = matrix1[i][j] + matrix2[i][j];
        }
        return res;
    }
    
    public static Double normOfVector(Double[] vector){
        Double max = Math.abs(vector[0]);
        for(int i = 1; i < vector.length; i++)
            if(Math.abs(vector[i]) > max)
                max = Math.abs(vector[i]);
        return max;
    }
    
}
