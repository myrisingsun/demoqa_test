package com.kpaharev.tests;

// Класс на тестирование функционала формы https://demoqa.com/automation-practice-form

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import com.kpaharev.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TestPracticeFormFaker {

    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String  firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            expectedFullName = String.format("%s %s", firstName, lastName),
            sex = "Male",
            birthMonth = "May",
            birthYear = "1983",
            birthDay = "30",
            expectedDateOfBirth = String.format("%s %s,%s", birthDay, birthMonth, birthYear),
            cellPhoneNumber = "0123456789",
            subjects = "Maths",
            hobby = "Music",
            picture = "img/1.jpg",
            expectedPicture = "1.jpg",
            state = "NCR",
            city = "Delhi",
            address = "Some street and house",
            resultFormTitle = "Thanks for submitting the form";

    @DisplayName("Setup of test")
    @BeforeAll
    static void setUp (){
        //Configuration.browser = "opera";
        Configuration.holdBrowserOpen = true; //конфигурация selenium не закрывающая браузер
        Configuration.baseUrl = "https://demoqa.com"; // базовый URL. В тестах уже относительный к базовому
        Configuration.browserSize = "1920x1080"; // размер она открываемого браузера, но не масштаб
        }

    @DisplayName("Fill form")
    @Test
    void openPage (){
        registrationFormPage.openPage(); // содержимое вынесено в отдельный класс в файле RegistrationFormPage.java
        registrationFormPage.titleCheck();;
        registrationFormPage.setFirstName(firstName)
                            .setLastName(lastName)
                            .setUserEmail(email)
                            .setUserSex(sex)
                            .setUserCellPhoneNumber(cellPhoneNumber)
                            .setDateOfBirth(birthDay, birthMonth, birthYear)
                            .setUserSubjects(subjects)
                            .setUserHobbies(hobby)
                            .setUserPicture(picture)
                            .setUserCurrentAddress(address)
                            .setUserState(state)
                            .setUserCity(city)
                            .clickSubmit()
                            .checkResult(resultFormTitle, expectedFullName, email, sex, cellPhoneNumber, expectedDateOfBirth, subjects, hobby, picture, expectedPicture, address, state, city)
                            .checkResultCloseButtonClick();
    }
}

