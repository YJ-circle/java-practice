package com.practice.file.run;

import java.io.File;

public class Setting {
    public static String file_path;

    public Setting(String path) {
        char separator = File.separatorChar;
        if (!path.endsWith(String.valueOf(separator))) {
            path += separator;
        }
        file_path = path;
        System.out.println("경로: " +file_path);
    }


}
