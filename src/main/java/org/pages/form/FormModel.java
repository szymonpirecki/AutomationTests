package org.pages.form;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Data
public class FormModel {

    private static List<String> accountTypes = Arrays.asList("FREELANCE", "SMALL_GROUP", "TEAM", "BUSINESS", "ENTERPRISE");

    private String firstname;
    private String lastname;
    private String email;
    private String company;
    private String phone;
    private String country;
    private String accountType;

    public FormModel(String firstname, String lastname, String email, String company, String phone, String country, String accountType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.company = company;
        this.phone = phone;
        this.country = country;
        this.accountType = accountType;
    }

    public static FormModel getRandomFormModel() {
        return new FormModel(
                System.getProperty("firstname"),
                System.getProperty("lastname"),
                System.getProperty("email"),
                System.getProperty("company"),
                System.getProperty("phone"),
                System.getProperty("country"),
                accountTypes.get(new Random().nextInt(accountTypes.size()))
        );
    }
}
