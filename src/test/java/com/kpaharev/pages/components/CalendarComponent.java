package com.kpaharev.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate (String day, String month, String year) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(react-datepicker__day--outside-month)").click(); // not - Это исключение из CSS селектора, мы выбираем 30 -е число именно марта, а не предыдущего месяца
    }
}
