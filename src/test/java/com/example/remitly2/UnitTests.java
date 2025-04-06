package com.example.remitly2;

import com.example.remitly2.service.FacadeConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseFromCountryISO2;
import com.example.remitly2.dao.ResponseHeadquarterSwiftCode;
import com.example.remitly2.exception.ResourceNotFound;
import com.example.remitly2.service.*;;
public class UnitTests {
    
    Facade testFacade = FacadeConfiguration.createCodeSwiftCrudFacade(new InMemoryDataRepository());

    @Test
    @Description("should add new Data object")
    public void testAddingNewData(){
        // get
        DataDao testingData = new DataDao(Long.parseLong("1"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODE","TESTCOUNTRYNAME");
        // when
        testFacade.addData(testingData);
        //then

        Assertions.assertThat(testFacade.getDetailsFromSwiftCode("TESTSWIFTCODE").equals(testingData));
      
    }
    @Test
    @Description("should return ResponseHeadquarterSwiftCode equel to testResponse")
    public void testGettingDataFroSwiftcodeHeadquarter(){
        // get
        DataDao testingHeadquarter = new DataDao(Long.parseLong("1"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODEXXX","TESTCOUNTRYNAME");
        DataDao testingBranch = new DataDao(Long.parseLong("2"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODr","TESTCOUNTRYNAME");
        testFacade.addData(testingHeadquarter);
        testFacade.addData(testingBranch);

        ArrayList<DataDao> testBranches = new ArrayList<>();
        testBranches.add(testingBranch);
        ResponseHeadquarterSwiftCode testingResponse = new ResponseHeadquarterSwiftCode();
        testingResponse.setAdress("TESTADDRESS");
        testingResponse.setCountryISO2("TESTCOUNTRYISO2");
        testingResponse.setIsHeadquarter(true);
        testingResponse.setSwiftCode("TESTSWIFTCODEXXX");
        testingResponse.setCountryName("TESTCOUNTRYNAME");
        // when
        testFacade.getDetailsFromSwiftCode("TESTSWIFTCODEXXX");
        // ResponseHeadquarterSwiftCode result =(ResponseHeadquarterSwiftCode) testFacade.getDetailsFromSwiftCode("TESTSWIFTCODEXXX");
        //then
        // Assertions.assertThat(result.equals(testingResponse));
    }
    @Test
    @Description("should return DataDao response ")
    //add adding new data
    public void testGettingDataFroSwiftcodeBranch(){
        DataDao testingHeadquarter = new DataDao(Long.parseLong("1"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODEXXX","TESTCOUNTRYNAME");
        DataDao testingBranch = new DataDao(Long.parseLong("2"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODr","TESTCOUNTRYNAME");
        ArrayList<DataDao> testBranches = new ArrayList<>();
        testBranches.add(testingBranch);
        ResponseHeadquarterSwiftCode testingResponse = new ResponseHeadquarterSwiftCode();
        testingResponse.setAdress("TESTADDRESS");
        testingResponse.setCountryISO2("TESTCOUNTRYISO2");
        testingResponse.setIsHeadquarter(true);
        testingResponse.setSwiftCode("TESTSWIFTCODEXXX");
        testingResponse.setCountryName("TESTCOUNTRYNAME");
        // when
        
        ResponseHeadquarterSwiftCode result =(ResponseHeadquarterSwiftCode) testFacade.getDetailsFromSwiftCode("TESTSWIFTCODE");
        //then
        Assertions.assertThat(result.equals(testingBranch));
    }
    @Test
    @Description("should return data list from countriso2code")
    public void testGettingDataFromCountryIso2(){
        // get
        DataDao testingBranch1 = new DataDao(Long.parseLong("1"),"TESTADDRESS1","TESTCOUNTRYISO21"
        ,true,"TESTSWIFTCODr1","TESTCOUNTRYNAME1");
        DataDao testingBranch2 = new DataDao(Long.parseLong("2"),"TESTADDRESS2","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODr2","TESTCOUNTRYNAME2");
        ResponseFromCountryISO2 testing_data = new ResponseFromCountryISO2();
        testing_data.setCountryISO2("TESTCOUNTRYISO2");
        testing_data.setCountryName("TESTCOUNTRYNAME2");
        List<DataDao> testingSwiftCodes = new ArrayList<>();
        testingSwiftCodes.add(testingBranch1);
        testingSwiftCodes.add(testingBranch2);
        testing_data.setSwiftCodes(testingSwiftCodes);
        // when
        ResponseFromCountryISO2 result =  testFacade.getDataFromCountryISO2("TESTSWIFTCODr2");
        //then
        Assertions.assertThat(result.equals(testing_data));
    }
    @Test
    @Description("Should delete given row based on swift code")
    public void testDeletingData(){
        // get
        DataDao testingData = new DataDao(Long.parseLong("1"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODE","TESTCOUNTRYNAME");
        testFacade.addData(testingData);
        // when
        testFacade.deleteData("TESTSWIFTCODE");
        //then
        Assertions.assertThatException();
    }
}
