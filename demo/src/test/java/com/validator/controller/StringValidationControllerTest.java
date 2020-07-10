package com.validator.controller;

import com.validator.service.IbanIsValid;
import com.validator.service.IbanIsValidImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class StringValidationControllerTest {

    private static String iban1 = "LT507854284812715925"; //Valid IBAN
    private static String iban2 = "ZZ507854284812715925"; //Bad IBAN country code
    private static String iban3 = ""; //No IBAN given

    private IbanIsValid validationService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        validationService = mock(IbanIsValidImpl.class);
    }

    @Test
    public void validate () throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("validation"));
    }

    @Test
    public void stringValidate () throws Exception {
        mockMvc.perform(post("/").param("iban", iban1))
                .andExpect(status().isOk())
                .andExpect(view().name("validation"))
                .andExpect(model().attribute("msg", "IBAN is valid"));
    }

    @Test
    public void stringValidateInvalid () throws Exception {
        mockMvc.perform(post("/").param("iban", iban2))
                .andExpect(status().isOk())
                .andExpect(view().name("validation"))
                .andExpect(model().attribute("msg", "IBAN is invalid"));
    }

    @Test
    public void stringValidateNoIban () throws Exception {
        mockMvc.perform(post("/").param("iban", iban3))
                .andExpect(status().isOk())
                .andExpect(view().name("validation"));
    }

}
