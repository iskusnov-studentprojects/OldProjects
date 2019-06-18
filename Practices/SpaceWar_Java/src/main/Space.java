/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;
import helpclasses.Constants;

/**
 *
 * @author домо
 */
public class Space extends BaseClass {
    public LocalizableObject[][] field;
    
    public Space(){
        field = new LocalizableObject[Constants.SPACEWIDTH+2][helpclasses.Constants.SPACEHEIGHT+2];
        for(int i = 0; i < Constants.SPACEWIDTH + 2; i++){
            field[i][0] = new LocalizableObject(i,0);
            field[i][Constants.SPACEWIDTH + 1] = new LocalizableObject(i, Constants.SPACEWIDTH + 1);
        }
        for(int j = 0; j < Constants.SPACEHEIGHT + 2; j++){
            field[0][j] = new LocalizableObject(0,j);
            field[Constants.SPACEHEIGHT + 1][j] = new LocalizableObject(Constants.SPACEHEIGHT + 1, j);
        }
    }
    
    public void clear(){
        for(int i = 0; i <= Constants.SPACEWIDTH; i++)
            for(int j = 0; j <= Constants.SPACEHEIGHT; j++)
                field[i][j] = null;
    }
    
    @Override
    public String toString(){
        return "Space";
    }
}
