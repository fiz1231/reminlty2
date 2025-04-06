package com.example.remitly2.service;

import com.example.remitly2.repository.DataRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.entity.*;
import com.example.remitly2.mapper.DataMapper;

@Service
@AllArgsConstructor
@Transactional
class DataAdder {
    private final DataRepository dataRepository;
    @Transactional
    public DataDao addData (DataDao dataDao){
        Data data = DataMapper.mapDataDaotoData(dataDao);
        // org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive();
        dataRepository.save(data);
        return dataDao;
    }
    
}
