package com.practice.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.*;

/**
 * Created by Work on 13.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class)
public class CardServiceImpTest {
    CardService cs;
    @Before
    public void setUp() throws Exception {
        cs = new CardServiceImp();
    }

    @Test
    public void testName() throws Exception {
        cs.clear();

    }
}