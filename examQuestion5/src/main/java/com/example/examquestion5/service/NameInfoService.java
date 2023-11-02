package com.example.examquestion5.service;

import com.example.examquestion5.dto.AgeResponse;
import com.example.examquestion5.dto.GenderResponse;
import com.example.examquestion5.dto.CountryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NameInfoService {

    private final WebClient ageClient = WebClient.create("https://api.agify.io");
    private final WebClient genderClient = WebClient.create("https://api.genderize.io");
    private final WebClient nationClient = WebClient.create("https://api.nationalize.io");

    public Mono<AgeResponse> getAgeInfo(String name) {
        return ageClient.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("name", name).build())
                .retrieve()
                .bodyToMono(AgeResponse.class);
    }

    public Mono<GenderResponse> getGenderInfo(String name) {
        return genderClient.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("name", name).build())
                .retrieve()
                .bodyToMono(GenderResponse.class);
    }

    public Mono<CountryResponse> getCountryInfo(String name) {
        return nationClient.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("name", name).build())
                .retrieve()
                .bodyToMono(CountryResponse.class);
    }
}

