package com.example.remitly2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseFindAll;
import com.example.remitly2.dao.ResponseFromCountryISO2;
import com.example.remitly2.dao.ResponseHeadquarterSwiftCode;
import com.example.remitly2.dao.ResponseMessage;
import com.example.remitly2.service.Facade;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/swift-codes")
@AllArgsConstructor
public class DataController {

    
    private final Facade facade;

   
    @CrossOrigin
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

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<ResponseMessage> addNewData(@RequestBody DataDao dataDao) {
        facade.addData(dataDao);
        
        return new ResponseEntity<>(new ResponseMessage("New data added"),HttpStatus.CREATED);
    }
    
    @CrossOrigin
    @DeleteMapping("{swift-code}")
    public ResponseEntity<ResponseMessage> deleteDataFromSwiftCode(@PathVariable(name="swift-code") String swiftCode){
        facade.deleteData(swiftCode);
        return ResponseEntity.ok(new ResponseMessage("Row with swiftCode:"+swiftCode+" deleted"));
    }
   
    @CrossOrigin
    @GetMapping("/country/{countryISO2code}")
    public ResponseEntity<ResponseFromCountryISO2> getDataFromCountrtyISO2 (
        @PathVariable(name="countryISO2code") String countryISO2code){
           
            return ResponseEntity.ok(facade.getDataFromCountryISO2(countryISO2code));
        }

    @CrossOrigin
    @PutMapping("{swift-code}")
    public ResponseEntity<ResponseMessage> modifyRow(@PathVariable (name ="swift-code" )String swift,@RequestBody DataDao datainput){
        
        facade.editRow(swift,datainput);

        return ResponseEntity.ok(new ResponseMessage("Row has been updated"));

    }
    
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<ResponseFindAll> getAllEntities() {
        
        return ResponseEntity.ok(new ResponseFindAll(facade.findAll()));
    }
    
    }
