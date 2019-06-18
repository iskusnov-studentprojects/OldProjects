/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;


public class Spline {	
	private class Splinei {
            public Double a, b, c, d, x;
            public Splinei() {
		
            }
               
            public Double count(Double _x){
                Double h = x - _x;
                return a + (b + (c/2. + d*h/6.)*h)*h;
            }
	}
	private Splinei[] splains;
	private int      n;
	private Double[] x;
	private Double[] y;
	
	Spline(Double[] _x, Double[] _y){
		n = _x.length;
		x = _x;
		y = _y;
		
		splains = new Splinei[n];
		calcSplineis();
	}
	public Double count(Double x) {
	if( x  < this.x[0] || x > this.x[n - 1] ) {
		return 0.;
	}
	
	Splinei s = new Splinei();
	int i = 0;
        int j = n - 1;
        while (i + 1 < j)
        {
            int k = i + (j - i) / 2;
            if (x <= splains[k].x)
            {
                j = k;
            }
            else
            {
                i = k;
            }
        }
        s = splains[j];
		
	Double dx = x - s.x;	
	return s.a + s.b * dx + (s.c / 2) * (dx * dx) + (s.d / 6) * (dx * dx * dx); 
	}
        
        
	private void calcSplineis() {
		for(int i = 0; i < n; i++) {
			Splinei s = new Splinei();
			s.x = x[i];
			s.a = y[i];
			splains[i] = s;
		}
		splains[0].c = splains[n - 1].c = 0.0;
		
		Double[] alpha = new Double[n - 1];
        Double[] beta  = new Double[n - 1];
        alpha[0] = beta[0] = 0.0;
        
        for (int i = 1; i < n - 1; i++)
        {
            Double hi  = x[i] - x[i - 1];
            Double hi1 = x[i + 1] - x[i];
            Double A = hi;
            Double C = 2.0 * (hi + hi1);
            Double B = hi1;
            Double F = 6.0 * ((y[i + 1] - y[i]) / hi1 - (y[i] - y[i - 1]) / hi);
            Double z = (A * alpha[i - 1] + C);
            alpha[i] = -B / z;
            beta[i] = (F - A * beta[i - 1]) / z;
        }
        
        for (int i = n - 2; i > 0; i--)
        {
            splains[i].c = alpha[i] * splains[i + 1].c + beta[i];
        }
 
        for (int i = n - 1; i > 0; --i)
        {
            Double hi = x[i] - x[i - 1];
            splains[i].d = (splains[i].c - splains[i - 1].c) / hi;
            splains[i].b = hi * (2.0 * splains[i].c + splains[i - 1].c) / 6.0 + (y[i] - y[i - 1]) / hi;
        }
		
	}

}
