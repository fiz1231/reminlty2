package com.example.remitly2.service;




import com.example.remitly2.repository.DataRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.remitly2.entity.Data;
@Service
@AllArgsConstructor
 class DataDeleter {
    private final DataRepository dataRepository;
    // poprawi usuwanie przy swift codezie
    void deleteData(String swiftCode ){
        Data data= dataRepository.findBySwiftCode(swiftCode);
        dataRepository.delete(data);
    }
}
