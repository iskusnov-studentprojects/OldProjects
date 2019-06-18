/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Sergey
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Double[][] table = readFromFile("d:\\input.dat");
        sortTable(table[0], table[1]);
        Spline spline = new Spline(table[0], table[1]);
        XYSeries approxF = new XYSeries("~f(x)"),
                fun = new XYSeries("f(x)");
        
        
        for(Double i = -100.; i <= 100.; i += 0.01){
            approxF.add(i, spline.count(i));
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
        frame.setSize(400,300);
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
    
    private static void sortTable(Double[] x, Double[] y){
        for(int i = 0; i < x.length; i++)
            for(int j = i + 1; j < x.length; j++)
                if(x[j] < x[i]){
                    Double tmpx = x[i],
                            tmpy = y[i];
                    x[i] = x[j];
                    y[i] = y[j];
                    x[j] = tmpx;
                    y[j] = tmpy;
                }
    }
}
