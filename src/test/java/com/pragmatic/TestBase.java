package com.pragmatic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeClass
    public void beforeClass(){
        System.out.println("TestNGMethodsTest.beforeClass =======================");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("TestNGMethodsTest.afterClass ========================");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("TestNGMethodsTest.beforeMethod");
        //soutm > shortcut
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("TestNGMethodsTest.afterMethod");
    }
}
