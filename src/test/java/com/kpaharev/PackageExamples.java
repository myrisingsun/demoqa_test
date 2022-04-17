package com.kpaharev;

import com.github.javafaker.Faker;

import java.util.Locale;

public class PackageExamples {

    public static void main (String[] args ) {
    Faker faker = new Faker();
        System.out.println(faker.address().fullAddress());

    Faker fakerRu = new Faker (new Locale("ru"));
        System.out.println(fakerRu.address().fullAddress());
    }


}
