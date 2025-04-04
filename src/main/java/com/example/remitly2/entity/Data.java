package com.example.remitly2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "swift_data")
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="address")
    private String adress;
    @Column(name = "country_iso")
    private String countryISO2;
    @Column(name = "is_headquarter")
    private Boolean isHeadquarter;
    @Column(name="swift_code")
    private String swiftCode;
    @Column(name="country_name")
    private String countryName;
    
}
