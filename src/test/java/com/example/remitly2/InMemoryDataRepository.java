package com.example.remitly2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
           
            return db.values().stream().filter(x->x.getSwiftCode().startsWith(swiftCode)).findFirst();
        }
        public List<Data> findBranches(String prefix){
            return db.values().stream().filter((Data x )-> x.getSwiftCode().startsWith(prefix)).toList();
         }
        public  List<Data> findByCountryISO2(String countryISO2){
            return db.values().stream().filter((Data x)-> x.getCountryISO2() == countryISO2).toList();
         }
        @Override
        public Data save(Data inputRow) {
         
            db.put(Long.valueOf(this.id.getAndIncrement()),inputRow);
            return inputRow;
        }
        @Override
        public void delete(Data data) {
           
            Data removeObj = this.findById(data.getId()).getFirst();
             Optional<Long> key =db.keySet().stream().filter(x->db.get(x).getAdress()==removeObj.getAdress()).findAny();
            
           db.remove(key.get());
        }
        @Override
        public List<Data> findById(Long id) {
           
           return this.db.values().stream().filter(x->x.getId().longValue()==id.longValue()).toList();
        }
}
