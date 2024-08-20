import java.util.ArrayList;
import java.util.Scanner;

public class BookMenu {
    private Scanner sc = new Scanner(System.in);
    private BookController bc = new BookController();

    public void mainMenu() {


        while (true) {
            System.out.println("\n******* 메인 메뉴 *******");
            System.out.println("1. 새 도서 추가");
            System.out.println("2. 도서 전체 조회");
            System.out.println("3. 도서 삭제");
            System.out.println("4. 프로그램 종료");
            System.out.print("메뉴 번호 선택: ");
            String userInput = sc.nextLine();
            switch(userInput){
                case "1":
                    insertBook();
                    break;
                case "2":
                    selectList();
                    break;
                case "3":
                    deleteBook();
                    break;
                case "4":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("없는 메뉴를 선택했습니다. 다시 선택하세요.");
                    break;
            }
        }
    }

    private void deleteBook() {
        String bookName = inputString("삭제할 도서명: ");
        String bookAuthor = inputString("삭제할 저자명: ");
        Book removeBook = bc.deleteBook(bookName, bookAuthor);
        if (removeBook.getTitle() == null) {
            System.out.println("삭제할 도서를 찾지 못했습니다.");
        } else {
            System.out.println("성공적으로 삭제하였습니다.");
        }
    }

    private void selectList() {
        ArrayList<Book> bookList = bc.selectList();
        if(bookList.isEmpty()){
            System.out.println("존재하는 도서가 없습니다.");
        } else {
            int count = 1;
            for (Book book : bookList) {
                System.out.println();
                System.out.println(count++ + "번 도서");
                System.out.println("제목: " + book.getTitle());
                System.out.println("저자: " + book.getAuthor());
                System.out.println("카테고리: " + book.getCategory());
                System.out.println("가격: " + book.getPrice());
            }
        }
    }

    private void insertBook() {

        String bookName = inputString("1. 도서명을 입력하세요: ");
        String bookAutor = inputString("2. 저자명을 입력하세요: ");
        int bookCategoryInput = inputInt("3. 장르를 입력하세요: ", 4);
        int price = inputInt("4. 가격을 입력하세요: ", 5000000);
        String bookCategory = categoryToString(bookCategoryInput);
        Book book = new Book(bookName, bookAutor, bookCategory, price);
        bc.insertBook(book);


    }

    private String categoryToString(int bookCategory) {
        String s = "";
        if (bookCategory == 1) {
            s = "인문";
        } else if (bookCategory == 2) {
            s = "자연과학";
        } else if (bookCategory == 3) {
            s = "의료";
        } else if (bookCategory == 4) {
            s = "기타";
        }
        return s;
    }

    private String inputString(String text) {
        while(true){
            System.out.print("\n" + text);
            String userInput = sc.nextLine();
            if (userInput.isBlank()){
                System.out.println("값을 입력해주세요");
                System.out.println();
                continue;
            }
            return userInput;
        }
    }

    private int inputInt(String text, int max) {
        while(true){
            System.out.print("\n" + text);
            String userInput = sc.nextLine();
            if (userInput.isBlank()){
                System.out.println("값을 입력해주세요");
                System.out.println();
                continue;
            }
            try {
                int i = Integer.parseInt(userInput);
                if (i >= max || i<=0){
                    System.out.println("허용되지 않는 입력 값입니다.");
                    System.out.println();
                    continue;
                }
                return i;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요");
                System.out.println();
            }

        }
    }
}
