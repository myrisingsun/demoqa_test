package com.kpaharev;

// Класс на тестирование функционала формы https://demoqa.com/automation-practice-form

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestPracticeForm {

    @BeforeAll
    static void setUp (){
        Configuration.holdBrowserOpen = true; //конфигурация selenium не закрывающая браузер
        Configuration.baseUrl = "https://demoqa.com"; // базовый URL. В тестах уже относительный к базовому
        // Configuration.browserSize = "640x480"; // размер она открываемого браузера, но не масштаб

    }

    @Test
    void fillFormTest (){

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String userEmail = "ivanivanov@gmail.com";
        String userSex = "Male";
        String userCellPhoneNumber = "896211111123";
        String userState = "NCR";
        String userCity = "Delhi";



        open ("/automation-practice-form"); // команда открывает страницу,закрытие автоматическое

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('fixedban').remove()");

        Selenide.zoom(0.5);

        $("[id=firstName").setValue(firstName); //$ - Означает сущность веб элемента firstName - это id на страницк данного элемента
        $("[id=lastName").setValue(lastName); // Ctrl+D добавляет строку (копирует)
        $("[id=userEmail").setValue(userEmail);
        $("[id=genterWrapper]").$(byText(userSex)).click(); //выбор всего элемента с RadioButton, а не отдельной RadioButton
        $("[id=userNumber").setValue(userCellPhoneNumber);
        //$("[id=dateOfBirthInput]").clear();
        //$("[id=dateOfBirthInput]").setValue("01 Apr 1983");
        $("[class=react-datepicker__month-select]").selectOption("September");
        //$("[id=hobbies-checkbox-2").click();
        $("[id=state]").scrollIntoView(true).click(); // выпадающий список штатов
        $(byText(userState)).click(); // выбор и клик по штату
        $("[id=city]").scrollIntoView(true).click(); // работает после выбора штата
        $(byText(userCity)).click();
        $("[id=submit]").click();

    }


}
