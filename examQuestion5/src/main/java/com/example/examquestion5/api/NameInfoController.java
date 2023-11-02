package com.example.examquestion5.api;

import com.example.examquestion5.dto.AgeResponse;
import com.example.examquestion5.dto.GenderResponse;
import com.example.examquestion5.dto.CountryResponse;
import com.example.examquestion5.dto.ResponseDTO;
import com.example.examquestion5.service.NameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NameInfoController {

    @Autowired
    private NameInfoService nameInfoService;

    @GetMapping("/name-info")
    public Mono<ResponseDTO> getNameInfo(@RequestParam String name) {
        Mono<AgeResponse> ageMono = nameInfoService.getAgeInfo(name);
        Mono<GenderResponse> genderMono = nameInfoService.getGenderInfo(name);
        Mono<CountryResponse> countryMono = nameInfoService.getCountryInfo(name);

        return Mono.zip(ageMono, genderMono, countryMono)
                .map(tuple -> {
                    AgeResponse ageResponse = tuple.getT1();
                    GenderResponse genderResponse = tuple.getT2();
                    CountryResponse countryResponse = tuple.getT3();

                    ResponseDTO res = new ResponseDTO(ageResponse, genderResponse, countryResponse);
                    res.setName(name);
                    res.setGender(genderResponse.getGender());
                    res.setGenderProbability(genderResponse.getProbability());
                    res.setAge(ageResponse.getAge());
                    res.setAgeCount(ageResponse.getCount());
                    res.setCountry(countryResponse.getCountry());
                    res.setCountryProbability(countryResponse.getProbability());

                    return res;
                });
    }
}

