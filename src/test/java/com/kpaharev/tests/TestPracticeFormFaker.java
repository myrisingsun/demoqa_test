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

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            expectedFullName = String.format("%s %s", firstName, lastName),
            userSex = "Male",
            userBirthMonth = "March",
            userBirthYear = "1983",
            userBirthDay = "30",
            //dateOfBirth = String.format("%s %s,%s", userBirthDay, userBirthMonth, userBirthYear),
            userCellPhoneNumber = "0123456789",
            userSubjects = "Maths",
            userHobby = "Music",
            userPicture = "img/1.jpg",
            userExpectedPicture = "1.jpg",
            userState = "NCR",
            userCity = "Delhi",
            userAddress = "Some street and house",
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
        registrationFormPage.OpenPage(); // содержимое вынесено в отдельный класс в файле RegistrationFormPage.java
        registrationFormPage.TitleCheck();
        registrationFormPage.setFirstName(firstName)
                            .setLastName(lastName)
                            .setUserEmail(userEmail)
                            .setUserSex(userSex)
                            .setUserCellPhoneNumber(userCellPhoneNumber)
                            .setDateOfBirth(userBirthDay, userBirthMonth, userBirthYear)
                            .setUserSubjects(userSubjects)
                            .setUserHobbies(userHobby)
                            .setUserPicture(userPicture)
                            .setUserCurrentAddress(userAddress)
                            .setUserState(userState)
                            .setUserCity(userCity)
                            .clickSubmit()
                            .checkResultTitle(resultFormTitle)
                            .checkResultFullName(expectedFullName)
                            .checkResultUserEmail(userEmail)
                            .checkResultUserSex(userSex)
                            .checkResultUserCellPhoneNumber(userCellPhoneNumber)
                            .checkResultUserBirthDay(userBirthDay, userBirthMonth, userBirthYear)
                            .checkResultUserSubjects(userSubjects)
                            .checkResultUserHobby(userHobby)
                            .checkResultUserPicture(userPicture, userExpectedPicture)
                            .checkResultUserAddress(userAddress)
                            .checkResultUserStateAndCity(userState, userCity)
                            .checkResultCloseButtonClick();

    }


}

