package com.validator.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileValidationControllerTest {

    private static String path1 = "/\\"; //Bad Path
    private static String path2 = ""; //No Path given

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void validate () throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"));
    }

    @Test
    public void validateFile () throws Exception {
        final File tempFile1 = tempFolder.newFile("iban.txt");
        mockMvc.perform(post("/upload").param("path", tempFile1.getAbsolutePath()))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"))
                .andExpect(model().attribute("msg", "Given path is valid, your output file should be ready in the same path"));
    }

    @Test
    public void validateBadPath () throws Exception {
        mockMvc.perform(post("/upload").param("path", path1))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"))
                .andExpect(model().attribute("msg", "Ooops, something went wrong! Check path and file inputs" +
                        ", or the file might already exist"));
    }

}
