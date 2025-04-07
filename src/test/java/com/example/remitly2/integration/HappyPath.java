package com.example.remitly2.integration;

import com.example.remitly2.Remitly2Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;






@SpringBootTest(classes = Remitly2Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class HappyPath {
    
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    
    
    @Test
    public void happyPath() throws Exception{
         //1. adding swift code data (headuarter) 
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/swift-codes").content("""
            {
        "adress":"TESTHEADQUARTER",
    "countryISO2":"TEST",
    "countryName":"TESTHEADQUARTER",
    "isHeadquarter":false,
    "swiftCode":"TESTXXX"
    }
        """.trim()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
        //2. adding swift code data (branch) 
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/swift-codes").content("""
            {
        "adress":"TESTBRANCH",
    "countryISO2":"TEST",
    "countryName":"TESTBRANCH",
    "isHeadquarter":false,
    "swiftCode":"TEST"
    }
        """.trim()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
        //3. getting data from swift code (headquarter)
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/swift-codes/TESTXXX").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
        //4. getting data from swift code (branch)
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/swift-codes/TEST").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
        //5. getting data from country codeIso2
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/swift-codes/country/TEST").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
        //6. deleting branch
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/swift-codes/TESTXXX"))
        .andExpect(MockMvcResultMatchers.status().isOk());
        //7. deleting headquarter
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/swift-codes/TEST"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
