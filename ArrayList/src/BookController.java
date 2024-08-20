import java.util.ArrayList;
import java.util.List;

public class BookController {
    private List<Book> list = new ArrayList<Book>();

    public void insertBook(Book book) {
        boolean isDup = false;
        for (Book saveBook : list) {
            if(saveBook.getTitle().equals(book.getTitle())){
                if (saveBook.getAuthor().equals(book.getAuthor())) {
                    System.out.println("이미 등록된 책 입니다.");
                    isDup = true;
                    break;
                }
            }
        }
        if (!isDup) {
            list.add(book);
            System.out.println(book.getTitle() + "을 추가하였습니다.");
        }

    }

    public ArrayList<Book> selectList(){
        return (ArrayList<Book>)list;
    }

    public Book deleteBook(String title, String author) {
        Book removeBook = new Book();

        for (Book book : list) {
            if (book.getTitle().equals(title)) {
                if (book.getAuthor().equals(author)) {
                    removeBook = book;
                    break;
                }
            }
        }
        list.remove(removeBook);


        return removeBook;
    }

}
