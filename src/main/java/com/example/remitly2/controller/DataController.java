package com.example.remitly2.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseFromCountryISO2;
import com.example.remitly2.dao.ResponseHeadquarterSwiftCode;

import com.example.remitly2.service.Facade;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/swift-codes")
@AllArgsConstructor
public class DataController {

    
    private final Facade facade;

   
    
    @GetMapping("{swiftCode}")
    public ResponseEntity<? super DataDao> getDataFromSwiftEntity (
         @PathVariable(name="swiftCode") String swiftCode){
          
    
            
        DataDao body = facade.getDetailsFromSwiftCode(swiftCode);
        if (body instanceof ResponseHeadquarterSwiftCode e){
            return ResponseEntity.ok(e);
        }
        else{
            return ResponseEntity.ok(body);
        }
    } 

    @PutMapping()
    public ResponseEntity<String> addNewData(@RequestBody DataDao dataDao) {
        facade.addData(dataDao);
        
        return new ResponseEntity<>("New data added",HttpStatus.CREATED);
    }
    @DeleteMapping("{swift-code}")
    public ResponseEntity<String> deleteDataFromSwiftCode(@PathVariable(name="swift-code") String swiftCode){
        facade.deleteData(swiftCode);
        return ResponseEntity.ok("Row with swiftCode:"+swiftCode+" deleted");
    }
    @GetMapping("/country/{countryISO2code}")
    public ResponseEntity<ResponseFromCountryISO2> getDataFromCountrtyISO2 (
        @PathVariable(name="countryISO2code") String countryISO2code){
           
            return ResponseEntity.ok( facade.getDataFromCountryISO2(countryISO2code));
        }
}
