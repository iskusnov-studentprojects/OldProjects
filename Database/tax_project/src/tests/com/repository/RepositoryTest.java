package com.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by Sergey on 25.12.2016.
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration ("/applicationContext.xml")
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class RepositoryTest {
    @Test
    public void testCrudMethods(){

    }
}