package com.validator.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.*;


public class FileValidateImplTest {

    private FileValidate validationService;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setup() {
        validationService = new FileValidateImpl();
    }
    @Test
    public void testReadAndWriteToFile() throws IOException {
        final File tempFile1 = tempFolder.newFile("iban.txt");
        Assert.assertTrue(validationService.validateFile(tempFile1.getAbsolutePath()));
    }

    @Test
    public void testFileExists() throws IOException {
        final File tempFile1 = tempFolder.newFile("iban.txt");
        validationService.validateFile(tempFile1.getAbsolutePath());
        Assert.assertFalse(validationService.validateFile(tempFile1.getAbsolutePath()));
    }

    @Test
    public void testBadPath() throws IOException {
        Assert.assertFalse(validationService.validateFile("/\\"));
    }


}
