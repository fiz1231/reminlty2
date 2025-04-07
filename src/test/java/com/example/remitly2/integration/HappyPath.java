package com.example.remitly2.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class HappyPath {
    
    @Autowired 
    public MockMvc mockMvc;

    @Test
    public void happyPath() throws Exception{}
}
