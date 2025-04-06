package com.example.remitly2.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHeadquarterSwiftCode extends DataDao {
    private List<DataDao> branches;
    
}
