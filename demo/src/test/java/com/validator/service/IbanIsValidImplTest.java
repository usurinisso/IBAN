package com.validator.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class IbanIsValidImplTest {

    private static String iban1 = "LT507854284812715925"; //Valid IBAN
    private static String iban2 = "LT22@@@5/A "; //Bad IBAN format
    private static String iban3 = "ZZ507854284812715925"; //Bad IBAN country code
    private static String iban4 = "LT5078542848127159251";//Bad IBAN length
    private static String iban5 = "LT407854284812715925";; //Bad IBAN check digits
    private static String iban6 = "LT40ABCDEFGHIJKLMNOP";; //Bad checkdigits (this one is used to test case: )
    private static String iban7 = "LT40QRSTUVWXYZ123456";; //Bad checkdigits (this one is used to test case: )


    private IbanIsValid validationService;

    @Before
    public void setup() {
        validationService = new IbanIsValidImpl();
    }

    @Test
    public void ibanIsValidTest() {
        Assert.assertTrue(validationService.isValid(iban1));
    }

    @Test
    public void ibanFormatInvalidTest() {
        Assert.assertFalse(validationService.isValid(iban2));
    }

    @Test
    public void ibanCountryCodeInvalidTest() {
        Assert.assertFalse(validationService.isValid(iban3));
    }

    @Test
    public void ibanLengthInvalidTest() {
        Assert.assertFalse(validationService.isValid(iban4));
    }

    @Test
    public void ibanCheckDigitsInvalidTest() {
        Assert.assertFalse(validationService.isValid(iban5));
    }

    @Test
    public void ibanInvalidCheckCaseTest() {
        Assert.assertEquals(validationService.isValid(iban6), validationService.isValid(iban7));
    }
}
