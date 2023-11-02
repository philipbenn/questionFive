package com.example.examquestion5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenderResponse {
    private String gender;
    private String name;
    private int count;
    private double probability;
}

