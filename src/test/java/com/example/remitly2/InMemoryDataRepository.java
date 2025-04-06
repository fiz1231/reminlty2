package com.example.remitly2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.remitly2.entity.Data;

public class InMemoryDataRepository {
    Map<Long,Data> db = new HashMap<>();
    AtomicInteger id = new AtomicInteger();
    
        Optional<Data> findBySwiftCode(String swiftCode){
            return db.values().stream().filter(x->x.getSwiftCode()==swiftCode).limit(1).findFirst();
        }
}
