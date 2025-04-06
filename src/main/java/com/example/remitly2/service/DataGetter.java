package com.example.remitly2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseHeadquarterSwiftCode;
import com.example.remitly2.entity.Data;
import com.example.remitly2.exception.ResourceNotFound;
import com.example.remitly2.mapper.DataMapper;
import com.example.remitly2.repository.DataRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
class DataGetter {
    private final DataRepository dataRepository;

    public ResponseHeadquarterSwiftCode getDetailsHeadquarterSwift(String swiftCode) throws ResourceNotFound{
        try{
        if(dataRepository.findById(dataRepository.findBySwiftCode(swiftCode).get().getId()).isEmpty()){
            throw new ResourceNotFound("Resource for switch code : "+swiftCode+" not found");
        }
    }catch(ResourceNotFound e){
        System.out.println(e.getMessage());
    }
        ResponseHeadquarterSwiftCode result;

            Data data =dataRepository.findBySwiftCode(swiftCode).get();
            System.out.println(dataRepository.findBranches(swiftCode).size());

            result = new ResponseHeadquarterSwiftCode(
                dataRepository.findBranches(data.getSwiftCode())
                    .stream()
                        .map(d ->  DataMapper.mapDatatoDataDao(d)).toList()
            );
            result.setAdress(data.getSwiftCode());
            result.setCountryISO2(data.getCountryISO2());
            result.setId(data.getId());
            result.setIsHeadquarter(data.getIsHeadquarter());
            result.setSwiftCode(data.getSwiftCode());
            result.setCountryName(data.getCountryName());
            return result;
        
    }
    public DataDao getDetailsFromBranch(String swiftCode){
        System.out.println(swiftCode+" swiftcode getter branch ------");
        try{
            if(dataRepository.findById(dataRepository.findBySwiftCode(swiftCode).get().getId()).isEmpty()){
                throw new ResourceNotFound("Resource for switch code : "+swiftCode+" not found");
            }
        }catch(ResourceNotFound e){
            System.out.println(e.getMessage());
        }
        
        Data data =dataRepository.findBySwiftCode(swiftCode).get();

        //DataDao result = new DataDao(data.getId(), data.getAdress(),data.getCountryISO2(),data.getIsHeadquarter(),data.getSwiftCode(),data.getCountryName());
        DataDao result =DataMapper.mapDatatoDataDao(data); 
        return result;
    }

    public List<DataDao> getDataFromCountryISO2Code(String countryCodeISO2Code){
        List<Data> data = dataRepository.findByCountryISO2(countryCodeISO2Code);
        List<DataDao> result =data.stream().map((d->DataMapper.mapDatatoDataDao(d))).toList();
        return result; 


    }
    
}
