package com.example.remitly2;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import com.example.remitly2.dao.DataDao;
import com.example.remitly2.dao.ResponseFindAll;
import com.example.remitly2.dao.ResponseFromCountryISO2;
import com.example.remitly2.dao.ResponseHeadquarterSwiftCode;
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
        DataDao testingHeadquarter = new DataDao(Long.parseLong("2"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODEXXX","TESTCOUNTRYNAME");
        DataDao testingBranch = new DataDao(Long.parseLong("1"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODE","TESTCOUNTRYNAME");
        testFacade.addData(testingBranch);
        testFacade.addData(testingHeadquarter);
       

        ArrayList<DataDao> testBranches = new ArrayList<>();
        testBranches.add(testingBranch);
        ResponseHeadquarterSwiftCode testingResponse = new ResponseHeadquarterSwiftCode();
        testingResponse.setAdress("TESTADDRESS");
        testingResponse.setCountryISO2("TESTCOUNTRYISO2");
        testingResponse.setIsHeadquarter(true);
        testingResponse.setSwiftCode("TESTSWIFTCODEXXX");
        testingResponse.setCountryName("TESTCOUNTRYNAME");
        testingResponse.setBranches(testBranches);
        // when
        testFacade.getDetailsFromSwiftCode("TESTSWIFTCODEXXX");
         ResponseHeadquarterSwiftCode result =(ResponseHeadquarterSwiftCode) testFacade.getDetailsFromSwiftCode("TESTSWIFTCODEXXX");
        //then
         Assertions.assertThat(result.getAdress().equals("TESTADDRESS"));
    }
    @Test
    @Description("should return DataDao response ")
    //add adding new data
    public void testGettingDataFroSwiftcodeBranch(){
        // get
        DataDao testingBranch1 = new DataDao(Long.parseLong("1"),"TESTADDRESS1","TESTCOUNTRYISO21"
        ,true,"TESTSWIFTCODr1","TESTCOUNTRYNAME1");
        testFacade.addData(testingBranch1);
        //when
         // when
         DataDao result =  testFacade.getDetailsFromSwiftCode("TESTSWIFTCODr1");
         //then
         Assertions.assertThat(result.equals(testingBranch1));
    }
    @Test
    @Description("should return data list from countriso2code")
    public void testGettingDataFromCountryIso2(){
        // get
        DataDao testingBranch1 = new DataDao(Long.parseLong("1"),"TESTADDRESS1","TESTCOUNTRYISO21"
        ,true,"TESTSWIFTCODr1","TESTCOUNTRYNAME1");
        DataDao testingBranch2 = new DataDao(Long.parseLong("2"),"TESTADDRESS2","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODr2","TESTCOUNTRYNAME2");
        testFacade.addData(testingBranch1);
        testFacade.addData(testingBranch2);
        ResponseFromCountryISO2 testing_data = new ResponseFromCountryISO2();
        testing_data.setCountryISO2("TESTCOUNTRYISO2");
        testing_data.setCountryName("TESTCOUNTRYNAME2");
        List<DataDao> testingSwiftCodes = new ArrayList<>();
        testingSwiftCodes.add(testingBranch1);
        testingSwiftCodes.add(testingBranch2);
        testing_data.setSwiftCodes(testingSwiftCodes);
        // when
        ResponseFromCountryISO2 result =  testFacade.getDataFromCountryISO2("TESTCOUNTRYISO2");
        //then
        Assertions.assertThat(result.equals(testing_data));
    }
    @Test
    @Description("Should delete given row based on swift code")
    public void testDeletingData(){
        // get
        DataDao testingData = new DataDao(Long.parseLong("5"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODE5","TESTCOUNTRYNAME");
        testFacade.addData(testingData);
        // when
        testFacade.deleteData("TESTSWIFTCODE5");
        //then
        try{
        testFacade.getDetailsFromSwiftCode("TESTSWIFTCODE5");
        Assertions.fail();
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
            Assertions.assertThat(e.getMessage()=="No value present");
        }
       
        
    }
    @Test
    @Description("Should update row")
    public void testUpdatingRow(){
        // get
        DataDao testingData = new DataDao(Long.parseLong("5"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODE5","TESTCOUNTRYNAME");
        testFacade.addData(testingData);
        DataDao updatedData = new DataDao(Long.parseLong("6"),"UPDATEDADDRESS","UPDATEDCOUNTRYISO2"
        ,true,"UPDATEDSWIFTCODE5","UPDATEDCOUNTRYNAME");
        // when
        testFacade.editRow(testingData.getSwiftCode(),updatedData);
        // then
        DataDao result =  testFacade.getDetailsFromSwiftCode("UPDATEDSWIFTCODE5");
        Assertions.assertThat(result.equals(updatedData));

    }
    @Test
    @Description("should return all one rows")
    public void testFindAll(){
        //given
        DataDao testingData = new DataDao(Long.parseLong("5"),"TESTADDRESS","TESTCOUNTRYISO2"
        ,true,"TESTSWIFTCODE5","TESTCOUNTRYNAME");
        testFacade.addData(testingData);
        List<DataDao> testingResult= new ArrayList<>();
        testingResult.add(testingData);

        ResponseFindAll probe = new ResponseFindAll(testingResult);
        //when then
        Assertions.assertThat(testFacade.findAll().equals(probe));
        
    }
}
