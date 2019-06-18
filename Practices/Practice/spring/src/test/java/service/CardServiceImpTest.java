/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.repository.CardRepository;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Work
 */
public class CardServiceImpTest {
    
    @Autowired CardRepository cs;
    Card card;
    
    public CardServiceImpTest() {
        cs.deleteAll();
    }
    
    @BeforeClass
    public static void setUpClass() {
        card = new Crad(
                "1111",
                1111,
                new Date(),
                new Date(),
                
        );
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
