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

    String  FirstName = faker.name().firstName(),
            LastName = faker.name().lastName(),
            Email = faker.internet().emailAddress(),
            ExpectedFullName = String.format("%s %s", FirstName, LastName),
            Sex = "Male",
            BirthMonth = "May",
            BirthYear = "1983",
            BirthDay = "30",
            //dateOfBirth = String.format("%s %s,%s", userBirthDay, userBirthMonth, userBirthYear),
            CellPhoneNumber = "0123456789",
            Subjects = "Maths",
            Hobby = "Music",
            Picture = "img/1.jpg",
            ExpectedPicture = "1.jpg",
            State = "NCR",
            City = "Delhi",
            Address = "Some street and house",
            ResultFormTitle = "Thanks for submitting the form";

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
        registrationFormPage.setFirstName(FirstName)
                            .setLastName(LastName)
                            .setUserEmail(Email)
                            .setUserSex(Sex)
                            .setUserCellPhoneNumber(CellPhoneNumber)
                            .setDateOfBirth(BirthDay, BirthMonth, BirthYear)
                            .setUserSubjects(Subjects)
                            .setUserHobbies(Hobby)
                            .setUserPicture(Picture)
                            .setUserCurrentAddress(Address)
                            .setUserState(State)
                            .setUserCity(City)
                            .clickSubmit()
                            .checkResult(ResultFormTitle, ExpectedFullName, Email, Sex, CellPhoneNumber, BirthDay, BirthMonth, BirthYear, Subjects, Hobby, Picture, ExpectedPicture, Address, State, City)
                            .checkResultCloseButtonClick();

                            /* .checkResultTitle(ResultFormTitle)
                            .checkResultFullName(ExpectedFullName)
                            .checkResultUserEmail(Email);
                            .checkResultUserSex(Sex)
                            .checkResultUserCellPhoneNumber(CellPhoneNumber)
                            .checkResultUserBirthDay(BirthDay, BirthMonth, BirthYear)
                            .checkResultUserSubjects(Subjects)
                            .checkResultUserHobby(Hobby)
                            .checkResultUserPicture(Picture, ExpectedPicture)
                            .checkResultUserAddress(Address)
                            .checkResultUserStateAndCity(State, City)*/


    }


}

