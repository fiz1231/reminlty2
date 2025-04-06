package com.example.remitly2.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFromCountryISO2 {
    private String countryISO2;
    private String CountryName;
    private List<DataDao>swiftCodes;
}
