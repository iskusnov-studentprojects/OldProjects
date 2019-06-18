/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathl2;

/**
 *
 * @author Work
 * @param <T>
 * @param <D>
 */
public class pair<T extends Object,D extends Object>{
    T a;
    D b;
    public pair(){
        a = (T) new Object();
        b = (D) new Object();
    }
    
    public pair(T t, D d){
        a = t;
        b = d;
    }
}
