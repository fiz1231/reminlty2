package com.example.remitly2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.remitly2.entity.Data;
import java.util.List;

@Repository
public interface  DataRepository extends JpaRepository<Data,Long> {
    @Query("select from swift_Code where swift_Code.swiftCode = :swiftCode")
    Data findBySwiftCode( String swiftCode);

    @Query("select from swift_Code where swift_Code.swiftCode Like :swiftCode")
    List<Data> findBranches( String prefix);

    @Query("select from swift_Code where swift_Code.countryISO2 Like :countryISO2")
    List<Data> findByCountryISO2( String countryISO2);
    
}
