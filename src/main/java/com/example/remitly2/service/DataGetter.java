package com.example.remitly2.service;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseHeadquarterSwiftCode;
import com.example.remitly2.entity.Data;
import com.example.remitly2.repository.DataRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
class DataGetter {
    private final DataRepository dataRepository;

    public ResponseHeadquarterSwiftCode getDetailsHeadquarterSwift(String swiftCode){
        ResponseHeadquarterSwiftCode result;
       
            Data data =dataRepository.findBySwiftCode(swiftCode);

            result = new ResponseHeadquarterSwiftCode(
                dataRepository.findBranches(data.getSwiftCode())
                    .stream()
                        .map(d -> new DataDao(d.getId(),d.getAdress(),d.getCountryISO2(),
                        d.getIsHeadquarter(),d.getSwiftCode())).toList()
            );
            result.setAdress(data.getSwiftCode());
            result.setCountryISO2(data.getCountryISO2());
            result.setId(data.getId());
            result.setIsHeadquarter(data.getIsHeadquarter());
            result.setSwiftCode(data.getSwiftCode());
            return result;
        
    }
    public DataDao getDetailsFromBranch(String swiftCode){
        Data data =dataRepository.findBySwiftCode(swiftCode);

        DataDao result = new DataDao(data.getId(), data.getAdress(),data.getCountryISO2(),data.getIsHeadquarter(),data.getSwiftCode());

        return result;
    }
}
