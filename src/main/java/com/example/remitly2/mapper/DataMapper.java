package com.example.remitly2.mapper;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.entity.Data;

public class DataMapper {
    static public DataDao mapDatatoDataDao(Data data){
        return new DataDao(data.getId(),data.getAdress(),data.getCountryISO2()
        ,data.getIsHeadquarter(),data.getSwiftCode(),data.getCountryName());
    }
    static public Data mapDataDaotoData (DataDao dataDao){
        return new Data(dataDao.getId(),dataDao.getAdress(),dataDao.getCountryISO2()
        ,dataDao.getIsHeadquarter(),dataDao.getSwiftCode(),dataDao.getCountryName());

    }
    
}
