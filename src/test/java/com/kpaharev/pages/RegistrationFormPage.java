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
                    UserEmailInput = $("#userEmail"),
                    UserSexInput = $("#genterWrapper"),
                    UserCellPhoneInput = $("#userNumber"),
                    UserDateOfBirthInput = $("#dateOfBirthInput"),
                    UserSubjectsInput = $("#subjectsInput"),
                    UserHobbiesInput = $("#hobbiesWrapper"),
                    UserPictureInput = $("#uploadPicture"),
                    UserCurrentAddressInput =  $("#currentAddress"),
                    UserStateInput = $("#state"),
                    UserCityInput = $("#city"),
                    SubmitButtonClick = $("#submit"),
                    resultTitle = $("#example-modal-sizes-title-lg"),
                    tableResponsive = $(".table-responsive"),
                    CheckResultButtonClick = $("#closeLargeModal");

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
        FirstNameInput.setValue(firstName);
        return this;

    }
    public RegistrationFormPage setLastName(String lastName){
        LastNameInput.setValue(lastName);
        return this;
    }
    public RegistrationFormPage setUserEmail(String userEmail){
        UserEmailInput.setValue(userEmail);
        return this;
    }
    public RegistrationFormPage setUserSex(String userSex){
        UserSexInput.$(byText(userSex)).click(); //выбор всего элемента с RadioButton, а не отдельной RadioButton
        return this;
    }
    public RegistrationFormPage setUserCellPhoneNumber(String userCellPhoneNumber){
        UserCellPhoneInput.setValue(userCellPhoneNumber);
        return this;
    }
    public RegistrationFormPage setDateOfBirth(String day, String month, String year){
        UserDateOfBirthInput.click();
        calendar.setDate(day, month, year); //Вынесено в отдельный компонент
        return this;
    }
    public RegistrationFormPage setUserSubjects(String userSubjects){
       UserSubjectsInput.setValue(userSubjects).pressEnter();
        return this;
    }
    public RegistrationFormPage setUserHobbies(String userHobby){
        UserHobbiesInput.$(byText(userHobby)).click();
        return this;
    }
    public RegistrationFormPage setUserPicture(String userPicture){
        UserPictureInput.uploadFromClasspath(userPicture);
        //UserPictureInput.uploadFile(new File ("src/test/resources/img/1.jpg"));
        return this;
    }
    public RegistrationFormPage setUserCurrentAddress(String userAddress){
        UserCurrentAddressInput.setValue(userAddress);
        return this;
    }
    public RegistrationFormPage setUserState(String userState){
        UserStateInput.scrollIntoView(true).click(); // выпадающий список штатов
        $(byText(userState)).click(); // выбор и клик по штату
        return this;
    }
    public RegistrationFormPage setUserCity(String userCity){
        UserCityInput.scrollIntoView(true).click(); // работает после выбора штата
        $(byText(userCity)).click();
        return this;
    }
    public RegistrationFormPage clickSubmit(){
        SubmitButtonClick.click();
        return this;
    }

    //asserts

    public RegistrationFormPage checkResultTitle(String resultFormTitle) {
        resultTitle.shouldHave(text(resultFormTitle));
        return this;
    }
    public RegistrationFormPage checkResultFullName(String expectedFullName) {
        tableResponsive.shouldHave(text(expectedFullName));
        return this;
    }
    public RegistrationFormPage checkResultUserEmail(String userEmail) {
        tableResponsive.shouldHave(text(userEmail));
        return this;
    }
    public RegistrationFormPage checkResultUserSex(String userSex) {
        tableResponsive.shouldHave(text(userSex));
        return this;
    }
    public RegistrationFormPage checkResultUserCellPhoneNumber(String userCellPhoneNumber) {
        tableResponsive.shouldHave(text(userCellPhoneNumber));
        return this;
    }
    public RegistrationFormPage checkResultUserBirthDay(String userBirthDay, String userBirthMonth, String userBirthYear) {
        tableResponsive.shouldHave(text(userBirthDay + " " + userBirthMonth + "," + userBirthYear));
        return this;
    }
    public RegistrationFormPage checkResultUserSubjects(String userSubjects) {
        tableResponsive.shouldHave(text(userSubjects));
        return this;
    }
    public RegistrationFormPage checkResultUserHobby(String userHobby) {
        tableResponsive.shouldHave(text(userHobby));
        return this;
    }

    public RegistrationFormPage checkResultUserPicture(String userPicture, String userExpectedPicture) {
        tableResponsive.shouldHave(text(userExpectedPicture));
        return this;
    }

    public RegistrationFormPage checkResultUserAddress(String userAddress) {
        tableResponsive.shouldHave(text(userAddress));
        return this;
    }
    public RegistrationFormPage checkResultUserStateAndCity(String userState, String userCity) {
        tableResponsive.shouldHave(text(userState + " " + userCity));
        return this;
    }
    public RegistrationFormPage checkResultCloseButtonClick(){
        CheckResultButtonClick.click();
        return this;
    }
}
