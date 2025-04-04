package com.example.remitly2.service;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;


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
    void deleteData(String swiftCode ){
        dataDeleter.deleteData(swiftCode);
    }
    public DataDao getDetailsfromSwiftCode(String swiftCode){
        if(swiftCode.startsWith("XXX")){
            return dataGetter.getDetailsHeadquarterSwift(swiftCode);
        }
        else{
            return dataGetter.getDetailsFromBranch(swiftCode);
        }
    }

}
