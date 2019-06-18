/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl6;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.Spring.height;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Work
 */
public class MathL6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Double[][] table = readFromFile("d:\\input.dat");
        Double[] coeff = countCoeff(table);
        XYSeries approxF = new XYSeries("~f(x)"),
                fun = new XYSeries("f(x)");
        
        
        for(Double i = table[0][0]; i < table[table.length-1][0]; i+=0.001){
            approxF.add(i, function(i, table, coeff));
            fun.add(i, (Double)(  Math.exp(-i*i)  ));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(approxF);
        dataset.addSeries(fun);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Приближение функции", 
                "Ось x", "Ось y",
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
    
    private static Double[] countCoeff(Double[][] table){
        Double[] res = new Double[table.length];
        for(int i = 0; i < res.length; i++){
            Double coeff = 0.;
            for(int j = 0; j <= i; j++){
                Double mul = 1.;
                for(int l = 0; l <= i; l++)
                    if(j != l)
                        mul *= table[j][0] - table[l][0];
                coeff += table[j][1]/mul;
            }
            res[i] = coeff;
        }
        return res;
    }
    
    private static Double function(Double x, Double[][] table, Double[] coeff){
        Double res = coeff[0];
        for(int i = 1; i < coeff.length; i++){
            Double mul = 1.;
            for(int j = 0; j < i; j++)
                mul *= x-table[j][0];
            res += coeff[i]*mul;
        }
        return res;
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
}
