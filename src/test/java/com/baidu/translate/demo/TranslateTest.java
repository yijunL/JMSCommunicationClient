package com.baidu.translate.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class TranslateTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void translate() {
        System.out.println(Locale.getDefault().getLanguage());
    }
}