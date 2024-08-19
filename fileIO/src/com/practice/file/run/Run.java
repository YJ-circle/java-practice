package com.practice.file.run;

import com.practice.file.view.FileMenu;

public class Run {
    private static FileMenu menu = new FileMenu();
    public static void main(String[] args) {
        menu.initSetting();
        while(true){
            menu.mainMenu();
        }

    }

}
