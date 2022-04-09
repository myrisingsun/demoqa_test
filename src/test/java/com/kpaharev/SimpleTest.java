package com.kpaharev;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

public class SimpleTest {

    @BeforeEach
    void openWebPage (){
        Selenide.open("http://www.ya.ru/");
    }

    @AfterEach
    void closeWebPage (){

    }

    @Test
    void assertTest (){
        Assertions.assertTrue(true);
    }

    @Test
    void assertTest2 (){
        Assertions.assertTrue(true);

    }

}
