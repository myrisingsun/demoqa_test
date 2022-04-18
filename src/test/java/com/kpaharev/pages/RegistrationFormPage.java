package com.kpaharev.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    //locators


    //actions

    public void OpenPage () {
        open ("/automation-practice-form"); // команда открывает страницу,закрытие автоматическое
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('fixedban').remove()");
        Selenide.zoom(0.98);
    }

    public void TitleCheck (){
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));//если выбираем Id -то используем #,  если класс то .
        $(".main-header").shouldHave(text("Practice Form"));
    }

    public RegistrationFormPage setFirstName(String firstName){
        $("#firstName").setValue(firstName);
        return this;

    }
    public RegistrationFormPage setLastName(String lastName){
        $("#lastName").setValue(lastName);
        return this;
    }
    public RegistrationFormPage setUserEmail(String userEmail){
        $("#userEmail").setValue(userEmail);
        return this;
    }
    public RegistrationFormPage setUserSex(String userSex){
        $("#genterWrapper").$(byText(userSex)).click(); //выбор всего элемента с RadioButton, а не отдельной RadioButton
        return this;
    }
    public RegistrationFormPage setUserCellPhoneNumber(String userCellPhoneNumber){
        $("#userNumber").setValue(userCellPhoneNumber);
        return this;
    }


}
