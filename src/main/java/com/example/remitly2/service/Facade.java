package com.example.remitly2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.repository.DataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class Facade {

    private final DataRepository dataRepository;
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
       if(swiftCode.toUpperCase().endsWith("XXX"))
        {
            return dataGetter.getDetailsHeadquarterSwift(swiftCode);}
        else{
        return dataGetter.getDetailsFromBranch(swiftCode.toUpperCase());
        }
        
    }
    public List<DataDao> getDetailsfromCountryISO2Code(String countryISO2COde){
        return dataGetter.getDataFromCountryISO2Code(countryISO2COde);
    }

}
