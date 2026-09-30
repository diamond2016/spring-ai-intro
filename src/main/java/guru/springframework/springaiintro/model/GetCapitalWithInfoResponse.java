package guru.springframework.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(@JsonPropertyDescription("This is the city name") Integer population, @JsonPropertyDescription("This is the region name") String region,
                             @JsonPropertyDescription("This is the language name") String language, @JsonPropertyDescription("This is the currency name") String currency) {
}
