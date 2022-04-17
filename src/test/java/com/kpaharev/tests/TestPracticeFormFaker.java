package com.kpaharev.tests;

// Класс на тестирование функционала формы https://demoqa.com/automation-practice-form

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TestPracticeFormFaker {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),

    //String firstName = "Иван",
    //        lastName = "Ivanov",
    //        userEmail = "ivanivanov@gmail.com",

            userSex = "Male",
            userBirthMonth = "March",
            userBirthYear = "1983",
            userBirthDay = "30",
            userCellPhoneNumber = "896211111123",
            userSubjects = "Maths",
            userHobby = "Music",
            userState = "NCR",
            userCity = "Delhi",
            userAddress = "Some street and house";

    @BeforeAll
    static void setUp (){
        //Configuration.browser = "opera";
        Configuration.holdBrowserOpen = true; //конфигурация selenium не закрывающая браузер
        Configuration.baseUrl = "https://demoqa.com"; // базовый URL. В тестах уже относительный к базовому
        Configuration.browserSize = "1920x1080"; // размер она открываемого браузера, но не масштаб

    }

    @Test
    void fillFormTest (){

        open ("/automation-practice-form"); // команда открывает страницу,закрытие автоматическое

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('fixedban').remove()");


        Selenide.zoom(0.98);
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        //если выбираем Id -то используем #,  если класс то .
        $(".main-header").shouldHave(text("Practice Form"));
        $("#firstName").setValue(firstName); //$ - Означает сущность веб элемента firstName - это id на страницк данного элемента
        $("#lastName").setValue(lastName); // Ctrl+D добавляет строку (копирует)
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(userSex)).click(); //выбор всего элемента с RadioButton, а не отдельной RadioButton
        $("#userNumber").setValue(userCellPhoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(userBirthMonth);
        $(".react-datepicker__year-select").selectOption(userBirthYear);
        $(".react-datepicker__day--030:not(react-datepicker__day--outside-month)").click(); // not - Это исключение из CSS селектора, мы выбираем 30 -е число именно марта, а не предыдущего месяца
        $("#subjectsInput").setValue(userSubjects).pressEnter();
        $("#hobbiesWrapper").$(byText(userHobby)).click(); // выбор хобби
        $("#uploadPicture").uploadFromClasspath("img/1.jpg");
        //$("#uploadPicture").uploadFile(new File ("src/test/resources/img/1.jpg"));
        $("#currentAddress").setValue(userAddress);

        //$("[id=dateOfBirthInput]").setValue("01 Apr 1983");
        //$("[id=hobbies-checkbox-2").click();
        $("#state").scrollIntoView(true).click(); // выпадающий список штатов
        $(byText(userState)).click(); // выбор и клик по штату
        $("#city").scrollIntoView(true).click(); // работает после выбора штата
        $(byText(userCity)).click();
        $("#submit").click();

        //$("#state").click();
        //$(byText(userState)).click();
        //$("#city").click();
        //$(byText(userCity)).click();
        //$("#city").click();
        //$("#submit").click();



        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text(userSex),
                text(userCellPhoneNumber),
                text(userBirthDay + " " + userBirthMonth + "," + userBirthYear),
                text(userSubjects),
                text(userHobby),
                text("1.jpg"),
                text(userAddress),
                text(userState + " " + userCity));
        $("#closeLargeModal").click();
    }
}
