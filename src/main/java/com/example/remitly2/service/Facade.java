package com.example.remitly2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseFromCountryISO2;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class Facade {

    private final DataAdder dataAdder;
    private final DataDeleter dataDeleter;
    private final DataGetter dataGetter;

   

    public DataDao addData (DataDao dataDao){
        return dataAdder.addData(dataDao);
    }
    public void deleteData(String swiftCode ){
        dataDeleter.deleteData(swiftCode);
    }
    public DataDao getDetailsFromSwiftCode(String swiftCode){
        System.out.println(swiftCode+"swift code facade---------------------");
       if(swiftCode.toUpperCase().endsWith("XXX"))
        {
            String subsequence = swiftCode.substring(0,swiftCode.length()-3);
            System.out.println(subsequence);
            return dataGetter.getDetailsHeadquarterSwift(subsequence);}
        else{
        return dataGetter.getDetailsFromBranch(swiftCode.toUpperCase());
        }
        
    }
    
    public ResponseFromCountryISO2 getDataFromCountryISO2(String countryISO2){
        ResponseFromCountryISO2 result =new ResponseFromCountryISO2();
        result.setSwiftCodes(dataGetter.getDataFromCountryISO2Code(countryISO2));
        result.setCountryISO2(countryISO2);
        result.setCountryName(result.getSwiftCodes().get(0).getCountryName());
        return result;
    }

}
