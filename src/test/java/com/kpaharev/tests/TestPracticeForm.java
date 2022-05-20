package com.kpaharev.tests;

// Класс на тестирование функционала формы https://demoqa.com/automation-practice-form

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.kpaharev.docs.RandomUtils.getRandomEmail;
import static com.kpaharev.docs.RandomUtils.getRandomString;


public class TestPracticeForm {

    String  FirstName = getRandomString(10),
            LastName = getRandomString(10),
            Email = getRandomEmail(),

    //String firstName = "Иван",
    //        lastName = "Ivanov",
    //        userEmail = "ivanivanov@gmail.com",

            Sex = "Male",
            BirthMonth = "March",
            BirthYear = "1983",
            BirthDay = "30",
            CellPhoneNumber = "896211111123",
            Subjects = "Maths",
            Hobby = "Music",
            State = "NCR",
            City = "Delhi",
            Address = "Some street and house";

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
        $("#firstName").setValue(FirstName); //$ - Означает сущность веб элемента firstName - это id на страницк данного элемента
        $("#lastName").setValue(LastName); // Ctrl+D добавляет строку (копирует)
        $("#userEmail").setValue(Email);
        $("#genterWrapper").$(byText(Sex)).click(); //выбор всего элемента с RadioButton, а не отдельной RadioButton
        $("#userNumber").setValue(CellPhoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(BirthMonth);
        $(".react-datepicker__year-select").selectOption(BirthYear);
        $(".react-datepicker__day--030:not(react-datepicker__day--outside-month)").click(); // not - Это исключение из CSS селектора, мы выбираем 30 -е число именно марта, а не предыдущего месяца
        $("#subjectsInput").setValue(Subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(Hobby)).click(); // выбор хобби
        $("#uploadPicture").uploadFromClasspath("img/1.jpg");
        //$("#uploadPicture").uploadFile(new File ("src/test/resources/img/1.jpg"));
        $("#currentAddress").setValue(Address);

        //$("[id=dateOfBirthInput]").setValue("01 Apr 1983");
        //$("[id=hobbies-checkbox-2").click();
        $("#state").scrollIntoView(true).click(); // выпадающий список штатов
        $(byText(State)).click(); // выбор и клик по штату
        $("#city").scrollIntoView(true).click(); // работает после выбора штата
        $(byText(City)).click();
        $("#submit").click();

        //$("#state").click();
        //$(byText(userState)).click();
        //$("#city").click();
        //$(byText(userCity)).click();
        //$("#city").click();
        //$("#submit").click();



        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(FirstName + " " + LastName),
                text(Email),
                text(Sex),
                text(CellPhoneNumber),
                text(BirthDay + " " + BirthMonth + "," + BirthYear),
                text(Subjects),
                text(Hobby),
                text("1.jpg"),
                text(Address),
                text(State + " " + City));
        $("#closeLargeModal").click();
    }
}
