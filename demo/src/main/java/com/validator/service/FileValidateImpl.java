package com.validator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service("FileValidate")
public class FileValidateImpl implements FileValidate {

    @Autowired
    private IbanIsValid validationService;

    @Override
    public boolean validateFile(String path) {
        BufferedReader reader;
        try {
            ArrayList ibans = new ArrayList();
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                ibans.add(line);
                line = reader.readLine();
            }
            reader.close();
            int inDot = path.lastIndexOf('.'); //finds the extension end
            if(inDot != -1) path = path.substring(0, inDot); //file might be without an extension
            StringBuilder nPath = new StringBuilder();
            nPath.append(path);
            nPath.append(".out");
            String newPath = nPath.toString();
            System.out.println(newPath);
            if(createFile(newPath)) return writeFile(newPath, ibans);
            else return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean createFile(String path) {
        try {
            File newFile = new File(path);
            if(newFile.createNewFile()) return true;
            else return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean writeFile(String path, ArrayList ibans) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(int i = 0; i < ibans.size(); i++) {
                String iban = ibans.get(i).toString();
                writer.write(iban + ";" + validationService.isValid(iban)+'\n');
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
