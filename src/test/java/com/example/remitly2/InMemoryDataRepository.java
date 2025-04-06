package com.example.remitly2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.remitly2.entity.Data;
import com.example.remitly2.repository.DataRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class InMemoryDataRepository implements DataRepository {
    Map<Long,Data> db = new HashMap<>();
    AtomicInteger id = new AtomicInteger();
    
        public Optional<Data> findBySwiftCode(String swiftCode){
            return db.values().stream().filter((Data x)->x.getSwiftCode()==swiftCode).limit(1).findFirst();
        }
        public List<Data> findBranches(String prefix){
            return db.values().stream().filter((Data x )-> x.getSwiftCode().startsWith(prefix)).toList();
         }
        public  List<Data> findByCountryISO2(String countryISO2){
            return db.values().stream().filter((Data x)-> x.getCountryISO2() == countryISO2).toList();
         }
        @Override
        public Data save(Data inputRow) {
            // TODO Auto-generated method stub
            db.put(Long.valueOf(this.id.getAndIncrement()),inputRow);
            return inputRow;
        }
        @Override
        public void delete(Data data) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }
        @Override
        public List<Data> findById(Long id) {
            // TODO Auto-generated method stub
           this.db.values().stream().forEach(x -> System.out.println(x.getId()));
           return this.db.values().stream().filter(x->x.getId()==id.longValue()).toList();
        }
}
