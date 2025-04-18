package com.example.remitly2.service;



import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;

import com.example.remitly2.exception.ResourceNotFound;
import com.example.remitly2.repository.DataRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DataEditor {
    
    private final DataRepository dataRepository;
    
    public void editRow(String swiftCode, DataDao datainput){
        //V1
        try{
            if(dataRepository.findById(dataRepository.findBySwiftCode(swiftCode).get().getId()).isEmpty()){
                throw new ResourceNotFound("Resource for switch code : "+swiftCode+" not found");
            }
        
        else{
            dataRepository.editRow(swiftCode, datainput.getAdress(), datainput.getCountryISO2(), datainput.getIsHeadquarter(), datainput.getSwiftCode(), datainput.getCountryName());
            }
        }
        catch(ResourceNotFound e){
            System.out.println(e.getMessage());
        }
      
        //V2
        // Data datafind = dataRepository.findById(id).get(0);
        // dataRepository.delete(datafind);
        // datainput.setId(datafind.get);
        // dataRepository.save()


    }
    
}
