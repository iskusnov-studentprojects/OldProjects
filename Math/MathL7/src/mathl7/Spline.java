/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl7;

/**
 *
 * @author Work
 */
public class Spline {
    private Splinei[] splines;
    int n;
    public Spline(Double[] x, Double[] y){
        n = x.length;
        // Инициализация массива сплайнов
        splines = new Splinei[n];
        for (int i = 0; i < n; ++i)
        {
            splines[i] = new Splinei();
            splines[i].x = x[i];
            splines[i].a = y[i];
        }
        splines[0].c = splines[n - 1].c = 0.0;
 
        Double[][] slau = createSLAU(x, y);
        Double[] C = solveSLAU(slau[2], slau[1], slau[0], slau[3]);
        
        for (int i = 1; i < n - 1; i++)
        {
            splines[i].c = C[i-1];
        }
 
        // По известным коэффициентам c[i] находим значения b[i] и d[i]
        for (int i = n - 1; i > 0; --i)
        {
            double hi = x[i] - x[i - 1];
            splines[i].d = (splines[i].c - splines[i - 1].c) / hi;
            splines[i].b = hi * (2.0 * splines[i].c + splines[i - 1].c) / 6.0 + (y[i] - y[i - 1]) / hi;
        }
    }
    
    private class Splinei{
        private Double a, b, c, d, x;
        public Splinei(Double _a, Double _b, Double _c, Double _d, Double _x){
            a = _a;
            b = _b;
            c = _c;
            d = _d;
            x = _x;
        }
        public Splinei(){
            
        }
        public Double count(Double _x){
            Double h = _x - x;
            return a + (b + (c/2. + d*h/6.)*h)*h;
        }
    }
    
    private Double[][] createSLAU(Double[] x, Double[] y){
        Double[][] res = new Double[4][];
        for(int i = 0; i < 4; i++)
            res[i] = new Double[n - 2];
        for(int i = 1; i < n - 1; i++){
            res[1][i-1] = 2.*((x[i]-x[i-1]) + (x[i+1] - x[i]));
            if(i < n-2)
                res[0][i-1] = x[i+1] - x[i];
            else
                res[0][i-1] = 0.;
            if(i > 1)
                res[2][i-1] = x[i] - x[i-1];
            else
                res[2][i-1] = 0.;
            res[3][i-1] = 6*((y[i+1] - y[i])/(x[i+1] - x[i]) - (y[i] - y[i-1])/(x[i] - x[i-1]));
        }
        return res;
    }
    
    private Double[] solveSLAU(Double[] A, Double[] B, Double[] C, Double[] dop){
        Double[] X = new Double[dop.length],
                P = new Double[B.length],
                Q = new Double[dop.length];
        P[0] = C[0]/-B[0];
        Q[0] = -dop[0]/-B[0];
        for(int i = 1; i < P.length; i++){
            P[i] = C[i]/(-B[i] - A[i]*P[i-1]);
            Q[i] = (A[i]*Q[i-1] - dop[i])/(-B[i] - A[i]*P[i-1]);
        }
        X[X.length - 1] = Q[X.length - 1];
        for(int i = X.length - 2; i >=0; i--){
            X[i] = P[i]*X[i+1]+Q[i];
        }
        return X;
    }
    
    public Double count(Double x){
        if (splines == null)
            return Double.NaN; // Если сплайны ещё не построены - возвращаем NaN
        
        Splinei s;
        if (x <= splines[0].x){
            s = splines[1];
        }
        else if (x >= splines[n - 1].x){
            s = splines[n - 1];
        }
        else{
            int i = 0;
            int j = n - 1;
            while (i + 1 < j){
                int k = i + (j - i) / 2;
                if (x <= splines[k].x)
                    j = k;
                else
                    i = k;
            }
            if(j != 0)
                s = splines[j];
            else
                s = splines[1];
        }
        
        return s.count(x);
    }
}
