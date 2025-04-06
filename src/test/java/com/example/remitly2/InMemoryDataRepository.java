package com.example.remitly2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.remitly2.entity.Data;

public class InMemoryDataRepository {
    Map<Long,Data> db = new HashMap<>();
    AtomicInteger id = new AtomicInteger();
    
}
