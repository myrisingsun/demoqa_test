package com.kpaharev;

// Изучение работы  JUnit5 по уроку https://qa.guru/pl/teach/control/lesson/view?id=236519080&editMode=0

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
