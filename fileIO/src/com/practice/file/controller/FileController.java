package com.practice.file.controller;

import com.practice.file.model.FileDao;
import com.practice.file.run.Setting;

public class FileController {
    private FileDao fd = new FileDao();

    public boolean checkName(String file) {
        String filePath = Setting.file_path + file;
        return fd.checkName(filePath);
    }

    public void fileSave(String file, StringBuilder sb) {
        String filePath = Setting.file_path + file;
        String toStringInput = sb.toString();
        fd.fileSave(filePath, toStringInput);
    }

    public StringBuilder fileOpen(String userInput) {
        String filePath = Setting.file_path + userInput;
        return fd.fileOpen(filePath);
    }

    public void fileEdit(String file, StringBuilder sb) {
        String filePath = Setting.file_path + file;
        String toStringInput = sb.toString();
        fd.fileEdit(filePath, toStringInput);
    }
}
