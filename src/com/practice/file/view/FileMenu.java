package com.practice.file.view;

import com.practice.file.controller.FileController;
import com.practice.file.run.Setting;

import java.util.Scanner;

public class FileMenu {
    private Scanner sc = new Scanner(System.in);
    private FileController fc = new FileController();
    public void initSetting(){
        System.out.print("파일 기본 경로 입력: ");
        String userInput = sc.nextLine();
        if (inputNullCheck(userInput)) return;
        Setting setting = new Setting(userInput);
    }

    public void mainMenu() {
        printSelectMenu();
        String userInput = sc.nextLine();
        if (inputNullCheck(userInput)) return;
        int userInputInt = 0;
            try {
                userInputInt = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력이 가능합니다. 다시 입력해주세요.");
            }
        if (userInputInt == 9) {
            System.out.println("프로그램을 종료 합니다.");
            System.exit(0);
        } else if (userInputInt == 1) {
            fileSave();
        } else if (userInputInt == 2) {
            fileOpen();
        } else if (userInputInt == 3) {
            fileEdit();
        } else {
            System.out.println("없는 메뉴를 선택하였습니다. 다시 입력하세요");
        }
    }

    /**
     * Enter what the user wants to write.
     * If "exit" is entered, the user's input will be terminated
     * Save the entered content as a file
     * If the file exists, the user checks to overwrite it and executes the command
     */

    private void fileSave() {
        System.out.println("\n파일에 저장할 내용을 입력하세요\n" +
                "exit를 입력하면 종료됩니다.");
        StringBuilder data = new StringBuilder();
        fileDataInput(data);
        String fileName = inputFileName();
        if(fc.checkName(fileName)){  //Return false only if the file does not exist
            // If the file name exists
            System.out.println("파일이 존재합니다. \n 덮어쓰시겠습니까?");
            if (userSelectYorN()) { // allow overwirte -> true
                fc.fileSave(fileName, data);
            }
        } else{
            fc.fileSave(fileName, data);
        }

    }

    /**
     * Enter the file name from the user.
     * Verify that any files match the file name entered.
     * If the file exists, it displays the contents.
     * If the file does not exist, it will show you a message that it does not exist.
     */

    public void fileOpen() {
        while (true) {
            System.out.println("\n열고자 하는 파일명을 입력하세요.");
            System.out.print("파일명: ");
            String userInput = sc.nextLine();
            if (inputNullCheck(userInput)){continue;}

            if(!userInput.contains(".txt")){
                userInput = userInput + ".txt";
            }

            if(fc.checkName(userInput)) {
                System.out.println("\n==== 파일 내용 ====");
                System.out.println(fc.fileOpen(userInput));
                System.out.println();
                System.out.println("계속하려면 엔터를 입력하세요.");
                sc.nextLine();
            } else {
                System.out.println("존재하지 않는 파일입니다.");
            }


            break;
        }

    }

    /**
     * Users can add the contents of the specified file.
     * User enters the name of the file to edit.
     * If the file exists, it displays and receives input from the user.
     * Users can choose to save modified files
     */

    private void fileEdit() {
        while (true) {
            System.out.println("\n수정하려는 파일명을 입력하세요.");
            System.out.print("파일명: ");
            String userInput = sc.nextLine();
            if (inputNullCheck(userInput)){continue;}

            if(!userInput.contains(".txt")){
                userInput = userInput + ".txt";
            }

            if(fc.checkName(userInput)) {
                System.out.println("==== 파일 내용 ====");
                StringBuilder data = fc.fileOpen(userInput);
                System.out.println(data);
                System.out.println();
                fileDataInput(data);
                System.out.println("추가된 내용을 적용할까요?");
                boolean writeAllow = userSelectYorN();
                if (writeAllow){
                    fc.fileEdit(userInput, data);
                    break;
                } else {
                    System.out.println("쓰기를 취소합니다.");
                    break;
                }
            } else {
                System.out.println("없는 파일 입니다.");
                break;

            }
        }
    }

    private void printSelectMenu() {
        System.out.println("\n" +
                "***** My Note *****\n" +
                "1. 노트 새로 만들기\n" +
                "2. 노트 열기\n" +
                "3. 노트 열어서 수정하기\n" +
                "9. 끝내기");
        System.out.print("메뉴 선택: ");
    }

    /**
     * user input from the console
     * and appends the input to the provided StringBuilder.
     *
     * The loop will continue until the user types "exit"
     *
     * @param data The StringBuilder object to which the user input will be appended.
     */

    private void fileDataInput(StringBuilder data) {
        while (true) {
            System.out.print("입력: ");
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }
            data.append(userInput).append("\n");
            System.out.println("\n==== 입력된 내용 ====");
            System.out.println(data);

        }
    }


    /**
     * Prompts the user to input a file name and processes the input to ensure it is valid.
     * If the file name does not include a ".txt" extension,
     * It appends ".txt" to the file name.
     * @return String the validated file name with a ".txt"
     */

    private String inputFileName() {
        String fileName = "";
        while(true) {
            System.out.println("\n저장할 파일 명을 입력해주세요");
            System.out.print("파일명: ");
            fileName = sc.nextLine();
            if (inputNullCheck(fileName)) continue; // If null, return true

            // Add txt extension, if the file name does not contain an extension
            if(!fileName.contains(".txt")){
                fileName = fileName + ".txt";
            }

            break;
        }
        return fileName;
    }

    /**
     * Checks if the given file name is empty or contains only whitespace.
     * If the input is empty or consists of only whitespace, an error message is printed
     * and `true` is returned. Otherwise, `false` is returned.
     *
     * @param fileName the file name string to be checked
     * @return boolean
     *          true: empty or contains only white space
     *          false: File name is valid
     */

    private boolean inputNullCheck(String fileName) {
        if (fileName.isBlank()) {
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            return true;
        }
        return false;
    }

    /**
     * Enter "Y" or "N" from the user.
     * Continue to request input until a valid input is received.
     * Returns 'true' if the input is "Y", and 'false' if the input is "N".
     * If the input is not "Y" or "N", output an incorrect input message Request input again
     *
     * @return boolean
     *         Y: true / N: false
     */
    public boolean userSelectYorN(){
        while(true){
            System.out.print("(Y/N): ");
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("Y")) {
                return true;
            } else if (userInput.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }

    }


}
