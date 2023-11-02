package com.example.examquestion5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String name;
    private String gender;
    private double genderProbability;
    private int age;
    private int ageCount;
    private List country = new ArrayList();
    private double countryProbability;

    public ResponseDTO (AgeResponse age, GenderResponse gender, CountryResponse country) {
        this.gender = gender.getGender();
        this.genderProbability = gender.getProbability();
        this.age = age.getAge();
        this.ageCount = age.getCount();
        this.country = country.getCountry();
        this.countryProbability = country.getProbability();
    }
}
