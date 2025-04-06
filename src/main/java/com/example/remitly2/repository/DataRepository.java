package com.example.remitly2.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


import com.example.remitly2.entity.Data;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface  DataRepository extends Repository<Data,Long> {
    @Query(value="select * from swift_data e where e.swift_code =:swiftCode limit 1" ,nativeQuery = true)
    Optional<Data> findBySwiftCode(@Param("swiftCode")String swiftCode);
// moliwy bla skladni sqla

    @Query(value = "select * from swift_data e where e.swift_code like Concat(:prefix,'%')" ,nativeQuery = true)
    List<Data> findBranches( @Param("prefix")String prefix);

    @Query(value = "select * from swift_data e where e.country_iso = :countryISO2",nativeQuery = true)
    List<Data> findByCountryISO2( @Param("countryISO2")String countryISO2);
    
    Data save (Data inputRow);
   
    
    void delete(Data data);

    List<Data> findById(Long id);
}
