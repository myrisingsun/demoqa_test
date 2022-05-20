package com.kpaharev.examples;

import com.github.javafaker.Faker;

import javax.xml.stream.events.Comment;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PackageExamples {

    public static void main (String[] args ) {



    Faker faker = new Faker();
        System.out.println(faker.address().fullAddress()); //sout - сокращение для  быстрой комадны

    Faker fakerRu = new Faker (new Locale("ru"));
        System.out.println(fakerRu.address().fullAddress());
    }

//   Multiple Comment - выделяем сразу несколько строк и нажимаем Ctrl + /

}
