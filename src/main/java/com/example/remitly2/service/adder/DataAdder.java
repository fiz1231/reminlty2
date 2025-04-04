package com.example.remitly2.service.adder;

import com.example.remitly2.repository.DataRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.entity.*;
import com.example.remitly2.mapper.DataMapper;

@Service
@AllArgsConstructor
 class DataAdder {
    private final DataRepository dataRepository;

    public DataDao addData (DataDao dataDao){
        Data data = DataMapper.mapDataDaotoData(dataDao);
        dataRepository.save(data);
        return dataDao;
    }
}
