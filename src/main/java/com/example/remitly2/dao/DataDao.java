package com.example.remitly2.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataDao {
    private Long id;
   
    private String adress;
   
    private String countryISO2;

    private Boolean isHeadquarter;
    
    private String swiftCode;
    
}
