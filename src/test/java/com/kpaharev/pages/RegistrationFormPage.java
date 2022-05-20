package com.kpaharev.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.kpaharev.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

   CalendarComponent calendar = new CalendarComponent();

    //locators
    SelenideElement FirstNameInput = $("#firstName"),
                    LastNameInput = $("#lastName"),
                    EmailInput = $("#userEmail"),
                    SexInput = $("#genterWrapper"),
                    CellPhoneInput = $("#userNumber"),
                    DateOfBirthInput = $("#dateOfBirthInput"),
                    SubjectsInput = $("#subjectsInput"),
                    HobbiesInput = $("#hobbiesWrapper"),
                    PictureInput = $("#uploadPicture"),
                    CurrentAddressInput =  $("#currentAddress"),
                    StateInput = $("#state"),
                    CityInput = $("#city"),
                    SubmitButtonClick = $("#submit"),
                    ResultTitle = $("#example-modal-sizes-title-lg"),
                    TableResponsive = $(".table-responsive"),
                    CheckResultButtonClick = $("#closeLargeModal");

    //actions

    public void openPage () {
        open ("/automation-practice-form"); // команда открывает страницу,закрытие автоматическое
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('fixedban').remove()");
        Selenide.zoom(0.98);
    }

    public void titleCheck (){
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));//если выбираем Id -то используем #,  если класс то .
        $(".main-header").shouldHave(text("Practice Form"));
    }

    public RegistrationFormPage setFirstName(String firstName){
        FirstNameInput.setValue(firstName);
        return this;

    }
    public RegistrationFormPage setLastName(String lastName){
        LastNameInput.setValue(lastName);
        return this;
    }
    public RegistrationFormPage setUserEmail(String userEmail){
        EmailInput.setValue(userEmail);
        return this;
    }
    public RegistrationFormPage setUserSex(String userSex){
        SexInput.$(byText(userSex)).click(); //выбор всего элемента с RadioButton, а не отдельной RadioButton
        return this;
    }
    public RegistrationFormPage setUserCellPhoneNumber(String userCellPhoneNumber){
        CellPhoneInput.setValue(userCellPhoneNumber);
        return this;
    }
    public RegistrationFormPage setDateOfBirth(String day, String month, String year){
        DateOfBirthInput.click();
        calendar.setDate(day, month, year); //Вынесено в отдельный компонент
        return this;
    }
    public RegistrationFormPage setUserSubjects(String userSubjects){
        SubjectsInput.setValue(userSubjects).pressEnter();
        return this;
    }
    public RegistrationFormPage setUserHobbies(String userHobby){
        HobbiesInput.$(byText(userHobby)).click();
        return this;
    }
    public RegistrationFormPage setUserPicture(String userPicture){
        PictureInput.uploadFromClasspath(userPicture);
        //PictureInput.uploadFile(new File ("src/test/resources/img/1.jpg"));
        return this;
    }
    public RegistrationFormPage setUserCurrentAddress(String userAddress){
        CurrentAddressInput.setValue(userAddress);
        return this;
    }
    public RegistrationFormPage setUserState(String userState){
        StateInput.scrollIntoView(true).click(); // выпадающий список штатов
        $(byText(userState)).click(); // выбор и клик по штату
        return this;
    }
    public RegistrationFormPage setUserCity(String userCity){
        CityInput.scrollIntoView(true).click(); // работает после выбора штата
        $(byText(userCity)).click();
        return this;
    }
    public RegistrationFormPage clickSubmit(){
        SubmitButtonClick.click();
        return this;
    }

    //asserts - проверки

    public RegistrationFormPage checkResult(String resultFormTitle,
                                            String expectedFullName,
                                            String userEmail,
                                            String userSex,
                                            String ExpectedDateOfBirth,
                                            String userCellPhoneNumber,
                                            String userSubjects,
                                            String userHobby,
                                            String userPicture,
                                            String userExpectedPicture,
                                            String userAddress,
                                            String userState,
                                            String userCity) {
        ResultTitle.shouldHave(text(resultFormTitle));
        TableResponsive.shouldHave(text(expectedFullName))
                       .shouldHave(text(userEmail))
                       .shouldHave(text(userSex))
                       .shouldHave(text(userCellPhoneNumber))
                       .shouldHave(text(ExpectedDateOfBirth))
                       .shouldHave(text(userSubjects))
                       .shouldHave(text(userHobby))
                       .shouldHave(text(userExpectedPicture))
                       .shouldHave(text(userAddress))
                       .shouldHave(text(userState + " " + userCity));
        return this;

    }
    public RegistrationFormPage checkResultCloseButtonClick(){
        CheckResultButtonClick.click();
        return this;
    }


}

    /* public RegistrationFormPage checkResultTitle(String resultFormTitle) {
        ResultTitle.shouldHave(text(resultFormTitle));
        return this;
    }
    public RegistrationFormPage checkResultFullName(String expectedFullName) {
        TableResponsive.shouldHave(text(expectedFullName));
        return this;
    }
    public RegistrationFormPage checkResultUserEmail(String userEmail) {
        TableResponsive.shouldHave(text(userEmail));
        return this;
    }
    public RegistrationFormPage checkResultUserSex(String userSex) {
        TableResponsive.shouldHave(text(userSex));
        return this;
    }
    public RegistrationFormPage checkResultUserCellPhoneNumber(String userCellPhoneNumber) {
        TableResponsive.shouldHave(text(userCellPhoneNumber));
        return this;
    }
    public RegistrationFormPage checkResultUserBirthDay(String userBirthDay, String userBirthMonth, String userBirthYear) {
        TableResponsive.shouldHave(text(userBirthDay + " " + userBirthMonth + "," + userBirthYear));
        return this;
    }
    public RegistrationFormPage checkResultUserSubjects(String userSubjects) {
        TableResponsive.shouldHave(text(userSubjects));
        return this;
    }
    public RegistrationFormPage checkResultUserHobby(String userHobby) {
        TableResponsive.shouldHave(text(userHobby));
        return this;
    }

    public RegistrationFormPage checkResultUserPicture(String userPicture, String userExpectedPicture) {
        TableResponsive.shouldHave(text(userExpectedPicture));
        return this;
    }

    public RegistrationFormPage checkResultUserAddress(String userAddress) {
        TableResponsive.shouldHave(text(userAddress));
        return this;
    }
    public RegistrationFormPage checkResultUserStateAndCity(String userState, String userCity) {
        TableResponsive.shouldHave(text(userState + " " + userCity));
        return this;
    }*/


