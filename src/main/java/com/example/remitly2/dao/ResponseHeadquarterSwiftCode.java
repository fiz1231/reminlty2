package com.example.remitly2.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ResponseHeadquarterSwiftCode extends DataDao {
    private List<DataDao> branches;
    
}
