package com.kpaharev;

// Класс на тестирование функционала формы https://demoqa.com/automation-practice-form

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestPracticeForm {

    @Test
    void fillFormTest (){
        open ("https://demoqa.com/automation-practice-form");

    }

}
