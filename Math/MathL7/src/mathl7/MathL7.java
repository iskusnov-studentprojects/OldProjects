/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathl7;

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
public class MathL7 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Double[][] tmp = readFromFile("d:\\input.dat");
        Double[] X = new Double[tmp.length],
                Y = new Double[tmp.length];
        for(int i = 0; i < tmp.length; i++){
            X[i] = tmp[i][0];
            Y[i] = tmp[i][1];
        }
        sortTable(X, Y);
        Spline spline = new Spline(X, Y);
        XYSeries approxF = new XYSeries("~f(x)"),
                fun = new XYSeries("f(x)");
        
        
        for(Double i = X[0]; i <= X[X.length-1]; i += 0.01){
            approxF.add(i, spline.count(i));
            fun.add(i, (Double)(   Math.exp(-i*i)   ));
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
    
    @SuppressWarnings("empty-statement")
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
