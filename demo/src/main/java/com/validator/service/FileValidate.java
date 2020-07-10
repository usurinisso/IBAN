package com.validator.service;

import java.util.ArrayList;

public interface FileValidate {

    boolean validateFile(String path);

    boolean createFile(String path);

    boolean writeFile(String path, ArrayList ibans);

}
