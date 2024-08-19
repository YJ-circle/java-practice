package com.practice.file.model;

import java.io.*;

public class FileDao {

    public boolean checkName(String file) {
        File f = new File(file);
        if(f.exists()){ return true; }
        else {return false;}
    }

    public void fileSave(String file, String s) {
        //String을 byte로 변환 후 저장
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(s.getBytes());
            System.out.println("\n************\n파일 저장 완료\n************\n");
            fos.close();
        } catch (IOException e) {
            System.out.println("저장 중에 오류가 발생했네요 ㅠㅠ");
        }

    }

    public  StringBuilder fileOpen(String file) {
        FileReader fr1 = null;
        File f = new File(file);
        StringBuilder fileData = new StringBuilder();


        try {
            fr1 = new FileReader(f);
            int i = 0;
            while( (i = fr1.read()) != -1){
                fileData.append((char) i);
            }
            fr1.close();
        } catch (IOException e) {
            System.out.println("오류가 발생했어요.");
        }
        return fileData;
    }

    public void fileEdit(String file, String toStringInput) {
        //String을 byte로 변환 후 저장
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(toStringInput.getBytes());
            System.out.println("\n************\n파일 저장 완료\n************\n");
            fos.close();
        } catch (IOException e) {
            System.out.println("저장 중에 오류가 발생했네요 ㅠㅠ");
        }

    }
}
